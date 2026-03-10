import postRepo from "../repo/post.repo.js";

const DEFAULT_LIMIT = 20;
const FAR_FUTURE = "9999-12-31T23:59:59.999Z";

const postService = {
    async createPost(payload) {
        if (!payload.title && !payload.description) {
            const error = new Error("A post must have at least a title or description.");
            error.status = 409;
            throw error;
        }

        return await postRepo.insertPost(payload);
    },

    async getFeed(cursor, limit) {
        const effectiveCursor = cursor || FAR_FUTURE;
        const effectiveLimit = Math.min(parseInt(limit) || DEFAULT_LIMIT, 100);

        const rows = await postRepo.selectFeed(effectiveCursor, effectiveLimit);

        const nextCursor =
            rows.length === effectiveLimit ? rows[rows.length - 1].created_at : null;

        return { posts: rows, nextCursor };
    },

    async getFollowingFeed(userId, cursor, limit) {
        const effectiveCursor = cursor || FAR_FUTURE;
        const effectiveLimit = Math.min(parseInt(limit) || DEFAULT_LIMIT, 100);

        const rows = await postRepo.selectFollowingFeed(userId, effectiveCursor, effectiveLimit);

        const nextCursor =
            rows.length === effectiveLimit ? rows[rows.length - 1].created_at : null;

        return { posts: rows, nextCursor };
    },
};

export default postService;

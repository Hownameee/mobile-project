import postService from "../services/post.service.js";

const postController = {
    async createPost(req, res, next) {
        try {
            const newPost = await postService.createPost(req.body);
            return res.created(newPost, "Post created successfully.");
        } catch (error) {
            if (error.status === 409) {
                console.error(error);
                return res.violate(null, error.message);
            }
            return next(error);
        }
    },

    async getFeed(req, res, next) {
        try {
            const { cursor, limit } = req.query;
            const result = await postService.getFeed(cursor, limit);
            return res.ok(result, "Feed retrieved successfully.");
        } catch (error) {
            if (error.status === 409) {
                console.error(error);
                return res.violate(null, error.message);
            }
            return next(error);
        }
    },

    async getFollowingFeed(req, res, next) {
        try {
            const userId = parseInt(req.params.userId);
            const { cursor, limit } = req.query;
            const result = await postService.getFollowingFeed(userId, cursor, limit);
            return res.ok(result, "Following feed retrieved successfully.");
        } catch (error) {
            if (error.status === 409) {
                console.error(error);
                return res.violate(null, error.message);
            }
            return next(error);
        }
    },
};

export default postController;

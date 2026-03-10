import db from "../utils/db/db.js";

const postRepo = {
    async insertPost({ owner_id, record_id, title, description, photo_url, view_mode }) {
        const stmt = db.prepare(
            `INSERT INTO POST (owner_id, record_id, title, description, photo_url, view_mode)
       VALUES (?, ?, ?, ?, ?, ?)
       RETURNING *`,
        );
        return stmt.get(owner_id, record_id ?? null, title ?? null, description ?? null, photo_url ?? null, view_mode);
    },

    async selectFeed(cursor, limit) {
        const stmt = db.prepare(
            `SELECT p.*, u.username, u.display_name, u.profile_picture_url
       FROM POST p
       JOIN USER u ON u.user_id = p.owner_id
       WHERE p.created_at < ?
       ORDER BY p.created_at DESC
       LIMIT ?`,
        );
        return stmt.all(cursor, limit);
    },

    async selectFollowingFeed(userId, cursor, limit) {
        const stmt = db.prepare(
            `SELECT p.*, u.username, u.display_name, u.profile_picture_url
       FROM POST p
       JOIN FOLLOW f ON f.following_id = p.owner_id
       JOIN USER u ON u.user_id = p.owner_id
       WHERE f.follower_id = ? AND p.created_at < ?
       ORDER BY p.created_at DESC
       LIMIT ?`,
        );
        return stmt.all(userId, cursor, limit);
    },
};

export default postRepo;

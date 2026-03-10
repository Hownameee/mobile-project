import { Router } from "express";
import postController from "../controllers/post.controller.js";
import validation from "../middlewares/validation.js";
import { followIdSchema } from "../utils/schemas/follow.schema.js";
import {
    createPostSchema,
    getPostFeedQuerySchema,
} from "../utils/schemas/post.schema.js";

const router = Router();

router.post(
    "/api/posts",
    validation(createPostSchema, "body"),
    postController.createPost,
);

router.get(
    "/api/posts/feed",
    validation(getPostFeedQuerySchema, "query"),
    postController.getFeed,
);

router.get(
    "/api/posts/following/feed/:userId",
    validation(followIdSchema, "params"),
    validation(getPostFeedQuerySchema, "query"),
    postController.getFollowingFeed,
);

export default router;

import express from "express";
import userController from "../controllers/user.controller.js";
import validation from "../middlewares/validation.js";
import authMiddleware from "../middlewares/auth.middleware.js";
import { updateProfileSchema } from "../utils/schemas/user.schema.js";

const router = express.Router();

router.get("/", authMiddleware.isAdmin, userController.getAllUsers);
router.get("/:id", userController.getUserById);
// router.post("/", authMiddleware.isAdmin, userController.createUser);
router.patch("/:id", validation(updateProfileSchema), userController.updateUser);

// 5. Delete user
// router.delete("/:id", authMiddleware.isAdmin, userController.deleteUser);

export default router;
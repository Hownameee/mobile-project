import express from "express";
const router = express.Router();
import userController from "../controllers/user.controller.js"

// Get all users

// Get user by id

// create user
router.post("/", userController.createUser);

// Update user

// Delete user

export default router;
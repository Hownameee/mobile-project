import userService from "../services/user.service.js"; // Đã thêm .js

const userController = {
  getAllUsers: async function (req, res, next) {
    try {
      const offset = parseInt(req.query.offset) || 0;
      const limit = parseInt(req.query.limit) || 10;
      
      // Đã thêm await
      const users = await userService.getAllUsers(offset, limit);
      res.ok(users, "Users fetched successfully");
    } catch (error) {
      next(error);
    }
  },

  getUserById: async function (req, res, next) {
    try {
      const user_id = req.params.id;
      // Đã thêm await
      const user = await userService.getUserById(user_id);
      
      res.ok(user, "User fetched successfully");
    } catch (error) {
      if (error.message === "User not found") {
        return res.notFound();
      }
      next(error);
    }
  },

  updateUser: async function (req, res, next) {
    try {
      const user_id = req.params.id;
      const updateData = req.body;
      
      await userService.updateUser(user_id, updateData);
      res.ok(null, "User updated successfully");
    } catch (error) {
      if (error.message === "User not found") return res.notFound();
      if (error.message === "No data updated") return res.violate(null, error.message);
      
      next(error);
    }
  }
};

export default userController;
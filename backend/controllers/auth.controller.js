import userService from "../services/user.service.js";

const authController = {
  register: async function (req, res, next) {
    try {
      const userData = req.body;
      const newUserId = await userService.createUser(userData);      
      res.created({ user_id: newUserId }, "User registered successfully");
    } catch (error) {
      if (error.message === "Email already exists") {
        return res.violate(null, error.message); 
      }
      next(error);
    }
  },

  login: async function (req, res, next) {
    try {
      const { email, password } = req.body;      
      const user = await userService.login(email, password);
      res.ok(user, "Login successful");
    } catch (error) {
      if (error.message === "Invalid email or password") {
        return res.unauthorized();
      }
      next(error);
    }
  },
  logout: async function (req, res, next) {
    try {
      res.ok(null, "Logged out successfully");
    } catch (error) {
      next(error);
    }
  }
};

export default authController;
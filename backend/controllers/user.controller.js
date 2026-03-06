import * as userRepo from "../repo/user.repo.js";

const controller = {
  getAllUsers: async function(req, res) {
    const { offset, limit } = req.query;
    const users = await userRepo.getAllUsers(offset, limit);
    res.ok(users, "Users founded successfully");
  },
  getUserById: async function(req, res) {
    const { user_id } = req.params.id;
    const user = await userRepo.getUserByID(user_id);
    
    if (!user) res.notFound();
    res.ok(user, "User founded successfully");
  },
  createUser: async function(req, res) {
    const user = req.body;
    const row_id = await userRepo.createUser(user);
    res.ok({row_id: row_id}, "Created user successfully");
  },
};

export default controller;
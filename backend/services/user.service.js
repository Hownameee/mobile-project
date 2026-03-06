import userRepo from "../repo/user.repo.js"; // Đã sửa import
import bcrypt from "bcrypt";
import config from "../config/config.js";

const userService = {
  getAllUsers: async function(offset = 0, limit = 10) {
    return await userRepo.getAllUsers(offset, limit);
  },

  getUserById: async function(user_id) {
    const user = await userRepo.getUserById(user_id);
    if (!user) throw new Error("User not found");
    return user;
  },

  createUser: async function(userData) {
    const existing_user = await userRepo.getUserByEmail(userData.email);
    if (existing_user) throw new Error("Email already exists");

    // Đã sửa cú pháp: Truyền thẳng SALT_ROUNDS vào hàm hash rất gọn
    const hashed_password = await bcrypt.hash(userData.password, config.SALT_ROUNDS);
    
    return await userRepo.createUser({
      user_name: userData.user_name,
      full_name: userData.full_name,
      email: userData.email,
      password_hash: hashed_password,
      birthdate: userData.birthdate,
    });
  },

  updateUser: async function(user_id, updateData) { 
    const existingUser = await userRepo.getUserById(user_id);
    if (!existingUser) throw new Error("User not found");

    const changes = await userRepo.updateUser(user_id, updateData);
    if (changes === 0) throw new Error("No data updated");
    
    return changes;
  },

  login: async function(email, password) {
    const user = await userRepo.getUserByEmail(email);
    if (!user) throw new Error("Invalid email or password");

    const isMatch = await bcrypt.compare(password, user.password_hash);
    if (!isMatch) throw new Error("Invalid email or password");

    const { password_hash, ...safeUser } = user;
    return safeUser;
  },

  changePassword: async function(user_id, old_password, new_password) {
    const user = userRepo.getUserAuthById(user_id);
    if (!user) throw new Error("User not found");

    const isMatch = await bcrypt.compare(old_password, user.password_hash);
    if (!isMatch) throw new Error("Incorrect old password");

    const new_password_hash = await bcrypt.hash(new_password, config.SALT_ROUNDS);

    return userRepo.updatePassword(user_id, new_password_hash);
  }
};

export default userService;
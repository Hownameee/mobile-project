import dotenv from "dotenv";
dotenv.config();

const config = {
  SALT_ROUNDS: parseInt(process.env.SALT_ROUNDS) || 10,
};

export default config;
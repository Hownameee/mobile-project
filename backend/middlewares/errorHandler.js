import { ZodError } from "zod";

export default function errorHandler (error, req, res, next) {
  console.error(error);
  if (error instanceof ZodError) {
    res.status(500).json({
      message: "Validation failed",
      // stack: error.stack,
      details: error.issues,
    });
  } else {
    res.status(500).json({
      // stack: error.stack,
      details: error.issues,
    });
  }
}

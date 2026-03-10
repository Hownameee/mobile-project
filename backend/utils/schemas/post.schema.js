import { z } from "zod";

export const createPostSchema = z.object({
    owner_id: z.number().int().positive("owner_id must be a positive number"),
    record_id: z
        .number()
        .int()
        .positive("record_id must be a positive number")
        .optional(),
    title: z.string().max(255, "title must be at most 255 characters").optional(),
    description: z
        .string()
        .max(2000, "description must be at most 2000 characters")
        .optional(),
    photo_url: z.string().optional(),
    view_mode: z.enum(["Everyone", "Followers", "Self"]).default("Everyone"),
});

export const getPostFeedQuerySchema = z.object({
    cursor: z.string().optional(),
    limit: z
        .string()
        .regex(/^\d+$/, "limit must be a numeric string")
        .optional(),
});

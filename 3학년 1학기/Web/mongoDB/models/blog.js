const { Schema, model, Types } = require("mongoose");
const BlogSchema = new Schema(
  {
    title: { type: String, required: true },
    content: { type: String, required: true },
    member: { type: Types.ObjectId, required: true, ref: "Member" },
  },
  { timestamp: true }
);
const Blog = model("Blog", BlogSchema);
module.exports = { Blog };

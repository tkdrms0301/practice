// const { Router } = require("express");
// const { Blog } = require("../models/blog");
// const { Member } = require("../models/member");
// const { isValidObjectId } = require("mongoose");
// const blogRouter = Router();
// blogRouter.post("/", async (req, res) => {
//       try {
//         const { title, content, memberId } = req.body;
//         if (!isValidObjectId(memberId))
//           res.status(400).send({ error: "memberId is invalid" });
//         let member = await Member.findById(memberId);
//         if (!member) res.status(400).send({ error: "member does not exist" });
//         //let blog = new Blog({ ...req.body, member });
//         let blog = new Blog({ ...req.body, member: member.toObject() });
//         await blog.save();
//         return res.send({ blog });
//       } catch (error) {
//         console.log(error);
//         res.status(500).send({ error: error.message });
//       }
//     });
    

// module.exports = blogRouter;

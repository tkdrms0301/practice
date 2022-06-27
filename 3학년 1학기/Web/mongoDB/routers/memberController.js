const express = require("express");
const router = express.Router();
const { Member } = require("../models/members");

router.post("/member", async (req, res) => {
    const member = new Member(req.body);
    await member.save();
    return res.send({ member });
});

router.post("/member-v1", async (req, res) => {
    try {
        const member = new Member(req.body);
        await member.save();
        return res.send({ member });
    } catch (err) {
        console.log(err);
        return res.status(400).send({ err: err.message });
    }
});

router.get("/member-v2", async (req, res) => {
    console.log(req.query);
    const { age } = req.query;
    const members = await Member.find({}).find({ age: { $gte: age } });
    //const members = await Member.find({ age: { $gte: age } });
    return res.send({ members });
});

router.get("/member-v3/:age", async (req, res) => {
    console.log(req.params);
    const { age } = req.params;
    const members = await Member.find({}).find({ age: { $gte: age } });
    return res.send({ members });
});

router.get("/member-v4/:memberId", async (req, res) => {
    const { memberId } = req.params;
    const members = await Member.findById(memberId);
    return res.send({ members });
});

router.put("/member-v1/:memberId", async (req, res) => {
    const { memberId } = req.params;
    const { age } = req.body;
    const member = await Member.updateOne({ _id: memberId }, { age: age });
    return res.send(member);
});

router.put("/member-v2/:memberId", async (req, res) => {
    const { memberId } = req.params;
    const { age } = req.body;
    const member = await Member.findByIdAndUpdate(
        memberId,
        { age: age },
        { new: true }
    );
    return res.send(member);
});

module.exports = router;
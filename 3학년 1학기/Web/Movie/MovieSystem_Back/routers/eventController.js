const express = require("express");
const router = express.Router();
const { Event } = require("../models/event");

router.get("/eventGet", async(req, res) => {
    try{
        const events = await Event.find({});
        res.send(events);
    }catch(err){
        console.log(err);
        res.status(500).send({ err : err.message });
    }
});

router.post("/event", async(req, res) => {
    try{
        const event = new Event(req.body);
        await event.save();
        return res.send({ event });
    }catch(err){
        console.log(err);
        res.status(500).send({ err : err.message });
    }
});


module.exports = router;
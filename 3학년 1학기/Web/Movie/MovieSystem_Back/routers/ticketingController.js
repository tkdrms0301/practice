const express = require('express');
const router = express.Router();
const { Ticketing } = require('../models/ticketing');

router.get('/ticketing', async (req, res) => {
    try {
        const ticketing = await Ticketing.find({})
            .populate({ path: 'member' })
            .populate({ path: 'ticketingSeat' })
            .populate({ path: 'theaterTime' });
        res.send(ticketing);
    } catch (err) {
        console.log(err);
        res.status(500).send({ err: err.message });
    }
});

router.post('/ticketingPost', async (req, res) => {
    const ticketing = new Ticketing(req.body);
    await ticketing.save();
    return res.send({ ticketing });
});

module.exports = router;

const express = require('express');
const router = express.Router();
const { TicketingSeat } = require('../models/ticketingSeat');

router.get('/theaterSeat', async (req, res) => {
    try {
        const theaterSeat = await TicketingSeat.find({}).populate({ path: 'theater' });
        res.send(theaterSeat);
    } catch (err) {
        console.log(err);
        res.status(500).send({ err: err.message });
    }
});
module.exports = router;

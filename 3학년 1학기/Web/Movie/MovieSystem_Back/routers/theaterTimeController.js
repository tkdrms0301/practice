const express = require('express');
const router = express.Router();
const mongoose = require('mongoose');
const { Types } = mongoose;
const { TheaterTime } = require('../models/theaterTime');

router.get('/theaterTime', async (req, res) => {
    try {
        const theaterTime = await TheaterTime.find({})
            .populate({ path: 'theater' })
            .populate({ path: 'movie' });
        res.send(theaterTime);
    } catch (err) {
        console.log(err);
        res.status(500).send({ err: err.message });
    }
});
router.get('/movie1', async (req, res) => {
    try {
        const theaterTime = await TheaterTime.find({})
            .limit(2)
            .populate({
                path: 'movie',
            })
            .populate({ path: 'theater' });
        res.send(theaterTime);
    } catch (err) {
        console.log(err);
        res.status(500).send({ err: err.message });
    }
});
router.get('/movie2', async (req, res) => {
    try {
        const theaterTime = await TheaterTime.find({})
            .skip(2)
            .populate({ path: 'theater' })
            .populate({
                path: 'movie',
            });
        res.send(theaterTime);
    } catch (err) {
        console.log(err);
        res.status(500).send({ err: err.message });
    }
});

module.exports = router;

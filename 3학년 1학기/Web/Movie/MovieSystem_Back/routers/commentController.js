const express = require('express');
const router = express.Router({ mergeParams: true });
const { Comment } = require('../models/comment');
const { Recommand } = require('../models/recommand');
const { Movie } = require('../models/movie');
const { Member } = require('../models/member');
const { isValidObjectId } = require('mongoose');

//추천
router.put('/:comment/:member', async (req, res) => {
    try {
        const isRecommand = req.params;
        const { recommandMember, recommandNum } = req.body;
        const { movie, comment, member } = req.params;

        console.log('recommand: ' + recommandMember);

        const allRecommand = await Recommand.find(isRecommand);
        const recommand = await Recommand.find({ recommandMember: recommandMember });
        let i;
        let j;

        let overlap = false;
        for (i = 0; i < recommand.length; i++) {
            for (j = 0; j < allRecommand.length; j++) {
                if (
                    String(recommand[i].recommandMember) == String(allRecommand[j].recommandMember)
                ) {
                    overlap = true;
                }
            }
        }

        if (!overlap) {
            const newRecommand = new Recommand({ movie, comment, member, recommandMember });
            await newRecommand.save();
            const updateComment = await Comment.findByIdAndUpdate(comment, {
                recommandNum: recommandNum,
            });
            overlap = false;
            return res.send(updateComment);
        } else {
            const originComment = await Comment.findById(comment);
            return res.send(originComment);
        }
    } catch (err) {
        console.log(err);
        res.status(500).send({ err: err.message });
    }
});

router.get('/', async (req, res) => {
    try {
        const movie = req.params;

        const comment = await Comment.find(movie)
            .populate({ path: 'member' })
            .populate({ path: 'movie' });

        res.send(comment);
    } catch (err) {
        console.log(err);
        res.status(500).send({ err: err.message });
    }
});

router.post('/', async (req, res) => {
    try {
        var { movie } = req.params;
        const { contents, memberId, grade } = req.body;
        if (!isValidObjectId(movie)) return res.status(400).send({ error: 'movieId is invalid' });
        if (!isValidObjectId(memberId))
            return res.status(400).send({ error: 'memberId is invalid' });
        var movie = await Movie.findById(movie);
        const member = await Member.findById(memberId);
        const recommandNum = 0;

        if (!movie || !member)
            return res.status(400).send({ error: 'movie or member does not exist' });
        const comment = new Comment({ contents, recommandNum, grade, movie, member });

        console.log(comment._id);
        await comment.save();

        return res.send({ comment });
    } catch (error) {
        return res.status(400).send({ error: error.message });
    }
});

router.put('/:commentId', async (req, res) => {
    try {
        const { commentId } = req.params;
        console.log(commentId);
        const { content, grade } = req.body;
        if (!isValidObjectId(commentId))
            return res.status(400).send({ error: 'commentId is invalid' });
        const comment = await Comment.findByIdAndUpdate(commentId, {
            contents: content,
            grade: grade,
        });
        return res.send(comment);
    } catch (err) {
        console.log(err);
        res.status(500).send({ err: err.message });
    }
});

module.exports = router;

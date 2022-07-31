const mongoose = require('mongoose');
const { Schema, Types } = mongoose;

const TheaterTimeSchema = new Schema({
    time: { type: String, required: true },
    // 상영관(theater) id
    theater: { type: Types.ObjectId, required: true, ref: 'Theater' },
    // 영화 id
    movie: { type: Types.ObjectId, required: true, ref: 'Movie' },
});
const TheaterTime = mongoose.model('TheaterTime', TheaterTimeSchema);
module.exports = { TheaterTime };

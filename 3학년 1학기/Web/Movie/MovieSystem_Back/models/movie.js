const mongoose = require('mongoose');
const { Schema } = mongoose;

const MovieSchema = new Schema({
    title: { type: String, required: true },
    url: String,
    screenGrade: { type: String, required: true },
    genre: { type: String, required: true, default: '액션' },
    runtime: { type: String, require: true },
    openingDate: { type: String, require: true },
    isOpen: { type: Boolean, default: false },
    averageGrade: { type: Number, default: 0 },
    ticketingRate: { type: Number, default: 0 },
});
const Movie = mongoose.model('Movie', MovieSchema);
module.exports = { Movie };

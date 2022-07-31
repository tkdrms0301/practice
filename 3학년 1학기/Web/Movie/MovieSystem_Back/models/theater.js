const mongoose = require('mongoose');
const { Schema } = mongoose;

const TheaterSchema = new Schema({
    theaterName: { type: String, required: true },
    totalSeat: { type: Number, required: true },
});
const Theater = mongoose.model('Theater', TheaterSchema);
module.exports = { Theater };

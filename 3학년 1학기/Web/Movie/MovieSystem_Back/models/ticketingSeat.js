const mongoose = require('mongoose');
const { Schema, Types } = mongoose;

const TicketingSeatSchema = new Schema({
    seatNumber: { type: String, required: true },
    // 상영관(theater) id
    theater: { type: Types.ObjectId, required: true, ref: 'Theater' },
});
const TicketingSeat = mongoose.model('TicketingSeat', TicketingSeatSchema);
module.exports = { TicketingSeat };

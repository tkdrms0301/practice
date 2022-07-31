const mongoose = require('mongoose');
const { Schema, Types } = mongoose;

const TicketingSchema = new Schema({
    // 회원 id
    member: { type: Types.ObjectId, required: true, ref: 'Member' },
    // 좌석 id
    ticketingSeat: { type: Types.ObjectId, required: true, ref: 'TicketingSeat' },
    // 상영시간 id
    theaterTime: { type: Types.ObjectId, required: true, ref: 'TheaterTime' },
});
const Ticketing = mongoose.model('Ticketing', TicketingSchema);
module.exports = { Ticketing };

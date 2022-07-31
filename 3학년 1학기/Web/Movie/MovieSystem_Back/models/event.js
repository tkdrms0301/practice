const mongoose = require("mongoose");
const { Schema } = mongoose;
const EventSchema = new Schema(
    {
        title : String,
        url : String,
        term : String,
    },
);
const Event = mongoose.model("Event", EventSchema);
module.exports = { Event };
const mongoose = require("mongoose");
const { Schema } = mongoose;
const MemberSchema = new Schema(
    {
        name: { type: String, required: true },
        age: Number, //age:{type:Number}
        address: {
            city: String,
            street: String,
            zipCode: String,
        },
    },
    { timestamps: true }
);
const Member = mongoose.model("Member", MemberSchema);
module.exports = { Member };
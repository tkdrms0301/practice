const mongoose = require("mongoose");
const { Schema } = mongoose;
const MemberSchema = new Schema(
    {
        id : {type : String, required : true},
        password : {type : String, required : true},
        name : {type : String, require : true},
        type : String,
    },
);

const Member = mongoose.model("Member", MemberSchema);
module.exports = { Member };
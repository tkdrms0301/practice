const mongoose = require('mongoose');
const { Schema, Types } = mongoose;
const CommentSchema = new Schema({
    contents: { type: String },
    recommandNum: { type: Number, default: 0 },
    grade: { type: Number },
    date: { type: Date, default: Date.now },
    // 영화 id
    movie: { type: Types.ObjectId, required: true, ref: 'Movie' },
    // 회원 id
    member: { type: Types.ObjectId, required: true, ref: 'Member' },
});

const Comment = mongoose.model('Comment', CommentSchema);
module.exports = { Comment };

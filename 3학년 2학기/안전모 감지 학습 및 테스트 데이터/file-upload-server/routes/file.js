// routes/file.js
const express = require("express");
const multer = require("multer");
const fs = require("fs");
const path = require("path");

const storage = multer.diskStorage({
  // 업로드 대상 디렉토리 지정
  destination(req, file, cb) {
    cb(null, "upload");
  },
  // 폴더에 저장될 파일명
  filename(req, file, cb) {
    cb(null, Date.now() + path.extname(file.originalname));
  },
});

const upload = multer({ storage: storage });

const router = express.Router();

// upload.singe(fieldname), fieldname은 폼에 정의된 필드명
router.post("/upload", upload.single("file"), (req, res) => {
  res.status(201).json({
    message: "file upload success!",
    url: "http://localhost:4000/files/" + req.file.filename,
  });
});

router.get("/file-name", (req, res) => {
  let path = "./upload";

  /* readdir에서 path, option, error, items(path에 있는 폴더,파일)를 넘긴다 */
  fs.readdir(path, { withFileTypes: true }, function (error, items) {
    if (error) {
      /* 에러 처리 */
      res.send({ error, fileList: [], folderList: [] });
      return;
    }

    let files = [];
    let folders = [];

    for (let item of items) {
      /* 폴더인 경우 folders에 추가 */
      if (item.isDirectory()) folders.push(item);
      else files.push(item);
    }

    res.send({ fileList: files, folderList: folders }); /* react로 전달 */
  });

  return;
});

module.exports = router;

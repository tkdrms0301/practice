const express = require("express");
const router = express.Router();

router.get("/mypage", function (req, res) {
  //console.log(req.cookies);
  console.log(req.session);
  //if (!req.cookies.username) res.redirect("/api/login");
  if (!req.session.username) res.redirect("/api/login");
  //else res.render("mypage", req.cookies);
  else res.render("mypage", req.session);
});

module.exports = router;

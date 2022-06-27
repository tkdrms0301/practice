const express = require("express");
const login = require("./routers/login");
const member = require("./routers/member");
const app = express();
const hostname = "127.0.0.1";
const port = 3000;

const bodyParser = require("body-parser");
const multer = require("multer");
const form_data = multer();

const session = require("express-session");
const memorystore = require("memorystore");
const MemoryStore = memorystore(session);
//const MemoryStore = require('memorystore')(session); //한 줄로 작성 가능

const cookieParser = require("cookie-parser");

const maxAge = 1000 * 60 * 5; //Specifies the number (in milliseconds)

const sessionObj = {
  secret: "se",
  resave: false,
  saveUninitialized: true,
  store: new MemoryStore({ checkPeriod: maxAge }),
  cookie: {
    maxAge: maxAge,
  },
};
const mySession = session(sessionObj);

app.use(bodyParser.json());
app.use(bodyParser.urlencoded({ extended: true }));
app.use(form_data.array());
app.use(express.json());
app.use(mySession);
app.use(cookieParser());
app.use("/api/login", login);
app.use("/member", member);
app.set("view engine", "pug");

app.get("/", function (req, res) {
  return res.render("index");
});

app.listen(port, hostname, function () {
  console.log(`Server running at http://${hostname}:${port}/`);
});

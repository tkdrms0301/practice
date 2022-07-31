const express = require('express');
const cors = require('cors');
const app = express();
const mongoose = require('mongoose');
const memberController = require('./routers/memberController');
const movieController = require('./routers/movieController');
const eventController = require('./routers/eventController');
const commentController = require('./routers/commentController');
const theaterController = require('./routers/theaterController');
const theaterTimeController = require('./routers/theaterTimeController');
const ticketingController = require('./routers/ticketingController');
const ticketingSeatController = require('./routers/ticketingSeatController');

const hostname = '127.0.0.1';
const port = 8080;
const DB_URI = 'mongodb://127.0.0.1:27017/movie';

const server = async () => {
    try {
        await mongoose.connect(DB_URI);
        app.use(cors({ origin: 'http://localhost:3000', credentials: true }));
        app.use(express.json());
        app.use(memberController);
        app.use(movieController);
        app.use(eventController);
        app.use(commentController);
        app.use(theaterController);
        app.use(theaterTimeController);
        app.use(ticketingController);
        app.use(ticketingSeatController);

        app.listen(port, hostname, function () {
            console.log('server is running');
        });
    } catch (err) {
        console.log(err);
    }
};
server();

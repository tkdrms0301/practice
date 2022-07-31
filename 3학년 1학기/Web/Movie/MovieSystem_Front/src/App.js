import './App.css';
import React, { useState, useEffect } from 'react';
import { BrowserRouter as Router, Routes, Route } from 'react-router-dom';
import HomeLayout from './HomeLayout';
import Movie from './components/Movie';
import Home from './components/Home';
import Login from './components/Login';
import Sign from './components/Sign';
import MyPage from './components/MyPage';
import MovieDetail from './components/MovieDetail';
import MovieSchedule from './components/MovieSchedule';
import Ticketing from './components/Ticketing';
import TicketingSeat from './components/TicketingSeat';
import { useCookies } from 'react-cookie';

function App() {
    const [cookies, setCookie, removeCookie] = useCookies(['jwt']);
    const [hasCookie, setHasCookie] = useState(false);

    useEffect(() => {
        if (cookies['jwt'] !== undefined) setHasCookie(true);
    }, [setHasCookie]);

    return (
        <div className="root">
            {console.log('cookie 초기값 : ' + cookies['jwt'])}
            {console.log('App 호출됨!')}
            <Router>
                <HomeLayout
                    cookies={cookies}
                    hasCookie={hasCookie}
                    setHasCookie={setHasCookie}
                    removeCookie={removeCookie}
                    setCookie={setCookie}
                >
                    <Routes>
                        <Route exact path="/" element={<Home cookies={cookies} />} />
                        <Route exact path="/movies" element={<Movie />} />
                        <Route exact path="/theaters" element={<MovieSchedule />}></Route>
                        <Route
                            exact
                            path="/login"
                            element={
                                <Login
                                    hasCookie={hasCookie}
                                    setCookie={setCookie}
                                    setHasCookie={setHasCookie}
                                    cookies={cookies}
                                />
                            }
                        />
                        <Route exact path="/sign" element={<Sign />} />
                        <Route exact path="/mypage" element={<MyPage />} />
                        <Route
                            exact
                            path="/movieDetail"
                            element={<MovieDetail cookies={cookies} />}
                        />
                        <Route exact path="/ticketing" element={<Ticketing />} />
                        <Route exact path="/ticketingSeat" element={<TicketingSeat />} />
                    </Routes>
                </HomeLayout>
            </Router>
        </div>
    );
}

export default App;

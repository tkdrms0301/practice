import Schedule from './Schedule';

function EachMovieSchedule({ movieSchedule }) {
    const strDate = String(movieSchedule[0].movie.openingDate);
    const date = strDate.split('T');
    return (
        <li>
            <div class="col-times">
                <div class="info-movie">
                    <span class={'ico-grade grade-' + movieSchedule[0].movie.screenGrade}>
                        {movieSchedule[0].movie.screenGrade}세 이상
                    </span>
                    <a href="/movies/detail-view/?midx=85689" target="_parent">
                        <strong>{movieSchedule[0].movie.title}</strong>
                    </a>
                    <span class="round lightblue">
                        <em>상영중</em>
                    </span>
                    <i>{movieSchedule[0].movie.genre}/</i> <i>{movieSchedule[0].movie.runtime}/</i>{' '}
                    <i>{date[0].replaceAll('-', '.')} 개봉</i>
                </div>

                <div class="type-hall">
                    <div class="info-hall">
                        <ul>
                            <li>2D</li>
                            {console.log(movieSchedule[0]?.theater?.theaterName)}

                            <li>{movieSchedule[0]?.theater?.theaterName}</li>
                            <li>총 {movieSchedule[0]?.theater?.totalSeat}석</li>
                        </ul>
                    </div>
                    <div class="info-timetable">
                        <ul>
                            <Schedule movieSchedule={movieSchedule[0]} />
                        </ul>
                    </div>
                </div>

                <div class="type-hall">
                    <div class="info-hall">
                        <ul>
                            <li>2D</li>
                            <li>{movieSchedule[1]?.theater?.theaterName}</li>
                            <li>총 {movieSchedule[1]?.theater?.totalSeat}석</li>
                        </ul>
                    </div>
                    <div class="info-timetable">
                        <ul>
                            <Schedule movieSchedule={movieSchedule[1]} />
                        </ul>
                    </div>
                </div>
            </div>
        </li>
    );
}

export default EachMovieSchedule;

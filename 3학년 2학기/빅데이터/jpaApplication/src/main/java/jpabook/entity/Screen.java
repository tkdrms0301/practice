package jpabook.entity;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Entity
@Getter
@Setter
@NoArgsConstructor
public class Screen {
    @Id @GeneratedValue
    @Column(name = "SCREEN_ID")
    private Long id;

    @Column(name = "SCREEN_START")
    private LocalDateTime start;

    @Column(name = "SCREEN_END")
    private LocalDateTime end;

    @ManyToOne
    @JoinColumn(name = "MOVIE_ID")
    private Movie movie;

    @ManyToOne
    @JoinColumn(name = "THEATER_ID")
    private Theater theater;

    @OneToMany(mappedBy = "screen", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Ticketing> ticketings = new ArrayList<>();

    public void setMovie(Movie movie){
        this.movie = movie;
        if(!movie.getScreens().contains(this)){
            movie.getScreens().add(this);
        }
    }

    public void setTheater(Theater theater){
        this.theater = theater;
        if(!theater.getScreens().contains(this)){
            theater.getScreens().add(this);
        }
    }

    public void addTicketing(Ticketing ticketing){
        ticketings.add(ticketing);
        if(ticketing.getScreen() != this){
            ticketing.setScreen(this);
        }
    }
}

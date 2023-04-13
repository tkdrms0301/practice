package jpabook.entity;

import jpabook.enums.GenreType;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Entity
@Getter
@Setter
@NoArgsConstructor
public class Movie {
    @GeneratedValue @Id
    @Column(name = "MOVIE_ID")
    private Long id;
    @Column(name = "MOVIE_NAME")
    private String name;
    @Column(name = "RUNNING_DATE")
    private LocalDate runningDate;
    @Enumerated(EnumType.STRING)
    @Column(name = "GENRE")
    private GenreType genreType;
    @Column(name = "RUNTIME")
    private Integer runtime;
    @Embedded
    ModifiedDate modifiedDate;

    @ManyToOne
    @JoinColumn(name = "DIRECTOR_ID")
    private Director director;

    @OneToMany(mappedBy = "movie", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Starring> starrings = new ArrayList<>();

    @OneToMany(mappedBy = "movie", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Screen> screens = new ArrayList<>();

    public void addStarring(Starring starring){
        starrings.add(starring);
        if(starring.getMovie() != this){
            starring.setMovie(this);
        }
    }

    public void addScreen(Screen screen){
        screens.add(screen);
        if(screen.getMovie() != this){
            screen.setMovie(this);
        }
    }
}

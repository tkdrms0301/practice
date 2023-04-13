package jpabook.entity;

import lombok.AllArgsConstructor;
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
@AllArgsConstructor
public class Director {
    @Id @GeneratedValue
    @Column(name = "DIRECTOR_ID")
    private Long id;
    @Column(name = "DIRECTOR_NAME")
    private String name;
    @Column(name = "DAY_OF_BIRTH")
    private LocalDate dayOfBirth;
    @Column(name = "PLACE_OF_BIRTH")
    private String placeOfBirth;
    @OneToMany(mappedBy = "director", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Movie> movies = new ArrayList<>();

    public void addMovie(Movie movie){
        movies.add(movie);
        if(movie.getDirector() != this){
            movie.setDirector(this);
        }
    }
}

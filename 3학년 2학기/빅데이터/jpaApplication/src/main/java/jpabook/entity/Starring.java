package jpabook.entity;

import jpabook.enums.ActorType;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;

@Entity
@Getter
@Setter
@NoArgsConstructor
public class Starring {
    @Id @GeneratedValue
    @Column(name = "STARRING_ID")
    private Long id;
    @Enumerated(EnumType.STRING)
    private ActorType actorType;

    @ManyToOne
    @JoinColumn(name = "ACTOR_ID")
    private Actor actor;

    @ManyToOne
    @JoinColumn(name = "MOVIE_ID")
    private Movie movie;

    public void setActor(Actor actor){
        this.actor = actor;
        if(!actor.getStarrings().contains(this)){
            actor.getStarrings().add(this);
        }
    }

    public void setMovie(Movie movie){
        this.movie = movie;
        if(!movie.getStarrings().contains(this)){
            movie.getStarrings().add(this);
        }
    }
}

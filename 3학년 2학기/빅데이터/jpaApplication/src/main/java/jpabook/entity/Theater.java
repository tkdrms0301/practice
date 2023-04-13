package jpabook.entity;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Getter
@Setter
public class Theater {
    @GeneratedValue @Id
    @Column(name = "THEATER_ID")
    private Long id;
    @Column(name = "THEATER_NAME")
    private String name;
    @Column(name = "FLOOR")
    private Integer floor;

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "theater", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Seat> seats = new ArrayList<>();

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "theater", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Screen> screens = new ArrayList<>();

    public void addSeat(Seat seat){
        seats.add(seat);
        if(seat.getTheater() != this){
            seat.setTheater(this);
        }
    }

    public void addScreen(Screen screen){
        screens.add(screen);
        if(screen.getTheater() != this){
            screen.setTheater(this);
        }
    }
}

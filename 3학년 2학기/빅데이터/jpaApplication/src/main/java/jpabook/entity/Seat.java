package jpabook.entity;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Getter
@Setter
@NoArgsConstructor
public class Seat {
    @GeneratedValue
    @Id
    @Column(name = "SEAT_ID")
    private Long id;
    @Column(name = "ROW_NUMBER")
    private Integer row;
    @Column(name = "COLUMN_NUMBER")
    private Integer column;
    @Column(name = "AVAILABLE")
    private Boolean available;

    public Seat(Integer row, Integer column){
        this.row = row;
        this.column = column;
        this.available = false;
    }

    public Seat(Integer row, Integer column, Boolean available){
        this.row = row;
        this.column = column;
        this.available = available;
    }

    @ManyToOne
    @JoinColumn(name = "THEATER_ID")
    private Theater theater;

    @OneToMany(mappedBy = "seat", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Ticketing> ticketings = new ArrayList<>();

    public void addTicketing(Ticketing ticketing){
        ticketings.add(ticketing);
        if(ticketing.getSeat() != this){
            ticketing.setSeat(this);
        }
    }
}

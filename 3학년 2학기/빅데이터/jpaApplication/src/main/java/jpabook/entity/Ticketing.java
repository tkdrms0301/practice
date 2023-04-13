package jpabook.entity;

import jpabook.enums.TicketingType;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;

@Entity
@Getter
@Setter
@NoArgsConstructor
public class Ticketing {
    @Id @GeneratedValue
    @Column(name = "TICKETING_ID")
    private Long id;

    @Enumerated(EnumType.STRING)
    @Column(name = "STATE")
    private TicketingType state;

    @ManyToOne
    @JoinColumn(name = "SCREEN_ID")
    private Screen screen;

    @ManyToOne
    @JoinColumn(name = "SEAT_ID")
    private Seat seat;

    @ManyToOne
    @JoinColumn(name = "CLIENT_ID")
    private Client client;

    public void setScreen(Screen screen){
        this.screen = screen;
        if(!screen.getTicketings().contains(this)){
            screen.getTicketings().add(this);
        }
    }

    public void setSeat(Seat seat){
        this.seat = seat;
        if(!seat.getTicketings().contains(this)){
            seat.getTicketings().add(this);
        }
    }

    public void setClient(Client client){
        this.client = client;
        if(!client.getTicketingList().contains(this)){
            client.getTicketingList().add(this);
        }
    }
}

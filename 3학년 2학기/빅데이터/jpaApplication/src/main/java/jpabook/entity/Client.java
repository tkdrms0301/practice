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
@DiscriminatorValue("CLIENT")
@NoArgsConstructor
public class Client extends Person{
    private Integer mileage;
    @Embedded
    private ModifiedDate modifiedDate;

    @OneToMany(mappedBy = "client", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Ticketing> ticketingList = new ArrayList<>();

    public void addTicketing(Ticketing ticketing){
        ticketingList.add(ticketing);
        if(ticketing.getClient() != this){
            ticketing.setClient(this);
        }
    }
}

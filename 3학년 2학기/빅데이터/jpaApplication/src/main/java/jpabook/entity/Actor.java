package jpabook.entity;

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
public class Actor {
    @Id @GeneratedValue
    @Column(name = "ACTOR_ID")
    private Long id;
    @Column(name = "ACTOR_NAME")
    private String name;
    @Column(name = "DAY_OF_BIRTH")
    private LocalDate dayOfBirth;
    @Column(name = "HEIGHT")
    private Integer height;
    @Column(name = "AGENCY")
    private String agency;

    @OneToMany(mappedBy = "actor", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Starring> starrings = new ArrayList<>();

    public void addActor(Starring starring){
        starrings.add(starring);
        if(starring.getActor() != this){
            starring.setActor(this);
        }
    }
}

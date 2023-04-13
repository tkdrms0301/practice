package jpabook.entity;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;
import java.time.LocalDate;

@Entity
@Getter
@Setter
@DiscriminatorValue("MANAGER")
@NoArgsConstructor
public class Manager extends Person{
    private LocalDate workingStartDate;
}

package jpabook.dto;

import jpabook.entity.Director;
import jpabook.entity.Starring;
import jpabook.enums.GenreType;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.Column;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import java.time.LocalDate;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class MovieDto {
    private String name;
    private LocalDate runningDate;
    private GenreType genreType;
    private Director director;
    private Starring[] starrings;
}

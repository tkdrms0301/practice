package Persistence.DTO;

import lombok.*;
import lombok.experimental.SuperBuilder;

import java.time.LocalDate;

@Data
@Getter
@Setter
@SuperBuilder
@ToString
@NoArgsConstructor
@AllArgsConstructor

public class GuideLineDTO {
    private int guideLineNum;
    private String vulnerabilityType;
    private String path;
    private LocalDate date;
}

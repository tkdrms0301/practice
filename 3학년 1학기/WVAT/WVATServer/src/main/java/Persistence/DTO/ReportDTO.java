package Persistence.DTO;

import lombok.*;

import java.time.LocalDate;
import java.util.Date;

@Getter
@Setter
@ToString
@NoArgsConstructor
@AllArgsConstructor

public class ReportDTO {
    private int reportNum;
    private int userNum;
    private int domainNum;
    private LocalDate date;
    private String reportPath;
}

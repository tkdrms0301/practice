package Persistence.DTO;

import lombok.*;
import lombok.experimental.SuperBuilder;

@Data
@Getter
@Setter
@SuperBuilder
@ToString
@NoArgsConstructor
@AllArgsConstructor

public class PayLoadDTO {
    private int payloadNum;
    private String vulnerabilityType;
    private String payload;
    private int count;
}

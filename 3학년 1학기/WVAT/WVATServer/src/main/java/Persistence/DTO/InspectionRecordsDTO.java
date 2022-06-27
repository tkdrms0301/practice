package Persistence.DTO;

import lombok.*;

import java.time.LocalDate;
import java.util.Date;

@Getter
@Setter
@ToString
@NoArgsConstructor
@AllArgsConstructor

public class InspectionRecordsDTO {
    private int domainNum;
    private String domain;
    private LocalDate date;
    private String osCommandInput;
    private String sqlInjectionInput;
    private String cveInput;
    private String directoryIndexingInput;
    private String informationLeakageInput;
    private String xssInput;
    private String pathTrackingInput;
    private String adminExposeInput;
    private String locationDisclosureInput;
    private String plainTextInput;
    private String webMethodInput;
    private String osCommandOutput;
    private String sqlInjectionOutput;
    private String cveOutput;
    private String directoryIndexingOutput;
    private String informationLeakageOutput;
    private String xssOutput;
    private String pathTrackingOutput;
    private String adminExposeOutput;
    private String locationDisclosureOutput;
    private String plainTextOutput;
    private String webMethodOutput;

}
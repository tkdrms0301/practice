package InspectionTool;

import lombok.*;
@Getter
@Setter
@ToString
@AllArgsConstructor

public class Modules {
    private String domain;
    private OSCommand osCommandResult;
    private SQLInjection sqlInjectionResult;
    private CVE cveResult;
    private DirectoryIndexing directoryIndexingResult;
    private InformationLeakage informationLeakageResult;
    private XSS xssResult;
    private PathTracking pathTrackingResult;
    private AdminExpose adminExposeResult;
    private LocationDisclosure locationDisclosureResult;
    private PlainText plainTextResult;
    private WebMethod webMethodResult;
    private boolean isExists;
    private String result;

    public Modules() {
        this.osCommandResult = new OSCommand();
        this.sqlInjectionResult = new SQLInjection();
        this.cveResult = new CVE();
        this.directoryIndexingResult = new DirectoryIndexing();
        this.informationLeakageResult = new InformationLeakage();
        this.xssResult = new XSS();
        this.pathTrackingResult = new PathTracking();
        this.adminExposeResult = new AdminExpose();
        this.locationDisclosureResult = new LocationDisclosure();
        this.plainTextResult = new PlainText();
        this.webMethodResult = new WebMethod();
    }

    @SneakyThrows
    public void run(boolean check[]) {

        if (check[0] && !xssResult.isExists()) {
            System.out.println("-------------------------------------" + domain + "___ XSS -------------------------------------");
            xssResult.check(domain);
        }

        if (check[1] && !sqlInjectionResult.isExists()) {
            System.out.println("-------------------------------------" + domain + "___ SQL Injection-------------------------------------");
            new Thread(this.sqlInjectionResult).start();
            sqlInjectionResult.check(domain);
        }

        if (check[2] && !osCommandResult.isExists()){
            System.out.println("-------------------------------------" + domain + "___ OSCommand -------------------------------------");
            osCommandResult.check(domain);
        }

        if (check[3] && !adminExposeResult.isExists()) {
            System.out.println("-------------------------------------" + domain + "___ Admin Expose -------------------------------------");
            adminExposeResult.check(domain);
        }


        if (check[4] && !locationDisclosureResult.isExists()) {
            System.out.println("-------------------------------------" + domain + "___ Location Disclosure -------------------------------------");
            locationDisclosureResult.check(domain);
        }
        if(check[5] && !pathTrackingResult.isExists())
            pathTrackingResult.check(domain);

        if (check[6] && !directoryIndexingResult.isExists()) {
            System.out.println("-------------------------------------" + domain + "___ DirectoryIndexing -------------------------------------");
            directoryIndexingResult.check(domain);
        }

        if (check[7] && !informationLeakageResult.isExists()) {
            System.out.println("-------------------------------------" + domain + "___ Information Leakage -------------------------------------");
            informationLeakageResult.check(domain);
        }

        if (check[8] && !plainTextResult.isExists()) {
            System.out.println("-------------------------------------" + domain + "___ PlainText -------------------------------------");
            new Thread(this.plainTextResult).start();
            plainTextResult.check(domain);
        }

        if (check[9] && !webMethodResult.isExists()) {
            System.out.println("-------------------------------------" + domain + "___ WebMethod Result -------------------------------------");
            webMethodResult.check(domain);
        }

        if (check[10] && !cveResult.isExists()) {
            System.out.println("-------------------------------------" + domain + "___ CVE -------------------------------------");
            cveResult.check(domain);
        }
    }
    public void select(String arr[]) {
        for (int i = 0; i < arr.length; i++) {
            switch(arr[i]) {
                case "1":
                    if (!sqlInjectionResult.isExists()) {
                        System.out.println("-------------------------------------" + domain + "___ SQL Injection-------------------------------------");
                        new Thread(this.sqlInjectionResult).start();
                        sqlInjectionResult.check(domain);
                    }
                    break;
                case "2":
                    if(!osCommandResult.isExists()) {
                        System.out.println("-------------------------------------" + domain + "___ OSCommand -------------------------------------");
                        osCommandResult.check(domain);
                    }
                    break;
                case "3":
                    if(!cveResult.isExists()) {
                        System.out.println("-------------------------------------" + domain + "___ CVE -------------------------------------");
                        cveResult.check(domain);
                    }
                    break;
                case "4":
                    if(!directoryIndexingResult.isExists()) {
                        System.out.println("-------------------------------------" + domain + "___ DirectoryIndexing -------------------------------------");
                        directoryIndexingResult.check(domain);
                    }
                    break;
                case "5":
                    if(!informationLeakageResult.isExists()) {
                        System.out.println("-------------------------------------" + domain + "___ Information Leakage -------------------------------------");
                        informationLeakageResult.check(domain);
                    }
                    break;
                case "6":
                    if(!xssResult.isExists()) {
                        System.out.println("-------------------------------------" + domain + "___ XSS -------------------------------------");
                        xssResult.check(domain);
                    }
                    break;
                case "7":
                     if(!pathTrackingResult.isExists())
                        pathTrackingResult.check(domain);

                    break;
                case "8":
                    if(!adminExposeResult.isExists()) {
                        System.out.println("-------------------------------------" + domain + "___ Admin Expose -------------------------------------");
                        adminExposeResult.check(domain);
                    }
                    break;
                case "9":
                    if(!locationDisclosureResult.isExists())
                        locationDisclosureResult.check(domain);

                    break;
                case "10":
                    if(!plainTextResult.isExists()) {
                        System.out.println("-------------------------------------" + domain + "___ PlainText -------------------------------------");
                        new Thread(this.plainTextResult).start();
                        plainTextResult.check(domain);
                    }
                    break;
                case "11":
                    if(!webMethodResult.isExists()) {
                        System.out.println("-------------------------------------" + domain + "___ WebMethod Result -------------------------------------");
                        webMethodResult.check(domain);
                    }
                    break;
            }
        }
    }
}

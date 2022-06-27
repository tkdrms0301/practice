package InspectionTool;

import java.net.HttpURLConnection;
import java.net.URL;

public class InformationLeakage extends Vulnerability{
    public InformationLeakage(){
        super();
    }

    public void check(String domain){
        runModule(toRootUrl(domain));
    }

    public String toRootUrl(String domain){
        String[] domainParse = domain.split("//|/");
        return domainParse[0] + "//" + domainParse[1]+ "/";
    }

    @Override
    public void runModule(String domain) {
        try{
            HttpURLConnection c = (HttpURLConnection) new URL(domain).openConnection();
            setSeleniumConfigSetting(); // 셀레니움 드라이버 설정 실행
            getDriver().get(domain);

            c.connect();

            String serverInfo = c.getHeaderField("Server");
            String phpInfo = c.getHeaderField("X-Powered-By");

            driver.close();
            driver.quit();

            //주소를 검색함
            setInputValue(domain);

            //결과는?
            if (serverInfo.contains("/")){
                setResult("Server : " + c.getHeaderField("Server"));
            }
            else if(phpInfo.contains("/")){
                setResult("X-Powered-By : " + c.getHeaderField("X-Powered-By"));
            }else{
                setResult(" ");
            }

        }catch (Exception e){
            e.printStackTrace();
        }finally {
            setExists(true);
        }
    }
}
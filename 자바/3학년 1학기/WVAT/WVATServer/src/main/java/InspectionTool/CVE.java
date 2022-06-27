package InspectionTool;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebElement;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

public class CVE extends Vulnerability{
    public CVE(){
        super();
    }

    public void check(String domain){
        runModule(toRootUrl(domain));
    }

    public String toRootUrl(String domain){
        String[] domainParse = domain.split("//|/");
        return domainParse[0] + "//" + domainParse[1];
    }

    @Override
    public void runModule(String domain) {
        try{
            String serverInfo, phpInfo;
            String res = " ";
            HttpURLConnection c = (HttpURLConnection) new URL(domain).openConnection();
            setSeleniumConfigSetting(); // 셀레니움 드라이버 설정 실행
            getDriver().get(domain);

            c.connect();

            serverInfo = c.getHeaderField("Server");
            phpInfo = c.getHeaderField("X-Powered-By");

            driver.close();
            driver.quit();

            if(serverInfo.contains("/") || phpInfo.contains("/")){
                setSeleniumConfigSetting(); // 셀레니움 드라이버 설정 실행
                getDriver().get("https://cve.mitre.org/cve/search_cve_list.html");
                WebElement element = getDriver().findElement(By.name("keyword"));

                if(serverInfo.contains("/")) {
                    element.sendKeys(serverInfo);
                    setInputValue(serverInfo);
                }
                else {
                    element.sendKeys(phpInfo);
                    setInputValue(phpInfo);
                }

                element.sendKeys(Keys.ENTER);
                String url = getDriver().getCurrentUrl();

                List<WebElement> elements = new ArrayList<>();
                elements = getDriver().findElements(By.tagName("a"));
                int cveCnt = 0;
                for(int i = 0; i < elements.size(); i++){
                    if(cveCnt >= 50) break;

                    if(elements.get(i).getAttribute("href").contains("/cgi-bin/cvename.cgi")){
                        res += elements.get(i).getAttribute("innerText") + "  ";
                        cveCnt ++;
                    }
                }
                res += "\n" + "more CVE : " + url;
                setResult(res);
            }else{
                setResult(res);
            }
        }catch (Exception e){
            e.printStackTrace();
        }finally {
            setExists(true);
            getDriver().close();
            getDriver().quit();
        }
    }
}
package InspectionTool;

import Persistence.DAO.PayLoadDAO;
import Persistence.DTO.PayLoadDTO;
import Persistence.MybatisConnectionFactory;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebElement;

import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

public class LocationDisclosure extends Vulnerability{
    public LocationDisclosure(){
        super();
    }

    @Override
    public void check(String domain){
        runModule(toRootUrl(domain));
    }

    public String toRootUrl(String domain){
        String[] domainParse = domain.split("//|/");
        return domainParse[0] + "//" + domainParse[1] + "/";
    }

    @Override
    public void runModule(String domain) {
        try{
            int code = 0;
            String codeMessage = " ";
            PayLoadDAO payLoadDAO = new PayLoadDAO(MybatisConnectionFactory.getSqlSessionFactory());
            List <PayLoadDTO> payLoadDTO = payLoadDAO.orderPayLoadSelectPrint("locationDisclosure");
            setSeleniumConfigSetting(); // 셀레니움 드라이버 설정 실행
            getDriver().get(domain);

            List<WebElement> element = getDriver().findElements(new By.ByTagName("a"));
            List<String> elementString = new ArrayList<>();

            for(int i = 0; i < element.size(); i++){
                elementString.add(element.get(i).getAttribute("href"));
            }
            getDriver().close();
            getDriver().quit();

            for(int i =0; i < payLoadDTO.size(); i++){
                boolean elementCheck = false;

                for(int j = 0; j < element.size(); j++) {
                    if (elementString.get(j).equals( domain + payLoadDTO.get(i).getPayload())) {
                        elementCheck = true;
                        break;
                    }
                }

                HttpURLConnection c = (HttpURLConnection) new URL(domain + payLoadDTO.get(i).getPayload()).openConnection();
                setSeleniumConfigSetting(); // 셀레니움 드라이버 설정 실행
                getDriver().get(domain + payLoadDTO.get(i).getPayload());

                c.connect();
                code = c.getResponseCode();
                codeMessage = c.getResponseMessage();

                if(code != 404 && c.getContentType().contains("html") && !elementCheck){
                    payLoadDAO.updatePayLoadCount(payLoadDTO.get(i).getPayload());
                    setInputValue(domain + payLoadDTO.get(i).getPayload());
                    setResult(code  + "  " +  codeMessage);
                    setExists(true);
                    setStateCode(String.valueOf(code));
                    getDriver().close();
                    getDriver().quit();
                    break;
                } else{
                    setResult(" ");
                }
                getDriver().close();
                getDriver().quit();
            }

        }catch (Exception e){

        }finally {
            setExists(true);
        }
    }
}
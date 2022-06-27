package InspectionTool;

import Persistence.DAO.PayLoadDAO;
import Persistence.DTO.PayLoadDTO;
import Persistence.MybatisConnectionFactory;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebElement;

import java.util.List;

public class XSS extends Vulnerability{
    public XSS(){
        super();
    }

    @Override
    public void check(String domain){
        runModule(domain);
    }

    @Override
    public void runModule(String domain) {
        PayLoadDAO payLoadDAO = new PayLoadDAO(MybatisConnectionFactory.getSqlSessionFactory());
        List<PayLoadDTO> payLoadDTO = payLoadDAO.randomAndOrderPayLoadSelectPrint("xss");
        String res = " ";
        int payloadIdx = 0;
        boolean isValue = false;
        boolean isXss = false;
        setSeleniumConfigSetting();
        getDriver().get(domain);
        try{
            List<WebElement> elements = getDriver().findElements(By.tagName("input"));

            for(int i = 0; i<elements.size(); i++){
                elements = getDriver().findElements(By.tagName("input"));
                if(elements.get(i).getAttribute("type").equals("text")){
                    if(elements.get(i).getAttribute("name").contains("user") || elements.get(i).getAttribute("name").contains("id") || elements.get(i).getAttribute("name").contains("uname")){
                        continue;
                    }
                    for(int j = 0; j < payLoadDTO.size(); j++){
                        elements = getDriver().findElements(By.tagName("input"));
                        elements.get(i).sendKeys(payLoadDTO.get(j).getPayload());
                        elements.get(i).sendKeys(Keys.ENTER);

                        res = getDriver().getPageSource();
                        elements = getDriver().findElements(By.tagName("input"));
                        for(int k =0; k<elements.size(); k++){
                            if(false || elements.get(k).getAttribute("value").equals(payLoadDTO.get(j).getPayload())) {
                                isValue = true;
                                break;
                            }
                        }
                        getDriver().navigate().back();
                        getDriver().navigate().refresh();

                        if(res.contains("alert(1)") && !isValue)    {
                            payLoadDAO.updatePayLoadCount(payLoadDTO.get(j).getPayload());
                            payloadIdx = j;
                            isXss = true;
                            break;
                        }
                    }
                }
                if(isXss) break;
            }

            if(isXss){
                setInputValue(payLoadDTO.get(payloadIdx).getPayload());
                setResult("1");
                setExists(true);
            }else{
                setInputValue("XSS Syntax");
                setResult(" ");
                setExists(false);
            }
        }catch (Exception e){
            System.out.println("Exception");
        }finally {
            getDriver().close();
            getDriver().quit();
        }
    }
}

package InspectionTool;

import Persistence.DAO.PayLoadDAO;
import Persistence.DTO.PayLoadDTO;
import Persistence.MybatisConnectionFactory;

import java.net.HttpURLConnection;
import java.net.URL;
import java.util.List;

public class AdminExpose extends Vulnerability{
    public AdminExpose(){
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
    public  void runModule(String domain) {
        try{
            int code = 0;
            PayLoadDAO payLoadDAO = new PayLoadDAO(MybatisConnectionFactory.getSqlSessionFactory());
            List <PayLoadDTO> payLoadDTO = payLoadDAO.orderPayLoadSelectPrint("adminExpose");

            for(int i =0; i < payLoadDTO.size(); i++){

                HttpURLConnection c = (HttpURLConnection) new URL(domain + payLoadDTO.get(i).getPayload()).openConnection();
                setSeleniumConfigSetting(); // 셀레니움 드라이버 설정 실행
                getDriver().get(domain + payLoadDTO.get(i).getPayload());

                c.connect();
                code = c.getResponseCode();

                if(code == 200){
                    payLoadDAO.updatePayLoadCount(payLoadDTO.get(i).getPayload());
                    setInputValue(domain + payLoadDTO.get(i).getPayload());
                    setResult(c.getResponseCode() + " " + c.getResponseMessage());
                    setStateCode(String.valueOf(code));
                    driver.close();
                    driver.quit();
                    break;
                } else{
                    setResult("");
                }
                driver.close();
                driver.quit();
            }

        }catch (Exception e){
            e.printStackTrace();
        }finally {
            setExists(true);
        }
    }
}
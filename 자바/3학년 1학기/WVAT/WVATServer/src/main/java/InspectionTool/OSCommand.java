package InspectionTool;

import Persistence.DAO.PayLoadDAO;
import Persistence.DTO.PayLoadDTO;
import Persistence.MybatisConnectionFactory;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebElement;

import java.net.HttpURLConnection;
import java.net.URL;
import java.util.List;

public class OSCommand extends Vulnerability {
    final static String OS_COMMAND_PAYLOAD1 = "root :";
    final static String OS_COMMAND_PAYLOAD2 = "daemon :";
    final static String OS_COMMAND_PAYLOAD3 = "bin :";
    final static String OS_COMMAND_PAYLOAD_ID1 = "uid";
    final static String OS_COMMAND_PAYLOAD_ID2 = "gid";
    final static String OS_COMMAND_PAYLOAD_ID3 = "groups";
    final static String OS_COMMAND_PAYLOAD_DIR_C1 = "Directory of C";
    final static String OS_COMMAND_PAYLOAD_DIR_C2 = "Windows";
    final static String OS_COMMAND_PAYLOAD_UNAME = "Linux";
    final static String OS_COMMAND_PAYLOAD_CURL1 = "href=\"http://crowdshield.com\"";
    final static String OS_COMMAND_PAYLOAD_CURL2 = "href=\"https://crowdshield.com\"";
    final static String OS_COMMAND_PAYLOAD_LS1 = "rwx";
    final static String OS_COMMAND_PAYLOAD_LS2 = "-wx";
    final static String OS_COMMAND_PAYLOAD_LS3 = "r-x";
    final static String OS_COMMAND_PAYLOAD_LS4 = "rw-";
    final static String OS_COMMAND_PAYLOAD_LS5 = "r--";
    final static String OS_COMMAND_PAYLOAD_LS6 = "-w-";
    final static String OS_COMMAND_PAYLOAD_LS7 = "--x";
    final static String OS_COMMAND_PAYLOAD_LS8 = "---";
    final static String OS_COMMAND_PAYLOAD_PING1 = "PING";
    final static String OS_COMMAND_PAYLOAD_PING2 = "32바이트 데이터 사용";
    final static String OS_COMMAND_PAYLOAD_PING3 = "bytes of data.";

    @Override
    public void check(String domain) {
        runModule(domain);
    }

    @Override
    public void runModule(String domain) {
        PayLoadDAO payLoadDAO = new PayLoadDAO(MybatisConnectionFactory.getSqlSessionFactory());
        setExists(false);
        setResult(" ");
        if (checkShadow(payLoadDAO, domain)) {
            setExists(true);
        } else if (checkPasswd(payLoadDAO, domain)) {
            setExists(true);
        } else if (checkId(payLoadDAO, domain)) {
            setExists(true);
        } else if (checkDirC(payLoadDAO, domain)) {
            setExists(true);
        } else if (checkLs(payLoadDAO, domain)) {
            setExists(true);
        } else if (checkCurl(payLoadDAO, domain)) {
            setExists(true);
        } else if (checkPing(payLoadDAO, domain)) {
            setExists(true);
        } else if (checkUname(payLoadDAO, domain)) {
            setExists(true);
        }
    }

    public boolean checkShadow(PayLoadDAO payLoadDAO, String domain) {
        List<PayLoadDTO> payloadDTO = payLoadDAO.selectOsCommandInjection("shadow");
        String pageSource = "";
        setSeleniumConfigSetting();
        getDriver().get(domain);
        try {
            List<WebElement> elements = getDriver().findElements(By.tagName("input"));

            for (int i = 0; i < elements.size(); i++) {
                elements = getDriver().findElements(By.tagName("input"));
                if (elements.get(i).getAttribute("type").equals("text")) {
                    if (elements.get(i).getAttribute("name").contains("user") || elements.get(i).getAttribute("name").contains("id") || elements.get(i).getAttribute("name").contains("uname")) {
                        continue;
                    }
                    for (int j = 0; j < payloadDTO.size(); j++) {
                        elements = getDriver().findElements(By.tagName("input"));
                        elements.get(i).sendKeys(payloadDTO.get(j).getPayload());
                        elements.get(i).sendKeys(Keys.ENTER);
                        HttpURLConnection c = (HttpURLConnection) new URL(driver.getCurrentUrl()).openConnection();
                        pageSource = getDriver().getPageSource();
                        if (c.getResponseCode() == 500) {
                            setInputValue(payloadDTO.get(j).getPayload());
                            setResult("서버 응답 거부 : " + c.getResponseCode());
                            return true;
                        } else if (pageSource.contains(OS_COMMAND_PAYLOAD1) || pageSource.contains(OS_COMMAND_PAYLOAD2) || pageSource.contains(OS_COMMAND_PAYLOAD3)) {
                            setInputValue(payloadDTO.get(j).getPayload());
                            setResult("Os CommandInjection 성공 : " + c.getResponseCode());
                            return true;
                        }
                        getDriver().navigate().back();
                        getDriver().navigate().refresh();
                    }
                }
            }
        } catch (Exception e) {
            System.out.println("OS Command Injection Shadow Exception");
            e.printStackTrace();
        } finally {
            getDriver().close();
            getDriver().quit();
        }
        return false;
    }

    public boolean checkPasswd(PayLoadDAO payLoadDAO, String domain) {
        List<PayLoadDTO> payloadDTO = payLoadDAO.selectOsCommandInjection("passwd");
        String pageSource = "";
        setSeleniumConfigSetting();
        getDriver().get(domain);
        try {
            List<WebElement> elements = getDriver().findElements(By.tagName("input"));

            for (int i = 0; i < elements.size(); i++) {
                elements = getDriver().findElements(By.tagName("input"));
                if (elements.get(i).getAttribute("type").equals("text")) {
                    if (elements.get(i).getAttribute("name").contains("user") || elements.get(i).getAttribute("name").contains("id") || elements.get(i).getAttribute("name").contains("uname")) {
                        continue;
                    }
                    for (int j = 0; j < payloadDTO.size(); j++) {
                        elements = getDriver().findElements(By.tagName("input"));
                        elements.get(i).sendKeys(payloadDTO.get(j).getPayload());
                        elements.get(i).sendKeys(Keys.ENTER);
                        HttpURLConnection c = (HttpURLConnection) new URL(driver.getCurrentUrl()).openConnection();
                        pageSource = getDriver().getPageSource();
                        if (c.getResponseCode() == 500) {
                            setInputValue(payloadDTO.get(j).getPayload());
                            setResult("서버 응답 거부 : " + c.getResponseCode());
                            return true;
                        } else if (pageSource.contains(OS_COMMAND_PAYLOAD1) || pageSource.contains(OS_COMMAND_PAYLOAD2) || pageSource.contains(OS_COMMAND_PAYLOAD3)) {
                            setInputValue(payloadDTO.get(j).getPayload());
                            setResult("Os CommandInjection 성공 : " + c.getResponseCode());
                            return true;
                        }
                        getDriver().navigate().back();
                        getDriver().navigate().refresh();
                    }
                }
            }
        } catch (Exception e) {
            System.out.println("OS Command Injection Passwd Exception");
            e.printStackTrace();
        } finally {
            getDriver().close();
            getDriver().quit();
        }
        return false;
    }

    public boolean checkId(PayLoadDAO payLoadDAO, String domain) {
        List<PayLoadDTO> payloadDTO = payLoadDAO.selectOsCommandInjection("id");
        String pageSource = "";
        setSeleniumConfigSetting();
        getDriver().get(domain);
        try {
            List<WebElement> elements = getDriver().findElements(By.tagName("input"));

            for (int i = 0; i < elements.size(); i++) {
                elements = getDriver().findElements(By.tagName("input"));
                if (elements.get(i).getAttribute("type").equals("text")) {
                    if (elements.get(i).getAttribute("name").contains("user") || elements.get(i).getAttribute("name").contains("id") || elements.get(i).getAttribute("name").contains("uname")) {
                        continue;
                    }
                    for (int j = 0; j < payloadDTO.size(); j++) {
                        elements = getDriver().findElements(By.tagName("input"));
                        elements.get(i).sendKeys(payloadDTO.get(j).getPayload());
                        elements.get(i).sendKeys(Keys.ENTER);
                        HttpURLConnection c = (HttpURLConnection) new URL(driver.getCurrentUrl()).openConnection();
                        pageSource = getDriver().getPageSource();
                        if (c.getResponseCode() == 500) {
                            setInputValue(payloadDTO.get(j).getPayload());
                            setResult("서버 응답 거부 : " + c.getResponseCode());
                            return true;
                        } else if (pageSource.contains(OS_COMMAND_PAYLOAD_ID1) || pageSource.contains(OS_COMMAND_PAYLOAD_ID2) || pageSource.contains(OS_COMMAND_PAYLOAD_ID3)) {
                            setInputValue(payloadDTO.get(j).getPayload());
                            setResult("Os CommandInjection 성공 : " + c.getResponseCode());
                            return true;
                        }
                        getDriver().navigate().back();
                        getDriver().navigate().refresh();
                    }
                }
            }
        } catch (Exception e) {
            System.out.println("OS Command Injection Id Exception");
            e.printStackTrace();
        } finally {
            getDriver().close();
            getDriver().quit();
        }
        return false;
    }

    public boolean checkDirC(PayLoadDAO payLoadDAO, String domain) {
        List<PayLoadDTO> payloadDTO = payLoadDAO.selectOsCommandInjection("dir");
        String pageSource = "";
        setSeleniumConfigSetting();
        getDriver().get(domain);
        try {
            List<WebElement> elements = getDriver().findElements(By.tagName("input"));

            for (int i = 0; i < elements.size(); i++) {
                elements = getDriver().findElements(By.tagName("input"));
                if (elements.get(i).getAttribute("type").equals("text")) {
                    if (elements.get(i).getAttribute("name").contains("user") || elements.get(i).getAttribute("name").contains("id") || elements.get(i).getAttribute("name").contains("uname")) {
                        continue;
                    }
                    for (int j = 0; j < payloadDTO.size(); j++) {
                        elements = getDriver().findElements(By.tagName("input"));
                        elements.get(i).sendKeys(payloadDTO.get(j).getPayload());
                        elements.get(i).sendKeys(Keys.ENTER);
                        HttpURLConnection c = (HttpURLConnection) new URL(driver.getCurrentUrl()).openConnection();
                        pageSource = getDriver().getPageSource();
                        if (c.getResponseCode() == 500) {
                            setInputValue(payloadDTO.get(j).getPayload());
                            setResult("서버 응답 거부 : " + c.getResponseCode());
                            return true;
                        } else if (pageSource.contains(OS_COMMAND_PAYLOAD_DIR_C1) || pageSource.contains(OS_COMMAND_PAYLOAD_DIR_C2)) {
                            setInputValue(payloadDTO.get(j).getPayload());
                            setResult("Os CommandInjection 성공 : " + c.getResponseCode());
                            return true;
                        }
                        getDriver().navigate().back();
                        getDriver().navigate().refresh();
                    }
                }
            }
        } catch (Exception e) {
            System.out.println("OS Command Injection Dir C: Exception");
            e.printStackTrace();
        } finally {
            getDriver().close();
            getDriver().quit();
        }
        return false;
    }

    public boolean checkLs(PayLoadDAO payLoadDAO, String domain) {
        List<PayLoadDTO> payloadDTO = payLoadDAO.selectOsCommandInjection("ls -l");
        String pageSource = "";
        setSeleniumConfigSetting();
        getDriver().get(domain);
        try {
            List<WebElement> elements = getDriver().findElements(By.tagName("input"));

            for (int i = 0; i < elements.size(); i++) {
                elements = getDriver().findElements(By.tagName("input"));
                if (elements.get(i).getAttribute("type").equals("text")) {
                    if (elements.get(i).getAttribute("name").contains("user") || elements.get(i).getAttribute("name").contains("id") || elements.get(i).getAttribute("name").contains("uname")) {
                        continue;
                    }
                    for (int j = 0; j < payloadDTO.size(); j++) {
                        elements = getDriver().findElements(By.tagName("input"));
                        elements.get(i).sendKeys(payloadDTO.get(j).getPayload());
                        elements.get(i).sendKeys(Keys.ENTER);
                        HttpURLConnection c = (HttpURLConnection) new URL(driver.getCurrentUrl()).openConnection();
                        pageSource = getDriver().getPageSource();
                        if (c.getResponseCode() == 500) {
                            setInputValue(payloadDTO.get(j).getPayload());
                            setResult("서버 응답 거부 : " + c.getResponseCode());
                            return true;
                        } else if (pageSource.contains(OS_COMMAND_PAYLOAD_LS1) || pageSource.contains(OS_COMMAND_PAYLOAD_LS2)
                                || pageSource.contains(OS_COMMAND_PAYLOAD_LS3) || pageSource.contains(OS_COMMAND_PAYLOAD_LS4)
                                || pageSource.contains(OS_COMMAND_PAYLOAD_LS5) || pageSource.contains(OS_COMMAND_PAYLOAD_LS6)
                                || pageSource.contains(OS_COMMAND_PAYLOAD_LS7) || pageSource.contains(OS_COMMAND_PAYLOAD_LS8))
                        {
                            setInputValue(payloadDTO.get(j).getPayload());
                            setResult("Os CommandInjection 성공 : " + c.getResponseCode());
                            return true;
                        }
                        getDriver().navigate().back();
                        getDriver().navigate().refresh();
                    }
                }
            }
        } catch (Exception e) {
            System.out.println("OS Command Injection ls -l Exception");
            e.printStackTrace();
        } finally {
            getDriver().close();
            getDriver().quit();
        }
        return false;
    }

    public boolean checkCurl(PayLoadDAO payLoadDAO, String domain) {
        List<PayLoadDTO> payloadDTO = payLoadDAO.selectOsCommandInjection("curl");
        String pageSource = "";
        setSeleniumConfigSetting();
        getDriver().get(domain);
        try {
            List<WebElement> elements = getDriver().findElements(By.tagName("input"));

            for (int i = 0; i < elements.size(); i++) {
                elements = getDriver().findElements(By.tagName("input"));
                if (elements.get(i).getAttribute("type").equals("text")) {
                    if (elements.get(i).getAttribute("name").contains("user") || elements.get(i).getAttribute("name").contains("id") || elements.get(i).getAttribute("name").contains("uname")) {
                        continue;
                    }
                    for (int j = 0; j < payloadDTO.size(); j++) {
                        elements = getDriver().findElements(By.tagName("input"));
                        elements.get(i).sendKeys(payloadDTO.get(j).getPayload());
                        elements.get(i).sendKeys(Keys.ENTER);
                        HttpURLConnection c = (HttpURLConnection) new URL(driver.getCurrentUrl()).openConnection();
                        pageSource = getDriver().getPageSource();
                        if (c.getResponseCode() == 500) {
                            setInputValue(payloadDTO.get(j).getPayload());
                            setResult("서버 응답 거부 : " + String.valueOf(c.getResponseCode()));
                            return true;
                        } else if (pageSource.contains(OS_COMMAND_PAYLOAD_CURL1) || pageSource.contains(OS_COMMAND_PAYLOAD_CURL2)) {
                            setInputValue(payloadDTO.get(j).getPayload());
                            setResult("Os CommandInjection 성공 : " + c.getResponseCode());
                            return true;
                        }
                        getDriver().navigate().back();
                        getDriver().navigate().refresh();
                    }
                }
            }
        } catch (Exception e) {
            System.out.println("OS Command Injection curl Exception");
            e.printStackTrace();
        } finally {
            getDriver().close();
            getDriver().quit();
        }
        return false;
    }

    public boolean checkPing(PayLoadDAO payLoadDAO, String domain) {
        List<PayLoadDTO> payloadDTO = payLoadDAO.selectOsCommandInjection("ping");
        String pageSource = "";
        setSeleniumConfigSetting();
        getDriver().get(domain);
        try {
            List<WebElement> elements = getDriver().findElements(By.tagName("input"));

            for (int i = 0; i < elements.size(); i++) {
                elements = getDriver().findElements(By.tagName("input"));
                if (elements.get(i).getAttribute("type").equals("text")) {
                    if (elements.get(i).getAttribute("name").contains("user") || elements.get(i).getAttribute("name").contains("id") || elements.get(i).getAttribute("name").contains("uname")) {
                        continue;
                    }
                    for (int j = 0; j < payloadDTO.size(); j++) {
                        elements = getDriver().findElements(By.tagName("input"));
                        elements.get(i).sendKeys(payloadDTO.get(j).getPayload());
                        elements.get(i).sendKeys(Keys.ENTER);
                        HttpURLConnection c = (HttpURLConnection) new URL(driver.getCurrentUrl()).openConnection();
                        pageSource = getDriver().getPageSource();
                        if (c.getResponseCode() == 500) {
                            setInputValue(payloadDTO.get(j).getPayload());
                            setResult("서버 응답 거부 : " + c.getResponseCode());
                            return true;
                        } else if (pageSource.contains(OS_COMMAND_PAYLOAD_PING1) || pageSource.contains(OS_COMMAND_PAYLOAD_PING2)
                                || pageSource.contains(OS_COMMAND_PAYLOAD_PING3)) {
                            setInputValue(payloadDTO.get(j).getPayload());
                            setResult("Os CommandInjection 성공 : " + c.getResponseCode());
                            return true;
                        }
                        getDriver().navigate().back();
                        getDriver().navigate().refresh();
                    }
                }
            }
        } catch (Exception e) {
            System.out.println("OS Command Injection ping Exception");
            e.printStackTrace();
        } finally {
            getDriver().close();
            getDriver().quit();
        }
        return false;
    }

    public boolean checkUname(PayLoadDAO payLoadDAO, String domain) {
        List<PayLoadDTO> payloadDTO = payLoadDAO.selectOsCommandInjection("uname");
        String pageSource = "";
        setSeleniumConfigSetting();
        getDriver().get(domain);
        try {
            List<WebElement> elements = getDriver().findElements(By.tagName("input"));

            for (int i = 0; i < elements.size(); i++) {
                elements = getDriver().findElements(By.tagName("input"));
                if (elements.get(i).getAttribute("type").equals("text")) {
                    if (elements.get(i).getAttribute("name").contains("user") || elements.get(i).getAttribute("name").contains("id") || elements.get(i).getAttribute("name").contains("uname")) {
                        continue;
                    }
                    for (int j = 0; j < payloadDTO.size(); j++) {
                        elements = getDriver().findElements(By.tagName("input"));
                        elements.get(i).sendKeys(payloadDTO.get(j).getPayload());
                        elements.get(i).sendKeys(Keys.ENTER);
                        HttpURLConnection c = (HttpURLConnection) new URL(driver.getCurrentUrl()).openConnection();
                        pageSource = getDriver().getPageSource();
                        if (c.getResponseCode() == 500) {
                            setInputValue(payloadDTO.get(j).getPayload());
                            setResult("서버 응답 거부 : " + c.getResponseCode());
                            return true;
                        } else if (pageSource.contains(OS_COMMAND_PAYLOAD_UNAME)) {
                            setInputValue(payloadDTO.get(j).getPayload());
                            setResult("Os CommandInjection 성공 : " + c.getResponseCode());
                            return true;
                        }
                        getDriver().navigate().back();
                        getDriver().navigate().refresh();
                    }
                }
            }
        } catch (Exception e) {
            System.out.println("OS Command Injection uname Exception");
            e.printStackTrace();
        } finally {
            getDriver().close();
            getDriver().quit();
        }
        return false;
    }
}
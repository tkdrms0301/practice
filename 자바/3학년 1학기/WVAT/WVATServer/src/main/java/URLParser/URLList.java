package URLParser;

import org.openqa.selenium.By;
import org.openqa.selenium.StaleElementReferenceException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.util.ArrayList;
import java.util.List;

public class URLList {
    SeleniumConfigSetting seleniumConfigSetting = null;
    WebDriver driver = null;
    String startUrl = "";
    URLTree urlTree = null;
    List<String> array_ROOT;
    List<String> array_LEVEL1;
    List<String> array_LEVEL2;
    String url = "";

    int count = 0;
    public URLList(String url){
        setSeleniumConfigSetting();
        startUrl = url;
        urlTree = new URLTree();
        urlTree.setRootNode(url);
        array_ROOT = new ArrayList<>();
        array_LEVEL1 = new ArrayList<>();
        array_LEVEL2 = new ArrayList<>();
    }

    public void setSeleniumConfigSetting(){
        seleniumConfigSetting = new SeleniumConfigSetting();
        driver = seleniumConfigSetting.getDriver();
    }

    public void run(){
        // 루트
        urlParse(startUrl);
        urlTree.toList(urlTree.getRootNode(), urlTree.getProtocol() + "/", array_ROOT);
        System.out.println("ㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡ");

        // 1
        for(int i = 0; i < array_ROOT.size(); i++){
            urlParse(array_ROOT.get(i));
        }
        urlTree.toList(urlTree.getRootNode(), urlTree.getProtocol() + "/", array_LEVEL1);
        array_ROOT = null;
        System.out.println("ㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡ");
        driver.close();
        driver.quit();
        System.out.println("cout : " + count);
        urlTree.toList(urlTree.getRootNode(), urlTree.getProtocol() + "/", array_LEVEL2);
        // 2
        /*for(int i = 0; i < array_LEVEL1.size(); i++){
            urlParse(array_LEVEL1.get(i));
        }
        urlTree.toList(urlTree.getRootNode(), urlTree.getProtocol() + "/", array_LEVEL2);
        array_LEVEL1 = null;
        System.out.println("ㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡ");
        driver.close();
        driver.quit();
        System.out.println("cout : " + count);*/
    }

    private void urlParse(String url){
        count++;
        System.out.println(url);
//        setSeleniumConfigSetting();
        try{
            driver.get(url);
            url = "";
            List<WebElement> attr = driver.findElements(By.tagName("a"));
            for (WebElement element : attr) {
                try {
                    url = element.getAttribute("href");
                }catch (NullPointerException e){
                    if (url == null) {
                        continue;
                    }
                }catch (StaleElementReferenceException e){
                    WebDriverWait wait = new WebDriverWait(driver,30);
                    wait.until(ExpectedConditions.presenceOfElementLocated(By.tagName("a")));
                }
                urlTree.addNode(url);
            }
        }catch (Throwable e){
            e.printStackTrace();
        }finally {
//            driver.close();
//            driver.quit();
        }
    }

    public List<String> toUrlList(){
        urlTree.toList(urlTree.getRootNode(), "/" + urlTree.getProtocol(), array_LEVEL2);
        return array_LEVEL2;
    }
}
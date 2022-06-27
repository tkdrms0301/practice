package URLParser;

import org.openqa.selenium.UnexpectedAlertBehaviour;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.remote.CapabilityType;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.util.concurrent.TimeUnit;
import java.util.logging.Level;

public class SeleniumConfigSetting {
    WebDriver driver = null;

    public SeleniumConfigSetting(){
        //url 창안띄우고 실행
        ChromeOptions options = new ChromeOptions();
        options.addArguments("headless");
        options.addArguments("--lang=ko");
        options.addArguments("--blink-settings=imagesEnabled=false");
        options.addArguments("--disable-popup-blocking");
        options.addArguments("--no-sandbox");
        options.addArguments("--disable-dev-shm-usage");
        System.setProperty("webdriver.chrome.args", "--disable-logging");
        System.setProperty("webdriver.chrome.silentOutput", "true");
        java.util.logging.Logger.getLogger("org.openqa.selenium").setLevel(Level.OFF);
        // drvier 설정 - 저는 d드라이브 work 폴더에 있습니다.
        System.setProperty("webdriver.chrome.driver", "C:\\Users\\tprud\\OneDrive\\Desktop\\chromedriver.exe");

        // Chrome 드라이버 인스턴스 설정
        DesiredCapabilities capabilities = new DesiredCapabilities();
        capabilities.setCapability(ChromeOptions.CAPABILITY, options);
        capabilities.setCapability(CapabilityType.UNEXPECTED_ALERT_BEHAVIOUR, UnexpectedAlertBehaviour.ACCEPT);
        driver = new ChromeDriver(capabilities);

        // 대기 설정
        driver.manage().timeouts().implicitlyWait(3, TimeUnit.SECONDS);

    }

    public WebDriver getDriver() {
        return driver;
    }
}

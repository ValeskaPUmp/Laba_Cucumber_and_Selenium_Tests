import cucumber.api.java.hu.Ha;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.remote.CapabilityType;
import org.openqa.selenium.remote.DesiredCapabilities;

import java.util.HashMap;

public class SeleniumTest {
    static WebDriver driver;
    @Test
    protected  void init(){
        System.setProperty("webdriver.chrome.driver","D://chromedriver.exe");
        ChromeOptions options = new ChromeOptions();
        options.addArguments("--headless");
        DesiredCapabilities capabilities = new DesiredCapabilities();
        capabilities.setCapability(ChromeOptions.CAPABILITY, options);
        options.merge(capabilities);
        ChromeDriver driver = new ChromeDriver(options);
        driver.get("http://distedu.ukma.edu.ua/");
        driver.manage().window().maximize();
        String title = driver.getTitle();
        Assertions.assertEquals(driver.getTitle(),"Дистанційне навчання у НаУКМА: Увійдіть на сайт");
        driver.navigate().to("https://distedu.ukma.edu.ua/login/forgot_password.php");
        Assertions.assertEquals(driver.getTitle(),"Забутий пароль");
        driver.quit();

    }
}

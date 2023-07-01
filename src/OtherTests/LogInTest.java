package OtherTests;

import org.openqa.selenium.By;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.support.ui.Select;
import org.testng.annotations.Test;

import java.time.Duration;

public class LogInTest {

    @Test
    public static void TestLogInFeature (){
        System.setProperty("webdriver.chrome.driver", "C:/Users/Kostya/Downloads/ChromeDrivers/chromedriver.exe");
        ChromeOptions options = new ChromeOptions();
        options.addArguments("--remote-allow-origins=*");

        ChromeDriver driver = new ChromeDriver(options);
        driver.manage().window().maximize();
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
        driver.get("https://www.way2automation.com/angularjs-protractor/banking/#/login");

        driver.findElement(By.xpath("//*[contains(text(),\"Customer Login\")]")).click();
        Select select = new Select(driver.findElement(By.xpath("//*[@id=\"userSelect\"]")));
        select.selectByVisibleText("Hermoine Granger");
        driver.findElement(By.xpath("//*[contains(text(),\"Login\")]")).click();

    }
}

package FleaMarketTests;

import org.junit.jupiter.api.Assertions;
import org.openqa.selenium.By;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.Test;

import java.time.Duration;
import java.util.Set;

public class PrivacyPolicyTest {

    @Test
    public void TestPrivacyPolicyLink (){
        System.setProperty("webdriver.chrome.driver", "C:/Users/Kostya/Desktop/PythonProjects/ChromeDrive102/chromedriver.exe");
        ChromeDriver driver = new ChromeDriver();
        driver.get("https://store-stage.portnov.com/");
        driver.manage().window().maximize();
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(20));


        driver.findElement(By.xpath("//li[@id='menu-item-77']/a")).click();
        driver.findElement(By.xpath("//a[contains(text(),'privacy policy')]")).click();
        String mainwindow = driver.getWindowHandle();
        Set<String> nextwindow = driver.getWindowHandles();

        for (String windowhandels: nextwindow)
            if (!windowhandels.equals(mainwindow)){
                driver.switchTo().window(windowhandels);
                String privacypolicytitle = driver.findElement(By.xpath("//header/h1")).getText();
                Assertions.assertEquals("Privacy Policy", privacypolicytitle);
                driver.close();
                driver.switchTo().window(mainwindow);
                driver.quit();
            }
    }
}

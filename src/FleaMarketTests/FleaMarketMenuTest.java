package FleaMarketTests;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import java.time.Duration;

public class FleaMarketMenuTest {
    WebDriver driver;

    @BeforeClass
    public void SetUp() {
        System.setProperty("webdriver.chrome.driver", "C:/Users/Kostya/Desktop/PythonProjects/ChromeDriver103/chromedriver.exe");
        driver = new ChromeDriver();
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(20));
    }
    @Test
    public void TestMenu() {
        driver = new ChromeDriver();
        driver.get("https://store-stage.portnov.com/");
        Actions actions = new Actions(driver);
        driver.findElement(By.xpath("//body/div[@id='page']/div[@id='content']/div[1]/div[2]/main[1]/ul[1]/li[1]/a[2]")).click();
        actions.moveToElement(driver.findElement(By.xpath("//ul[@id='site-header-cart']")));
        actions.perform();
        String addedItem = driver.findElement(By.xpath(" //header/div[1]/div[1]/div[2]/div[1]/ul[1]/li[2]/div[1]/div[1]/ul[1]/li[1]/a[2]")).getText();
        Assert.assertEquals(addedItem, "AirPods Pro");
    }
    @AfterClass
    public void AfterClass () {
        driver.quit();
    }
}

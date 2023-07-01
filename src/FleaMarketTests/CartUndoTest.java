package FleaMarketTests;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;

import java.time.Duration;
import java.util.*;
import java.util.concurrent.TimeUnit;
import java.util.stream.Collectors;

import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.util.*;
import org.testng.annotations.*;


public class CartUndoTest {

    @Test
    public void TestCartUndoFunction () throws Exception {
        System.setProperty("webdriver.chrome.driver", "C:/Users/Kostya/Desktop/PythonProjects/ChromeDrive102/chromedriver.exe");
        ChromeDriver driver = new ChromeDriver();
        driver.get("https://store-qa.portnov.com/");
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(20));
        driver.manage().window().maximize();


        //add item to cart
        driver.findElement(By.xpath("//a[@data-product_id=\"58\"]")).click();
        Thread.sleep(2000);
        //move to cart
        driver.findElement(By.xpath("//span[contains(text(),'1 item')]")).click();
        //delete item
        driver.findElement(By.xpath("//a[contains(text(),'×')]")).click();
        //wait for massage Undo? appear
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(20));
        wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//a[contains(text(),'Undo?')]")));
        //Click Undo
        driver.findElement(By.xpath("//a[contains(text(),'Undo?')]")).click();
        //Verify item is restored
        List<WebElement> restroeditems = driver.findElements(By.xpath("//tbody/tr[1]/td[3]"));
        ArrayList<String> itemsrestored = new ArrayList<>();
        boolean resotred = false;
        for (WebElement e: restroeditems)
            itemsrestored.add(e.getText());
        if (itemsrestored.size() > 0) {
            resotred = true;
        }
        Assert.assertTrue(resotred);
        driver.quit();
    }
}

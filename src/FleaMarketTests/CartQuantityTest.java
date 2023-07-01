package FleaMarketTests;

import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.chrome.ChromeDriver;

import java.time.Duration;
import java.util.List;
import java.util.concurrent.TimeUnit;
import java.util.*;
import java.util.stream.*;


public class CartQuantityTest {

    @Test
    public void TestCartQuantityFunction() throws InterruptedException {
        System.setProperty("webdriver.chrome.driver", "C:/Users/Kostya/Desktop/PythonProjects/ChromeDrive102/chromedriver.exe");
        ChromeDriver driver = new ChromeDriver();
        driver.get("https://store-qa.portnov.com/");
        driver.manage().timeouts().implicitlyWait(2000, TimeUnit.MILLISECONDS);
        driver.manage().window().maximize();

        //Add item to cart
        driver.findElement(By.xpath("//a[@data-product_id=\"58\"]")).click();
        //Navigate to cart
        Thread.sleep(4000);

        driver.findElement(By.xpath("//a[@class=\"cart-contents\"]")).click();
        //Increase quantity to 2

        driver.findElement(By.xpath("//input[@class=\"input-text qty text\"]")).clear();
        driver.findElement(By.xpath("//input[@class=\"input-text qty text\"]")).sendKeys("2");

        //Click update cart
        driver.findElement(By.xpath("//button[contains(text(),'Update cart')]")).click();
        //Verify price is correct
        String itempriceraw = driver.findElement(By.xpath("//tbody/tr[1]/td[4]/span[1]/bdi")).getText().replaceAll("[$.,0]","");
        int itemprice = Integer.parseInt(itempriceraw);
        int expectedprice = itemprice + itemprice;
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(5));
        wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//div[contains(text(),'Cart updated.')]")));
        //Verify your cart is updated massage displayed
        String actualpriceraw = driver.findElement(By.xpath("//tbody/tr[1]/td[6]/span/bdi")).getText().replaceAll("[$,.0]","");
        int actualprice = Integer.parseInt(actualpriceraw);
        String cartupdatemsg = driver.findElement(By.xpath("//div[contains(text(),'Cart updated.')]")).getText();
        Assert.assertEquals(actualprice, expectedprice);
        Assert.assertEquals(cartupdatemsg, "Cart updated.");
        driver.quit();

    }



}

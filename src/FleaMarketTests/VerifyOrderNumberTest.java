package FleaMarketTests;

import org.junit.jupiter.api.Assertions;
import org.openqa.selenium.By;
import org.openqa.selenium.StaleElementReferenceException;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import java.time.Duration;
import java.util.*;
import org.openqa.selenium.interactions.Actions;
import org.testng.Assert;
import org.testng.annotations.*;

public class VerifyOrderNumberTest {
    @Test
    public void TestOrderNumberId() throws Exception {
        System.setProperty("webdriver.chrome.driver", "C:/Users/Kostya/Desktop/PythonProjects/ChromeDrive102/chromedriver.exe");
        ChromeDriver driver = new ChromeDriver();
        driver.get("");
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(20));
        driver.manage().window().maximize();

        //add item to cart
        driver.findElement(By.xpath("//a[@data-product_id=\"58\"]")).click();
        Thread.sleep(5000);
        //click proceed to checkout
        Actions actions = new Actions(driver);
        actions.moveToElement(driver.findElement(By.xpath("//a[@class=\"cart-contents\"]")));
        actions.moveToElement(driver.findElement(By.xpath("//a[@class=\"button checkout wc-forward\"]"))).click();
        actions.perform();
        //fill out required fields
        driver.findElement(By.xpath("//input[@id='billing_first_name']")).sendKeys("Tester");
        driver.findElement(By.xpath("//input[@id='billing_last_name']")).sendKeys("Tester");
        driver.findElement(By.xpath("//input[@id='billing_email']")).sendKeys("tester@gmail.com");
        //click Place order
        Thread.sleep(3000);
        driver.findElement(By.xpath("//button[@id='place_order']")).click();
        //Save order number
        String  idtext1 = driver.findElement(By.xpath("//li[@class=\"woocommerce-order-overview__order order\"]/strong")).getText();
        int idnumber1 = Integer.parseInt(idtext1);
        //Repeat above steps
        //add item to cart
        driver.findElement(By.xpath("//header/div[1]/div[1]/div[1]/div[1]/a[1]/img[1]")).click();
        driver.findElement(By.xpath("//a[@data-product_id=\"58\"]")).click();
        Thread.sleep(5000);
        //click proceed to checkout
        actions.moveToElement(driver.findElement(By.xpath("//a[@class=\"cart-contents\"]"))).moveToElement(
                driver.findElement(By.xpath("//a[@class=\"button checkout wc-forward\"]"))).click().perform();
        //fill out required fields
        driver.findElement(By.xpath("//input[@id='billing_first_name']")).clear();
        driver.findElement(By.xpath("//input[@id='billing_first_name']")).sendKeys("Tester");
        driver.findElement(By.xpath("//input[@id='billing_last_name']")).clear();
        driver.findElement(By.xpath("//input[@id='billing_last_name']")).sendKeys("Tester");
        driver.findElement(By.xpath("//input[@id='billing_email']")).clear();
        driver.findElement(By.xpath("//input[@id='billing_email']")).sendKeys("tester@gmail.com");
        //click Place order
        Thread.sleep(3000);
        driver.findElement(By.xpath("//button[@id='place_order']")).click();
        //Save order number
        String  idtext2 = driver.findElement(By.xpath("//li[@class=\"woocommerce-order-overview__order order\"]/strong")).getText();
        int idnumber2 = Integer.parseInt(idtext2);
        boolean orderidisdifferent = idnumber2 > idnumber1;
        //Verify new order number generated
        Assert.assertTrue(orderidisdifferent);
        driver.quit();
    }

    @Test
    public void TestOrderConfirmForEachProduct(){
        System.setProperty("webdriver.chrome.driver", "C:/Users/Kostya/Desktop/PythonProjects/ChromeDrive102/chromedriver.exe");
        ChromeDriver driver = new ChromeDriver();
        driver.get("https://store-qa.portnov.com/");
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(20));
        driver.manage().window().maximize();

        //Store all products from maine page in list
        List<WebElement> allproducts = driver.findElements(By.xpath("//a[contains(text(),\"Add to cart\")]"));

        //Loop through every element, add to cart and checkout, verify order confirmation massage
        for (int i=0; i<allproducts.size(); i++)
        {
            try {
                allproducts.get(i).click();
                Actions actions = new Actions(driver);
                driver.findElement(By.xpath("//ul[@class=\"products columns-3\"]/li/a[3]")).click();
                driver.findElement(By.xpath("//a[contains(text(),'Proceed to checkout')]")).click();
                driver.findElement(By.xpath("//input[@id='billing_first_name']")).clear();
                driver.findElement(By.xpath("//input[@id='billing_first_name']")).sendKeys("Tester");
                driver.findElement(By.xpath("//input[@id='billing_last_name']")).clear();
                driver.findElement(By.xpath("//input[@id='billing_last_name']")).sendKeys("Tester");
                driver.findElement(By.xpath("//input[@id='billing_email']")).clear();
                driver.findElement(By.xpath("//input[@id='billing_email']")).sendKeys("tester@gmail.com");
                actions.pause(Duration.ofSeconds(5)).perform();
                driver.findElement(By.xpath("//button[@id='place_order']")).click();
                Assertions.assertTrue(driver.findElement(By.xpath("//h1[contains(text(),'Order received')]")).isDisplayed());
                driver.findElement(By.xpath("//div[@class=\"site-branding\"]")).click();
                actions.pause(2).perform();
            }
            catch (StaleElementReferenceException e)
            {
                allproducts = driver.findElements(By.xpath("//a[contains(text(),\"Add to cart\")]"));
                allproducts.get(i).click();
                Actions actions = new Actions(driver);
                driver.findElement(By.xpath("//ul[@class=\"products columns-3\"]/li/a[3]")).click();
                driver.findElement(By.xpath("//a[contains(text(),'Proceed to checkout')]")).click();
                actions.pause(3).perform();
                driver.findElement(By.xpath("//input[@id='billing_first_name']")).clear();
                driver.findElement(By.xpath("//input[@id='billing_first_name']")).sendKeys("Tester");
                driver.findElement(By.xpath("//input[@id='billing_last_name']")).clear();
                driver.findElement(By.xpath("//input[@id='billing_last_name']")).sendKeys("Tester");
                driver.findElement(By.xpath("//input[@id='billing_email']")).clear();
                driver.findElement(By.xpath("//input[@id='billing_email']")).sendKeys("tester@gmail.com");
                actions.pause(Duration.ofSeconds(5)).perform();
                driver.findElement(By.xpath("//button[@id='place_order']")).click();
                Assertions.assertTrue(driver.findElement(By.xpath("//h1[contains(text(),'Order received')]")).isDisplayed());
                driver.findElement(By.xpath("//div[@class=\"site-branding\"]")).click();
                actions.pause(2).perform();
            }
        }
        driver.quit();
    }

}

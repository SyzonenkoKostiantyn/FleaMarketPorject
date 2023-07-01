package FleaMarketV1;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.Point;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;
import org.testng.annotations.Test;

import java.awt.*;
import java.awt.event.InputEvent;
import java.time.Duration;

public class OrderConfirmWithBrokenSubmitTest {

    @Test
    public void TestConfirmOrderWithBrokenSubmitButton () throws Exception {
        System.setProperty("webdriver.chrome.driver", "C:/Users/Kostya/Desktop/PythonProjects/ChromeDrive102/chromedriver.exe");
        ChromeDriver driver = new ChromeDriver();
        driver.get("https://store-stage.portnov.com/");
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(20));
        driver.manage().window().maximize();


        driver.findElement(By.xpath("//a[@data-product_id=\"58\"]")).click();
        driver.findElement(By.xpath("//a[@class=\"added_to_cart wc-forward\"]")).click();
        driver.findElement(By.xpath("//a[contains(text(),'Proceed to checkout')]")).click();
        driver.findElement(By.xpath("//input[@id='billing_first_name']")).sendKeys("Tester");
        driver.findElement(By.xpath("//input[@id='billing_last_name']")).sendKeys("Tester");
        driver.findElement(By.xpath("//input[@id='billing_email']")).sendKeys("tester@gmail.com");
        Thread.sleep(5000);
        WebElement placeorederbutton = driver.findElement(By.xpath("//button[@id='place_order']"));
        Point point = placeorederbutton.getLocation();
        int xcord = point.getX();
        int ycord = point.getY();
        System.out.println(xcord);
        System.out.println(ycord);
        Actions actions = new Actions(driver);
        actions.sendKeys(Keys.PAGE_DOWN);
        actions.perform();
        Robot robot = new Robot();
        robot.mouseMove(1400, 750);
        robot.mousePress(InputEvent.BUTTON1_DOWN_MASK);
        robot.mouseRelease(InputEvent.BUTTON1_DOWN_MASK);

    }
}

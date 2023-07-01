package QuoteTests;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.Test;
import org.testng.Assert;

public class TestOne {
    @Test
    public static void TestMain(){
        System.setProperty("webdriver.chrome.driver", "C:/Users/Kostya/Desktop/PythonProjects/ChromeDrive102/chromedriver.exe");
        ChromeDriver driver=new ChromeDriver();
        driver.get("https://skryabin.com/market/quote.html");


        driver.findElement(By.xpath("//input[@ng-model=\"formData.username\"]")).sendKeys("T");
        driver.findElement(By.xpath("//input[@id='password']")).click();
        String error_msg = driver.findElement(By.xpath("//label[@id='username-error']")).getText();
        Assert.assertEquals(error_msg, "Please enter at least 2 characters.");
        driver.quit();

    }
    @Test
    public static void TestPasswordDisabled(){
        System.setProperty("webdriver.chrome.driver", "C:/Users/Kostya/Desktop/PythonProjects/ChromeDrive102/chromedriver.exe");
        ChromeDriver driver=new ChromeDriver();
        driver.get("https://skryabin.com/market/quote.html");


        WebElement passwordfield = driver.findElement(By.xpath("//input[@id='confirmPassword']"));
        Assert.assertFalse(passwordfield.isEnabled());
        driver.quit();
    }

    @Test
    public static void TestConfirmPasswordDismatch(){
        System.setProperty("webdriver.chrome.driver", "C:/Users/Kostya/Desktop/PythonProjects/ChromeDrive102/chromedriver.exe");
        ChromeDriver driver=new ChromeDriver();
        driver.get("https://skryabin.com/market/quote.html");

        driver.findElement(By.xpath("//input[@id='password']")).sendKeys("Tester");
        driver.findElement(By.xpath("//input[@id='confirmPassword']")).sendKeys("Teste");
        driver.findElement(By.xpath("//input[@id='password']")).click();
        String error_msg = driver.findElement(By.xpath("//label[@id='confirmPassword-error']")).getText();
        Assert.assertEquals(error_msg, "Passwords do not match!");
        driver.quit();
    }
}


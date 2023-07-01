package QuoteTests;

import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.Select;
import org.testng.Assert;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.Test;


public class AgreementTest {
    @Test
    public void TestAgreement() {
        System.setProperty("webdriver.chrome.driver", "C:/Users/Kostya/Desktop/PythonProjects/ChromeDrive102/chromedriver.exe");
        ChromeDriver driver = new ChromeDriver();
        driver.get("https://skryabin.com/market/quote.html");

        driver.findElement(By.xpath("//button[@id='thirdPartyButton']")).click();
        driver.switchTo().alert().accept();
        String agreement_msg = driver.findElement(By.xpath("//span[@id='thirdPartyResponseMessage']")).getText();
        Assert.assertEquals(agreement_msg, "You accepted third party agreement.");
        driver.quit();

    }

    @Test
    public void TestNameModal() {
        System.setProperty("webdriver.chrome.driver", "C:/Users/Kostya/Desktop/PythonProjects/ChromeDrive102/chromedriver.exe");
        ChromeDriver driver = new ChromeDriver();
        driver.get("https://skryabin.com/market/quote.html");

        driver.findElement(By.xpath("//input[@id='name']")).click();
        driver.findElement(By.xpath("//input[@id='firstName']")).sendKeys("Tester");
        driver.findElement(By.xpath("//input[@id='lastName']")).sendKeys("Qa");
        driver.findElement(By.xpath("//span[contains(text(),'Save')]")).click();
        String name = driver.findElement(By.xpath("//input[@id='name']")).getAttribute("value");
        Assert.assertEquals(name, "Tester Qa");
        driver.quit();
    }

    @Test
    public void TestOriginCountry() {
        System.setProperty("webdriver.chrome.driver", "C:/Users/Kostya/Desktop/PythonProjects/ChromeDrive102/chromedriver.exe");
        ChromeDriver driver = new ChromeDriver();
        driver.get("https://skryabin.com/market/quote.html");

        driver.findElement(By.xpath("//input[@id='name']")).click();
        driver.findElement(By.xpath("//input[@id='firstName']")).sendKeys("Tester");
        driver.findElement(By.xpath("//input[@id='lastName']")).sendKeys("Qa");
        driver.findElement(By.xpath("//span[contains(text(),'Save')]")).click();
        Select countryorigin = new Select(driver.findElement(By.xpath("//select[@ng-model=\"formData.countryOfOrigin\"]")));
        countryorigin.selectByVisibleText("China");
        driver.findElement(By.xpath("//input[@ng-model=\"formData.username\"]")).sendKeys("Tester");
        driver.findElement(By.xpath("//input[@ng-model=\"formData.email\"]")).sendKeys("tester@gmail.com");
        driver.findElement(By.xpath("//input[@id='password']")).sendKeys("Tester");
        driver.findElement(By.xpath("//input[@id='confirmPassword']")).sendKeys("Tester");
        driver.findElement(By.xpath("//input[@ng-model=\"formData.agreedToPrivacyPolicy\"]")).click();
        driver.findElement(By.xpath("//button[@id='formSubmit']")).click();
        String selectedCountry = driver.findElement(By.xpath("//b[contains(text(),'China')]")).getText();
        Assert.assertEquals(selectedCountry, "China");
        driver.quit();
    }
}

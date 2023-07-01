package FleaMarketTests;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.StaleElementReferenceException;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;

import java.time.Duration;
import java.util.List;

public class ProductDetailsPageTest {

    @Test
    public void TestAllProductsHasDescription() {
        System.setProperty("webdriver.chrome.driver", "C:/Users/Kostya/Desktop/PythonProjects/ChromeDrive102/chromedriver.exe");
        ChromeDriver driver = new ChromeDriver();
        driver.get("");
        driver.manage().window().maximize();
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(20));


        List<WebElement> allproducts = driver.findElements(By.xpath("//div[@id='primary']/main/ul/li"));

        for (int i=0; i<allproducts.size(); i++)
        {
            try
            {
                allproducts.get(i).click();
                List<WebElement> productdescription = driver.findElements(By.xpath("//h2[contains(text(),'Description')]"));

                if (productdescription.size() > 0) {
                    boolean hasdescription = true;
                } else
                    System.out.println("This product dosen't has description" + driver.findElement(
                            By.xpath("//h1[@class=\"product_title entry-title\"]")).getText());
                driver.findElement(By.xpath("//div[@class=\"site-branding\"]")).click();
            }
            catch (StaleElementReferenceException e)
            {
                allproducts = driver.findElements(By.xpath("//div[@id='primary']/main/ul/li"));
                allproducts.get(i).click();
                List<WebElement> productdescription = driver.findElements(By.xpath("//h2[contains(text(),'Description')]"));
                boolean hasdescription = false;
                if (productdescription.size() > 0)
                    hasdescription = true;
                else
                    System.out.println("This product dosen't has description" + driver.findElement(
                            By.xpath("//h1[@class=\"product_title entry-title\"]")).getText());
                driver.findElement(By.xpath("//div[@class=\"site-branding\"]")).click();
            }
        }
        driver.quit();
    }
}

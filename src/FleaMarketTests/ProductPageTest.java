package FleaMarketTests;

import org.junit.jupiter.api.Assertions;
import org.openqa.selenium.By;
import org.openqa.selenium.StaleElementReferenceException;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.Test;

import java.time.Duration;
import java.util.List;

public class ProductPageTest {

    @Test

    public void TestImageIsDisplayed () {
        System.setProperty("webdriver.chrome.driver", "C:/Users/Kostya/Desktop/PythonProjects/ChromeDrive102/chromedriver.exe");
        ChromeDriver driver = new ChromeDriver();
        driver.get("");
        driver.manage().window().maximize();
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(20));

        List<WebElement> allproducts = driver.findElements(By.xpath("//ul[@id='menu-main']/li"));

        for (int i=0; i<allproducts.size(); i++)
            try
            {
                allproducts.get(i).click();
                List<WebElement> images = driver.findElements(By.xpath("//div[@id='primary']/main/ul/li"));
                boolean isdisplayed = false;
                for (WebElement image : images)
                    if (image.isDisplayed())
                        isdisplayed = true;
                    else
                    {
                        Assertions.assertTrue(isdisplayed);
                    }

            }
        catch (StaleElementReferenceException e)
        {
               allproducts = driver.findElements(By.xpath("//ul[@id='menu-main']/li"));
               allproducts.get(i).click();
                List<WebElement> images = driver.findElements(By.xpath("//div[@id='primary']/main/ul/li"));
                boolean isdisplayed = false;
                for (WebElement image : images)
                    if (image.isDisplayed())
                        isdisplayed = true;
                    else
                    {
                        Assertions.assertTrue(isdisplayed);
                    }
            }
        driver.quit();
    }

    @Test
    public void TestAddToCartFromPdp () throws Exception{
        System.setProperty("webdriver.chrome.driver", "C:/Users/Kostya/Desktop/PythonProjects/ChromeDrive102/chromedriver.exe");
        ChromeDriver driver = new ChromeDriver();
        driver.get("https://store-qa.portnov.com/");
        driver.manage().window().maximize();
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));

        List<WebElement> allproducts = driver.findElements(By.xpath("//div[@id='primary']/main/ul/li"));
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(2));

        for (int i=0; i<allproducts.size(); i++)
            try {
                allproducts.get(i).click();
                List<WebElement> outofstock = driver.findElements(By.xpath("//p[contains(text(),'Out of stock')]"));
                boolean isoutofstock = true;
                if (outofstock.size() > 0){
                    Assertions.assertTrue(isoutofstock);
                    driver.findElement(By.xpath("//div[@class=\"site-branding\"]/a")).click();
                }

                else {
                    driver.findElement(By.xpath("//button[contains(text(),'Add to cart')]")).click();
                    wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//div[@class=\"woocommerce-message\"]")));
                    WebElement sucessfuladdtocart = driver.findElement(By.xpath("//div[@class=\"woocommerce-message\"]"));
                    Assertions.assertTrue(sucessfuladdtocart.isDisplayed());
                    driver.findElement(By.xpath("//div[@class=\"site-branding\"]/a")).click();
                }
            }
        catch (StaleElementReferenceException e) {
                allproducts = driver.findElements(By.xpath("//div[@id='primary']/main/ul/li"));
                allproducts.get(i).click();
                List<WebElement> outofstock = driver.findElements(By.xpath("//p[contains(text(),'Out of stock')]"));
                boolean isoutofstock = true;
                if (outofstock.size() > 0) {
                    Assertions.assertTrue(isoutofstock);
                    driver.findElement(By.xpath("//div[@class=\"site-branding\"]/a")).click();
                }

                else {
                    driver.findElement(By.xpath("//button[contains(text(),'Add to cart')]")).click();
                    wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//div[@class=\"woocommerce-message\"]")));
                    WebElement sucessfuladdtocart = driver.findElement(By.xpath("//div[@class=\"woocommerce-message\"]"));
                    Assertions.assertTrue(sucessfuladdtocart.isDisplayed());
                    driver.findElement(By.xpath("//div[@class=\"site-branding\"]/a")).click();
                }
            }
        driver.quit();
    }
    @Test
    public void TestRelatedProductsDisplayed() {
        System.setProperty("webdriver.chrome.driver", "C:/Users/Kostya/Desktop/PythonProjects/ChromeDrive102/chromedriver.exe");
        ChromeDriver driver = new ChromeDriver();
        driver.get("https://store-qa.portnov.com/");
        driver.manage().window().maximize();
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));

        List<WebElement> allproducts = driver.findElements(By.xpath("//ul[@class=\"products columns-3\"]/li/a[1]"));

        for (int i=0; i<allproducts.size(); i++)
            try {
                boolean hasrelatedproducts = false;
                allproducts.get(i).click();
                List<WebElement> relatedproducts = driver.findElements(By.xpath("//ul[@class=\"products columns-3\"]/li/a[1]"));
                if (relatedproducts.size() > 0)
                    hasrelatedproducts = true;
                else
                    System.out.println("This product doesn't has related products" + driver.findElement(By.xpath("//h1[@class=\"product_title entry-title\"]")).getText());
                driver.findElement(By.xpath("//a[@class=\"custom-logo-link\"]")).click();

            } catch (StaleElementReferenceException e){
                allproducts = driver.findElements(By.xpath("//ul[@class=\"products columns-3\"]/li/a[1]"));
                allproducts.get(i).click();
                boolean hasrelatedproducts = false;
                List<WebElement> relatedproducts = driver.findElements(By.xpath("//ul[@class=\"products columns-3\"]/li/a[1]"));
                if (relatedproducts.size() > 0)
                    hasrelatedproducts = true;
                else
                    System.out.println("This product doesn't has related products" + driver.findElement(By.xpath("//h1[@class=\"product_title entry-title\"]")).getText());
                driver.findElement(By.xpath("//a[@class=\"custom-logo-link\"]")).click();
            }
        driver.quit();


    }
}

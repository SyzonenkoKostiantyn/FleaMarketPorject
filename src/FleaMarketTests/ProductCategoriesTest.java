package FleaMarketTests;

import org.junit.jupiter.api.Assertions;
import org.openqa.selenium.By;
import org.openqa.selenium.StaleElementReferenceException;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.Test;

import java.time.Duration;
import java.util.List;


public class ProductCategoriesTest {

    @Test
    public void TestCategoriesHasRightProducts() {
        System.setProperty("webdriver.chrome.driver", "C:/Users/Kostya/Desktop/PythonProjects/ChromeDrive102/chromedriver.exe");
        ChromeDriver driver = new ChromeDriver();
        driver.get("");
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(20));
        driver.manage().window().maximize();

        List<WebElement> allcategories = driver.findElements(By.xpath("//ul[@id='menu-main']/li"));

        for (int i=0; i<allcategories.size(); i++){
            try {
                allcategories.get(i).click();
                List<WebElement> products = driver.findElements(By.xpath("//main[@id='main']/ul/li"));
                String actualcategorie = driver.findElement(By.xpath("//main[@id='main']/ul/li")).getAttribute("class");
                String expectedcategorie = actualcategorie.substring(actualcategorie.indexOf("product_cat"), actualcategorie.indexOf("has-post")-1);
                List<String> categorie = products.stream().map(s -> s.getAttribute("class")).map(c -> c.substring(c.indexOf("product_cat"), c.indexOf("has-post")-1)).toList();
                for (String e: categorie)
                    Assertions.assertEquals(e, expectedcategorie);
            }
            catch (StaleElementReferenceException e) {
                allcategories = driver.findElements(By.xpath("//ul[@id='menu-main']/li"));
                allcategories.get(i).click();
                List<WebElement> products = driver.findElements(By.xpath("//main[@id='main']/ul/li"));
                String actualcategorie = driver.findElement(By.xpath("//main[@id='main']/ul/li")).getAttribute("class");
                String expectedcategorie = actualcategorie.substring(actualcategorie.indexOf("product_cat"), actualcategorie.indexOf("has-post")-1);
                List<String> categorie = products.stream().map(s -> s.getAttribute("class")).map(c -> c.substring(c.indexOf("product_cat"), c.indexOf("has-post")-1)).toList();
                for (String r: categorie)
                    Assertions.assertEquals(r, expectedcategorie);
            }
        }
        driver.quit();
    }
}

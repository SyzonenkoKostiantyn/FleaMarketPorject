package FleaMarketTests;

import org.junit.jupiter.api.Assertions;
import org.openqa.selenium.By;
import org.openqa.selenium.StaleElementReferenceException;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.Test;

import java.time.Duration;
import java.util.List;

public class AmountDisplayedSortingTabTest {


    @Test
    public void TestAmountDisplayedIsCorrect () {
        System.setProperty("webdriver.chrome.driver", "C:/Users/Kostya/Desktop/PythonProjects/ChromeDrive102/chromedriver.exe");
        ChromeDriver driver = new ChromeDriver();
        driver.get("https://store-qa.portnov.com/");
        driver.manage().window().maximize();
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(20));


        List<WebElement> allitems = driver.findElements(By.xpath("//ul[@class=\"products columns-3\"]/li"));
        Integer actualnumber = allitems.size();
        String numberdisplayed = driver.findElement(By.xpath("//p[@class=\"woocommerce-result-count\"]")).getText();
        Integer expectednumber = Integer.valueOf(numberdisplayed.replaceAll("[a-zA-Z]", "").trim());
        System.out.println(actualnumber);
        System.out.println(expectednumber);
        Assertions.assertEquals(actualnumber, expectednumber);
        driver.quit();
    }

    @Test
    public void TestAmountDIsplayedInAllCategories () {
        System.setProperty("webdriver.chrome.driver", "C:/Users/Kostya/Desktop/PythonProjects/ChromeDrive102/chromedriver.exe");
        ChromeDriver driver = new ChromeDriver();
        driver.get("https://store-qa.portnov.com/");
        driver.manage().window().maximize();
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(20));

        List<WebElement> allcategories = driver.findElements(By.xpath("//ul[@id='menu-main']/li"));

        for (int i=0; i<allcategories.size(); i++)
            try
            {
                allcategories.get(i).click();
                boolean no_items_in_categorie = true;
                List<WebElement> allitems = driver.findElements(By.xpath("//ul[@class=\"products columns-3\"]/li"));
                if (allitems.size() < 2){
                    System.out.println("Single item");
                    Assertions.assertTrue(no_items_in_categorie);
                }
                else
                {
                Integer actualnumber = allitems.size();
                String numberdisplayed = driver.findElement(By.xpath("//p[@class=\"woocommerce-result-count\"]")).getText();
                Integer expectednumber = Integer.valueOf(numberdisplayed.replaceAll("[a-zA-Z]", "").trim());
                System.out.println(actualnumber);
                Assertions.assertEquals(actualnumber, expectednumber);
                }
            }
        catch (StaleElementReferenceException e)
            {
                allcategories = driver.findElements(By.xpath("//ul[@id='menu-main']/li"));
                allcategories.get(i).click();
                boolean no_items_in_categorie = true;
                List<WebElement> allitems = driver.findElements(By.xpath("//ul[@class=\"products columns-3\"]/li"));
                if (allitems.size() < 2) {
                    System.out.println("Single item");
                    Assertions.assertTrue(no_items_in_categorie);
                }
                else
                {
                    Integer actualnumber = allitems.size();
                    String numberdisplayed = driver.findElement(By.xpath("//p[@class=\"woocommerce-result-count\"]")).getText();
                    Integer expectednumber = Integer.valueOf(numberdisplayed.replaceAll("[a-zA-Z]", "").trim());
                    System.out.println(actualnumber);
                    Assertions.assertEquals(actualnumber, expectednumber);
                }
            }
        driver.quit();
    }
}

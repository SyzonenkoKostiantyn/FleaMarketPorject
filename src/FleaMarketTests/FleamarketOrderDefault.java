package FleaMarketTests;

import org.antlr.v4.runtime.misc.IntegerList;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.Test;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.concurrent.TimeUnit;


public class FleamarketOrderDefault {

    @Test
    public void DefaultListTest() {
        System.setProperty("webdriver.chrome.driver", "C:/Users/Kostya/Desktop/PythonProjects/ChromeDrive102/chromedriver.exe");
        ChromeDriver driver = new ChromeDriver();
        driver.get("https://store-stage.portnov.com/");
        driver.manage().timeouts().implicitlyWait(20, TimeUnit.SECONDS);


        List<WebElement> allitems = driver.findElements(By.xpath("//body[1]/div[4]/div[2]/div[1]/div[2]/main[1]/ul[1]/li/a[1]/span[1]/span[1]/bdi"));
        List<String> itemnames = new ArrayList<String>();
        ArrayList<String> itemoriginal = new ArrayList<>(Arrays.asList("$249.00", "$529.00", "$1,799.00", "$699.00", "$999.00"));
        for (WebElement i : allitems)
            itemnames.add(i.getText());
        System.out.println(itemnames);
        Assert.assertEquals(itemoriginal, itemnames);
    }
}
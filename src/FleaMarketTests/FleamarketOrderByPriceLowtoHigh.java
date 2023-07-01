package FleaMarketTests;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.Select;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.util.*;
import java.util.concurrent.TimeUnit;
import java.util.stream.Collectors;

public class FleamarketOrderByPriceLowtoHigh {

    @Test
    public void TestOrderByPrice () {
        System.setProperty("webdriver.chrome.driver", "C:/Users/Kostya/Desktop/PythonProjects/ChromeDrive102/chromedriver.exe");
        ChromeDriver driver = new ChromeDriver();
        driver.get("https://store-qa.portnov.com/");
        driver.manage().timeouts().implicitlyWait(20, TimeUnit.SECONDS);

        Select orderby = new Select(driver.findElement(By.xpath("//select[@class=\"orderby\"]")));
        orderby.selectByVisibleText("Sort by price: low to high");
        List<WebElement> items = driver.findElements(By.xpath("//body[1]/div[4]/div[2]/div[1]/div[2]/main[1]/ul[1]/li/a[1]/span[1]/span[1]/bdi"));
        ArrayList<String> pricesoriginal = new ArrayList<>();
        List<String> actualprices = items.stream().map(s -> s.getText().replaceAll("[$,.00]", "")).toList();
        List<Integer> actalpriceInt = actualprices.stream().map(Integer::parseInt).toList();

//        List<String> price1 = items.stream().map(WebElement::getText).toList();

//        for (WebElement e: items) {
//            pricesoriginal.add(e.getText().replaceAll("[$,.00]", ""));
//
//        }
        System.out.println(actalpriceInt);
        List<Integer> sortedprices = actalpriceInt.stream().sorted().collect(Collectors.toList());
        System.out.println(sortedprices);
        Assert.assertEquals(sortedprices, actalpriceInt);




    }
}

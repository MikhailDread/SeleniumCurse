import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import java.util.*;
import java.util.concurrent.TimeUnit;

public class CountryTest {
    private WebDriver driver;
    private String baseUrl;

    @BeforeClass(alwaysRun = true)
    public void setUp() throws Exception {
        driver = new ChromeDriver();
        baseUrl = "https://www.google.com/";
        driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
    }

    @Test
    public void countryTest() throws Exception {
        driver.get("http://localhost/litecart/admin/?app=countries&doc=countries");
        driver.findElement(By.name("username")).sendKeys("admin");
        driver.findElement(By.name("password")).sendKeys("admin");
        driver.findElement(By.name("login")).click();
        List<WebElement> list = driver.findElements(By.cssSelector("tr.row"));
        List<String> unsortedCountry = new ArrayList<>();
        List<String> sortedCountry = new ArrayList<>();
        for (WebElement l : list) {
            sortedCountry.add(l.findElement(By.cssSelector("td a")).getText());
            unsortedCountry.add(l.findElement(By.cssSelector("td a")).getText());
        }
        Collections.sort(sortedCountry);
        for (String k : sortedCountry) {
            // System.out.println(k); // Вывод сортированного списка
        }
        System.out.println("------------------------");
        for (String i : unsortedCountry) {
            // System.out.println(i); // Вывод не сортированного списка
        }

        Assert.assertEquals(sortedCountry, unsortedCountry);// Сравнение их

        List<String> countryWithZone = new ArrayList<>();
        List<WebElement> zone = new ArrayList<>();
        List<String> unsortedZone = new ArrayList<>();
        List<String> sortedZone = new ArrayList<>();
        for (WebElement i : list) {
            int zoneScore = Integer.parseInt(i.findElement(By.cssSelector("td:nth-child(6)")).getText());
            if (zoneScore > 0) {
                countryWithZone.add(i.findElement(By.cssSelector("td:nth-child(5)")).getText());
            }
        }

        countryZ:
        for (String q : countryWithZone) {
            List<WebElement> country2 = driver.findElements(By.cssSelector("tr.row"));
            for (WebElement l : country2) {
                if (q.equals(l.findElement(By.cssSelector("td a")).getText())) {
                    l.findElement(By.cssSelector("td a")).click();
                    zone = driver.findElements(By.cssSelector("table#table-zones.dataTable tr:not(.header)>td:nth-child(3)"));
                    zone.forEach(z->unsortedZone.add(z.getText()));
                    zone.forEach(z->sortedZone.add(z.getText()));
                    driver.get("http://localhost/litecart/admin/?app=countries&doc=countries");
                    continue countryZ;
                }
            }
        }

        Assert.assertEquals(sortedZone, unsortedZone);
    }

    @Test
    public void ZoneTest() throws InterruptedException {
        driver.get("http://localhost/litecart/admin/?app=geo_zones&doc=geo_zones");
        driver.findElement(By.name("username")).sendKeys("admin");
        driver.findElement(By.name("password")).sendKeys("admin");
        driver.findElement(By.name("login")).click();
        List<String> table = new ArrayList<>();
        List<WebElement> list = driver.findElements(By.cssSelector("table.dataTable tr.row"));
        List<WebElement> zone = new ArrayList<>();
        List<String> unsortedZone = new ArrayList<>();
        List<String> sortedZone = new ArrayList<>();
        for(WebElement k : list){
            table.add(k.findElement(By.cssSelector("td:nth-child(3) a")).getText());
        }
        zoneZ:for(String e : table){
            List<WebElement> list1 = driver.findElements(By.cssSelector("table.dataTable tr.row"));
            for(WebElement k : list1){
                k.findElement(By.cssSelector("td:nth-child(3) a")).click();
                zone = driver.findElements(By.cssSelector("table#table-zones.dataTable tr:not(.header)>td:nth-child(3)"));
                zone.forEach(z->unsortedZone.add(z.getText()));
                zone.forEach(z->sortedZone.add(z.getText()));
                driver.get("http://localhost/litecart/admin/?app=countries&doc=countries");
                continue zoneZ;
            }
        }

        Assert.assertEquals(sortedZone, unsortedZone);

    }

    @AfterClass(alwaysRun = true)
    public void tearDown() throws Exception {
        driver.quit();
    }

    public boolean isElementPresent(By by) {
        try {
            driver.findElement(by);
            return true;
        } catch (NoSuchElementException e) {
            return false;
        }
    }
}

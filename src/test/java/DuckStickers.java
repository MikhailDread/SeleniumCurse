import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeUnit;

public class DuckStickers {
    private WebDriver driver;
    private String baseUrl;

    @BeforeClass(alwaysRun = true)
    public void setUp() throws Exception {
        driver = new ChromeDriver();
        baseUrl = "https://www.google.com/";
        driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
    }

    @Test
    public void openYa() throws Exception {
        driver.get("http://localhost/litecart");
        ArrayList<WebElement> uls=(ArrayList<WebElement>) driver.findElements(By.cssSelector("ul.listing-wrapper.products"));
        for(WebElement ul:uls)
        {
            ArrayList<WebElement> lis=(ArrayList<WebElement>) ul.findElements(By.cssSelector("li"));
            for(WebElement li:lis)
            {
                List<WebElement> stickers=li.findElements(By.cssSelector("div.sticker"));
                System.out.println(li.findElement(By.cssSelector("a.link")).getAttribute("title")+" Стикеры: "+stickers.size());
                assert (stickers.size()==1);
            }
        }
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

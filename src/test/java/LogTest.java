import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import java.util.List;
import java.util.concurrent.TimeUnit;

public class LogTest {
    private WebDriver driver;
    private String baseUrl;

    @BeforeClass(alwaysRun = true)
    public void setUp() throws Exception {
        driver = new ChromeDriver();
        baseUrl = "https://www.google.com/";
        driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
    }

    @Test
    public void logTest() throws InterruptedException {
        driver.get("http://localhost/litecart/admin/?app=catalog&doc=catalog&category_id=1");
        driver.findElement(By.name("username")).sendKeys("admin");
        driver.findElement(By.name("password")).sendKeys("admin");
        driver.findElement(By.name("login")).click();
        List<WebElement> elements = driver.findElements(By.cssSelector("tr.row>td:nth-child(3)>a"));
        for(int i = 0; i < elements.size(); i++) {
            driver.get("http://localhost/litecart/admin/?app=catalog&doc=catalog&category_id=1");
            elements = driver.findElements(By.cssSelector("tr.row>td:nth-child(3)>a"));
            elements.get(i).click();
            assert (driver.manage().logs().get("browser").getAll().isEmpty());
            assert (driver.manage().logs().get("driver").getAll().isEmpty());
            assert (driver.manage().logs().get("client").getAll().isEmpty());
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

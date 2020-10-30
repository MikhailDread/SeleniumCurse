import org.checkerframework.checker.units.qual.A;
import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import java.util.concurrent.TimeUnit;

public class NewPage {

    private WebDriver driver;
    private String baseUrl;
    private WebDriverWait wait;

    @BeforeClass(alwaysRun = true)
    public void setUp() throws Exception {
        driver = new ChromeDriver();
        baseUrl = "https://www.google.com/";
        driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
        wait = new WebDriverWait(driver, 40);
    }

    @Test
    public void pageTest() throws InterruptedException {
        driver.get("http://localhost/litecart/admin/?app=countries&doc=countries");
        driver.findElement(By.name("username")).sendKeys("admin");
        driver.findElement(By.name("password")).sendKeys("admin");
        driver.findElement(By.name("login")).click();
        WebElement country = driver.findElement(By.cssSelector("table.dataTable tr.row:nth-child(6)"));
        country.findElement(By.cssSelector("td:nth-child(7)")).click();
        List<WebElement> list = driver.findElements(By.cssSelector("tr td i.fa.fa-external-link"));
        for (WebElement i : list){
            String oldHandle = driver.getWindowHandle();
            i.click();
            ArrayList<String> handles = new ArrayList<String>(driver.getWindowHandles());
            assert (handles.size() > 0);
            driver.switchTo().window(handles.get(1)).close();
            driver.switchTo().window(oldHandle);
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

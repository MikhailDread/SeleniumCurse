import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import java.util.List;
import java.util.concurrent.TimeUnit;

public class AddNewGoods {
    private WebDriver driver;
    private String baseUrl;
    private WebDriverWait wait;

    @BeforeClass(alwaysRun = true)
    public void setUp() throws Exception {
        driver = new ChromeDriver();
        baseUrl = "https://www.google.com/";
        driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
        wait = new WebDriverWait(driver, 10);
    }

    @Test
    public void AddNewGoods() throws Exception {
        driver.get("http://localhost/litecart/admin/");
        driver.findElement(By.name("username")).sendKeys("admin");
        driver.findElement(By.name("password")).sendKeys("admin");
        driver.findElement(By.name("login")).click();
        wait.until(ExpectedConditions.elementToBeClickable(driver.findElement(By.cssSelector("li#app-:nth-child(2)"))));
        driver.findElement(By.cssSelector("li#app-:nth-child(2)")).click();
        driver.findElement(By.cssSelector("a.button")).click();
        driver.findElement(By.cssSelector("name=name[en]]")).sendKeys("testProduct");
        driver.findElement(By.cssSelector("[name=code]")).sendKeys("11111");
        driver.findElements(By.cssSelector("input[type='checkbox']")).forEach(a->{if(!a.isSelected()){a.click();}});
        driver.findElement(By.cssSelector("[name=quantity]")).sendKeys("20");
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

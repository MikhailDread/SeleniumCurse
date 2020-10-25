import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import java.util.List;
import java.util.concurrent.TimeUnit;

import static org.openqa.selenium.support.ui.ExpectedConditions.visibilityOf;

public class AddBasket {
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
    public void addBasket() throws InterruptedException {
        for (int i = 0; i < 3; i++) {
            driver.get("http://localhost/litecart/en/");
            int numOfItems = Integer.parseInt(driver.findElement(By.cssSelector("div#cart span.quantity")).getText());

            driver.manage().timeouts().implicitlyWait(0, TimeUnit.SECONDS);
            driver.findElement(By.cssSelector("ul.listing-wrapper.products li")).click();
            if (driver.findElements(By.cssSelector("select[name='options[Size]']")).size() > 0) {
                Select size = new Select(driver.findElement(By.cssSelector("select[name='options[Size]']")));
                size.selectByIndex(1);
            }
            driver.manage().timeouts().implicitlyWait(3, TimeUnit.SECONDS);
            driver.findElement(By.cssSelector("button[name='add_cart_product']")).click();

            int quantityOfItems = Integer.parseInt(driver.findElement(By.cssSelector("input[name='quantity']")).getAttribute("value"));
            wait.until(d -> d.findElement(By.cssSelector("div#cart span.quantity")).getText().equals("" + (numOfItems + quantityOfItems)));
        }
        driver.get("http://localhost/litecart/en/");
        driver.findElement(By.cssSelector("div#cart a.link")).click();

        while (driver.findElements(By.cssSelector("button[name='remove_cart_item']")).size() > 0) {
            WebElement div = driver.findElement(By.cssSelector("div#order_confirmation-wrapper"));
            WebElement tr = div.findElement(By.cssSelector("tr:not(.header)"));
            driver.findElement(By.cssSelector("button[name='remove_cart_item']")).click();
            wait.until(ExpectedConditions.stalenessOf(tr));
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

import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;
import sun.awt.windows.ThemeReader;

import java.io.File;
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
        driver.findElement(By.cssSelector("a.button:nth-child(2)")).click();
        String name = "testProduct";
        Thread.sleep(3000);
        driver.findElement(By.cssSelector("input[name='name[en]']")).sendKeys(name);
        driver.findElement(By.cssSelector("[name=code]")).sendKeys("11111");
        driver.findElements(By.cssSelector("input[type=checkbox]")).forEach(a->{if(!a.isSelected()){a.click();}});
        driver.findElement(By.cssSelector("[name=quantity]")).sendKeys("20");
        driver.findElement(By.cssSelector("div.input-wrapper tr:nth-child(4)")).click();
        new Select(driver.findElement(By.xpath("//select[@name='sold_out_status_id']"))).selectByValue("2");
        File file = new File("./src/test/resources/box.jpg");
        driver.findElement(By.cssSelector("input [name='new_images[]']")).sendKeys(file.getAbsolutePath());
        WebElement datepickerFrom = driver.findElement(By.cssSelector("input[name='date_valid_from']"));
        datepickerFrom.sendKeys(Keys.HOME+"30082017");
        wait.until(ExpectedConditions.elementToBeClickable(driver.findElement(By.cssSelector("input[name='date_valid_to']"))));
        WebElement datepickerTo = driver.findElement(By.cssSelector("input[name='date_valid_to']"));
        datepickerTo.sendKeys(Keys.HOME+"25092017");

        //Information
        driver.findElement(By.cssSelector("ul.index li:nth-child(2)")).click();
        new Select(driver.findElement(By.xpath("//select[@name='manufacturer_id']"))).selectByValue("1");
        driver.findElement(By.cssSelector("input[name=keywords]")).sendKeys("box");
        driver.findElement(By.cssSelector("input[name='short_description[en]']")).sendKeys("box");
        driver.findElement(By.cssSelector("div.trumbowyg-editor")).sendKeys("This is box");
        driver.findElement(By.cssSelector("input[name='head_title[en]']")).sendKeys("TestBox");
        driver.findElement(By.cssSelector("input[name='meta_description[en]']")).sendKeys("TestBox");

        //Prices
        driver.findElement(By.cssSelector("ul.index li:nth-child(4)")).click();
        driver.findElement(By.cssSelector("input[name=purchase_price]")).sendKeys("1");
        new Select(driver.findElement(By.xpath("//select[@name='purchase_price_currency_code']"))).selectByValue("EUR");
        driver.findElement(By.cssSelector("input[name='prices[USD]']")).sendKeys("1");
        driver.findElement(By.cssSelector("input[name='prices[EUR]']")).sendKeys("1");
        driver.findElement(By.cssSelector("input[name='gross_prices[USD]']")).sendKeys("1");
        driver.findElement(By.cssSelector("input[name='gross_prices[EUR]']")).sendKeys("1");
        driver.findElement(By.xpath("//button[@value='Save']")).click();
        List <WebElement> list = driver.findElements(By.cssSelector("tr.row"));
        boolean found = false;
        for(WebElement i : list){
            if(i.findElement(By.cssSelector("td:nth-child(3) a")).getText().equals(name)){
                found = true;
            }
        }
        assert (found);
        Thread.sleep(3000);
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

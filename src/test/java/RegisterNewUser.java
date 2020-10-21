import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import java.util.List;
import java.util.Random;
import java.util.concurrent.TimeUnit;

public class RegisterNewUser {
        private WebDriver driver;
        private String baseUrl;
        private WebDriverWait wait;

        @BeforeClass(alwaysRun = true)
        public void setUp() throws Exception {
            driver = new ChromeDriver();
            wait=new WebDriverWait(driver,1);
            baseUrl = "https://www.google.com/";
            driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
        }

        @Test
        public void register() throws Exception {
            driver.get("http://localhost/litecart/");
            WebElement createAccountLink = driver.findElement(By.cssSelector("td.account li:nth-child(3)"));
            createAccountLink.click();
            driver.findElement(By.cssSelector("input[name=firstname]")).sendKeys("Mikhail");
            driver.findElement(By.cssSelector("input[name=lastname]")).sendKeys("Van");
            driver.findElement(By.cssSelector("input[name=address1]")).sendKeys("street Pushkin 1");
            driver.findElement(By.cssSelector("input[name=postcode]")).sendKeys("12345");
            driver.findElement(By.cssSelector("input[name=city]")).sendKeys("Moscow");
            driver.findElement(By.cssSelector("span.select2-selection.select2-selection--single")).click();
            Select selectCountry = new Select(driver.findElement(By.cssSelector("select[name='country_code']")));
            selectCountry.selectByValue("US");
            Select selectZone=new Select(driver.findElement(By.cssSelector("select[name='zone_code']")));
            selectZone.selectByValue("HI");
            String email = randomEmail();
            driver.findElement(By.cssSelector("input[name=email]")).sendKeys(email);
            driver.findElement(By.cssSelector("input[name=phone]")).sendKeys("12345");
            driver.findElement(By.cssSelector("input[name=password]")).sendKeys("test111");
            driver.findElement(By.cssSelector("input[name=confirmed_password]")).sendKeys("test111");
            driver.findElement(By.cssSelector("button[name=create_account]")).click();
            driver.findElement(By.cssSelector("td.account li:last-child a")).click();
            driver.findElement(By.cssSelector("input[name=email]")).sendKeys(email);
            driver.findElement(By.cssSelector("input[name=password]")).sendKeys("test111");
            driver.findElement(By.cssSelector("button[value=Login]")).click();
            driver.findElement(By.cssSelector("td.account li:last-child a")).click();

        }

        public static String randomEmail(){
            int r = (int) (Math.random() * 100);
            String e = "Qtest" + r + "@yandex.ru";
            return e;
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

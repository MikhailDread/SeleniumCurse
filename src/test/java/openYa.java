import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.events.WebDriverEventListener;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import java.util.List;
import java.util.concurrent.TimeUnit;

public class openYa {
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
            driver.get("http://localhost/litecart/admin/");
            driver.findElement(By.name("username")).sendKeys("admin");
            driver.findElement(By.name("password")).sendKeys("admin");
            driver.findElement(By.name("login")).click();
            List<WebElement> list = driver.findElements(By.cssSelector("#app-"));
            for(int i = 1; i < list.size() + 1; i++){
                driver.findElement(By.cssSelector("li#app-:nth-child(" + i + ")")).click();
                isElementPresent(By.cssSelector("h1"));
                    List<WebElement> list1 = driver.findElement(By.cssSelector("li#app-:nth-child(" + i + ")")).findElements(By.cssSelector("li"));
                    if(list1.size() > 0) {
                        for (int j = 2; j < list1.size() + 1; j++) {
                            driver.findElement(By.cssSelector("li:nth-child(" + j + ")")).click();
                            isElementPresent(By.cssSelector("h1"));
                        }
                    }
                    else i++;
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



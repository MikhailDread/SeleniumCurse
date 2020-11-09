package HelperClasses;

import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;

import java.util.concurrent.TimeUnit;

public class ApplicationManager {
    private WebDriver driver;
    private String baseUrl;
    private WebDriverWait wait;
    private AddToCart atc;
    private GoodsPage gp;
    private RemoveOfCart roc;

    @BeforeClass(alwaysRun = true)
    public void setUp() throws Exception {
        driver = new ChromeDriver();
        baseUrl = "https://www.google.com/";
        driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
        wait = new WebDriverWait(driver, 10);
        roc = new RemoveOfCart(driver, wait);
        gp = new GoodsPage(driver, wait);
        atc = new AddToCart(driver);
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

    public AddToCart getAtc() {
        return atc;
    }

    public GoodsPage getGp() {
        return gp;
    }

    public RemoveOfCart getRoc() {
        return roc;
    }

}

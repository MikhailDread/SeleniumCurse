import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import java.util.concurrent.TimeUnit;
import java.util.regex.Matcher;

public class CheckPage {
    private WebDriver driver;
    private String baseUrl;

    @BeforeClass(alwaysRun = true)
    public void setUp() throws Exception {
        driver = new ChromeDriver();
        baseUrl = "https://www.google.com/";
        driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
    }

    @Test
    public void CheckPage() throws Exception {
        driver.get("http://localhost/litecart");
        WebElement element = driver.findElement(By.cssSelector("div#box-campaigns"));
        String productName = element.findElement(By.cssSelector("div.name")).getText();
        String regularPrice = element.findElement(By.cssSelector("s.regular-price")).getText();
        String salePrice = element.findElement(By.cssSelector("strong.campaign-price")).getText();
        String saleColor = element.findElement(By.cssSelector("strong.campaign-price")).getCssValue("color");
        String regularColor = element.findElement(By.cssSelector("s.regular-price")).getCssValue("color");
        Dimension saleSize = element.findElement(By.cssSelector("strong.campaign-price")).getSize();
        Point saleLocation = element.findElement(By.cssSelector("strong.campaign-price")).getLocation();
        Dimension regularSize = element.findElement(By.cssSelector("s.regular-price")).getSize();
        Point regularLocation = element.findElement(By.cssSelector("s.regular-price")).getLocation();
        
        DuckModel duckModelInMainPage = new DuckModel().withName(productName).withSalePrice(salePrice).
                withRegularPrice(regularPrice).withSaleColor(saleColor).withRegularColor(regularColor).
                withSaleSize(saleSize).withRegularSize(regularSize).withSaleLocation(saleLocation).withRegularLocation(regularLocation);
        System.out.println(duckModelInMainPage.passTestSale(saleColor)); //проверяем, что у сейл утки G и В ноль
        System.out.println(duckModelInMainPage.passTestRegular(regularColor)); // проверяем, что у регулар утки R, G и В одинаковые

        driver.findElement(By.id("box-campaigns")).findElement(By.cssSelector("div.image-wrapper")).click();
        WebElement elementIn = driver.findElement(By.cssSelector("div#box-product"));
        String productNameIn = elementIn.findElement(By.cssSelector("h1.title")).getText();
        String salePriceIn = elementIn.findElement(By.xpath("//strong[@class='campaign-price']")).getText();
        String regularPriceIn = elementIn.findElement(By.xpath("//s[@class='regular-price']")).getText();
        String saleColorIn = elementIn.findElement(By.xpath("//strong[@class='campaign-price']")).getCssValue("color");
        String regularColorIn = elementIn.findElement(By.xpath("//s[@class='regular-price']")).getCssValue("color");
        Dimension saleSizeIn = elementIn.findElement(By.xpath("//strong[@class='campaign-price']")).getSize();
        Dimension regularSizeIn = elementIn.findElement(By.xpath("//s[@class='regular-price']")).getSize();
        Point saleLocationIn = elementIn.findElement(By.xpath("//strong[@class='campaign-price']")).getLocation();
        Point regularLocationIn = elementIn.findElement(By.xpath("//s[@class='regular-price']")).getLocation();

        DuckModel duckModelInClickPage = new DuckModel().withName(productNameIn).withSalePrice(salePriceIn).
                withRegularPrice(regularPriceIn).withSaleColor(saleColorIn).withRegularColor(regularColorIn).
                withSaleSize(saleSizeIn).withRegularSize(regularSizeIn).withSaleLocation(saleLocationIn).
                withRegularLocation(regularLocationIn);
        System.out.println(duckModelInClickPage.passTestSale(saleColorIn)); //проверяем, что у сейл утки G и В ноль
        System.out.println(duckModelInClickPage.passTestRegular(regularColorIn)); // проверяем, что у регулар утки R, G и В одинаковые

        assert (duckModelInClickPage.getName().equals(duckModelInMainPage.getName())); //и тд
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

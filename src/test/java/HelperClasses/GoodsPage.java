package HelperClasses;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.util.concurrent.TimeUnit;

public class GoodsPage{

    WebDriver driver;
    WebDriverWait wait;

    public GoodsPage(WebDriver driver, WebDriverWait wait){
        this.driver = driver;
        this.wait = wait;
    }
    public void addInCart() {
        driver.manage().timeouts().implicitlyWait(3, TimeUnit.SECONDS);
        driver.findElement(By.cssSelector("button[name='add_cart_product']")).click();
    }

    public void quanityScore(int numOfItems) {
        int quantityOfItems = Integer.parseInt(driver.findElement(By.cssSelector("input[name='quantity']")).getAttribute("value"));
        wait.until(d -> d.findElement(By.cssSelector("div#cart span.quantity")).getText().equals("" + (numOfItems + quantityOfItems)));
    }

    public int getNumOfItems() {
        return Integer.parseInt(driver.findElement(By.cssSelector("div#cart span.quantity")).getText());
    }

    public void checkDuckSize() {
        if (driver.findElements(By.cssSelector("select[name='options[Size]']")).size() > 0) {
            Select size = new Select(driver.findElement(By.cssSelector("select[name='options[Size]']")));
            size.selectByIndex(1);
        }
    }
}

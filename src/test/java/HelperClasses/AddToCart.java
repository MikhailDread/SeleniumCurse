package HelperClasses;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

import java.util.concurrent.TimeUnit;

public class AddToCart{
    WebDriver driver;

    public AddToCart(WebDriver driver){
        this.driver = driver;
    }

    public void clickGoodsInMainPage() {
        driver.manage().timeouts().implicitlyWait(0, TimeUnit.SECONDS);
        driver.findElement(By.cssSelector("ul.listing-wrapper.products li")).click();
    }

    public void goToTheCart() {
        driver.findElement(By.cssSelector("div#cart a.link")).click();
    }

}

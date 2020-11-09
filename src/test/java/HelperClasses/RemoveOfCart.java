package HelperClasses;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class RemoveOfCart{

    WebDriver driver;
    WebDriverWait wait;

    public RemoveOfCart(WebDriver driver, WebDriverWait wait){
        this.driver = driver;
        this.wait = wait;
    }

    public void removeOfCart() {
        while (driver.findElements(By.cssSelector("button[name='remove_cart_item']")).size() > 0) {
            WebElement div = driver.findElement(By.cssSelector("div#order_confirmation-wrapper"));
            WebElement tr = div.findElement(By.cssSelector("tr:not(.header)"));
            driver.findElement(By.cssSelector("button[name='remove_cart_item']")).click();
            wait.until(ExpectedConditions.stalenessOf(tr));
        }
    }
}

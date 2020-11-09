import HelperClasses.ApplicationManager;
import org.openqa.selenium.WebDriver;
import org.testng.annotations.Test;

public class AddBasket {
    ApplicationManager ap = new ApplicationManager();
    WebDriver driver;


    @Test
    public void addBasket() throws InterruptedException {
        for (int i = 0; i < 3; i++) {
            driver.get("http://localhost/litecart/en/");
            int numOfItems = ap.getGp().getNumOfItems();

            ap.getAtc().clickGoodsInMainPage();
            ap.getGp().checkDuckSize();
            ap.getGp().addInCart();

            ap.getGp().quanityScore(numOfItems);
        }
        driver.get("http://localhost/litecart/en/");
        ap.getAtc().goToTheCart();

        ap.getRoc().removeOfCart();
    }

}

package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class inventoryPage {

    private WebDriver driver;
    private WebDriverWait wait;

//    private By pageTitle = By.cssSelector(".title");
    private By pageTitle = By.cssSelector("head title");
    private By cartIcon = By.cssSelector(".shopping_cart_link");
    private By products = By.cssSelector(".inventory_item");


    public inventoryPage(WebDriver driver) {
        this.driver = driver;
        this.wait = new WebDriverWait(driver, Duration.ofSeconds(10));
    }

//    public String getPageTitle() {
//
//        return wait.until(
//                ExpectedConditions.visibilityOfElementLocated(pageTitle)
//        ).getText();
//    }
    public String getPageTitle() {
        return wait.until(ExpectedConditions.presenceOfElementLocated(pageTitle)).getAttribute("textContent");
    }

    public boolean isCartIconDisplayed() {

        return wait.until(ExpectedConditions.visibilityOfElementLocated(cartIcon)).isDisplayed();
    }

    public int getNumberOfProducts() {

        return wait.until(ExpectedConditions.presenceOfAllElementsLocatedBy(products)).size();
    }
}
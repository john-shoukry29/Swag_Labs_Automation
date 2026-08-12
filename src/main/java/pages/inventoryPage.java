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
    private By facebookIcon = By.cssSelector("[data-test='social-facebook']");
    private By linkedInIcon = By.cssSelector("[data-test='social-linkedin']");
    private By twitterIcon = By.cssSelector("[data-test='social-twitter']");
    private By menuButton = By.id("react-burger-menu-btn");
    private By logoutButton = By.id("logout_sidebar_link");



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
    public void addProduct(String productName) {

        By addButton = By.xpath(
                "//div[@data-test='inventory-item' and .//*[contains(@class,'inventory_item_name') and text()='"
                        + productName
                        + "']]//button[starts-with(@data-test,'add-to-cart-')]"
        );

        wait.until(ExpectedConditions.elementToBeClickable(addButton)).click();
    }

    public void clickFacebook() {
        wait.until(ExpectedConditions.elementToBeClickable(facebookIcon)).click();
    }

    public void clickLinkedIn() {
        wait.until(ExpectedConditions.elementToBeClickable(linkedInIcon)).click();
    }

    public void clickTwitter() {
        wait.until(ExpectedConditions.elementToBeClickable(twitterIcon)).click();
    }

    public void openCart() {
        wait.until(ExpectedConditions.elementToBeClickable(cartIcon)).click();
    }


    public String getProductButtonText(String productName) {

        By productButton = By.xpath(
                "//div[@data-test='inventory-item' and .//*[contains(@class,'inventory_item_name') and text()='"
                        + productName
                        + "']]//button"
        );

        return wait.until(ExpectedConditions.visibilityOfElementLocated(productButton)).getText();
    }

    public String getProductPrice(String productName) {

        By productPrice = By.xpath(
                "//div[@data-test='inventory-item' and .//*[contains(@class,'inventory_item_name') and text()='"
                        + productName
                        + "']]//div[@data-test='inventory-item-price']"
        );

        return wait.until(ExpectedConditions.visibilityOfElementLocated(productPrice)).getText();
    }

    public void logout() {
        // open side bar
        wait.until(ExpectedConditions.elementToBeClickable(menuButton)).click();
        // press the logout button
        wait.until(ExpectedConditions.elementToBeClickable(logoutButton)).click();
    }
}
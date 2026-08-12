package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;
import java.util.ArrayList;
import java.util.List;

public class CartPage {
    private WebDriver driver;
    private WebDriverWait wait;

    private By cartItem = By.cssSelector("[data-test='inventory-item']");

    private By cartItemName = By.cssSelector("[data-test='inventory-item'] .inventory_item_name");

    private By checkoutButton = By.cssSelector("[data-test='checkout']");

    private By continueShoppingButton = By.cssSelector("[data-test='continue-shopping']");


    public CartPage(WebDriver driver){
        this.driver=driver;
        this.wait = new WebDriverWait(driver, Duration.ofSeconds(10));
    }

    public int getCartItemCount() {
        return driver.findElements(cartItem).size();
    }
    public List<String> getCartItemNames(){
        List<WebElement> elements= driver.findElements(cartItemName);
        List<String> names= new ArrayList<>();
        for(WebElement element:elements){
            names.add(element.getText());
        }
        return names;
    }

    public void clickCheckout(){
        wait.until(ExpectedConditions.elementToBeClickable(checkoutButton)).click();
    }
    public void clickContinueShopping(){
        wait.until(ExpectedConditions.elementToBeClickable(continueShoppingButton)).click();
    }
    public void removeProduct(String productName) {

        By removeButton = By.xpath(
                "//div[@data-test='inventory-item' and .//*[contains(@class,'inventory_item_name') and text()='"
                        + productName
                        + "']]//button[starts-with(@data-test,'remove-')]"
        );

        wait.until(ExpectedConditions.elementToBeClickable(removeButton)).click();
    }
}

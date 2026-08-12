package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class CheckoutCompletePage {

    private WebDriver driver;
    private WebDriverWait wait;


    private By completeHeader = By.className("complete-header");
    private By backHomeButton = By.id("back-to-products");


    public CheckoutCompletePage(WebDriver driver) {

        this.driver = driver;
        this.wait = new WebDriverWait(driver, Duration.ofSeconds(10));
    }

    // Verify Order Completion
    public boolean isOrderCompleted() {

        return wait.until(ExpectedConditions.visibilityOfElementLocated(completeHeader)).isDisplayed();
    }

    // Read Success Message
    public String getSuccessMessage() {

        return wait.until(ExpectedConditions.visibilityOfElementLocated(completeHeader)).getText();
    }

    // Back Home
    public void clickBackHome() {

        wait.until(ExpectedConditions.elementToBeClickable(backHomeButton)).click();
    }
}
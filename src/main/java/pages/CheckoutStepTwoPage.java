package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class CheckoutStepTwoPage {

    private WebDriver driver;
    private WebDriverWait wait;


    private By itemTotal = By.className("summary_subtotal_label");
    private By finishButton = By.id("finish");
    private By cancelButton = By.id("cancel");


    public CheckoutStepTwoPage(WebDriver driver) {

        this.driver = driver;
        this.wait = new WebDriverWait(driver, Duration.ofSeconds(10));
    }

    // Read Item Total price
    public double getItemTotal() {

        String total = wait.until(ExpectedConditions.visibilityOfElementLocated(itemTotal)).getText();

        total = total.replace("Item total: $", "");

        return Double.parseDouble(total);
    }

    public void clickFinish() {

        wait.until(ExpectedConditions.elementToBeClickable(finishButton)).click();
    }


    public void clickCancel() {

        wait.until(ExpectedConditions.elementToBeClickable(cancelButton)).click();
    }
}
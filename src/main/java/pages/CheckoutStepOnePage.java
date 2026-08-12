package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class CheckoutStepOnePage {

    private WebDriver driver;
    private WebDriverWait wait;
    private By firstName = By.id("first-name");
    private By lastName = By.id("last-name");
    private By zipCode = By.id("postal-code");
    private By continueButton = By.id("continue");
    private By cancelButton = By.id("cancel");

    public CheckoutStepOnePage(WebDriver driver){

        this.driver = driver;
        this.wait = new WebDriverWait(driver,
                Duration.ofSeconds(10));

    }

    public void fillInformation(String firstName,
                                String lastName,
                                String zipCode) {

        wait.until(ExpectedConditions.visibilityOfElementLocated(this.firstName)).sendKeys(firstName);

        wait.until(ExpectedConditions.visibilityOfElementLocated(this.lastName)).sendKeys(lastName);

        wait.until(ExpectedConditions.visibilityOfElementLocated(this.zipCode)).sendKeys(zipCode);

    }

    public void clickContinue() {

        wait.until(ExpectedConditions.elementToBeClickable(continueButton)).click();
    }

    public void clickCancel() {

        wait.until(ExpectedConditions.elementToBeClickable(cancelButton)).click();

    }

}
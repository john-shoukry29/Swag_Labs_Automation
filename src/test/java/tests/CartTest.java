package tests;

import base.BaseTest;
import com.fasterxml.jackson.databind.JsonNode;
import org.testng.Assert;
import org.testng.annotations.Test;
import pages.CartPage;
import pages.inventoryPage;
import pages.loginPage;
import pages.CheckoutStepOnePage;
import pages.CheckoutStepTwoPage;

import utils.DataDriven;

import java.io.IOException;
import java.util.List;

public class CartTest extends BaseTest {

    // scenario 1
    @Test
    public void verifySocialLinks() throws IOException {

        JsonNode data = DataDriven.jsonReader();

        String username = data.get("validUser").get("username").asText();

        String password = data.get("validUser").get("password").asText();

        loginPage loginPage = new loginPage(driver);
        loginPage.login(username, password);

        inventoryPage inventoryPage =
                new inventoryPage(driver);


        String originalWindow = driver.getWindowHandle();

        // LinkedIn
        inventoryPage.clickLinkedIn();

        for (String window : driver.getWindowHandles()) {
            if (!window.equals(originalWindow)) {
                driver.switchTo().window(window);
                break;
            }
        }

        Assert.assertTrue(driver.getCurrentUrl().contains("linkedin"), "LinkedIn URL is incorrect");

        driver.close();
        driver.switchTo().window(originalWindow);


        // Facebook
        inventoryPage.clickFacebook();

        for (String window : driver.getWindowHandles()) {
            if (!window.equals(originalWindow)) {
                driver.switchTo().window(window);
                break;
            }
        }

        Assert.assertTrue(driver.getCurrentUrl().contains("facebook"), "Facebook URL is incorrect");

        driver.close();
        driver.switchTo().window(originalWindow);


        // X / Twitter
        inventoryPage.clickTwitter();

        for (String window : driver.getWindowHandles()) {
            if (!window.equals(originalWindow)) {
                driver.switchTo().window(window);
                break;
            }
        }

        Assert.assertTrue(driver.getCurrentUrl().contains("x.com"), "X/Twitter URL is incorrect");

        driver.close();
        driver.switchTo().window(originalWindow);
    }

    // scenario 2
    @Test
    public void verifyCartIsEmpty() throws IOException {

        // Read valid user from JSON
        JsonNode data = DataDriven.jsonReader();

        String username = data.get("validUser").get("username").asText();

        String password = data.get("validUser").get("password").asText();

        // Login
        loginPage loginPage = new loginPage(driver);

        loginPage.login(username, password);

        // Open cart
        inventoryPage inventoryPage = new inventoryPage(driver);

        inventoryPage.openCart();

        // Verify cart is empty
        CartPage cartPage = new CartPage(driver);

        Assert.assertEquals(
                cartPage.getCartItemCount(),
                0,
                "Cart is not empty"
        );
    }

    // scenario 3
    @Test
    public void verifyThreeProductsAreAddedToCart() throws IOException {

        JsonNode data = DataDriven.jsonReader();

        String username = data.get("validUser").get("username").asText();

        String password = data.get("validUser").get("password").asText();

        loginPage loginPage = new loginPage(driver);

        loginPage.login(username, password);

        inventoryPage inventoryPage = new inventoryPage(driver);

        List<String> products = DataDriven.getCartProducts();

        for (String product : products) {
            inventoryPage.addProduct(product);
        }

        inventoryPage.openCart();

        CartPage cartPage =
                new CartPage(driver);

        List<String> actualProducts =
                cartPage.getCartItemNames();

        Assert.assertEquals(
                actualProducts,
                products,
                "Cart products do not match the products from JSON"
        );
    }


    // scenario 4
    @Test
    public void verifyRemoveProduct() throws IOException {

        JsonNode data = DataDriven.jsonReader();

        String username = data.get("validUser").get("username").asText();

        String password = data.get("validUser").get("password").asText();

        loginPage loginPage = new loginPage(driver);

        loginPage.login(username, password);

        inventoryPage inventoryPage =
                new inventoryPage(driver);

        // Read products from JSON
        List<String> products =
                DataDriven.getCartProducts();

        // Add the 3 products
        for (String product : products) {
            inventoryPage.addProduct(product);
        }

        // Open cart
        inventoryPage.openCart();

        CartPage cartPage =
                new CartPage(driver);

        // Remove Bolt T-Shirt
        cartPage.removeProduct("Sauce Labs Bolt T-Shirt");

        // Go back to inventory
        cartPage.clickContinueShopping();

        // Verify button states
        Assert.assertEquals(
                inventoryPage.getProductButtonText("Sauce Labs Backpack"),
                "Remove",
                "Backpack should still be in the cart"
        );

        Assert.assertEquals(
                inventoryPage.getProductButtonText("Sauce Labs Bolt T-Shirt"),
                "Add to cart",
                "Bolt T-Shirt should have been removed from the cart"
        );

        Assert.assertEquals(
                inventoryPage.getProductButtonText("Sauce Labs Onesie"),
                "Remove",
                "Onesie should still be in the cart"
        );
    }

    // scenario 5
    @Test
    public void verifyCartTotalPrice() throws IOException {

        JsonNode data = DataDriven.jsonReader();

        String username = data.get("validUser").get("username").asText();

        String password = data.get("validUser").get("password").asText();

        loginPage loginPage = new loginPage(driver);
        loginPage.login(username, password);

        inventoryPage inventoryPage = new inventoryPage(driver);

        // Get products from JSON
        List<String> products = DataDriven.getCartProducts();

        // Read prices before adding products
        double expectedTotal = 0.0;

        for (String product : products) {

            String priceText = inventoryPage.getProductPrice(product);

            double price = Double.parseDouble(priceText.replace("$", ""));

            expectedTotal += price;
        }

        // Add the 3 products
        for (String product : products) {
            inventoryPage.addProduct(product);
        }

        // Open cart
        inventoryPage.openCart();

        CartPage cartPage = new CartPage(driver);

        cartPage.clickCheckout();

        // Checkout Step One
        CheckoutStepOnePage checkoutStepOnePage = new CheckoutStepOnePage(driver);

        checkoutStepOnePage.fillInformation(
                "John",
                "Shoukry",
                "0000"
        );

        checkoutStepOnePage.clickContinue();

        // Checkout Step Two
        CheckoutStepTwoPage checkoutStepTwoPage = new CheckoutStepTwoPage(driver);

        double actualTotal = checkoutStepTwoPage.getItemTotal();

        Assert.assertEquals(
                actualTotal,
                expectedTotal,
                0.01,
                "Item total does not match the calculated total"
        );
    }

    //Scenario 6 it will fail as the app allow checkout with empty cart
    @Test
    public void verifyCheckoutWithEmptyCart() throws IOException {

        JsonNode data = DataDriven.jsonReader();

        String username = data.get("validUser").get("username").asText();

        String password = data.get("validUser").get("password").asText();

        // Login
        loginPage loginPage = new loginPage(driver);
        loginPage.login(username, password);

        // Open cart
        inventoryPage inventoryPage = new inventoryPage(driver);

        inventoryPage.openCart();

        // Verify cart is empty
        CartPage cartPage = new CartPage(driver);

        Assert.assertEquals(
                cartPage.getCartItemCount(),
                0,
                "Cart should be empty before attempting checkout"
        );

        // Try to checkout
        cartPage.clickCheckout();

        // Application should NOT allow checkout with an empty cart
        Assert.assertFalse(
                driver.getCurrentUrl().contains("checkout-step-one.html"),
                "Application allows checkout with an empty cart"
        );
    }

    // scenario 7
    @Test
    public void verifyCartStateAfterLogoutLogin() throws IOException {

        JsonNode data = DataDriven.jsonReader();

        String username = data.get("validUser").get("username").asText();

        String password = data.get("validUser").get("password").asText();

        // Login
        loginPage loginPage = new loginPage(driver);
        loginPage.login(username, password);

        // Read products from JSON
        inventoryPage inventoryPage = new inventoryPage(driver);

        List<String> products = DataDriven.getCartProducts();

        // Add at least 2 products
        List<String> expectedProducts = products.subList(0, 2);

        for (String product : expectedProducts) {
            inventoryPage.addProduct(product);
        }

        // Logout
        inventoryPage.logout();

        // Login again with the same user
        loginPage.login(username, password);

        // Open cart
        inventoryPage.openCart();

        CartPage cartPage = new CartPage(driver);

        // Verify products are still in the cart
        List<String> actualProducts = cartPage.getCartItemNames();

        Assert.assertEquals(
                actualProducts,
                expectedProducts,
                "Cart items were not preserved after logout and login"
        );
    }
}
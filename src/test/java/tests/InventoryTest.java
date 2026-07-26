package tests;

import base.BaseTest;
import com.fasterxml.jackson.databind.JsonNode;
import org.testng.Assert;
import org.testng.annotations.Test;
import pages.inventoryPage;
import pages.loginPage;
import utils.DataDriven;

import java.io.IOException;

public class InventoryTest extends BaseTest {

    @Test
    public void verifyinventoryPageElements() throws IOException {

        JsonNode data = DataDriven.jsonReader();

        String username = data.get("validUser").get("username").asText();
        String password = data.get("validUser").get("password").asText();

        loginPage loginPage = new loginPage(driver);
        loginPage.login(username, password);

        inventoryPage inventoryPage = new inventoryPage(driver);

        Assert.assertEquals(inventoryPage.getPageTitle(), "Swag Labs", "Page title is incorrect");

        Assert.assertTrue(inventoryPage.isCartIconDisplayed(), "Cart icon is not displayed");

        Assert.assertEquals(inventoryPage.getNumberOfProducts(), 6, "The number of products is incorrect");
    }
}
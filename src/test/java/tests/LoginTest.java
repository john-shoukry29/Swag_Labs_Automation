package tests;

import base.BaseTest;
import com.fasterxml.jackson.databind.JsonNode;
import org.testng.Assert;
import org.testng.annotations.Test;
import pages.loginPage;
import utils.DataDriven;

import java.io.IOException;

public class LoginTest extends BaseTest {

    @Test
    public void verifySuccessfulLogin() throws IOException {

        JsonNode data = DataDriven.jsonReader();

        String username = data.get("validUser").get("username").asText();
        String password = data.get("validUser").get("password").asText();

        loginPage loginPage = new loginPage(driver);

        loginPage.login(username, password);

        Assert.assertTrue(
                driver.getCurrentUrl().contains("/inventory.html"),
                "User was not redirected to the Inventory page"

        );
    }


    @Test
    public void verifyInvalidLogin() throws IOException {

        JsonNode data = DataDriven.jsonReader();

        String username = data.get("invalidUser").get("username").asText();
        String password = data.get("invalidUser").get("password").asText();

        loginPage loginPage = new loginPage(driver);

        loginPage.login(username, password);


        String actualErrorMessage =
                loginPage.getErrorMessage();

        Assert.assertTrue(
                actualErrorMessage.contains(
                        "Username and password do not match"
                ),
                "Invalid login error message is incorrect"
        );
    }


    @Test
    public void verifyLoginWithoutPassword() throws IOException {

        JsonNode data = DataDriven.jsonReader();

        String username = data.get("noPasswordUser").get("username").asText();
        String password = data.get("noPasswordUser").get("password").asText();

        loginPage loginPage = new loginPage(driver);

        loginPage.login(username,password);

        String actualErrorMessage =
                loginPage.getErrorMessage();

        Assert.assertTrue(
                actualErrorMessage.contains(
                        "Password is required"
                ),
                "Password required error message is incorrect"
        );
    }
}
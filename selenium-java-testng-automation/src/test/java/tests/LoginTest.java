package tests;

import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import base.BaseTest;
import pages.LoginPage;
import pages.ProductsPage;

public class LoginTest extends BaseTest {

    @Test(description = "Valid user can log in and see the Products page")
    public void validLogin() {
        LoginPage loginPage = new LoginPage(driver);
        loginPage.login("standard_user", "secret_sauce");

        ProductsPage productsPage = new ProductsPage(driver);
        Assert.assertEquals(productsPage.getPageTitle(), "Products");
        Assert.assertTrue(driver.getCurrentUrl().contains("inventory"));
    }

    // Data-driven negative test: username, password, expected error text
    @DataProvider(name = "invalidCredentials")
    public Object[][] invalidCredentials() {
        return new Object[][] {
            { "standard_user", "wrong_password", "Username and password do not match" },
            { "invalid_user", "secret_sauce", "Username and password do not match" },
            { "locked_out_user", "secret_sauce", "Sorry, this user has been locked out" }
        };
    }

    @Test(dataProvider = "invalidCredentials", description = "Invalid credentials show the correct error")
    public void invalidLogin(String username, String password, String expectedError) {
        LoginPage loginPage = new LoginPage(driver);
        loginPage.login(username, password);
        String actual = loginPage.getErrorMessage();
        Assert.assertTrue(actual.contains(expectedError), "Unexpected error message: " + actual);
    }

    @Test(description = "Empty username shows a validation error")
    public void emptyUsername() {
        LoginPage loginPage = new LoginPage(driver);
        loginPage.enterPassword("secret_sauce");
        loginPage.clickLogin();
        Assert.assertTrue(loginPage.getErrorMessage().contains("Username is required"));
    }

    @Test(description = "Empty password shows a validation error")
    public void emptyPassword() {
        LoginPage loginPage = new LoginPage(driver);
        loginPage.enterUsername("standard_user");
        loginPage.clickLogin();
        Assert.assertTrue(loginPage.getErrorMessage().contains("Password is required"));
    }

    @Test(description = "User can log out and returns to the login page")
    public void logout() {
        new LoginPage(driver).login("standard_user", "secret_sauce");
        new ProductsPage(driver).logout();
        Assert.assertTrue(new LoginPage(driver).isLoginPageDisplayed());
    }
}

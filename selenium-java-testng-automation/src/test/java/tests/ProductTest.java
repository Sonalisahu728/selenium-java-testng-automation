package tests;

import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import base.BaseTest;
import pages.LoginPage;
import pages.ProductsPage;

public class ProductTest extends BaseTest {

    private ProductsPage productsPage;

    @BeforeMethod(dependsOnMethods = "setUp")
    public void loginFirst() {
        new LoginPage(driver).login("standard_user", "secret_sauce");
        productsPage = new ProductsPage(driver);
    }

    @Test(description = "Products are listed after login")
    public void verifyProductsDisplayed() {
        Assert.assertEquals(productsPage.getProductCount(), 6);
    }

    @Test(description = "Adding a product updates the cart count")
    public void addProductToCart() {
        productsPage.addBackpackToCart();
        Assert.assertEquals(productsPage.getCartCount(), 1);
    }

    @Test(description = "Removing a product clears the cart count")
    public void removeProductFromCart() {
        productsPage.addBackpackToCart();
        productsPage.removeBackpackFromCart();
        Assert.assertEquals(productsPage.getCartCount(), 0);
    }

    @Test(description = "Added product appears in the cart page")
    public void verifyCart() {
        productsPage.addBackpackToCart();
        productsPage.openCart();
        Assert.assertEquals(productsPage.getCartItems().size(), 1);
        Assert.assertEquals(productsPage.getCartItems().get(0).getText(), "Sauce Labs Backpack");
    }
}

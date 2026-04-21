package services;

import ui.pages.CheckoutPage;
import ui.pages.HomePage;
import ui.pages.CartPage;
import ui.pages.LoginPage;

import org.openqa.selenium.WebDriver;

import java.util.List;

public class CheckoutService {

    private WebDriver driver;

    private HomePage homePage;
    private LoginPage loginPage;
    private CartPage cartPage;
    private CheckoutPage checkoutPage;

    public CheckoutService(WebDriver driver) {
        this.driver = driver;

        this.homePage = new HomePage(driver);
        this.loginPage = new LoginPage(driver);
        this.cartPage = new CartPage(driver);
        this.checkoutPage = new CheckoutPage(driver);
    }

    // ---------- FLOW METHODS ----------

    public void openHome() {
        homePage.open();
    }

    public void login(String username, String password) {
        loginPage.login(username, password);
    }

    public void addItems(List<String> items) {
        homePage.addItems(items);
    }

    public int getCartCount() {
        return homePage.getCartCount();
    }

    public void openCart() {
        homePage.openCart();
    }

    public boolean verifyQty() {
        return cartPage.verifyQtyIsOne();
    }

    public void removeItem(String item) {
        cartPage.removeItem(item);
    }

    public void clickCheckout() {
        cartPage.clickCheckout();
    }

    // ---------- CHECKOUT ----------

    public void typeFirst(String value) {
        checkoutPage.typeFirstName(value);
    }

    public void typeLast(String value) {
        checkoutPage.typeLastName(value);
    }

    public void typeZip(String value) {
        checkoutPage.typeZip(value);
    }

    public void continueCheckout() {
        checkoutPage.clickContinue();
    }

    // ---------- BUSINESS LOGIC ----------

    public double getCalcTotal() {

        List<Double> prices = checkoutPage.getItemPrices();

        return prices.stream()
                .mapToDouble(Double::doubleValue)
                .sum();
    }

    public double getActualTotal() {
        return checkoutPage.getDisplayedTotal();
    }

    public double getTax() {
        return checkoutPage.getDisplayedTax();
    }

    public double calculateExpectedTax(double total, int percent) {
        return total * percent / 100.0;
    }
}
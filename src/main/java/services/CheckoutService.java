package services;

import java.util.List;

import org.openqa.selenium.WebDriver;

import ui.pages.CartPage;
import ui.pages.CheckoutPage;
import ui.pages.LoginPage;
import ui.pages.ProductsPage;

public class CheckoutService {

	LoginPage login;
	ProductsPage products;
	CartPage cart;
	CheckoutPage checkout;

	public CheckoutService(WebDriver driver) {
		login = new LoginPage(driver);
		products = new ProductsPage(driver);
	}

	public void openHome() {
		login.open();
	}

	public void login(String user, String pass) {
		products = login.login(user, pass);
	}

	public void addItems(List<String> items) {
		for (String item : items) {
			products.addItem(item);
		}
	}

	public int getCartCount() {
		return products.getCartCount();
	}

	public void openCart() {
		cart = products.openCart();
	}

	public boolean verifyQty() {
		return cart.verifyQtyAllOne();
	}

	public void removeItem(String item) {
		cart.removeItem(item);
	}

	 public int getCartItems() { return cart.getItemCount(); }


	public void clickCheckout() {
		checkout = cart.clickCheckout();
	}

	public void typeFirst(String val) {
		checkout.typeFirstName(val);
	}

	public void typeLast(String val) {
		checkout.typeLastName(val);
	}

	public void typeZip(String val) {
		checkout.typeZip(val);
	}

	public void continueCheckout() {
		checkout.clickContinue();
	}

	public double getCalcTotal() {
		return checkout.calculateItemTotal();
	}

	public double getActualTotal() {
		return checkout.getDisplayedTotal();
	}

	public double getTax() {
		return checkout.getTax();
	}
	
	
}
package ui.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

import ui.base.BasePage;
import core.config.ConfigLoader;

public class LoginPage extends BasePage {

    private By user = By.id("user-name");
    private By pass = By.id("password");
    private By login = By.id("login-button");

    public LoginPage(WebDriver driver){
        super(driver);
    }

    public LoginPage open(){
        driver.get(ConfigLoader.get("baseUrl"));
        return this;
    }

    public ProductsPage login(String u,String p){
        type(user,u);
        type(pass,p);
        click(login);
        return new ProductsPage(driver);
    }
}
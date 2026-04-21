package core.interfaces;

import org.openqa.selenium.WebDriver;

public interface DriverProvider {

    void init();

    WebDriver get();

    void quit();
}
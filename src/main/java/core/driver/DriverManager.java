package core.driver;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;

public class DriverManager {
	private static ThreadLocal<WebDriver> driver = new ThreadLocal<>();

	public static void initDriver(String browser) {

	    switch (browser.toLowerCase()) {
	        case "firefox":
	            driver.set(new FirefoxDriver());
	            break;

	        case "edge":
	            driver.set(new EdgeDriver());
	            break;

	        default:
	            driver.set(new ChromeDriver());
	    }
	}

	public static WebDriver getDriver() {
	    return driver.get();
	}

	public static void quit() {
	    driver.get().quit();
	    driver.remove();
	}
}
package core.driver;

import core.config.ConfigLoader;
import core.interfaces.DriverProvider;
import io.github.bonigarcia.wdm.WebDriverManager;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;

import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.edge.EdgeOptions;

import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxOptions;

import java.nio.file.Files;
import java.nio.file.Path;
import java.time.Duration;
import java.util.HashMap;
import java.util.Map;

public class DriverFactory implements DriverProvider {

    private static ThreadLocal<WebDriver> driver = new ThreadLocal<>();

    @Override
    public void init() {

        if (driver.get() != null) return;

        String browser = ConfigLoader.get("browser");

        try {
            WebDriver webDriver;

            switch (browser.toLowerCase()) {

                case "firefox":
                    webDriver = initFirefox();
                    break;

                case "edge":
                    webDriver = initEdge();
                    break;

                case "chrome":
                default:
                    webDriver = initChrome();
                    break;
            }

            webDriver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
            driver.set(webDriver);

        } catch (Exception e) {
            throw new RuntimeException("Driver initialization failed", e);
        }
    }

    private WebDriver initChrome() throws Exception {

        WebDriverManager.chromedriver().setup();

        ChromeOptions options = new ChromeOptions();

        options.addArguments("--incognito");
        options.addArguments("--disable-notifications");
        options.addArguments("--start-maximized");

        Map<String, Object> prefs = new HashMap<>();
        prefs.put("credentials_enable_service", false);
        prefs.put("profile.password_manager_enabled", false);

        options.setExperimentalOption("prefs", prefs);

        Path profile = Files.createTempDirectory("chrome-profile-");
        options.addArguments("--user-data-dir=" + profile.toAbsolutePath());

        return new ChromeDriver(options);
    }

    private WebDriver initFirefox() {

        WebDriverManager.firefoxdriver().setup();

        FirefoxOptions options = new FirefoxOptions();
        options.addArguments("-private");

        return new FirefoxDriver(options);
    }

    private WebDriver initEdge() throws Exception {

        WebDriverManager.edgedriver().setup();

        EdgeOptions options = new EdgeOptions();
        options.addArguments("--start-maximized");

        Path profile = Files.createTempDirectory("edge-profile-");
        options.addArguments("--user-data-dir=" + profile.toAbsolutePath());

        return new EdgeDriver(options);
    }

    @Override
    public WebDriver get() {
        return driver.get();
    }

    @Override
    public void quit() {

        if (driver.get() != null) {
            driver.get().quit();
            driver.remove();
        }
    }
}
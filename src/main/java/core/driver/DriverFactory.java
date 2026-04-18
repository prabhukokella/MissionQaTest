package core.driver;

import java.time.Duration;
import java.util.HashMap;
import java.util.Map;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;

import io.github.bonigarcia.wdm.WebDriverManager;

public class DriverFactory {

    private static ThreadLocal<WebDriver> driver = new ThreadLocal<>();

    // ---------- INIT DRIVER ----------
    public static void init() {

        if (driver.get() == null) {

            WebDriverManager.chromedriver().setup();

            ChromeOptions options = new ChromeOptions();

            // ---------- CRITICAL FIX ----------
            options.addArguments("--incognito"); // 🔥 no stored passwords
            options.addArguments("--disable-notifications");
            options.addArguments("--disable-infobars");
            options.addArguments("--disable-extensions");
            options.addArguments("--start-maximized");

            // Disable password manager + leak detection
            Map<String, Object> prefs = new HashMap<>();

            prefs.put("credentials_enable_service", false);
            prefs.put("profile.password_manager_enabled", false);
            prefs.put("profile.password_manager_leak_detection", false); // 🔥 IMPORTANT

            options.setExperimentalOption("prefs", prefs);

            // 🔥 Use fresh profile (MOST IMPORTANT)
            options.addArguments("--user-data-dir=/tmp/chrome-test-profile");

            WebDriver webDriver = new ChromeDriver(options);

            webDriver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));

            driver.set(webDriver);
        }
    }

    // ---------- GET DRIVER ----------
    public static WebDriver get() {
        return driver.get();
    }

    // ---------- QUIT DRIVER ----------
    public static void quit() {

        if (driver.get() != null) {
            driver.get().quit();
            driver.remove();
        }
    }
}
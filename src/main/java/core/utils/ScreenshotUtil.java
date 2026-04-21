package core.utils;

import java.io.File;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;

import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;

import core.driver.DriverFactory;

public class ScreenshotUtil {

    public static void capture(WebDriver driver, String scenarioName) {
        try {
            Path path = Paths.get("target/screenshots/" + scenarioName + "_" + System.currentTimeMillis() + ".png");

            Files.createDirectories(path.getParent());

            Files.copy(
                    ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE).toPath(),
                    path,
                    StandardCopyOption.REPLACE_EXISTING
            );

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
package core.utils;

import java.io.File;
import java.nio.file.Files;
import java.nio.file.Paths;

import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;

import core.driver.DriverFactory;

public class ScreenshotUtil {

    public static void capture(String name) {

        try {
            WebDriver driver = DriverFactory.get();

            File src = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);

            String path = "target/screenshots/" + name.replaceAll(" ", "_") + ".png";

            Files.createDirectories(Paths.get("target/screenshots"));
            Files.copy(src.toPath(), Paths.get(path));

            System.out.println("Screenshot saved: " + path);

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
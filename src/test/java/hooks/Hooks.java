package hooks;

import core.driver.DriverFactory;
import core.interfaces.DriverProvider;
import core.utils.ScreenshotUtil;

import io.cucumber.java.After;
import io.cucumber.java.Before;
import io.cucumber.java.Scenario;

public class Hooks {

    // ✅ Depend on interface, not concrete class
    private DriverProvider driverProvider = new DriverFactory();

    @Before("@ui")
    public void setupUI() {
        driverProvider.init();
    }

    @After("@ui")
    public void teardownUI(Scenario scenario) {

        if (scenario.isFailed()) {
            ScreenshotUtil.capture(
                    driverProvider.get(),
                    scenario.getName()
            );

            // ✅ OPTIONAL: attach to report
            byte[] screenshot = ((org.openqa.selenium.TakesScreenshot) driverProvider.get())
                    .getScreenshotAs(org.openqa.selenium.OutputType.BYTES);

            scenario.attach(screenshot, "image/png", scenario.getName());
        }

        driverProvider.quit();
    }
}
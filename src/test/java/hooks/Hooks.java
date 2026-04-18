package hooks;

import core.driver.DriverFactory;
import core.utils.ScreenshotUtil;
import io.cucumber.java.After;
import io.cucumber.java.Before;
import io.cucumber.java.Scenario;

public class Hooks {

	@Before("@ui")
	public void setupUI() {
	    DriverFactory.init();
	}

	@After("@ui")
	public void teardownUI(Scenario scenario) {
	    if (scenario.isFailed()) {
	        ScreenshotUtil.capture(scenario.getName());
	    }
	    DriverFactory.quit();
	}
}

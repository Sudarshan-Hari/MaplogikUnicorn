package hooks;

import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;

import maven.Base;

import io.cucumber.java.After;
import io.cucumber.java.Before;
import io.cucumber.java.Scenario;

public class Hooks extends Base {

	@Before
	public void studentLoginPage() {
		initBrowser();
	}
	
	@After(order = 1)
	public void tearDown(Scenario scenario) {
		if(scenario.isFailed()) {
			String scName = scenario.getName();
			byte[] sourceSc = ((TakesScreenshot)(driver))
					.getScreenshotAs(OutputType.BYTES);
			scenario.attach(sourceSc, "image/png", scName);
		}
	}
	
	@After(order = 0)
	public void closeBrowse() {
		tearDown();
	}
	
}

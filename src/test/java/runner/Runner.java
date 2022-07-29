package runner;

import io.cucumber.testng.AbstractTestNGCucumberTests;
import io.cucumber.testng.CucumberOptions;

@CucumberOptions(features = "Features/Login.feature", glue = { "stepdef",
"hooks" }, dryRun = false, tags = "@all")

public class Runner extends AbstractTestNGCucumberTests {

}
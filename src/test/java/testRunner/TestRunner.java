package testRunner;
import org.junit.runner.RunWith;

import io.cucumber.junit.Cucumber;
import io.cucumber.junit.CucumberOptions;

@RunWith(Cucumber.class)
@CucumberOptions(
		features="src/test/java/Feature",
		glue={"step_def"},
		dryRun = false,
		monochrome = true,
		plugin = {"pretty","html:target/cucumber-html-report.html","json:target/jsonReports/cucumber-report.json",
				"com.aventstack.extentreports.cucumber.adapter.ExtentCucumberAdapter:"},
		publish = true
)
public class TestRunner {

}
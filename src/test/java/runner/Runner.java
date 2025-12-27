package runner;


import io.cucumber.testng.AbstractTestNGCucumberTests;
import io.cucumber.testng.CucumberOptions;

@CucumberOptions(
        features = "src/test/resources/features",
        glue = {"step_definition"},
        monochrome = true,
        dryRun = false,
        plugin = { "pretty"}
)
public class Runner  extends AbstractTestNGCucumberTests {
}

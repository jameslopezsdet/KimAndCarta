package org.KimAndCarta.cucumber.mobile.test_mobile_runners;

import cucumber.api.CucumberOptions;
import cucumber.api.junit.Cucumber;
import org.junit.runner.RunWith;

@RunWith(Cucumber.class)

@CucumberOptions(
        features = "src/test/resources/features/mobile/mobile.feature",
        glue = {"org.KimAndCarta.cucumber.mobile.steps1"},
        tags = {},
        dryRun = false
)
public class CalculatorRunner {
}

package org.KimAndCarta.cucumber.testrunners;

import cucumber.api.CucumberOptions;
import cucumber.api.junit.Cucumber;
import org.junit.runner.RunWith;

@RunWith(Cucumber.class)

@CucumberOptions(
        glue = {"org.KimAndCarta.cucumber.steps"},
        tags = {"@verifyInCart"},
        dryRun = false


)
public class AmazonPurchaseRunner {
}

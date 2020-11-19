package org.KimAndCarta.cucumber.testrunners;

import cucumber.api.CucumberOptions;
import cucumber.api.junit.Cucumber;
import org.junit.runner.RunWith;

@RunWith(Cucumber.class)

@CucumberOptions(
        features = {"src/test/resources/features/amazon_purchase.feature"},
        glue = {"org.KimAndCarta.cucumber.steps"},
        tags = {},
        dryRun = false


)
public class AmazonPurchaseRunner {
}

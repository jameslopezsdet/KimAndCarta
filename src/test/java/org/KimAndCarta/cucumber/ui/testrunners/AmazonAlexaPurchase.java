package org.KimAndCarta.cucumber.ui.testrunners;


import cucumber.api.CucumberOptions;
import cucumber.api.junit.Cucumber;
import org.junit.runner.RunWith;

@RunWith(Cucumber.class)

@CucumberOptions(
        features = {"src/test/resources/features/amazon_purchase.feature"},
        glue = {"org.KimAndCarta.cucumber.ui.steps"},
        tags = {},
        dryRun = false


)


public class AmazonAlexaPurchase {
}

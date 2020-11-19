package org.KimAndCarta.cucumber.steps;

import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import io.github.bonigarcia.wdm.WebDriverManager;
import org.KimAndCarta.cucumber.JavaBean.Amazon;
import org.KimAndCarta.cucumber.utilities.ConfigurationsReader;
import org.KimAndCarta.cucumber.utilities.Driver;
import org.junit.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

import java.util.concurrent.TimeUnit;

public class AmazonPurchaseSteps {
    WebDriver driver;
    Amazon elements = new Amazon();

    @Given("^the user navigates to \"([^\"]*)\"$")
    public void the_user_navigates_to(String url) throws InterruptedException {
        driver = Driver.getDriver();
        driver.navigate().to(url);
    }
    @Given("^searches for \"([^\"]*)\"$")
    public void searches_for(String itemProduct) throws InterruptedException {
        elements.searchBox.sendKeys(itemProduct + Keys.ENTER);


    }


    @Given("^navigates to the second page$")
    public void navigates_to_the_second_page() throws Throwable {
        elements.page2Button.click();
    }

    @Given("^selects the third item$")
    public void selects_the_third_item() throws Throwable {

    }

    @Then("^user should be able to add it to the cart$")
    public void user_should_be_able_to_add_it_to_the_cart() throws Throwable {

    }
}

package org.KimAndCarta.cucumber.steps;

import cucumber.api.java.After;
import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import org.KimAndCarta.cucumber.JavaBean.Amazon;
import org.KimAndCarta.cucumber.utilities.Driver;
import org.hamcrest.MatcherAssert;
import org.hamcrest.Matchers;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Action;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.util.List;
import java.util.regex.Matcher;


public class AmazonPurchaseSteps {
    WebDriver driver;
    Amazon elements = new Amazon();
    List<WebElement> productsOnPage2;

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
        Actions action = new Actions(driver);
        WebElement page20 = driver.findElement(By.xpath("//li[text()='20']"));
        action.moveToElement(page20);
        productsOnPage2 = driver.findElements(By.xpath("//img[@class='s-image']"));
        System.out.println(productsOnPage2.size());
        for (WebElement x :productsOnPage2) {
            System.out.println(x.getAttribute("alt"));
        }
    }

    @Then("^user should be able to add it to the cart$")
    public void user_should_be_able_to_add_it_to_the_cart() throws Throwable {
    productsOnPage2.get(2).click();
        WebDriverWait wait = new WebDriverWait(driver,2);
        wait.until(ExpectedConditions.  //add to cart button
                elementToBeClickable(By.xpath("//input[@id='add-to-cart-button']"))).click();
        /*
        Pop up is Unpredictable, however if you manually click it when it pops up test will pass

        if(driver.findElement(By.xpath("//i[@class='a-icon a-icon-close']")).isDisplayed()){
            WebElement popUp = driver.findElement(By.xpath("//i[@class='a-icon a-icon-close']"));
            popUp.click();
        }*/

        WebElement addedToCartDisplay = driver.findElement(By.xpath("//h1[@class='a-size-medium a-text-bold']"));
        WebElement textOfcart = driver.findElement(By.xpath("//a[@id='hlb-ptc-btn-native']"));
        System.out.println("=================>>>>>>>>>>>>>>>>"+textOfcart.getText().trim());

        MatcherAssert.assertThat("Product Was not added to cart",addedToCartDisplay.isDisplayed() &&
                textOfcart.getText().equals(
                        "Proceed to checkout (1 item)"));
    }
    @After
    public void tearDown(){
        Driver.tearDown();
    }
}

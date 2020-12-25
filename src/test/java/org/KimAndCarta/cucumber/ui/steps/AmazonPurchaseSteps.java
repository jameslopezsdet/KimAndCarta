package org.KimAndCarta.cucumber.ui.steps;

import cucumber.api.java.After;
import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import org.KimAndCarta.cucumber.ui.utilities.Driver;
import org.KimAndCarta.a_ui_bean.Amazon;
import org.hamcrest.MatcherAssert;
import org.openqa.selenium.*;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.util.List;


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
        for (WebElement x : productsOnPage2) {
            System.out.println(x.getAttribute("alt"));
        }
    }

    @Then("^user should be able to add it to the cart$")
    public void user_should_be_able_to_add_it_to_the_cart() throws Throwable {
        productsOnPage2.get(2).click();
        WebDriverWait wait = new WebDriverWait(driver, 4);

        /*
        Pop up is Unpredictable, however if you manually click it when it pops up test will pass
        try{
            List<WebElement> newList = driver.findElements(By.xpath("//*[contains(text(),\"Add to your order\")]"));
        if(driver.findElement(By.xpath("//i[@class='a-icon a-icon-close']")).isDisplayed()){
            WebElement popUp = driver.findElement(By.xpath("//i[@class='a-icon a-icon-close']"));
            popUp.click();
        }else if(driver.findElement((By.id("a-popover-header-1"))).isDisplayed()){
            driver.findElement(By.xpath("//button[@aria-label='Close']")).click();
        }else if(driver.findElement(By.id("a-popover-header-1")).isDisplayed()){
            driver.findElement(By.xpath("//i[@class='a-icon a-icon-close']")).click();
        }else if(driver.findElement(By.xpath("//h4[@class='a-popover-header-content']")).isDisplayed()){
            driver.findElement(By.xpath("//button[@aria-label='Close']")).click();
        }else if(newList.size() > 0){
            driver.findElement(By.xpath("//i[@class='a-icon a-icon-close']")).click();
 }
        }catch (NoSuchElementException e){
           // e.printStackTrace();
        }
*/
        wait.until(ExpectedConditions.  //add to cart button
                elementToBeClickable(By.xpath("//input[@id='add-to-cart-button']"))).click();

        WebElement addedToCartDisplay = driver.findElement(By.xpath("//h1[@class='a-size-medium a-text-bold']"));
        WebElement textOfcart = driver.findElement(By.xpath("//a[@id='hlb-ptc-btn-native']"));
        System.out.println("=================>>>>>>>>>>>>>>>>" + textOfcart.getText().trim());

        MatcherAssert.assertThat("Product Was not added to cart", addedToCartDisplay.isDisplayed() &&
                textOfcart.getText().equals(
                        "Proceed to checkout (1 item)"));
    }

    @After
    public void tearDown() {
        Driver.tearDown();
    }
}

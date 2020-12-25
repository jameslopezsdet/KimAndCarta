package org.KimAndCarta.a_ui_bean;

import org.KimAndCarta.cucumber.ui.utilities.Driver;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class Amazon {

    WebDriver driver;

    public Amazon(){
        driver = Driver.getDriver();
        PageFactory.initElements(driver, this);
    }

    @FindBy(id = "twotabsearchtextbox")
    public WebElement searchBox;

    @FindBy(xpath = "//a[.='2']")
    public WebElement page2Button;
}

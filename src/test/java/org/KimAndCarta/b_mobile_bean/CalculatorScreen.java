package org.KimAndCarta.b_mobile_bean;

import io.appium.java_client.MobileBy;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.android.AndroidElement;
import io.appium.java_client.pagefactory.AndroidFindBy;
import io.appium.java_client.pagefactory.AppiumFieldDecorator;
import org.KimAndCarta.cucumber.mobile.Utility.MobileAndroidDriver;
import org.openqa.selenium.support.PageFactory;

import java.net.MalformedURLException;

public class CalculatorScreen {

    AndroidDriver<AndroidElement> driverAndroid;

    public CalculatorScreen() throws MalformedURLException {
        driverAndroid = MobileAndroidDriver.loadMobileDriver();
        PageFactory.initElements(new AppiumFieldDecorator(driverAndroid), this);
    }

    @AndroidFindBy(xpath = "//android.widget.Button[@text='=']")
    public AndroidElement equalsButton;

    @AndroidFindBy(xpath = "//android.widget.LinearLayout[2]//android.widget.LinearLayout[1]/android.widget.Button[2]")
    public AndroidElement parenthesisButton;

    @AndroidFindBy(xpath = "//android.widget.LinearLayout[2]/android.widget.LinearLayout/android.widget.LinearLayout[2]" +
            "/android.widget.Button[4]")
public AndroidElement multiplicationButton;

    @AndroidFindBy(xpath = "//android.widget.LinearLayout[2]//android.widget.LinearLayout[3]/android.widget.Button[4]")
    public AndroidElement minusButton;

    @AndroidFindBy(uiAutomator = "text(\"9\")")
    public AndroidElement numberNine;
}

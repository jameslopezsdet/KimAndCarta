package org.KimAndCarta.cucumber.mobile.steps1;

import cucumber.api.java.en.Given;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.android.AndroidElement;
import org.KimAndCarta.cucumber.mobile.Utility.MobileAndroidDriver;

public class CalculatorSteps {
    AndroidDriver<AndroidElement> driverAndroid;
    MobileAndroidDriver mb = new MobileAndroidDriver();

    @Given("^the user opens the calculator app$")
    public void the_user_opens_the_calculator_app() throws Throwable {
   driverAndroid = mb.loadMobileDriver();
    }
}

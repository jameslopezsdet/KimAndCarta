package org.KimAndCarta.cucumber.mobile.steps1;

import cucumber.api.java.After;
import cucumber.api.java.Before;
import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import io.appium.java_client.TouchAction;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.android.AndroidElement;
import io.appium.java_client.android.nativekey.AndroidKey;
import io.appium.java_client.android.nativekey.KeyEvent;
import io.appium.java_client.touch.LongPressOptions;
import io.appium.java_client.touch.TapOptions;
import io.appium.java_client.touch.WaitOptions;
import io.appium.java_client.touch.offset.ElementOption;
import io.appium.java_client.touch.offset.PointOption;
import org.KimAndCarta.cucumber.mobile.Utility.MobileAndroidDriver;
import org.hamcrest.Matcher;
import org.hamcrest.MatcherAssert;
import org.hamcrest.Matchers;
import org.openqa.selenium.By;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.math.RoundingMode;
import java.net.MalformedURLException;
import java.text.DecimalFormat;
import java.time.Duration;

import static io.appium.java_client.touch.offset.ElementOption.element;

public class CalculatorTouchSteps {

    AndroidDriver<AndroidElement> driverAndroid;
    String result;

    @Given("^the user opens the calculator application by swiping$")
    public void the_user_opens_the_calculator_app_by_swiping() throws InterruptedException, MalformedURLException {
        driverAndroid = MobileAndroidDriver.loadMobileDriverWithoutResetingAPP();
       // AndroidElement appsButton = driverAndroid.findElementByAccessibilityId("Apps list");
        TouchAction t = new TouchAction(driverAndroid);
        t.press(PointOption.point(516, 1300)).waitAction(WaitOptions.waitOptions(Duration.ofSeconds(2)))
                .moveTo(PointOption.point(515, 132)).release().perform();
        // t.press(PointOption.point(515,1250)).(PointOption.point(515,132)).release().perform();

    }

    @Given("^taps square root of addition of 95 Plus 23 and multiplies it by negative 1$")
    public void adds_Ninety_five_plus_twenty_three_by_tapping() throws Throwable {
        TouchAction action = new TouchAction(driverAndroid);
        AndroidElement calculatorButton = driverAndroid.findElementByAndroidUIAutomator("text(\"Calculator\")");
        action.tap(TapOptions.tapOptions().withElement(element(calculatorButton))).perform();
        WebDriverWait wait = new WebDriverWait(driverAndroid,5);
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("com.android.calculator2:id/digit_9")));
        action.tap(PointOption.point(1061, 1169)).perform();

        AndroidElement squareRoot = driverAndroid.findElement(By.id("com.android.calculator2:id/op_sqrt"));
        action.tap(TapOptions.tapOptions().withElement(element(squareRoot))).perform();
        AndroidElement openParenthesis = driverAndroid.findElement(By.id("com.android.calculator2:id/lparen"));
        AndroidElement closeParenthesis = driverAndroid.findElement(By.id("com.android.calculator2:id/rparen"));
        action.tap(TapOptions.tapOptions().withElement(element(openParenthesis))).perform();
        action.tap(PointOption.point(108, 1258)).perform();


        AndroidElement nine = driverAndroid.findElement(By.id("com.android.calculator2:id/digit_9"));
        AndroidElement five = driverAndroid.findElement(By.id("com.android.calculator2:id/digit_5"));
        AndroidElement two = driverAndroid.findElement(By.id("com.android.calculator2:id/digit_2"));
        AndroidElement three = driverAndroid.findElement(By.id("com.android.calculator2:id/digit_3"));

        AndroidElement plusButton = driverAndroid.findElement(By.id("com.android.calculator2:id/op_add"));

        action.tap(TapOptions.tapOptions().withElement(element(nine))).perform();
        action.tap(TapOptions.tapOptions().withElement(element(five))).perform();
        action.tap(TapOptions.tapOptions().withElement(element(plusButton))).perform();

        action.tap(TapOptions.tapOptions().withElement(element(two))).perform();
        action.tap(TapOptions.tapOptions().withElement(element(three))).perform();

        action.tap(PointOption.point(1061, 1169)).perform();

        action.tap(TapOptions.tapOptions().withElement(element(closeParenthesis))).perform();
        action.tap(PointOption.point(108, 1258)).perform();

        AndroidElement multiplication = driverAndroid.findElement(By.id("com.android.calculator2:id/op_mul"));
        AndroidElement negative = driverAndroid.findElement(By.id("com.android.calculator2:id/op_sub"));
        AndroidElement equalsSign = driverAndroid.findElement(By.id("com.android.calculator2:id/eq"));
        AndroidElement one = driverAndroid.findElement(By.id("com.android.calculator2:id/digit_1"));


        action.tap(TapOptions.tapOptions().withElement(element(multiplication))).perform();
        action.tap(TapOptions.tapOptions().withElement(element(negative))).perform();
        action.tap(TapOptions.tapOptions().withElement(element(one))).perform();
        action.tap(TapOptions.tapOptions().withElement(element(equalsSign))).perform();

        AndroidElement resultWindow = driverAndroid.findElement(By.id("com.android.calculator2:id/result"));
        System.out.println(result = resultWindow.getText());
    }

    @Then("^after getting the result the result should be \"([^\"]*)\"$")
    public void after_getting_the_result_the_result_should_be(String expected) throws Throwable {
        MatcherAssert.assertThat(result, Matchers.is(expected));
    }

    @Then("^also the result rounded to four digits after decimal should be \"([^\"]*)\"$")
    public void also_the_result_rounded_to_four_digits_after_decimal_should_be(String roundedExpectedResult) throws Throwable {
        double newNumber = Double.parseDouble(result.substring(1, 11));
        System.out.println(newNumber);

        DecimalFormat df = new DecimalFormat("##.####");
        df.setRoundingMode(RoundingMode.CEILING);
        newNumber = Double.parseDouble(df.format(newNumber));

        MatcherAssert.assertThat(newNumber * -1, Matchers.is(Double.parseDouble(roundedExpectedResult)));

        driverAndroid.pressKey(new KeyEvent(AndroidKey.APP_SWITCH));
        Thread.sleep(2000);//Unnecessary just added to observe
        TouchAction t = new TouchAction(driverAndroid);

        t.press(PointOption.point(523, 1208)).waitAction(WaitOptions.waitOptions(Duration.ofSeconds(2)))
                .moveTo(PointOption.point(538, 97)).release().perform();

        driverAndroid.quit();
        MobileAndroidDriver.closeServer();
    }


}

package org.KimAndCarta.cucumber.mobile.steps1;

import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import io.appium.java_client.TouchAction;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.android.AndroidElement;
import io.appium.java_client.android.nativekey.AndroidKey;
import io.appium.java_client.android.nativekey.KeyEvent;
import io.appium.java_client.touch.TapOptions;
import io.appium.java_client.touch.offset.PointOption;
import org.KimAndCarta.b_mobile_bean.CalculatorScreen;
import org.KimAndCarta.cucumber.mobile.Utility.MobileAndroidDriver;
import org.hamcrest.MatcherAssert;
import org.hamcrest.Matchers;
import org.openqa.selenium.By;
import org.openqa.selenium.DeviceRotation;
import org.openqa.selenium.ScreenOrientation;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import java.math.RoundingMode;
import java.net.MalformedURLException;
import java.text.DecimalFormat;
import java.util.concurrent.TimeUnit;

public class CalculatorSteps {
    AndroidDriver<AndroidElement> driverAndroid;
    MobileAndroidDriver mb = new MobileAndroidDriver();
    String sumOf118;
    String negativeResultOfCalculator;
    CalculatorScreen cs = new CalculatorScreen();

    public CalculatorSteps() throws MalformedURLException {
    }

    @Given("^the user opens the calculator app$")
    public void the_user_opens_the_calculator_app() throws Throwable {
        driverAndroid = mb.loadMobileDriver();
    }

    @Given("^add Ninety five plus twenty three$")
    public void add_Ninety_five_plus_twenty_three() throws Throwable {

        cs.numberNine.click();

        AndroidElement number5 = driverAndroid.findElementByAndroidUIAutomator("text(\"5\")");
        number5.click();

        AndroidElement plusSign = driverAndroid.findElementByAndroidUIAutomator("text(\"+\")");
        plusSign.click();

        AndroidElement number2 = driverAndroid.findElementByAndroidUIAutomator("text(\"2\")");
        number2.click();

        AndroidElement number3 = driverAndroid.findElement(By.id("com.bng.calculator:id/btn_3"));
        number3.click();

        cs.equalsButton.click();

        AndroidElement clearButton = driverAndroid.findElement(By.id("com.bng.calculator:id/btn_clear"));
        clearButton.click();

    }

    @Given("^then takes the sum and gets the square root$")
    public void then_takes_the_sum_and_gets_the_square_root() throws Throwable {
          DeviceRotation deviceRotation = new DeviceRotation(90,90,90);
          driverAndroid.rotate(deviceRotation);
          Thread.sleep(1000);

        AndroidElement squareRootButton = driverAndroid.findElementByAndroidUIAutomator("text(\"√\")");
        squareRootButton.click();
        WebDriverWait wait = new WebDriverWait(driverAndroid, 7);
        //wait.until(ExpectedConditions.elementToBeClickable((By.xpath("//android.widget.Button[@text='√']")))).click();


        AndroidElement history = driverAndroid.findElement(By.id("com.bng.calculator:id/historyImage"));
        wait.until(ExpectedConditions.elementToBeClickable(history));
        history.click();
        AndroidElement sumHistoryOf118Button = driverAndroid.findElement(By.id("com.bng.calculator:id/resultV"));
        sumOf118 = sumHistoryOf118Button.getText();
        sumOf118 = sumOf118.substring(1);
        System.out.println(sumOf118);
        sumHistoryOf118Button.click();

        cs.equalsButton.click();

        driverAndroid.rotate(ScreenOrientation.PORTRAIT);
    }

    @Given("^then multiplies it by negative one$")
    public void then_multiplies_it_by_negative_one() throws Throwable {
        cs.multiplicationButton.click();

        cs.parenthesisButton.click();

        cs.minusButton.click();
        AndroidElement theNumberOne = driverAndroid.findElementByAndroidUIAutomator("text(\"1\")");
        theNumberOne.click();

        cs.equalsButton.click();

    }

    @Then("^the result should be \"([^\"]*)\"$")
    public void the_result_should_be(String fullExpectedResult) throws Throwable {
        AndroidElement resultWindow = driverAndroid.findElement(By.id("com.bng.calculator:id/formula"));

        negativeResultOfCalculator = resultWindow.getText().trim();
        System.out.println(negativeResultOfCalculator + " This is negative result : ");

        MatcherAssert.assertThat(sumOf118, Matchers.is("118"));

        MatcherAssert.assertThat("Failed Expected Result not there", fullExpectedResult, Matchers.is(resultWindow.getText()));
        /**
         * Calculator hyphen did not work, was causing exception while parsing to double
         * loop not necessary for testing, would be removed
         */
        for (int i = 0; i < negativeResultOfCalculator.length(); i++) {
            if (Character.isDigit(negativeResultOfCalculator.charAt(i)) || negativeResultOfCalculator.charAt(i) == '-') {
                System.out.println(i);
            } else {
                System.out.println(negativeResultOfCalculator.charAt(i));
            }
        }
    }

    @Then("^the result rounded to four digits after decimal should be \"([^\"]*)\"$")
    public void the_result_rounded_to_four_digits_after_decimal_should_be(String expectedResult) throws Throwable {

        double newNumber = Double.parseDouble(negativeResultOfCalculator.substring(1, 11));
        System.out.println(newNumber);

        DecimalFormat df = new DecimalFormat("##.####");
        df.setRoundingMode(RoundingMode.CEILING);
        newNumber = Double.parseDouble(df.format(newNumber));

        MatcherAssert.assertThat(newNumber * -1, Matchers.is(Double.parseDouble(expectedResult)));

        driverAndroid.pressKey(new KeyEvent(AndroidKey.HOME));


        driverAndroid.quit();

    }


}

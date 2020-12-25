package org.KimAndCarta.cucumber.mobile.Utility;

import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.android.AndroidElement;
import io.appium.java_client.remote.MobileCapabilityType;
import org.openqa.selenium.remote.DesiredCapabilities;

import java.io.File;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.concurrent.TimeUnit;

public class MobileAndroidDriver {

    public static AndroidDriver loadMobileDriver() throws MalformedURLException {
String simpleCalculator = "src/test/resources/simple-calculator_2.9.2+.apk";

        File fs  = new File(simpleCalculator);
        DesiredCapabilities cap = new DesiredCapabilities();
        cap.setCapability(MobileCapabilityType.DEVICE_NAME,"Pixel 2");
        cap.setCapability(MobileCapabilityType.APP, fs.getAbsolutePath());
        cap.setCapability(MobileCapabilityType.AUTOMATION_NAME,"uiautomator2");

        AndroidDriver<AndroidElement> driverAndroid = new AndroidDriver<>(new URL("http:127.0.0.1:4723/wd/hub"),cap);

        driverAndroid.manage().timeouts().implicitlyWait(9, TimeUnit.SECONDS);
        return driverAndroid;
    }
}

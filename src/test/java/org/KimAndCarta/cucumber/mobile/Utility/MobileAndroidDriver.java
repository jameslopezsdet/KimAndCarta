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

    /**
     *
     * @return Android driver with capabilities
     * @throws MalformedURLException
     */

    public static AndroidDriver loadMobileDriver() throws MalformedURLException {

        /**
         * This class, if company is testing in IOS and ANDROID,
         * would have a if statement to switch between ANDROID and IOS, It would also have a
         * file reader class to eliminate
         * hardcoded values of DEVICE_NAME, and PLATFORM_VERSION
         */
        String simpleCalculator = "src/test/resources/simple-calculator_2.9.2+.apk";

        File fs  = new File(simpleCalculator);
        DesiredCapabilities cap = new DesiredCapabilities();
        cap.setCapability(MobileCapabilityType.DEVICE_NAME,"Pixel 2");
        cap.setCapability(MobileCapabilityType.APP, fs.getAbsolutePath());
        cap.setCapability(MobileCapabilityType.AUTOMATION_NAME,"uiautomator2");
        cap.setCapability(MobileCapabilityType.PLATFORM_NAME,"Android");

        AndroidDriver<AndroidElement> driverAndroid = new AndroidDriver<>(new URL("http:127.0.0.1:4723/wd/hub"),cap);

        driverAndroid.manage().timeouts().implicitlyWait(9, TimeUnit.SECONDS);
        return driverAndroid;
    }


    /**
     * IN this method using calculator already installed in Pixel 2,  phone.
     * Tool creating Emulator is Android Studio
     *
     * @return Mobile driver
     * @throws MalformedURLException
     */
    public static AndroidDriver loadMobileDriverWithoutResetingAPP() throws MalformedURLException {
        // String alreadyInstalledCalculator = "";
        // File fs  = new File(simpleCalculator);

        DesiredCapabilities cap = new DesiredCapabilities();
        cap.setCapability(MobileCapabilityType.NO_RESET,true);
        cap.setCapability(MobileCapabilityType.FULL_RESET,false);
        cap.setCapability(MobileCapabilityType.DEVICE_NAME,"Pixel 2");
       // cap.setCapability(MobileCapabilityType.APP, fs.getAbsolutePath());
        cap.setCapability(MobileCapabilityType.PLATFORM_NAME,"Android");
        cap.setCapability(MobileCapabilityType.PLATFORM_VERSION,"9.0");
        cap.setCapability(MobileCapabilityType.AUTOMATION_NAME,"uiautomator2");

        AndroidDriver<AndroidElement> driverAndroid = new AndroidDriver<>(new URL("http:127.0.0.1:4723/wd/hub"),cap);

        driverAndroid.manage().timeouts().implicitlyWait(9, TimeUnit.SECONDS);
        return driverAndroid;
    }

}

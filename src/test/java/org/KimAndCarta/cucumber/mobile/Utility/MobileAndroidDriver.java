package org.KimAndCarta.cucumber.mobile.Utility;

import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.android.AndroidElement;
import io.appium.java_client.remote.MobileCapabilityType;
import io.appium.java_client.service.local.AppiumDriverLocalService;
import io.appium.java_client.service.local.AppiumServiceBuilder;
import org.openqa.selenium.remote.DesiredCapabilities;

import java.io.File;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.ServerSocket;
import java.net.URL;
import java.util.concurrent.TimeUnit;

public class MobileAndroidDriver {
    public static AppiumDriverLocalService service;

    /**
     *
     * @return Android driver with capabilities
     * @throws MalformedURLException
     */
    /**
     * This class, if company is testing in IOS and ANDROID,
     * would have a if statement to switch between ANDROID and IOS, It would also have a
     * file reader class to eliminate
     * hardcoded values of DEVICE_NAME, and PLATFORM_VERSION
     */

    public static AppiumDriverLocalService startServer() {
        boolean isRunning = checkIfServerIsStarted(4723);
        if(!isRunning)
        {
        service = AppiumDriverLocalService.buildDefaultService();

        service.start();
        }
        return service;

    }
    public static void closeServer(){
       // service.stop();
        Runtime runtime = Runtime.getRuntime();
        try{
            String [] command = {"/usr/bin/killall","-9","node" };
            runtime.exec(command);
            System.out.println("Server stopped!!!");
        }catch (IOException e){
            e.printStackTrace();
        }
    }

    //taskKill /F /IM node.exe

    public static boolean checkIfServerIsStarted(int port) {
        boolean isSeverRunning = false;
        ServerSocket serverSocket;
        try {
            serverSocket = new ServerSocket(port);
            serverSocket.close();
        } catch (IOException e) {
            //if control comes here, then it means that the port is in use
            isSeverRunning = true;
        } finally {
            serverSocket = null;
        }
        return isSeverRunning;
    }


    public static AndroidDriver loadMobileDriver() throws MalformedURLException {


        String simpleCalculator = "src/test/resources/simple-calculator_2.9.2+.apk";

        File fs = new File(simpleCalculator);
        DesiredCapabilities cap = new DesiredCapabilities();
        cap.setCapability(MobileCapabilityType.DEVICE_NAME, "Android Emulator");
        cap.setCapability(MobileCapabilityType.APP, fs.getAbsolutePath());
        cap.setCapability(MobileCapabilityType.AUTOMATION_NAME, "uiautomator2");
        cap.setCapability(MobileCapabilityType.PLATFORM_NAME, "Android");
//        cap.setCapability("avd","Pixel_2");
        AndroidDriver<AndroidElement> driverAndroid = new AndroidDriver<>(new URL("http:127.0.0.1:4723/wd/hub"), cap);

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
//        cap.setCapability("avd","JamesEmulator");
//        cap.setCapability(MobileCapabilityType.UDID,"emulator-5554 ");
//        cap.setCapability("avdLaunchTimeout","90000");
//        cap.setCapability("avdReadyTimeout","10000");
        cap.setCapability(MobileCapabilityType.NO_RESET, true);
        cap.setCapability(MobileCapabilityType.FULL_RESET, false);
        cap.setCapability(MobileCapabilityType.DEVICE_NAME, "Pixel 2");
        // cap.setCapability(MobileCapabilityType.APP, fs.getAbsolutePath());
        cap.setCapability(MobileCapabilityType.PLATFORM_NAME, "Android");
        cap.setCapability(MobileCapabilityType.PLATFORM_VERSION, "9.0");
        cap.setCapability(MobileCapabilityType.AUTOMATION_NAME, "uiautomator2");



        AndroidDriver<AndroidElement> driverAndroid = new AndroidDriver<>(new URL("http:127.0.0.1:4723/wd/hub"), cap);

        driverAndroid.manage().timeouts().implicitlyWait(9, TimeUnit.SECONDS);
        return driverAndroid;
    }

}

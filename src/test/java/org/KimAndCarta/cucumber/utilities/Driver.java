package org.KimAndCarta.cucumber.utilities;

import org.openqa.selenium.WebDriver;

public class Driver {

    private Driver() {
        //private constructor only this class will be able to access constructor, Creating a singleton
        //pattern for the instantiation of Driver
    }

    private static WebDriver driver;

    public static WebDriver getDriver() {

        if (driver == null) {
            String typeOfBrowser = ConfigurationsReader.getProperty("browser").toLowerCase();

            if ((typeOfBrowser).equals("firefox")) {
                driver = FireFoxWebDriver.loadFirefoxDriver(Boolean.parseBoolean(ConfigurationsReader.getProperty("headless")));
                //else if(condition) if need to run test on multiple browsers, would be determined by
                //Kim+Carta Requirements
                //Or switch statement if a few cases
            } else if ((typeOfBrowser).equals("chrome")) {
                driver = ChromeWebDriver.loadChromeDriver(Boolean.parseBoolean(ConfigurationsReader.getProperty("headless")));

            }

        }
        return driver;
    }

    public static void tearDown() {
      try{  if (driver != null) {
            driver.close();
            driver.quit();
            driver = null;
        }}catch (Exception e){
          e.printStackTrace();
      }

    }
}

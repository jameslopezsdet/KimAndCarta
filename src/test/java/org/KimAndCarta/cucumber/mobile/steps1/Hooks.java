package org.KimAndCarta.cucumber.mobile.steps1;

import cucumber.api.java.After;
import cucumber.api.java.Before;
import org.KimAndCarta.cucumber.mobile.Utility.MobileAndroidDriver;

public class Hooks {

    MobileAndroidDriver sS =new MobileAndroidDriver();

    @Before
    public void setUpServer(){
        sS.startServer();
    }


    @After
    public void stopServer(){
        sS.closeServer();
    }

}

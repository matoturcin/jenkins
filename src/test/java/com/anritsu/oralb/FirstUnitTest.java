package com.anritsu.oralb;

import com.anritsu.oralb.config.AppContext;
import io.appium.java_client.MobileElement;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.remote.AndroidMobileCapabilityType;
import io.appium.java_client.remote.MobileCapabilityType;
import java.net.MalformedURLException;
import java.net.URL;
import org.junit.After;
import static org.junit.Assert.assertEquals;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.openqa.selenium.By;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

/**
 *
 * @author SK040191
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = AppContext.class)
public class FirstUnitTest {
            
    @Value("${url}")
    private String appiumIP;
    
    @Value("${phoneNumber}")
    private String number;
    
    private AndroidDriver<MobileElement> driver;
    
    @Before
    public void initialize() throws MalformedURLException{
        DesiredCapabilities capabilities = new DesiredCapabilities();
        capabilities.setCapability(MobileCapabilityType.DEVICE_NAME, "emulator-5554");
        capabilities.setCapability(MobileCapabilityType.PLATFORM_VERSION, "5.1.1");
        capabilities.setCapability(MobileCapabilityType.PLATFORM_NAME, "Android");
        capabilities.setCapability(AndroidMobileCapabilityType.APP_PACKAGE, "com.android.dialer");
        capabilities.setCapability(AndroidMobileCapabilityType.APP_ACTIVITY, "com.android.dialer.DialtactsActivity");        
        driver = new AndroidDriver(new URL(appiumIP), capabilities);
    }

    @Test
    public void testAppium() throws InterruptedException{
//        removeOldNumber();
        openDialer();
        enterNumber();
        dialNumber();
        Thread.sleep(3000);
        EndCall();
    }
        
//    @Test
//    public void testingMethodFailure(){
//        assertEquals("helloxx", "helloxx");
//    }
//    
//    @Test
//    public void htcTest(){
//        assertEquals("192.168.126.128:4031", appiumIP);
//    }
    
    private void removeOldNumber() {
        MobileElement deleteButton = driver.findElement(By.id("com.android.dialer:id/deleteButton"));
        deleteButton.tap(1, 3000);
    }

    private void openDialer() {
        MobileElement dialButton = driver.findElement(By.id("com.android.dialer:id/floating_action_button"));
        dialButton.click();
    }
    
    private void enterNumber() {
        MobileElement dialterDigitsArea = driver.findElement(By.id("com.android.dialer:id/digits"));
        dialterDigitsArea.sendKeys(number);
    }

    private void dialNumber() {
        MobileElement dialButton = driver.findElement(By.id("com.android.dialer:id/dialpad_floating_action_button"));
        dialButton.click();
    }
    
    private void EndCall() {
        MobileElement dialButton = driver.findElement(By.id("com.android.dialer:id/floating_end_call_action_button"));
        dialButton.click();
    }
    
    @After
    public void terminate(){
        if (driver != null) {
            this.tryToCloseApp();
            driver.quit();            
        }
        driver = null;
    }
    
    private void tryToCloseApp(){
        try {
            driver.close();
        } catch (Exception ex){
            System.out.println(ex.getMessage());
            System.out.println("Application could not be closed! Will just quit.");
        }
    }
    
}

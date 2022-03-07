package Base;

import TestData.testData;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.android.AndroidElement;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;
import pageObjects.HomePage;
import pageObjects.Preference;

import java.io.IOException;


public class ApiDemoTest extends baseTest {

    @BeforeTest
    public void killAllNodes() throws IOException, InterruptedException {
        Runtime.getRuntime().exec("taskkill /F /IM node.exe");
        Thread.sleep(3000);
    }

    @Test(dataProvider = "inputData",dataProviderClass = testData.class)
    public void DemoAppValidation(String input) throws IOException, InterruptedException {
        service = startServer();
        AndroidDriver<AndroidElement> driver = runCapabilities(loadPropertiesFile("deviceAppNameDemo"),false);
        HomePage h = new HomePage(driver);
        h.preference.click();
//        driver.findElementByXPath("//android.widget.TextView[@text='Preference']").click();
        Preference p = new Preference(driver);
        p.preferenceDependencies.click();
//        driver.findElementByXPath("//android.widget.TextView[@text='3. Preference dependencies']").click();
        driver.findElementById("android:id/checkbox").click();
        driver.findElementByXPath("(//android.widget.RelativeLayout)[2]").click();
        driver.findElementByClassName("android.widget.EditText").sendKeys(input);
        driver.findElementsByClassName("android.widget.Button").get(1).click();
        service.stop();
    }
}

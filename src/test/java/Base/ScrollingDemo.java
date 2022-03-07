package Base;

import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.android.AndroidElement;

import java.io.IOException;
import java.net.MalformedURLException;

public class ScrollingDemo extends baseTest {
    public static void main(String[] args) throws IOException, InterruptedException {

        AndroidDriver<AndroidElement> driver = capabilities(loadPropertiesFile("deviceAppName"));
        driver.findElementByXPath("//android.widget.TextView[@text='Views']").click();
//        driver.findElementByAndroidUIAutomator("text(\"Views\")").click();
        driver.findElementByAndroidUIAutomator("new UiScrollable(new UiSelector()).scrollIntoView(text(\"Radio Group\"));");



    }
}

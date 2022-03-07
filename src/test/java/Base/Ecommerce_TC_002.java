package Base;

import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.android.AndroidElement;

import java.io.IOException;
import java.net.MalformedURLException;
import java.sql.Time;
import java.util.concurrent.TimeUnit;

public class Ecommerce_TC_002 extends baseTest{
    public static void main(String[] args) throws IOException, InterruptedException {

        AndroidDriver<AndroidElement> driver = capabilities(loadPropertiesFile("deviceAppName"));
        driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
        driver.findElementByAndroidUIAutomator("text(\"Let's  Shop\")").click();
        String toastMsg = driver.findElementByXPath("//android.widget.Toast[1]").getAttribute("name");
        System.out.println(toastMsg);

    }
}

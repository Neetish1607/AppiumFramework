package Base;

import io.appium.java_client.TouchAction;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.android.AndroidElement;
import org.openqa.selenium.WebElement;

import java.io.IOException;
import java.net.MalformedURLException;
import java.util.concurrent.TimeUnit;

import static io.appium.java_client.touch.offset.ElementOption.element;

public class DragAndDrop extends baseTest {
    public static void main(String[] args) throws IOException, InterruptedException {

        AndroidDriver<AndroidElement> driver = capabilities(loadPropertiesFile("deviceAppName"));
        driver.findElementByXPath("//android.widget.TextView[@text='Views']").click();
        driver.manage().timeouts().implicitlyWait(3, TimeUnit.SECONDS);
        driver.findElementByXPath("//android.widget.TextView[@text='Drag and Drop']").click();
        WebElement source = driver.findElementsByClassName("android.view.View").get(0);
        WebElement destination = driver.findElementsByClassName("android.view.View").get(1);

        TouchAction t = new TouchAction(driver);
        t.longPress(element(source)).moveTo(element(destination)).release().perform();


    }
}

package IOSAutomation;

import io.appium.java_client.MobileElement;
import io.appium.java_client.ios.IOSDriver;
import io.appium.java_client.ios.IOSElement;
import io.appium.java_client.ios.IOSTouchAction;
import io.appium.java_client.touch.LongPressOptions;
import io.appium.java_client.touch.TapOptions;
import io.appium.java_client.touch.offset.ElementOption;
import org.testng.Assert;

import java.net.MalformedURLException;
import java.time.Duration;

public class IOSSliderTest extends IOSBaseTest{

    public static void main(String[] args) throws MalformedURLException {
        IOSDriver driver = capabilities();
        driver.findElementByAccessibilityId("Sliders").click();
        IOSElement slider = (IOSElement) driver.findElementByXPath("//XCUIElementTypeSlider");
        slider.setValue("0%");
        slider.setValue("1%");
        Assert.assertEquals("100%",slider.getAttribute("value"));

        MobileElement e =(MobileElement) driver.findElementByName("");
        IOSTouchAction touch = new IOSTouchAction(driver);
        touch.longPress(LongPressOptions.longPressOptions().withElement(ElementOption.element(e)).withDuration(Duration.ofSeconds(2))).release().perform();

        MobileElement tap = (MobileElement) driver.findElementByXPath("");
        touch.tap(TapOptions.tapOptions().withElement(ElementOption.element(tap))).perform();

    }
}

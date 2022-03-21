package IOSAutomation;

import io.appium.java_client.ios.IOSDriver;
import io.appium.java_client.remote.AutomationName;
import io.appium.java_client.remote.IOSMobileCapabilityType;
import io.appium.java_client.remote.MobileCapabilityType;
import org.openqa.selenium.remote.DesiredCapabilities;

import java.net.MalformedURLException;
import java.net.URL;

public class IOSBaseTest {

    public static IOSDriver capabilities() throws MalformedURLException {

        DesiredCapabilities cap = new DesiredCapabilities();
        cap.setCapability(MobileCapabilityType.PLATFORM_NAME,"15.2");
        cap.setCapability(MobileCapabilityType.DEVICE_NAME,"iPhone 12 Pro Max");
        cap.setCapability(MobileCapabilityType.AUTOMATION_NAME, AutomationName.IOS_XCUI_TEST);
        cap.setCapability(IOSMobileCapabilityType.LAUNCH_TIMEOUT,50000);
        cap.setCapability("commandTimeouts","12000");
        cap.setCapability(MobileCapabilityType.APP,"UIKitCatalog.app");
        IOSDriver driver = new IOSDriver(new URL("http://localhost:4723/wd/hub"),cap);
        return driver;
    }
}

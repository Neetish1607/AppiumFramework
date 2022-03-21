package IOSAutomation;

import io.appium.java_client.ios.IOSDriver;

import java.net.MalformedURLException;

public class AppiumClickTest extends IOSBaseTest{

    public static void main(String[] args) throws MalformedURLException {

        IOSDriver driver = capabilities();
        driver.findElementByAccessibilityId("Alert Views").click();
        driver.findElementByXPath("//XCUIElementTypeStaticText[@name='Text Entry']").click();
        driver.findElementByXPath("XCUIElementTypeCell").sendKeys("hello");
        driver.findElementByAccessibilityId("OK").click();
        driver.findElementByAccessibilityId("Confirm / Cancel").click();
        System.out.println(driver.findElementByXPath("//*[contains(@name,'message')]").getText());
        driver.findElementByAccessibilityId("Confirm").click();
    }
}

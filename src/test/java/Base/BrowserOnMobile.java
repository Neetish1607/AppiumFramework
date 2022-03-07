package Base;

import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.android.AndroidElement;
import io.appium.java_client.remote.MobileCapabilityType;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.testng.Assert;

import java.net.MalformedURLException;


public class BrowserOnMobile extends chromeOnMobileBaseTest{

    public static void main(String[] args) throws MalformedURLException {

       AndroidDriver<AndroidElement> driver = capabilities();
       driver.get("https://rahulshettyacademy.com/angularAppdemo/");
       driver.findElementByCssSelector(".navbar-toggler-icon").click();
       driver.findElement(By.cssSelector("a[href*='products']")).click();
       JavascriptExecutor js = (JavascriptExecutor) driver;
       js.executeScript("window.scrollBy(0,1000)","");
       String text = driver.findElement(By.xpath("(//li[@class='list-group-item'])[3]/div/div/a")).getText();
        Assert.assertEquals(text,"Devops");

    }
}

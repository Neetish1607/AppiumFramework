package Base;

import io.appium.java_client.TouchAction;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.android.AndroidElement;
import io.appium.java_client.android.nativekey.AndroidKey;
import io.appium.java_client.android.nativekey.KeyEvent;
import io.appium.java_client.touch.LongPressOptions;
import io.appium.java_client.touch.TapOptions;
import io.appium.java_client.touch.offset.ElementOption;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebElement;
import org.testng.Assert;

import java.io.IOException;
import java.net.MalformedURLException;
import java.time.Duration;
import java.util.Set;
import java.util.concurrent.TimeUnit;

public class Ecommerce_TC_005 extends baseTest{
    public static void main(String[] args) throws InterruptedException, IOException {
        AndroidDriver<AndroidElement> driver = capabilities(loadPropertiesFile("deviceAppName"));
        driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
        driver.findElement(By.id("android:id/text1")).click();
        driver.findElementByAndroidUIAutomator("new UiScrollable(new UiSelector()).scrollIntoView(text(\"Argentina\"));");
        driver.findElement(By.xpath("//*[@text='Argentina']")).click();
        driver.findElement(By.id("com.androidsample.generalstore:id/nameField")).sendKeys("Hello");
        driver.hideKeyboard();
        driver.findElement(By.xpath("//*[@text='Female']")).click();
        driver.findElementByAndroidUIAutomator("text(\"Let's  Shop\")").click();
        driver.findElementsByXPath("//*[@text='ADD TO CART']").get(0).click();
        driver.findElementsByXPath("//*[@text='ADD TO CART']").get(0).click();
        driver.findElementById("com.androidsample.generalstore:id/appbar_btn_cart").click();
        Thread.sleep(4000);
        double sum =0;
        for(int i =0;i<driver.findElementsById("com.androidsample.generalstore:id/productPrice").size();i++){
            Double amount = Double.parseDouble(driver.findElementsById("com.androidsample.generalstore:id/productPrice").get(i).getText().substring(1));
            sum = sum + amount;
        }
        System.out.println(sum);
        double totalAmount = Double.parseDouble(driver.findElementById("com.androidsample.generalstore:id/totalAmountLbl").getText().substring(1));
        System.out.println(totalAmount);
        Assert.assertEquals(sum,totalAmount);

        WebElement checkBox = driver.findElementByClassName("android.widget.CheckBox");
        TouchAction checkBoxTap = new TouchAction(driver);
        checkBoxTap.tap(TapOptions.tapOptions().withElement(ElementOption.element(checkBox))).perform();

        WebElement tnC = driver.findElementById("com.androidsample.generalstore:id/termsButton");
        TouchAction t = new TouchAction(driver);
        t.longPress(LongPressOptions.longPressOptions().withElement(ElementOption.element(tnC)).withDuration(Duration.ofSeconds(2))).release().perform();
        driver.findElementById("android:id/button1").click();

        driver.findElementByClassName("android.widget.Button").click();
        Thread.sleep(9000);
        Set<String> contexts = driver.getContextHandles();
        for(String context : contexts){
            System.out.println(context);
        }
        driver.context("WEBVIEW_com.androidsample.generalstore");
        driver.findElementByName("q").sendKeys("hello");
        driver.findElementByName("q").sendKeys(Keys.ENTER);
        driver.pressKey(new KeyEvent(AndroidKey.BACK));
    }
}

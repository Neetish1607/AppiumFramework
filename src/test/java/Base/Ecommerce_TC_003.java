package Base;

import io.appium.java_client.MobileBy;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.android.AndroidElement;
import org.openqa.selenium.By;
import org.testng.Assert;

import java.io.IOException;
import java.net.MalformedURLException;
import java.util.concurrent.TimeUnit;

public class Ecommerce_TC_003 extends baseTest{
    public static void main(String[] args) throws IOException, InterruptedException {

        AndroidDriver<AndroidElement> driver = capabilities(loadPropertiesFile("deviceAppName"));
        driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
        driver.findElement(By.id("android:id/text1")).click();
        driver.findElementByAndroidUIAutomator("new UiScrollable(new UiSelector()).scrollIntoView(text(\"Argentina\"));");
        driver.findElement(By.xpath("//*[@text='Argentina']")).click();
        driver.findElement(By.id("com.androidsample.generalstore:id/nameField")).sendKeys("Hello");
        driver.hideKeyboard();
        driver.findElement(By.xpath("//*[@text='Female']")).click();
        driver.findElementByAndroidUIAutomator("text(\"Let's  Shop\")").click();
//        com.androidsample.generalstore:id/rvProductList
        driver.findElement(MobileBy.AndroidUIAutomator("new UiScrollable(new UiSelector().scrollable(true).instance(0)).scrollIntoView(new UiSelector().textMatches(\"Jordan 6 Rings\").instance(0))"));
        int count = driver.findElementsById("com.androidsample.generalstore:id/productName").size();
        for(int i = 0;i<count;i++){
            String text = driver.findElementsById("com.androidsample.generalstore:id/productName").get(i).getText();
            if(text.equalsIgnoreCase("Jordan 6 Rings")){
                driver.findElementsById("com.androidsample.generalstore:id/productAddCart").get(i).click();
                break;
            }
        }
        driver.findElementById("com.androidsample.generalstore:id/appbar_btn_cart").click();

        String cartPageItemText = driver.findElementById("com.androidsample.generalstore:id/productName").getText();
        Assert.assertEquals("Jordan 6 Rings",cartPageItemText);
    }
}

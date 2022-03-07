package Base;

import Utils.Utilities;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.android.AndroidElement;
import org.testng.Assert;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;
import pageObjects.CheckoutPage;
import pageObjects.FormPage;

import java.io.IOException;
import java.util.concurrent.TimeUnit;

public class Ecommerce_TC_004 extends baseTest{

    public static AndroidDriver<AndroidElement> driver;

    @BeforeTest
    public void killAllNodes() throws IOException, InterruptedException {
        Runtime.getRuntime().exec("taskkill /F /IM node.exe");
        Thread.sleep(3000);
    }

    @Test
    public void TotalAmountValidation() throws IOException, InterruptedException {
        service = startServer(); // This also we can wrap in a condition as on cloud we do not need this but on local we need !
        AndroidDriver<AndroidElement> driver = runCapabilities(loadPropertiesFile("deviceAppName"),false);
        driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
        FormPage formPage = new FormPage(driver);
        Utilities utilities = new Utilities(driver);
        driver.manage().timeouts().implicitlyWait(15, TimeUnit.SECONDS);
        formPage.textButtonClick.click();
//        utilities.scrollTillTextVisible("Argentina");
        driver.findElementByAndroidUIAutomator("new UiScrollable(new UiSelector()).scrollIntoView(text(\"Argentina\"));");
        formPage.countrySelection.click();
        formPage.getInputField().sendKeys("hello");
        driver.hideKeyboard();
        formPage.buttonSelection.click();
        formPage.getLetsShopButton().click();
        CheckoutPage checkoutPage = new CheckoutPage(driver);
        checkoutPage.getAddToCartList().get(0).click();
        checkoutPage.getAddToCartList().get(0).click();
        checkoutPage.getCartButton().click();
        Thread.sleep(4000);
        double sum =0;
        for(int i =0;i<checkoutPage.getProductPrice().size();i++){
            Double amount = Double.parseDouble(checkoutPage.getProductPrice().get(i).getText().substring(1));
            sum = sum + amount;
        }
        System.out.println(sum);
        double totalAmount = Double.parseDouble(checkoutPage.getTotalAmt().getText().substring(1));
        System.out.println(totalAmount);
        Assert.assertEquals(sum,totalAmount);
        service.stop(); // This also we can wrap in a condition as on cloud we do not need this but on local we need !
    }
}

package pageObjects;

import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.android.AndroidElement;
import io.appium.java_client.pagefactory.AndroidFindBy;
import io.appium.java_client.pagefactory.AppiumFieldDecorator;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.PageFactory;

public class FormPage {

    public AndroidDriver<AndroidElement> driver;

    public FormPage(AndroidDriver<AndroidElement> driver){
        PageFactory.initElements(new AppiumFieldDecorator(driver),this);
        this.driver = driver;
    }

    @AndroidFindBy(id = "android:id/text1")
    public WebElement textButtonClick;

    @AndroidFindBy(xpath = "//*[@text='Argentina']")
    public WebElement countrySelection;

    @AndroidFindBy(id = "com.androidsample.generalstore:id/nameField")
    public WebElement inputField;

    @AndroidFindBy(xpath = "//*[@text='Female']")
    public WebElement buttonSelection;

    @AndroidFindBy(uiAutomator = "text(\"Let's  Shop\")")
    public WebElement letsShopButton;

    public WebElement getTextButtonClick(){
        return textButtonClick;
    }

    public WebElement getCountrySelection(){
        return countrySelection;
    }

    public WebElement getInputField(){
        return inputField;
    }

    public WebElement getButtonSelection(){
        return buttonSelection;
    }

    public WebElement getLetsShopButton(){
        return letsShopButton;
    }

}

package Base;

import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.android.AndroidElement;
import io.appium.java_client.remote.MobileCapabilityType;
import io.appium.java_client.service.local.AppiumDriverLocalService;
import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.remote.DesiredCapabilities;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.net.ServerSocket;
import java.net.URL;
import java.util.Properties;
import java.util.concurrent.TimeUnit;

public class baseTest {

    public static AppiumDriverLocalService service;
    public static AndroidDriver<AndroidElement> driver;

    public AppiumDriverLocalService startServer(){
        boolean flag = checkIfServerIsRunning(4723);
        if(!flag){
            service = AppiumDriverLocalService.buildDefaultService();
            service.start();
        }
        return service;
    }

    public static void startEmulator() throws IOException, InterruptedException {
        Runtime.getRuntime().exec(System.getProperty("user.dir") + "\\src\\main\\resources\\startEmulator.bat");
        Thread.sleep(10000);
    }

    public static boolean checkIfServerIsRunning(int port){
        boolean isServerRunning = false;
        ServerSocket serverSocket;
        try{
            serverSocket = new ServerSocket(port);
            serverSocket.close();
        }catch (IOException e){
            isServerRunning = true;
        } finally{
            serverSocket = null;
        }
        return isServerRunning;
    }

    public static String loadPropertiesFile(String name) throws IOException {

        FileInputStream fis = new FileInputStream(System.getProperty("user.dir")+"\\"+"global.properties");
        Properties prop = new Properties();
        prop.load(fis);
        return prop.getProperty(name);

    }

    public AndroidDriver<AndroidElement> runCapabilities(String appName, boolean cloud) throws IOException, InterruptedException {
        if(cloud){
            return cloudCapabilities(appName);
        }
        else {
           return capabilities(appName);
        }
    }

    public static AndroidDriver<AndroidElement> cloudCapabilities(String deviceAppName) throws IOException, InterruptedException {
//        File appDir = new File("src");
//        File app = new File(appDir,deviceAppName);
        DesiredCapabilities cap = new DesiredCapabilities();
        if(loadPropertiesFile("deviceName").contains("Emulator")){
            startEmulator();
        }
        String deviceName = System.getProperty("deviceName")!=null && !System.getProperty("deviceName").isEmpty()?System.getProperty("deviceName")
                :loadPropertiesFile("deviceName");

        cap.setCapability("browserstack.user","username");
        cap.setCapability("browserstack.key","userkey");
        cap.setCapability("app","app_url");
        cap.setCapability("device","Google Pixel 3");
        cap.setCapability("os_version","9.0");
        cap.setCapability(MobileCapabilityType.NEW_COMMAND_TIMEOUT,10);
        cap.setCapability(MobileCapabilityType.AUTOMATION_NAME,"uiautomator2");
        AndroidDriver<AndroidElement> driver = new AndroidDriver<>(new URL("http://hub.browserstack.com/wd/hub"),cap);
        driver.manage().timeouts().implicitlyWait(15, TimeUnit.SECONDS);
        return driver;
    }

    public static AndroidDriver<AndroidElement> capabilities(String deviceAppName) throws IOException, InterruptedException {
        File appDir = new File("src");
        File app = new File(appDir,deviceAppName);
        DesiredCapabilities cap = new DesiredCapabilities();
        if(loadPropertiesFile("deviceName").contains("Emulator")){
            startEmulator();
        }
        String deviceName = System.getProperty("deviceName")!=null && !System.getProperty("deviceName").isEmpty()?System.getProperty("deviceName")
                :loadPropertiesFile("deviceName");
        cap.setCapability(MobileCapabilityType.DEVICE_NAME,deviceName);
        cap.setCapability(MobileCapabilityType.NEW_COMMAND_TIMEOUT,10);
        cap.setCapability(MobileCapabilityType.APP,app.getAbsolutePath());
        cap.setCapability(MobileCapabilityType.AUTOMATION_NAME,"uiautomator2");
        AndroidDriver<AndroidElement> driver = new AndroidDriver<>(new URL("http://127.0.0.1:4723/wd/hub"),cap);
        driver.manage().timeouts().implicitlyWait(15, TimeUnit.SECONDS);
        return driver;
    }

    public static void getScreenshot(String testCaseName) throws IOException {
        File file = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);
        FileUtils.copyFile(file, new File(System.getProperty("user.dir") + "\\" + testCaseName + ".png"));
    }

}

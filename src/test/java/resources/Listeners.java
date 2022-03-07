package resources;

import org.testng.ITestListener;
import org.testng.ITestResult;

import java.io.IOException;

import static Base.baseTest.getScreenshot;

public class  Listeners implements ITestListener {

    @Override
    public void onTestFailure(ITestResult result) {

        String s = result.getName();
        try {
            getScreenshot(s);
        } catch (IOException e) {
            e.printStackTrace();
        }

    }
}

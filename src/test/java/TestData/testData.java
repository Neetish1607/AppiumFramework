package TestData;

import org.testng.annotations.DataProvider;

public class testData {
    @DataProvider(name = "inputData")
    public Object[][] getData(){
        Object[][] obj = new Object[][]
                {
                        {"hello"},{"!@!@#!@"}
                };
        return obj;
    }
}

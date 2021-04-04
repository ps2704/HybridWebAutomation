package FrameWork;

import org.testng.ITestContext;
import org.testng.ITestResult;
import org.testng.Reporter;
import org.testng.TestListenerAdapter;
import com.relevantcodes.extentreports.ExtentTest;

import java.util.LinkedHashMap;
public class TestListner extends TestListenerAdapter{

    public static ThreadLocal<ExtentTest> testing = new ThreadLocal<ExtentTest>()
    {
        @Override
        protected  ExtentTest initialValue()
        {
            return null ;

        }
    };

    public static ThreadLocal<LinkedHashMap<String, ExtentTest>> extentMap = new ThreadLocal<LinkedHashMap<String, ExtentTest>>() {
        @Override
        protected LinkedHashMap<String, ExtentTest> initialValue() {
            return new LinkedHashMap<String, ExtentTest>();

        }
    };

    public static ExtentTest getExtentTest() {
        return testing.get();
    }

    public ExtentTest startChild(String childName)
    {
        ExtentTest childTest= Framework.extentReports.startTest(childName);
        testing.set(childTest);
        //       System.out.println(testing.get().toString());
        return testing.get();
    }

    public void onTestStart(ITestResult result) {
        // TODO Auto-generated method stub

        Reporter.log("*******************************************************************", true);
        Reporter.log("TEST CASE : " + result.getName().replace("_", " "), true);
        Reporter.log("*******************************************************************", true);
        ExtentTest test = null;
        if (TestRunner.parentExtentMap == null || TestRunner.parentExtentMap.isEmpty() || !TestRunner.parentExtentMap.containsKey(result.getName().replace("_", " "))) {
            TestRunner.parentExtentMap.put(result.getName().replace("_", " "), Framework.extentReports.startTest(result.getName().replace("_", " ")));
        }
        test = TestRunner.parentExtentMap.get(result.getName().replace("_", " "));
        testing.set(test);
    }

    public void onTestSuccess(ITestResult result) {
        if(!extentMap.get().isEmpty()) {
            for (String key : extentMap.get().keySet()) {
                //                  System.out.println(extentMap.get());
                //                  System.out.println(key);
                if (key.contains("child")) {
                    TestRunner.parentExtentMap.get(result.getName().replace("_", " ")).appendChild((ExtentTest) extentMap.get().get(key));
                }
            }
            Framework.extentReports.endTest(TestRunner.parentExtentMap.get(result.getName().replace("_", " ")));
            extentMap.get().clear();
        }
    }

    public void onTestFailure(ITestResult result) {
        if(!extentMap.get().isEmpty()){
            for(String key:extentMap.get().keySet())
            {
                //            System.out.println( key);
                if( key.contains("child"))
                {
                    //extentMap.get().get("parent").appendChild((ExtentTest) extentMap.get().get(key));
                    TestRunner.parentExtentMap.get(result.getName().replace("_", " ")).appendChild((ExtentTest) extentMap.get().get(key));
                }
            }
            Framework.extentReports.endTest(TestRunner.parentExtentMap.get(result.getName().replace("_", " ")));
            extentMap.get().clear();
            System.out.println("failed method end");
        }
    }

    public void onTestSkipped(ITestResult result) {
        for(String key:extentMap.get().keySet()){
            //      System.out.println( key);
            if( key.contains("child"))
            {
                //extentMap.get().get("parent").appendChild((ExtentTest) extentMap.get().get(key));
                TestRunner.parentExtentMap.get(result.getName().replace("_", " ")).appendChild((ExtentTest) extentMap.get().get(key));
            }
        }
        //Framework.extentReports.endTest(TestRunner.parentExtentMap.get(result.getName().replace("_", " ")));
        extentMap.get().clear();
    }

    public void onTestFailedButWithinSuccessPercentage(ITestResult result) {
        // TODO Auto-generated method stub

    }

    public void onStart(ITestContext context) {
        // TODO Auto-generated method stub

    }



}

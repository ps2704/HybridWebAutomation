package FrameWork;

import io.qameta.allure.Attachment;
import org.apache.commons.lang.exception.ExceptionUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.testng.*;
import com.relevantcodes.extentreports.ExtentTest;

import java.io.IOException;
import java.sql.DriverManager;
import java.util.LinkedHashMap;

public class TestListner implements ITestListener {

    public static ThreadLocal<ExtentTest> testing = new ThreadLocal<ExtentTest>() {
        @Override
        protected ExtentTest initialValue() {
            return null;

        }
    };

    public static ThreadLocal<LinkedHashMap<String, ExtentTest>> extentMap = new ThreadLocal<LinkedHashMap<String, ExtentTest>>() {
        @Override
        protected LinkedHashMap<String, ExtentTest> initialValue() {
            return new LinkedHashMap<String, ExtentTest>();

        }
    };

    @Attachment(value = "Page screenshot", type = "image/png")
    public byte[] saveScreenshotPNG(WebDriver driver) {
        return ((TakesScreenshot) driver).getScreenshotAs(OutputType.BYTES);
    }

    // Text attachments for Allure
    @Attachment(value = "{0}", type = "text/plain")
    public static String saveTextLog(String message) {
        return message;
    }

    // HTML attachments for Allure
    @Attachment(value = "{0}", type = "text/html")
    public static String attachHtml(String html) {
        return html;
    }

    public static ExtentTest getExtentTest() {
        return testing.get();
    }

    public ExtentTest startChild(String childName) {
        ExtentTest childTest = Framework.extentReports.startTest(childName);
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
        if (!extentMap.get().isEmpty()) {
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

    public ExtentTest startParent(String ParentName) {
        if (TestRunner.parentExtentMap == null || TestRunner.parentExtentMap.isEmpty() || !TestRunner.parentExtentMap.containsKey(ParentName.replace("_", " "))) {
            System.out.println(TestRunner.parentExtentMap.toString());
            TestRunner.parentExtentMap.put(ParentName.replace("_", " "), Framework.extentReports.startTest(ParentName.replace("_", " ")));
        }
        ExtentTest test = null;
        test = TestRunner.parentExtentMap.get(ParentName.replace("_", " "));
        testing.set(test);
        //System.out.println(testing.get().toString());
        return testing.get();
    }

    public void childParentLink(String Parent) {
        if (!extentMap.get().isEmpty()) {
            for (String key : extentMap.get().keySet()) {
                //System.out.println(extentMap.get());
                //System.out.println(key);
                //System.out.println(TestRunner.parentExtentMap.get("parentTest1"));
                if (key.contains(Parent)) {
                    TestRunner.parentExtentMap.get("parentTest1").appendChild((ExtentTest) extentMap.get().get(key));
                }
            }

        }
        extentMap.remove();
    }

    public void onTestFailure(ITestResult result) {
        JiraPolicy jiraPolicy = result.getMethod().getConstructorOrMethod().getMethod().getAnnotation(JiraPolicy.class);
        boolean isTicketReady = jiraPolicy.logTicketReady();
        if (!extentMap.get().isEmpty() || isTicketReady) {
            System.out.println("is ticket ready for JIRA: " + isTicketReady);
            JiraServiceProvider jiraSp = new JiraServiceProvider("https://testusedjira.atlassian.net",
                    "ps8940@gmail.com", "kNBkKl0zhKQjc5vxGAVyF9B3", "TEST");
            String issueSummary = result.getMethod().getConstructorOrMethod().getMethod().getName()
                    +  " got failed due to some assertion or exception";
            String issueDescription = result.getThrowable().getMessage() + "\n";
            issueDescription.concat(ExceptionUtils.getFullStackTrace(result.getThrowable()));

            jiraSp.createJiraTicket("Bug", issueSummary, issueDescription, "Pradeep Singh");
            System.out.println("Screenshot captured for test case:" + result.getMethod());
            WebDriver driver = null;
            saveScreenshotPNG(driver);


            for (String key : extentMap.get().keySet()) {
                //            System.out.println( key);
                if (key.contains("child")) {
                    //extentMap.get().get("parent").appendChild((ExtentTest) extentMap.get().get(key));
                    TestRunner.parentExtentMap.get(result.getName().replace("_", " ")).appendChild((ExtentTest) extentMap.get().get(key));

                }
            }
            Framework.extentReports.endTest(TestRunner.parentExtentMap.get(result.getName().replace("_", " ")));
            extentMap.get().clear();
            System.out.println("failed method end");
            saveTextLog(result.getMethod() + " failed and screenshot taken!");
        }



    }

    public void onTestSkipped(ITestResult result) {
        for (String key : extentMap.get().keySet()) {
            //      System.out.println( key);
            if (key.contains("child")) {
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

    @Override
    public void onFinish(ITestContext context) {

    }


}

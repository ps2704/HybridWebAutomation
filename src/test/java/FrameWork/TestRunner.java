package FrameWork;

import TestSuite.Demo;

import TestSuite.MDemo;
import com.relevantcodes.extentreports.ExtentTest;
import org.testng.TestListenerAdapter;
import org.testng.TestNG;
import org.testng.annotations.Test;
import org.testng.xml.XmlSuite;
import sun.misc.Cache;

import java.util.HashMap;


public class TestRunner {

    public static volatile HashMap<String, ExtentTest> parentExtentMap=new HashMap<String,ExtentTest>();

    public static void main(String args[]){
//   @Test
//  public void registrationFLow(){
        try {
            String TestcaseIds = "";
            if(System.getProperty("TestGroup")!=null){
                TestcaseIds= System.getProperty("TestGroup");
            }else {
               // TestcaseIds = "MDemotest";
               // TestcaseIds = "BrokenLink";
                TestcaseIds = "Demo";
            }
            TestListenerAdapter tla = new TestListenerAdapter();
            TestNG testNG =new TestNG();
            TestListner testListner = new TestListner();
            testNG.addListener(testListner);
           // testNG.setParallel(XmlSuite.ParallelMode.TESTS);
            testNG.setThreadCount(2);
            if(System.getProperty("TestGroup")!=null){
                testNG.setGroups(System.getProperty("TestGroup"));
            }else {
                testNG.setGroups(TestcaseIds);
                // testNG.setGroups(Common_Constants.Platfrom_name);
            }
            testNG.setParallel(XmlSuite.ParallelMode.METHODS);

            testNG.setDataProviderThreadCount(2);

           // testNG.setThreadCount(2);
            testNG.setVerbose(12);
            testNG.setTestClasses(new Class[] {Demo.class, Framework.class, MDemo.class});
            testNG.run();
        } catch(Exception e){
            System.out.println(e.toString());
        }

    }


}

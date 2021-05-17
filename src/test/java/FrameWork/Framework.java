package FrameWork;
//Author - Sourabh Shreemali
import Data.EnvironmentParameterData;
import com.relevantcodes.extentreports.ExtentReports;
import com.relevantcodes.extentreports.ExtentTest;
import com.relevantcodes.extentreports.LogStatus;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.remote.MobileCapabilityType;
import io.github.bonigarcia.wdm.WebDriverManager;
import org.apache.commons.io.FileUtils;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.hibernate.*;
import org.hibernate.cfg.Configuration;
import org.hibernate.criterion.Restrictions;
import org.openqa.selenium.Dimension;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxProfile;
import org.openqa.selenium.logging.LogType;
import org.openqa.selenium.logging.LoggingPreferences;
import org.openqa.selenium.remote.CapabilityType;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.*;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeSuite;

import java.awt.*;
import java.io.File;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.*;
import java.util.List;
import java.util.logging.Level;

public class Framework {
    public static ExtentReports extentReports;
    DataBaseActivity dbActivity = new DataBaseActivity();
    static volatile SessionFactory SessionFactory;
    WebDriver webDriver;
    DesiredCapabilities caps;
    Session session = null;

    public WebDriver getBrowser(String platform) throws Throwable {

		/* DesiredCapabilities caps = new DesiredCapabilities();
		    caps.setCapability("browser", "chrome");
		    caps.setCapability("browserstack.debug", "true");
		    caps.setCapability("build", "First build");

		    WebDriver driver = new RemoteWebDriver(new URL(URL),caps);*/
        if (platform == null) {
            platform = "Web";
        }
        String browsername;
        EnvironmentParameterData environmentData = getData(EnvironmentParameterData.class, platform);
        PropertyConfiguration propertyConfig = new PropertyConfiguration();
        Properties pf = propertyConfig.getInstance();
        browsername = pf.getProperty("BrowserName");
        browsername = environmentData.getBrowsername();
        WebDriver driver = null;
        if (browsername.equalsIgnoreCase("firefox"))
        //if(true)
        {/****commented***/
            File file = new File("src/test/resources/geckodriver.exe").getCanonicalFile();
            System.setProperty("webdriver.gecko.driver", file.getAbsolutePath().replace("\\", "\\\\"));
            FirefoxProfile ffprofile = new FirefoxProfile();
            DesiredCapabilities capabilities = new DesiredCapabilities();
            capabilities.setCapability(CapabilityType.ACCEPT_SSL_CERTS, true);
            ffprofile.setAcceptUntrustedCertificates(true);
            capabilities.setCapability("acceptInsecureCerts", true);
            ffprofile.setAssumeUntrustedCertificateIssuer(true);
            ffprofile.setPreference("dom.webnotifications.enabled", false);
            ffprofile.setPreference("security.insecure_field_warning.contextual.enabled", false);
            capabilities.setCapability(FirefoxDriver.PROFILE, ffprofile);
            driver = new FirefoxDriver(capabilities);
            driver.manage().window().maximize();

        }
        if (browsername.equalsIgnoreCase("chrome")) {

            File file;
            if (System.getProperty("os.name").contains("Mac")) {
                file = new File("src/test/resources/chromedriver").getCanonicalFile();
            } else {
                file = new File("src/test/resources/chromedriver").getCanonicalFile();
            }
            System.setProperty("webdriver.chrome.driver", file.getAbsolutePath().replace("\\", "\\\\"));
            System.out.println(file.getAbsolutePath().replace("\\", "\\\\"));
            try {
                ChromeOptions options = new ChromeOptions();
                options.addArguments("--disable-extensions");
                options.addArguments("disable-infobars");
                driver = new ChromeDriver(options);

            } catch (Exception e) {
                e.printStackTrace();
            }
            driver.manage().window().maximize();

        }

        if (browsername.equalsIgnoreCase("mobile")) {
            File file;
            if (System.getProperty("os.name").contains("Mac")) {
                file = new File("src/test/resources/chromedriver").getCanonicalFile();
            } else {
                file = new File("src/test/resources/chromedriver.exe").getCanonicalFile();
            }
            System.setProperty("webdriver.chrome.driver", file.getAbsolutePath().replace("\\", "\\\\"));
            System.out.println(file.getAbsolutePath().replace("\\", "\\\\"));
            try {
                ChromeOptions options = new ChromeOptions();
                options.addArguments("--user-agent=Mozilla/5.0 (Linux; Android 6.0; HTC One M9 Build/MRA58K) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/52.0.2743.98 Mobile Safari/537.36");
                options.addArguments("--start-maximized");
                options.addArguments("disable-infobars");
                driver = new ChromeDriver(options);
            } catch (Exception e) {
                e.printStackTrace();
            }
            Dimension d = new Dimension(500, 700);
            driver.manage().window().setSize(d);

        }

        return driver;
    }

    public WebDriver getWebBrowser(String platform) throws IOException {

		/* DesiredCapabilities caps = new DesiredCapabilities();
		    caps.setCapability("browser", "chrome");
		    caps.setCapability("browserstack.debug", "true");
		    caps.setCapability("build", "First build");

		    WebDriver driver = new RemoteWebDriver(new URL(URL),caps);*/
        if (platform == null) {
            platform = "Web";
        }
        String browsername;
        EnvironmentParameterData environmentData = getData(EnvironmentParameterData.class, platform);
        PropertyConfiguration propertyConfig = new PropertyConfiguration();
        Properties pf = propertyConfig.getInstance();
        browsername = pf.getProperty("BrowserName");
        browsername = environmentData.getBrowsername();
        WebDriver driver = null;
        if (browsername.equalsIgnoreCase("firefox"))
        //if(true)
        {/****commented***/
            // File file = new File("src/test/resources/geckodriver.exe").getCanonicalFile();
            //System.setProperty("webdriver.gecko.driver", file.getAbsolutePath().replace("\\", "\\\\"));
//            FirefoxProfile ffprofile = new FirefoxProfile();
//            DesiredCapabilities capabilities = new DesiredCapabilities();
//            capabilities.setCapability(CapabilityType.ACCEPT_SSL_CERTS, true);
//            ffprofile.setAcceptUntrustedCertificates(true);
//            capabilities.setCapability("acceptInsecureCerts", true);
//            ffprofile.setAssumeUntrustedCertificateIssuer(true);
//            ffprofile.setPreference("dom.webnotifications.enabled", false);
//            ffprofile.setPreference("security.insecure_field_warning.contextual.enabled", false);
//            capabilities.setCapability(FirefoxDriver.PROFILE, ffprofile);
            WebDriverManager.firefoxdriver().setup();
            webDriver = new FirefoxDriver();
            //driver = new FirefoxDriver(capabilities);
          //  webDriver.manage().window().maximize();

        }
        if (browsername.equalsIgnoreCase("chrome")) {

            File file;
            if (System.getProperty("os.name").contains("Mac")) {
                // file = new File("src/test/resources/chromedriver").getCanonicalFile();
                WebDriverManager.chromedriver().setup();
                webDriver.manage().window().maximize();
            } else {
                // file = new File("src/test/resources/chromedriver.exe").getCanonicalFile();
                WebDriverManager.chromedriver().setup();
                //  webDriver.manage().window().maximize();
            }
            //    System.setProperty("webdriver.chrome.driver", file.getAbsolutePath().replace("\\", "\\\\"));
            //  System.out.println(file.getAbsolutePath().replace("\\", "\\\\"));
            try {
                ChromeOptions options = new ChromeOptions();
                options.addArguments("--disable-extensions");
                options.addArguments("disable-infobars");
                webDriver = new ChromeDriver(options);

            } catch (Exception e) {
                e.printStackTrace();
            }
            webDriver.manage().window().maximize();

        }
        if (browsername.equalsIgnoreCase("browserStack")) {

            File file;
            if (System.getProperty("os.name").contains("Mac")) {
                // file = new File("src/test/resources/chromedriver").getCanonicalFile();
                WebDriverManager.chromedriver().setup();
                webDriver.manage().window().maximize();
            } else {
                // file = new File("src/test/resources/chromedriver.exe").getCanonicalFile();

                // caps.setCapability("name", methodName);
                WebDriverManager.chromedriver().setup();

                //webDriver = new RemoteWebDriver(new URL(URL),caps);

                //  webDriver.manage().window().maximize();
            }
            //    System.setProperty("webdriver.chrome.driver", file.getAbsolutePath().replace("\\", "\\\\"));
            //  System.out.println(file.getAbsolutePath().replace("\\", "\\\\"));
            try {
//                ChromeOptions options = new ChromeOptions();
//                options.addArguments("--disable-extensions");
//                options.addArguments("disable-infobars");
//                webDriver = new ChromeDriver(options);
                String USERNAME = pf.getProperty("BrowserStackUSERNAME");
                String AUTOMATE_KEY = pf.getProperty("BrowserStackAUTOMATE_KEY");
               // String url= pf.getProperty("BrowserStackURL");
                String url = "https://" + USERNAME + ":" + AUTOMATE_KEY + "@hub-cloud.browserstack.com/wd/hub";
                DesiredCapabilities caps = new DesiredCapabilities();
                caps.setCapability("browser", "chrome");
                caps.setCapability("os", "Windows");
                caps.setCapability("os_version", "10");
                caps.setCapability("browser_version", "90.0");
                webDriver = new RemoteWebDriver(new URL(url),caps);
                //   webDriver.manage().timeouts().implicitlyWait(20, TimeUnit.SECONDS);
            } catch (Exception e) {
                e.printStackTrace();
            }
            //  webDriver.manage().window().maximize();

        }


        if (browsername.equalsIgnoreCase("mobile")) {
            File file;
            if (System.getProperty("os.name").contains("Mac")) {
                // file = new File("src/test/resources/chromedriver").getCanonicalFile();
                WebDriverManager.chromedriver().setup();
                webDriver.manage().window().maximize();
            } else {
                // file = new File("src/test/resources/chromedriver.exe").getCanonicalFile();
                WebDriverManager.chromedriver().setup();
                //  webDriver.manage().window().maximize();
            }
            //    System.setProperty("webdriver.chrome.driver", file.getAbsolutePath().replace("\\", "\\\\"));
            //  System.out.println(file.getAbsolutePath().replace("\\", "\\\\"));
            try {
                ChromeOptions options = new ChromeOptions();
                options.addArguments("--disable-extensions");
                options.addArguments("disable-infobars");
                webDriver = new ChromeDriver(options);

            } catch (Exception e) {
                e.printStackTrace();
            }
            Dimension d = new Dimension(500, 700);
            webDriver.manage().window().setSize(d);

        }
        if (browsername.equalsIgnoreCase("mobileD")) {
            File file;
            if (System.getProperty("os.name").contains("Mac")) {
                file = new File("src/test/resources/chromedriver").getCanonicalFile();
            } else {
                // file = new File("src/test/resources/chromedriver.exe").getCanonicalFile();
                DesiredCapabilities caps = new DesiredCapabilities();
                caps.setCapability(MobileCapabilityType.DEVICE_NAME, "Android");
                caps.setCapability(MobileCapabilityType.BROWSER_NAME, "Chrome");
                caps.setCapability(MobileCapabilityType.VERSION, "6.0");
                //capabilitiesAndroid.setCapability(CapabilityType.PLATFORM, "Android");
                caps.setCapability(MobileCapabilityType.PLATFORM_NAME, "Android");
                caps.setCapability(MobileCapabilityType.DEVICE_NAME, "pradeep");
                // common.driver = new RemoteWebDriver(new
                // URL("http://10.179.111.50:4723/wd/hub"), capabilitiesAndroid);
                driver = new AndroidDriver(new URL("http://127.0.0.1:4723/wd/hub"), caps);
                // common.driver = new RemoteWebDriver(new URL("http://127.0.0.1:4723/wd/hub"), capabilitiesAndroid);
                // common.driver.manage().timeouts().implicitlyWait(200, TimeUnit.SECONDS);
                //WebDriverManager.chromedriver().setup();
            }
        }

        return webDriver;
    }


    public <T> List<T> getDataList(Class clazz, String ID) {
        List<T> listOfhibernateData = new ArrayList<T>();
        try {
            if (SessionFactory == null) {
                SessionFactory = getSessionInstance();
            }
        } catch (Throwable e) {
            e.printStackTrace();
        }

        try {
            session = SessionFactory.openSession();
        } catch (HibernateException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        Transaction tx = session.beginTransaction();
        Criteria criteria = session.createCriteria(clazz);
        criteria.add(Restrictions.like("testcaseid", ID));
        listOfhibernateData = criteria.list();
        tx.commit();
        session.close();
        return listOfhibernateData;

    }

    private SessionFactory getSessionInstance() throws Throwable {
        String path = new File("src/test/resources/hibernate.cfg.xml").getCanonicalPath();
        return new Configuration().configure(new File(path).getCanonicalFile()).buildSessionFactory();
    }
    public <T> T getData(Class clazz, String ID) {
        List<T> listOfhibernateData = new ArrayList<T>();
        try {
            if (SessionFactory == null) {
                SessionFactory = getSessionInstance();
            }
        } catch (Throwable e) {
            e.printStackTrace();
        }
        Session session = SessionFactory.openSession();
        Transaction tx = session.beginTransaction();
        Criteria criteria = session.createCriteria(clazz);
        criteria.add(Restrictions.like("testcaseid", ID));
        listOfhibernateData = criteria.list();
        tx.commit();
        session.flush();
        session.close();


        return listOfhibernateData.get(0);

    }
    public <T> T getOR(Class clazz, String ID) {
        List<T> listOfhibernateData = new ArrayList<T>();
        try {
            if (SessionFactory == null) {
                SessionFactory = getSessionInstance();
            }
        } catch (Throwable e) {
            e.printStackTrace();
        }
        Session session = SessionFactory.openSession();
        Transaction tx = session.beginTransaction();
        Criteria criteria = session.createCriteria(clazz);
        criteria.add(Restrictions.like("object_Name", ID));
        listOfhibernateData = criteria.list();
        tx.commit();
        session.flush();
        session.close();


        return listOfhibernateData.get(0);

    }
    @BeforeSuite(alwaysRun = true)
    public void beforeSuite() throws Throwable {
        File fileres = new File("src/test/resources/ExtentReport.html").getCanonicalFile();
        File file = new File(fileres.getAbsolutePath().replace("\\", "\\\\"));
        file.createNewFile();
        extentReports = new ExtentReports(fileres.getAbsolutePath().replace("\\", "\\\\"), true);

        SessionFactory = getSessionInstance();
        dbActivity.StartConnection();
        List<XSSFSheet> sheetlist = dbActivity.getSheetsFromExcel();
        for(XSSFSheet sheet:sheetlist)
        {
            String sheetname = sheet.getSheetName();
            dbActivity.dropTable(sheetname);

        }

        //dbActivity.dropTableFromGsheet();
        String test = System.getProperty("base_url");
        if(!(test==null)){
            dbActivity.createTableFromGSpreadsheets();
            System.out.println("Getting Value from Google-Sheet");
        }else{
            dbActivity.dumpDataIntoDatabase();
            System.out.println("Getting Value from Local Sheet");
        }
        // dbActivity.createTableFromGSpreadsheets();
        //dbActivity.dumpDataIntoDatabase();
        //    GoogleSheets gs = new GoogleSheets();

        //  com.google.gdata.data.spreadsheet.SpreadsheetEntry Worksheet = GoogleSheets.getSpreadSheet("automation Regression Coverage");
        //com.google.gdata.data.spreadsheet.SpreadsheetEntry Worksheet1 = GoogleSheets.getSpreadSheet("Automation Data");
        //com.google.gdata.data.spreadsheet.SpreadsheetEntry Worksheet2 = GoogleSheets.getSpreadSheet("Sanity Checklist");
        //gs.clearField(Worksheet,"DeskTop" ,"Result");
        //  gs.clearField(Worksheet1, "APIData", "Result");
        //gs.clearField(Worksheet2,"Desktop" ,"AutomationResult");
        //gs.clearField(Worksheet2,"Msite" ,"AutomationResult");
    }


    public <T> List<T>  getIndependentAPIdata(Class clazz, String ID) {
        List<T> listOfhibernateData = new ArrayList<T>();
        try {
            if (SessionFactory == null) {
                SessionFactory = getSessionInstance();
            }
        } catch (Throwable e) {
            e.printStackTrace();
        }

        Session session = null;
        try {
            session = SessionFactory.openSession();
        } catch (HibernateException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        Transaction tx = session.beginTransaction();
        Criteria criteria = session.createCriteria(clazz);
        criteria.add(Restrictions.ilike("NeedtoExecute", ID));
        // Below Action did because only "Status" - "Blank" only be taking care in Independent data.
        if (ID == "TRUE") {
            ID = "";
        }
        criteria.add(Restrictions.in("IsDependent", ID, "null"));
        criteria.add(Restrictions.in("Prerequisite", ID, "null"));
        listOfhibernateData = criteria.list();
        tx.commit();
        session.close();
        List<T> DataList = new ArrayList<T>();
        for (T data : listOfhibernateData) {
            DataList.add((T) new Object[]{data});
        }

        return DataList;


    }


	/*	@AfterTest(alwaysRun=true)

	public void AfterTest(){
		SessionFactory.close();
	}*/

    public <T> List<T> getdependentAPIdata(Class clazz, String ID) {
        List<T> listOfhibernateData = new ArrayList<T>();
        try {
            if (SessionFactory == null) {
                SessionFactory = getSessionInstance();
            }
        } catch (Throwable e) {
            e.printStackTrace();
        }

        Session session = null;
        try {
            session = SessionFactory.openSession();
        } catch (HibernateException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        Transaction tx = session.beginTransaction();
        Criteria criteria = session.createCriteria(clazz);
        criteria.add(Restrictions.ilike("NeedtoExecute", ID));
        criteria.add(Restrictions.ilike("IsDependent", ID));
        if (ID == "TRUE") {
            ID = "null";
        }
        //criteria.add(Restrictions.ilike("Prerequisite", ID));
        listOfhibernateData = criteria.list();
        tx.commit();
        session.close();

        return listOfhibernateData;


    }


    @AfterSuite(alwaysRun = true)
    public void SessionTearDown() throws Throwable {
        //CreateExcelForAnalyticsData();
        extentReports.flush();
        //extentReports.close();
        SessionFactory.close();
       // dbActivity.dropTableFromGsheet();
        //dbActivity.afterTest();

        System.out.println(SessionFactory.isClosed());

        //to copy html report as backup
        File dest = null;
        try {
            DateFormat dateFormat = new SimpleDateFormat("dd_MM_HH_mm");
            Date date = new Date();
            File source = new File("src/test/resources/ExtentReport.html");
            dest = new File("src/test/resources/ExtentReports");

            FileUtils.copyFileToDirectory(source, dest);
            File oldfile = new File("src/test/resources/ExtentReports/ExtentReport.html");
            File newfile = new File("src/test/resources/ExtentReports/Report_" + dateFormat.format(date) + ".html");
            oldfile.renameTo(newfile);
        } catch (Exception e) {
            e.printStackTrace();
        }

        try {
            File[] files = dest.listFiles();

            Arrays.sort(files, new Comparator<File>() {
                public int compare(File f1, File f2) {
                    return Long.compare(f2.lastModified(), f1.lastModified());
                }
            });

            if (files.length > 50) {
                for (int i = files.length - 1; i >= 50; i--) {
                    files[i].delete();
                }
            }

        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            //killDriverProcess();
        }


    }


}

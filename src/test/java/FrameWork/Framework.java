package FrameWork;

import Data.EnvironmentParameterData;
import com.relevantcodes.extentreports.ExtentReports;
import com.relevantcodes.extentreports.ExtentTest;
import com.relevantcodes.extentreports.LogStatus;
import io.appium.java_client.MobileElement;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.ios.IOSDriver;
import io.appium.java_client.remote.MobileCapabilityType;
import io.appium.java_client.remote.MobilePlatform;
import io.appium.java_client.service.local.AppiumDriverLocalService;
import io.appium.java_client.service.local.AppiumServiceBuilder;
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
import java.util.concurrent.TimeUnit;
import java.util.logging.Level;

public class Framework {
    public static ExtentReports extentReports;
    DataBaseActivity dbActivity = new DataBaseActivity();
    static volatile SessionFactory SessionFactory;
    WebDriver webDriver;
    //AndroidDriver<MobileElement> androiddriver;
    DesiredCapabilities caps;
    Session session = null;

    public AndroidDriver getAndroidDriver() throws IOException, InterruptedException {

        DesiredCapabilities capabilities = new DesiredCapabilities();
        AppiumDriverLocalService appiumService = null;
        String appiumServiceUrl = null;
        String deviceName = null;
        AndroidDriver driver = null;
        if (CommonConstant.Local_run) {
            deviceName = AdbDevice.getDeviceInstance().getDevice();

            appiumService =getAppiumServiceAnyport(deviceName);

            appiumServiceUrl = appiumService.getUrl().toString();
            System.out.println("Device NAME ****************" + deviceName);
            System.out.println("Appium Service Address ************************: - " + appiumServiceUrl);
            capabilities.setCapability(MobileCapabilityType.UDID, deviceName);
            capabilities.setCapability(MobileCapabilityType.UDID, "emulator-5554");
            capabilities.setCapability(MobileCapabilityType.DEVICE_NAME, "Redmi");
            capabilities.setCapability(MobileCapabilityType.PLATFORM_NAME, MobilePlatform.ANDROID);
            capabilities.setCapability(MobileCapabilityType.NO_RESET,"true");
            capabilities.setCapability(MobileCapabilityType.FULL_RESET,"false");
            //capabilities.setCapability("appPackage", "com.lezasolutions.boutiqaat");
            //capabilities.setCapability("appActivity", "com.lezasolutions.boutiqaat.ui.welcome.WelcomeActivity");
            capabilities.setCapability("appPackage", "com.swaglabsmobileapp");
            capabilities.setCapability("appActivity", "com.swaglabsmobileapp.SplashActivity");
            capabilities.setCapability("automationName", "uiautomator2");

            driver = new AndroidDriver<MobileElement>(new URL(appiumServiceUrl), capabilities);
        }
        driver.manage().timeouts().implicitlyWait(60, TimeUnit.SECONDS);
        return driver;
    }

    public IOSDriver<MobileElement> getIOSDriver() throws IOException, InterruptedException {

        DesiredCapabilities capabilities = new DesiredCapabilities();
        AppiumDriverLocalService appiumService = null;
        String appiumServiceUrl = null;
        String deviceName = null;
        IOSDriver<MobileElement> driver = null;
        if (CommonConstant.Local_run) {
            // deviceName = Common_Constants.DeviceName;
            appiumService =getAppiumServiceAnyport(deviceName);
            appiumServiceUrl = appiumService.getUrl().toString();
            System.out.println("Device NAME ****************" + deviceName);
            System.out.println("Appium Service Address ************************: - " + appiumServiceUrl);

            // need to change the UDID - priority 1
            // capabilities.setCapability(MobileCapabilityType.UDID, "00008020-00021DCC227A002E");
            capabilities.setCapability(MobileCapabilityType.UDID, "EBE6B79A-D7DE-4D3D-A932-7046F8D8527E");
            // capabilities.setCapability(MobileCapabilityType.DEVICE_NAME, "iPhone XR");
            // need to change the UDID - priority 2
            capabilities.setCapability(MobileCapabilityType.DEVICE_NAME, "iPhone 6 Plus");
            capabilities.setCapability(MobileCapabilityType.PLATFORM_NAME, MobilePlatform.IOS);
            capabilities.setCapability(MobileCapabilityType.NO_RESET,true);
            // capabilities.setCapability(MobileCapabilityType.FULL_RESET,false);
            // Real device build path
            // capabilities.setCapability(MobileCapabilityType.APP,"/Users/qateam/Desktop/BetaBuild/Boutiqaat5.8.16.ipa");
            // Simulator build path
            capabilities.setCapability(MobileCapabilityType.APP,"/Users/qateam/Desktop/BetaBuild/SimulatorApp/BoutiqaatLatest.app");
            capabilities.setCapability("automationName", "XCUITEST");
            // Beta bundle id
            capabilities.setCapability("bundleId", "com.upgrade.boutiqaat");
            // Production, Adhoc, Preprod bundle id
            // capabilities.setCapability("bundleId", "com.leza.Boutiqaat");
            // need to change the UDID - priority 3
            capabilities.setCapability(MobileCapabilityType.PLATFORM_VERSION,"12.1");
            capabilities.setCapability("useNEWWDA",false);


            driver = new IOSDriver<MobileElement>(new URL(appiumServiceUrl), capabilities);
        }
        driver.manage().timeouts().implicitlyWait(60, TimeUnit.SECONDS);
        return driver;
    }


    public AppiumDriverLocalService getAppiumServiceAnyport(String DeviceName){

        AppiumDriverLocalService appiumService = AppiumDriverLocalService.buildService(new AppiumServiceBuilder().usingAnyFreePort());
        if(appiumService.isRunning()){
            appiumService.stop();
        }

        // AppiumDriverLocalService appiumService = AppiumDriverLocalService.buildService(new AppiumServiceBuilder().usingDriverExecutable(new File("/usr/local/bin/node")).withIPAddress("127.0.0.1").usingAnyFreePort());
        appiumService.start();
        return appiumService;
    }

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

        if(environmentData.getIsBrowserStack().equalsIgnoreCase("yes")) {
            if (environmentData.getBrowsername().equalsIgnoreCase("chrome")) {
               // WebDriverManager.chromedriver().setup();
                try {
//
                    String USERNAME = pf.getProperty("BrowserStackUSERNAME");
                    String AUTOMATE_KEY = pf.getProperty("BrowserStackAUTOMATE_KEY");
                    // String url= pf.getProperty("BrowserStackURL");
                    String url = "https://" + USERNAME + ":" + AUTOMATE_KEY + "@hub-cloud.browserstack.com/wd/hub";
                    DesiredCapabilities caps = new DesiredCapabilities();
                    caps.setCapability("browser", "chrome");
                    caps.setCapability("os", "Windows");
                    caps.setCapability("os_version", "10");
                    caps.setCapability("browser_version", "90.0");
                    webDriver = new RemoteWebDriver(new URL(url), caps);
                    //   webDriver.manage().timeouts().implicitlyWait(20, TimeUnit.SECONDS);
                } catch (Exception e) {
                    e.printStackTrace();
                }

            }
            if (environmentData.getBrowsername() .equalsIgnoreCase("firefox")) {
               // WebDriverManager.firefoxdriver().setup();
                try {
//
                    String USERNAME = pf.getProperty("BrowserStackUSERNAME");
                    String AUTOMATE_KEY = pf.getProperty("BrowserStackAUTOMATE_KEY");
                    // String url= pf.getProperty("BrowserStackURL");
                    String url = "https://" + USERNAME + ":" + AUTOMATE_KEY + "@hub-cloud.browserstack.com/wd/hub";
                    DesiredCapabilities caps = new DesiredCapabilities();
                    caps.setCapability("browser", "firefox");
                    caps.setCapability("os", "Windows");
                    caps.setCapability("os_version", "10");
                    caps.setCapability("browser_version", "latest");
                    webDriver = new RemoteWebDriver(new URL(url), caps);
                    //   webDriver.manage().timeouts().implicitlyWait(20, TimeUnit.SECONDS);
                } catch (Exception e) {
                    e.printStackTrace();
                }

            }
            if (environmentData.getBrowsername().equalsIgnoreCase("safari")) {
                //WebDriverManager.chromedriver().setup();
                try {
//
                    String USERNAME = pf.getProperty("BrowserStackUSERNAME");
                    String AUTOMATE_KEY = pf.getProperty("BrowserStackAUTOMATE_KEY");
                    // String url= pf.getProperty("BrowserStackURL");
                    String url = "https://" + USERNAME + ":" + AUTOMATE_KEY + "@hub-cloud.browserstack.com/wd/hub";
                    DesiredCapabilities caps = new DesiredCapabilities();
                    caps.setCapability("browser", "safari");
                    caps.setCapability("os", "Windows");
                    caps.setCapability("os_version", "10");
                    caps.setCapability("browser_version", "latest");
                    webDriver = new RemoteWebDriver(new URL(url), caps);
                    //   webDriver.manage().timeouts().implicitlyWait(20, TimeUnit.SECONDS);
                } catch (Exception e) {
                    e.printStackTrace();
                }

            }
        }

        else {
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
          else  if (browsername.equalsIgnoreCase("firefox"))
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

           else if (browsername.equalsIgnoreCase("mobile")) {
                File file;
                if (System.getProperty("os.name").contains("Mac")) {
                    // file = new File("src/test/resources/chromedriver").getCanonicalFile();
                    //WebDriverManager.chromedriver().setup();
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
          else  if (browsername.equalsIgnoreCase("mobileD")) {
                File file;
                if (System.getProperty("os.name").contains("Mac")) {
                    file = new File("src/test/resources/chromedriver").getCanonicalFile();
                } else {
                    // file = new File("src/test/resources/chromedriver.exe").getCanonicalFile();
                    DesiredCapabilities caps = new DesiredCapabilities();
                    caps.setCapability(MobileCapabilityType.DEVICE_NAME, "AndroidEmulator");
                    caps.setCapability(MobileCapabilityType.BROWSER_NAME, "");
                    caps.setCapability(MobileCapabilityType.VERSION, "11.0");
                    caps.setCapability(MobileCapabilityType.AUTOMATION_NAME,"UiAutomator2");
                    //capabilitiesAndroid.setCapability(CapabilityType.PLATFORM, "Android");
                    caps.setCapability(MobileCapabilityType.PLATFORM_NAME, "Android");
                    caps.setCapability("appPackage","com.android.calculator2");
                    caps.setCapability("appActivity","com.android.calculator2.Calculator");
                    // caps.setCapability(MobileCapabilityType.DEVICE_NAME, "pradeep");
                    // common.driver = new RemoteWebDriver(new
                    // URL("http://10.179.111.50:4723/wd/hub"), capabilitiesAndroid);
                    URL url = new URL("http://127.0.0.1:4723/wd/hub");
                   // driver = new AndroidDriver(new URL("http://127.0.0.1:4723/wd/hub"), caps);
                    webDriver = new AndroidDriver<MobileElement>(url,caps);
                    //androiddriver.get("https://ctdemob2c.anthive.tech/");
                    //webDriver = new RemoteWebDriver(new URL("http://127.0.0.1:4723/wd/hub"), caps);
                    // common.driver.manage().timeouts().implicitlyWait(200, TimeUnit.SECONDS);
                    //WebDriverManager.chromedriver().setup();
                }
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

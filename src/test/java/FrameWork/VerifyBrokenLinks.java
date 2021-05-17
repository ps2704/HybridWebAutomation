package FrameWork;
import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

import io.github.bonigarcia.wdm.WebDriverManager;
import io.github.bonigarcia.wdm.managers.ChromeDriverManager;
import io.github.bonigarcia.wdm.managers.FirefoxDriverManager;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;


public class VerifyBrokenLinks {
 //static WebDriverManager driver;
 //static WebDriver driver;


    public static void main(String[] args) throws InterruptedException, MalformedURLException, IOException
        {
          //   WebDriverManager driver = new ChromeDriverManager();

            WebDriverManager.chromedriver().setup();

         //   driver.manage().window().maximize();
            WebDriver driver = new ChromeDriver();
            driver.get("https://ctdemob2c.anthive.tech/");

            List<WebElement> links=driver.findElements(By.tagName("a"));
            links.addAll(driver.findElements(By.tagName("img")));
            List<WebElement> activelinks = new ArrayList<WebElement>();

            System.out.println("Total links are "+links.size());

            for(int i=0;i<links.size();i++)

            {
                System.out.println(links.get(i).getAttribute("href"));
            if(links.get(i).getAttribute("href")!= null){
                activelinks.add(links.get(i));
            }
//                WebElement ele= links.get(i);
//
//                String url=ele.getAttribute("href");

              //  verifyLinkActive(url);

            }
            System.out.println("size of active links and images----->>" + activelinks.size());

            for (int j=0;j<activelinks.size();j++){
           HttpURLConnection connection =  (HttpURLConnection)new URL(activelinks.get(j).getAttribute("href")).openConnection();
                    connection.connect();
                   String response = connection.getResponseMessage();
                    connection.disconnect();
                    System.out.println(activelinks.get(j).getAttribute("href")+"----->>"+response);
            }

        }

       // public static void verifyLinkActive(String linkUrl)
//        {
//            try
//            {
//                URL url = new URL(linkUrl);
//
//                HttpURLConnection httpURLConnect=(HttpURLConnection)url.openConnection();
//
//                httpURLConnect.setConnectTimeout(3000);
//
//                httpURLConnect.connect();
//
//                if(httpURLConnect.getResponseCode()==200)
//                {
//                    System.out.println(linkUrl+" - "+httpURLConnect.getResponseMessage());
//                }
//                if(httpURLConnect.getResponseCode()==HttpURLConnection.HTTP_NOT_FOUND)
//                {
//                    System.out.println(linkUrl+" - "+httpURLConnect.getResponseMessage() + " - "+ HttpURLConnection.HTTP_NOT_FOUND);
//                }
//            } catch (Exception e) {
//
//            }
//        }




    }


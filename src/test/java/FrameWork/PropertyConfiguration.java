package FrameWork;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

/**
 * @author Sourabh Shreemali
 */
public class PropertyConfiguration {
    public Properties getInstance() throws IOException {

        Properties prop = new Properties();
        //inputStream = getClass().getClassLoader().getResourceAsStream("./config.properties");

        String EnvironmentVariable = null;
        try {
            EnvironmentVariable = System.getenv("ConfigType");
            System.out.println(EnvironmentVariable);
        } catch (Exception e) {
            // TODO Auto-generated catch block

            e.printStackTrace();
        }


        InputStream inputStream = null;
        if (EnvironmentVariable == null) {

            inputStream = getClass().getClassLoader().getResourceAsStream("./config.properties");
        } else {
            if (EnvironmentVariable.equalsIgnoreCase("PreprodSanity")) {
                inputStream = getClass().getClassLoader().getResourceAsStream("./preprodSanity.properties");
            }
            if (EnvironmentVariable.equalsIgnoreCase("LiveSmoke")) {
                inputStream = getClass().getClassLoader().getResourceAsStream("./liveSmoke.properties");
            }
            if (EnvironmentVariable.equalsIgnoreCase("BetaSmoke")) {
                inputStream = getClass().getClassLoader().getResourceAsStream("./BetaSmoke.properties");
            }
            if (EnvironmentVariable.equalsIgnoreCase("LiveAlertSmoke")) {
                inputStream = getClass().getClassLoader().getResourceAsStream("./liveAlertSmoke.properties");
            }
            System.out.println(EnvironmentVariable.toString() + " is here");
            if (EnvironmentVariable.equalsIgnoreCase("LiveSanity")) {
                System.out.println("gone through LiveSanity");
                inputStream = Thread.currentThread().getContextClassLoader().getResourceAsStream("./liveSanity.properties");
            }
        }
        System.out.println(inputStream);
        if (inputStream != null) {
            prop.load(inputStream);
        } else {
            throw new FileNotFoundException("property file ' not found in the classpath");
        }
        return prop;

    }
}

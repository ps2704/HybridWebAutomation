package FrameWork;
import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class AdbDevice {
    public volatile static AdbDevice DeviceINSTANCE = null;
    List<String> deviceList = new ArrayList<String>();
    public volatile  static BlockingQueue<String> FreeListDeviceQue = new LinkedBlockingQueue<String>();
    public volatile static BlockingQueue<String> UsedAccountDevice = new LinkedBlockingQueue<String>();

    public static synchronized AdbDevice getDeviceInstance() throws IOException {
        if (DeviceINSTANCE == null) {
            DeviceINSTANCE = new AdbDevice();
            DeviceINSTANCE.populateDevice();
        }
        return DeviceINSTANCE;
    }

    public void populateDevice() throws IOException {
        Framework frameWork = new Framework();
        //List<AccountData> deviceList = frameWork.getDataList(AccountData.class,"Devices");

        try{

            File dir=new File("C:\\Program Files (x86)\\Android\\android-sdk\\platform-tools");
            Process process = Runtime.getRuntime().exec("adb devices");


            BufferedReader in = new BufferedReader(new InputStreamReader(process.getInputStream()));
            String line = null;



            Pattern pattern = Pattern.compile("^([a-zA-Z0-9:.\\-]+)(\\s+)(device)");
            Matcher matcher;

            while ((line = in.readLine()) != null)
            {
                if (line.matches(pattern.pattern()))
                {
                    matcher = pattern.matcher(line);
                    if (matcher.find())
                        System.out.println(matcher.group(1));
                    deviceList.add(matcher.group(1));
                }
            }
            for(String deviceid: deviceList)
            {
                Runtime.getRuntime().exec("adb -s "+deviceid+" shell am start -n 'io.appium.unlock/.Unlock' -a 'android.intent.action.MAIN' -c 'android.intent.category.LAUNCHER' -f '0x10200000'");
            }


        } catch (IOException e)
        {
            e.printStackTrace();
        }
        FreeListDeviceQue = new LinkedBlockingQueue<String>();
        for (String device : deviceList)
        {
            FreeListDeviceQue.add(device);

        }

    }
    public void clearDeviceLock() throws InterruptedException {
        String Device = UsedAccountDevice.take();

        FreeListDeviceQue.put(Device);


    }

    public String getDevice() throws InterruptedException {
        String device = FreeListDeviceQue.take();
        UsedAccountDevice.put(device);
        // UsedLoginque.put(login);

        return device;
    }


}






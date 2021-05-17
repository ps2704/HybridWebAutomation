package FrameWork;

import Data.AccountData;

import java.util.List;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingQueue;

public class Loginsync {

    public volatile static Loginsync INSTANCE = null;
    public volatile static BlockingQueue<AccountData> UsedAccountLogine = new LinkedBlockingQueue<AccountData>();
    public volatile  static BlockingQueue<AccountData> FreeListLoginQue = new LinkedBlockingQueue<AccountData>();
    public AccountData getLogin() throws InterruptedException {
        // populateLogins();// not required
        // Entry<String, String> login = FreeLoginQue.take();
        AccountData accountData = FreeListLoginQue.take();
        UsedAccountLogine.put(accountData);
        // UsedLoginque.put(login);

        return accountData;
    }
    public static synchronized Loginsync getInstance() {
        if (INSTANCE == null) {
            INSTANCE = new Loginsync();
            INSTANCE.populateLogins();
        }
        return INSTANCE;
    }
    private void populateLogins() {

        Framework framework = new Framework();
        List<AccountData> accountDataList = framework.getDataList(AccountData.class, "loginset");
        FreeListLoginQue = new LinkedBlockingQueue<AccountData>();
        for (AccountData accountData : accountDataList) {
            FreeListLoginQue.add(accountData);

        }
        // Set<Entry<String, String>> entries = hm.entrySet();
        // FreeLoginQue = new
        // LinkedBlockingQueue<HashMap.Entry<String,String>>(entries);

    }

}

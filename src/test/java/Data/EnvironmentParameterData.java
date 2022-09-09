package Data;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
@Table
@Entity
public class EnvironmentParameterData {
    @Id
    @Column
    String rowid;
    @Column
    String testcaseid;
    @Column
    String baseurl;
    @Column
    String browsername;

    @Column
    String DeviceName;

    @Column
    String IsBrowserStack;
    @Column
    /**
     * @return the rowid
     */
    public String getRowid() {
        return rowid;
    }
    /**
     * @param rowid the rowid to set
     */
    public void setRowid(String rowid) {
        this.rowid = rowid;
    }
    /**
     * @return the baseurl
     */
    public String gettestcaseid() {
        return testcaseid;
    }
    /**
     * @param testcaseid the browsername to set
     */
    public void settestcaseid(String testcaseid) {
        this.testcaseid = testcaseid;
    }
    /**
     * @return the emailAddress
     */
    public String getBaseurl() {
        return baseurl;
    }
    /**
     * @param baseurl the baseurl to set
     */
    public void setBaseurl(String baseurl) {
        this.baseurl = baseurl;
    }
    /**
     * @return the browsername
     */
    public String getBrowsername() {
        return browsername;
    }
    /**
     * @param browsername the browsername to set
     */
    public void setBrowsername(String browsername) {
        this.browsername = browsername;
    }
    /**
     * @return the emailAddress
     */
    public String getIsBrowserStack() {
        return IsBrowserStack;
    }

    public void setIsBrowserStack(String isBrowserStack) {
        IsBrowserStack = isBrowserStack;
    }

    public String getDeviceName() {
        return DeviceName;
    }

    public void setDeviceName(String deviceName) {
        DeviceName = deviceName;
    }
}


package Data;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
@Table
@Entity
public class AccountData {
    @Id
    @Column
    String rowid;
    @Column
    String testcaseid;
    @Column
    String username;
    @Column
    String password;
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
     * @return the LoginType
     */
    public String gettestcaseid() {
        return testcaseid;
    }
    /**
     * @param testcaseid the baseurl to set
     */
    public void settestcaseid(String testcaseid) {
        this.testcaseid = testcaseid;
    }
    /**
     * @return the Email
     */
    public String getUsername() {
        return username;
    }
    /**
     * @param username the browsername to set
     */
    public void setUsername(String username
    ) {
        this.username = username;
    }
    /**
     * @return the emailAddress
     */
    public String getPassword() {
        return password;
    }
    /**
     * @param password the browsername to set
     */
    public void setPassword(String password) {
        this.password = password;
    }
    /**
     * @return the Password
     */

}


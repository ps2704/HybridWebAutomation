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
    String firstName;
    @Column
    String lastName;
    @Column
    String confirmPassword;
    @Column
    String mobile;
    @Column
    String signupMail;
    @Column
    String signupPassword;
    @Column
    String cEmail;
    @Column
    String ShippingName;
    @Column
    String shippingAddressLine;
    @Column
    String ShippingAddressLineII;
    @Column
    String shippingLocality;
    @Column
    String ShippingPostalCode;
    @Column
    String CardNumber;
    @Column
    String shippingCardExpiry;
    @Column
    String CardCvc;
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
    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getConfirmPassword() {
        return confirmPassword;
    }

    public void setConfirmPassword(String confirmPassword) {
        this.confirmPassword = confirmPassword;
    }

    public String getMobile() {
        return mobile;
    }

    public void setMobile(String mobile) {
        this.mobile = mobile;
    }
    public String getSignupMail() {
        return signupMail;
    }

    public void setSignupMail(String signupMail) {
        this.signupMail = signupMail;
    }

    public String getSignupPassword() {
        return signupPassword;
    }

    public void setSignupPassword(String signupPassword) {
        this.signupPassword = signupPassword;
    }

    public String getTestcaseid() {
        return testcaseid;
    }

    public void setTestcaseid(String testcaseid) {
        this.testcaseid = testcaseid;
    }

    public String getcEmail() {
        return cEmail;
    }

    public void setcEmail(String cEmail) {
        this.cEmail = cEmail;
    }

    public String getShippingName() {
        return ShippingName;
    }

    public void setShippingName(String shippingName) {
        ShippingName = shippingName;
    }

    public String getShippingAddressLine() {
        return shippingAddressLine;
    }

    public void setShippingAddressLine(String shippingAddressLine) {
        this.shippingAddressLine = shippingAddressLine;
    }

    public String getShippingAddressLineII() {
        return ShippingAddressLineII;
    }

    public void setShippingAddressLineII(String shippingAddressLineII) {
        ShippingAddressLineII = shippingAddressLineII;
    }

    public String getShippingLocality() {
        return shippingLocality;
    }

    public void setShippingLocality(String shippingLocality) {
        this.shippingLocality = shippingLocality;
    }

    public String getShippingPostalCode() {
        return ShippingPostalCode;
    }

    public void setShippingPostalCode(String shippingPostalCode) {
        ShippingPostalCode = shippingPostalCode;
    }

    public String getCardNumber() {
        return CardNumber;
    }

    public void setCardNumber(String cardNumber) {
        CardNumber = cardNumber;
    }

    public String getShippingCardExpiry() {
        return shippingCardExpiry;
    }

    public void setShippingCardExpiry(String shippingCardExpiry) {
        this.shippingCardExpiry = shippingCardExpiry;
    }

    public String getCardCvc() {
        return CardCvc;
    }

    public void setCardCvc(String cardCvc) {
        CardCvc = cardCvc;
    }



}


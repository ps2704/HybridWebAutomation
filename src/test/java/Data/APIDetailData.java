/**
 *
 */
package Data;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * @author Sourabh Shreemali
 *
 */
@Entity
@Table
public class APIDetailData {
    @Id
    @Column
    String rowid;
    @Column
    String testdataid;
    @Column
    String testcaseid;
    @Column
    String path;
    @Column
    String parameter;
    @Column
    String status;
    @Column
    String Header;
    @Column
    String response;
    @Column
    String expected;
    @Column
    String method;
    @Column
    String name;
    @Column
    String expectedstatus;
    @Column
    String scenario;
    @Column
    String result;

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
     * @return the method
     */
    public String getMethod() {
        return method;
    }

    /**
     * @param method the method to set
     */
    public void setMethod(String method) {
        this.method = method;
    }

    /**
     * @return the itrtestcaseid
     */
    public String getTestDataId() {
        return testdataid;
    }

    /**
     * @param itrtestcaseid the itrtestcaseid to set
     */
    public void setTestDataId(String itrtestcaseid) {
        this.testdataid = testdataid;
    }

    /**
     * @return the row
     */
    public String getRow() {
        return rowid;
    }

    /**
     * @param row the row to set
     */
    public void setRow(String row) {
        this.rowid = row;
    }

    /**
     * @return the testcaseid
     */
    public String getTestcaseid() {
        return testcaseid;
    }

    /**
     * @param testcaseid the testcaseid to set
     */
    public void setTestcaseid(String testcaseid) {
        this.testcaseid = testcaseid;
    }

    /**
     * @return the path
     */
    public String getPath() {
        return path;
    }

    /**
     * @param path the path to set
     */
    public void setPath(String path) {
        this.path = path;
    }

    /**
     * @return the parameter
     */
    public String getParameter() {
        return parameter;
    }

    /**
     * @param parameter the parameter to set
     */
    public void setParameter(String parameter) {
        this.parameter = parameter;
    }

    /**
     * @return the status
     */
    public String getStatus() {
        return status;
    }

    /**
     * @param status the status to set
     */
    public void setStatus(String status) {
        this.status = status;
    }

    /**
     * @return the Header
     */
    public String getHeader() {
        return Header;
    }

    /**
     * @param Header the result to set
     */
    public void setHeader(String Header) {
        this.Header = Header;
    }

    /**
     * @return the response
     */
    public String getResponse() {
        return response;
    }

    /**
     * @param response the response to set
     */
    public void setResponse(String response) {
        this.response = response;
    }

    /**
     * @return the expected
     */
    public String getExpected() {
        return expected;
    }

    /**
     * @param expected the expected to set
     */
    public void setExpected(String expected) {
        this.expected = expected;
    }

    /**
     * @name the expected
     */
    /**
     * @return the expected
     */
    public String getResult() {
        return result;
    }

    /**
     * @param result the expected to set
     */
    public void setResult(String result) {
        this.result = result;
    }

    /**
     * @name the expected
     */

    public String getName() {
        return name;
    }

    /**
     * @param name the rowid to set
     */
    public void setName(String name) {
        this.name = name;
    }

    public String getExpectedStatus() {
        return expectedstatus;
    }

    /**
     * @param expectedStatus the rowid to set
     */
    public void setExpectedStatus(String expectedStatus) {
        this.expectedstatus = expectedStatus;
    }


    /* (non-Javadoc)
     * @see java.lang.Object#toString()
     */
    @Override
    public String toString() {
        return "APIData [testcaseid=" + testcaseid + ", path="
                + path + ", parameter=" + parameter + ", status=" + status + ", Header=" + Header + ", response="
                + response + ", Result=" + result + ", expected=" + expected + "]";
    }

    public String getScenario() {
        // TODO Auto-generated method stub
        return scenario;
    }

    public String setScenario() {
        // TODO Auto-generated method stub
        return this.scenario = scenario;
    }


}

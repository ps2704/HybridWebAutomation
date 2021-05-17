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
public class APIData {
    @Id
    @Column
    String rowid;
    @Column
    String itrtestcaseid;
    @Column
    String testcaseid;
    @Column
    String path;
    @Column
    String parameter;
    @Column
    String IsDependent;
    @Column
    String ResponseField;
    @Column
    String NeedtoExecute;
    @Column
    String UsethisField;
    @Column
    String method;
    @Column
    String Name;
    @Column
    String SetThisField;
    @Column
    String GetThisField;
    @Column
    String Prerequisite;
    @Column
    String PaginationValidation;


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
    public String getItrtestcaseid() {
        return itrtestcaseid;
    }

    /**
     * @param itrtestcaseid the itrtestcaseid to set
     */
    public void setItrtestcaseid(String itrtestcaseid) {
        this.itrtestcaseid = itrtestcaseid;
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
     * @return the IsDependent
     */
    public String getIsDependent() {
        return IsDependent;
    }

    /**
     * @param IsDependent the status to set
     */
    public void setIsDependent(String IsDependent) {
        this.IsDependent = IsDependent;
    }

    /**
     * @return the ResponseField
     */
    public String getResponseField() {
        return ResponseField;
    }

    /**
     * @param ResponseField the result to set
     */
    public void setResult(String ResponseField) {
        this.ResponseField = ResponseField;
    }

    /**
     * @return the NeedtoExecute
     */
    public String getNeedtoExecute() {
        return NeedtoExecute;
    }

    /**
     * @param NeedtoExecute the response to set
     */
    public void setResponse(String NeedtoExecute) {
        this.NeedtoExecute = NeedtoExecute;
    }

    /**
     * @return the UsethisField
     */
    public String getUseThisField() {
        return UsethisField;
    }

    /**
     * @param UsethisField the expected to set
     */
    public void setExpected(String UsethisField) {
        this.UsethisField = UsethisField;
    }

    /**
     * @return the Name
     */
    public String getName() {
        return Name;
    }

    /**
     * @param Name the rowid to set
     */
    public void setName(String Name) {
        this.Name = Name;
    }

    /**
     * @return the Set_This_Field
     */

    public String getSetThisField() {
        return SetThisField;
    }

    /**
     * @param SetThisField the rowid to set
     */
    public void setSetThisField(String SetThisField) {
        this.SetThisField = SetThisField;
    }

    /**
     * @return the Get_This_Field
     */

    public String getGetThisField() {
        return GetThisField;
    }

    /**
     * @param GetThisField the rowid to set
     */
    public void setGetThisField(String GetThisField) {
        this.GetThisField = GetThisField;
    }


    /**
     * @return the Prerequisite
     */

    public String getPrerequisite() {
        return Prerequisite;
    }

    /**
     * @param Prerequisite the rowid to set
     */
    public void setPrerequisite(String Prerequisite) {
        this.Prerequisite = Prerequisite;
    }
    /**
     * @return the Pageinationresponse
     */
    public String getPaginationValidation() {
        return PaginationValidation;
    }

    /**
     * @param PaginationValidation the response to set
     */
    public void setPaginationValidation(String PaginationValidation) {
        this.PaginationValidation = PaginationValidation;
    }

    /* (non-Javadoc)
     * @see java.lang.Object#toString()
     */
    @Override
    public String toString() {
        return "APIData [testcaseid=" + testcaseid + ", path="
                + path + ", parameter=" + parameter + ", IsDependent=" + IsDependent + ", ResponseField=" + ResponseField + ", NeedtoExecute="
                + NeedtoExecute + ", Usethisfield=" + UsethisField + ", SetThisField=" + SetThisField + ", GetThisField=" + getGetThisField() + ", Prerequisite=" + getPrerequisite() + ", PaginationValidation=" + getPaginationValidation() +"]";
    }


}

package Data;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
@Table
@Entity
public class ObjectRepo {
    @Id
    @Column
    String rowid;
    @Column
    String Screen_Name;
    @Column
    String object_Name;
    @Column
    String locator_type;
    @Column
    String locator_value;
    @Column
    String locator_name_for_reporting;
    @Column
    /**
     * @return the Screen_Name
     */
    public String getScreen_Name() {
        return Screen_Name;
    }
    /**
     * @param Screen_Name the rowid to set
     */
    public void setScreen_Name(String Screen_Name) {
        this.Screen_Name = Screen_Name;
    }
    /**
     * @return the object_Name
     */
    public String getobject_Name() {
        return object_Name;
    }
    /**
     * @param object_Name the browsername to set
     */
    public void setobject_Name(String object_Name) {
        this.object_Name = object_Name;
    }
    /**
     * @return the locator_type
     */
    public String getlocator_type() {
        return locator_type;
    }
    /**
     * @param locator_type the baseurl to set
     */
    public void setlocator_type(String locator_type) {
        this.locator_type = locator_type;
    }
    /**
     * @return the browsername
     */
    public String getlocator_value() {
        return locator_value;
    }
    /**
     * @param locator_value the browsername to set
     */
    public void setlocator_value(String locator_value) {
        this.locator_value = locator_value;
    }
    /**
     * @return the Screen_Name
     */
    public String getlocator_name_for_reporting() {
        return locator_name_for_reporting;
    }
    /**
     * @param locator_name_for_reporting the rowid to set
     */
    public void setlocator_name_for_reporting(String locator_name_for_reporting) {
        this.locator_name_for_reporting = locator_name_for_reporting;
    }



}


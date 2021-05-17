package FrameWork;

import Data.APIData;
import Data.APIDetailData;
import Data.EnvironmentParameterData;
import com.google.gson.JsonObject;
import com.relevantcodes.extentreports.ExtentTest;
import com.relevantcodes.extentreports.LogStatus;
import io.restassured.RestAssured;
import io.restassured.http.Headers;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import org.json.JSONObject;
import org.testng.Assert;
import org.testng.Reporter;

import java.io.IOException;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

/**
 * @author Sourabh Shreemali
 */
public class Method extends BrowserAction{

    private final static String PREPROD = "preprod";
    private final static String MASTER = "master";
    Response respEnv = null;
    ExtentTest childTest1 = null;
    String gender = System.getProperty("Gender");

    public Response PostMethod(APIDetailData apiDetailData, EnvironmentParameterData environmentData, String basepath, String token) throws IOException {
        try {
            RestAssured.baseURI = environmentData.getBaseurl();
            RestAssured.basePath = basepath;
            String gender = System.getProperty("Gender");
// System.out.println(Parameter);
            Map HeaderMap = new HashMap<String, Object>();
            HeaderMap.put("Authorization", "Bearer " + token);
            String headers = apiDetailData.getHeader();
            String Parameter = apiDetailData.getParameter();
            Map keyValuePairMap = new HashMap<String, Object>();
// System.out.println(apiDetailData.getPath());
            if (!(Parameter.contains("null"))) {
                String[] keyValuePair = Parameter.split("&");
                keyValuePairMap = Bodyparameter(keyValuePair);
            }
            if (gender != null) {
                System.out.println("Getting Value from Jenkins");
                String APP_version = System.getProperty("App_Version");
                String Header_Value;
                System.out.println(gender);
                if(!(gender.contains("Blank"))) {
                    if (gender =="") {
                        Header_Value = apiDetailData.getHeader();
                    } else {
                        String gender_id;
                        if (gender == "Male") {
                            System.out.println("Gender is Male");
                            gender_id = "2791";
                        } else {
                            System.out.println("Gender is Female");
                            gender_id = "4194";
                        }
                        Header_Value = "{\"gender\":\"" + gender_id + "\" , \"app_version\" :\"" + APP_version + "\"}";
                    }
                    HeaderMap.put("Param", Header_Value);
                }else{
                    System.out.println("Getting Blank Gender");
                }
            }
            else {
                System.out.println("Getting Value from Google-Sheet");
                if (!(headers.contains("null"))) {
                    String[] keyValuePair = headers.split("&");
                    for (String keyValue : keyValuePair) {
                        String[] keyValueNamePair = keyValue.split("=");
                        String key = keyValueNamePair[0];
                        String value = keyValueNamePair[1];
                        HeaderMap.put(key, value);
                    }
                }
            }
//String baseURL = environmentData.getBaseurl();
            respEnv = RestAssured.given().headers(HeaderMap).contentType("application/json").body(keyValuePairMap).when().post("");
            String response = respEnv.asString();
            System.out.println(response);
/* int StatucCode = respEnv.statusCode();
if(StatucCode!=200){
childTest1.log(LogStatus.FAIL, "Exception or Assert Error " + respEnv.toString());
Assert.fail("Failed Testcase Please check log");
}else{
Reporter.log(respEnv.asString());
}*/
// Assert.assertEquals(StatucCode, 200);
//respEnv.then().assertThat().statusCode(200);
            Reporter.log(respEnv.asString());
            return respEnv;
        } catch (Exception e) {
            childTest1.log(LogStatus.FAIL, "Exception or Assert Error " + respEnv.toString());
            Assert.fail("Failed Testcase Please check log");
        }
        return respEnv;
    }

    public Response NewPostmethod(APIDetailData apiDetailData, EnvironmentParameterData environmentData, String basepath, String token){
        try {
            RestAssured.baseURI = environmentData.getBaseurl();
            RestAssured.basePath = basepath;
            String gender = System.getProperty("Gender");
            Map HeaderMap = new HashMap<String, Object>();
            HeaderMap.put("Authorization", "Bearer " + token);
            String headers = apiDetailData.getHeader();
            String Parameter = apiDetailData.getParameter();
            Map keyValuePairMap = new HashMap<String, Object>();
            JSONObject jsonObject;
            if (Parameter.startsWith("[")) {
                Parameter = Parameter.substring(1, Parameter.length() - 1);
            }
            jsonObject = new JSONObject(Parameter);
            //ApiComparator apiComparator = new ApiComparator();
            keyValuePairMap =jsonToMap(jsonObject);
            if (gender != null) {
                System.out.println("Getting Value from Jenkins");
                String APP_version = System.getProperty("App_Version");
                String Header_Value;
                System.out.println(gender);
                if(!(gender.contains("Blank"))) {
                    if (gender =="") {
                        Header_Value = apiDetailData.getHeader();
                    } else {
                        String gender_id;
                        if (gender == "Male") {
                            System.out.println("Gender is Male");
                            gender_id = "2791";
                        } else {
                            System.out.println("Gender is Female");
                            gender_id = "4194";
                        }
                        Header_Value = "{\"gender\":\"" + gender_id + "\" , \"app_version\" :\"" + APP_version + "\"}";
                    }
                    HeaderMap.put("Param", Header_Value);
                }else{
                    System.out.println("Getting Blank Gender");
                }
            }
            else {
                System.out.println("Getting Value from Google-Sheet");
                if (!(headers.contains("null")|| headers.equalsIgnoreCase(""))) {
                    String[] keyValuePair = headers.split("&");
                    for (String keyValue : keyValuePair) {
                        String[] keyValueNamePair = keyValue.split("=");
                        String key = keyValueNamePair[0];
                        String value = keyValueNamePair[1];
                        HeaderMap.put(key, value);
                    }
                }
            }
//String baseURL = environmentData.getBaseurl();
            respEnv = RestAssured.given().headers(HeaderMap).contentType("application/json").body(keyValuePairMap).when().post("");
            String response = respEnv.asString();
            System.out.println(response);
            Reporter.log(respEnv.asString());
            return respEnv;
        }catch (Exception e){
            return null;
        }
    }

    public Response GetMethod(APIDetailData apiDetailData, EnvironmentParameterData environmentData, String basepath, String token) {
        try {

            String baseURL = environmentData.getBaseurl();
            System.out.println(baseURL);
            RestAssured.baseURI = baseURL;
            RestAssured.basePath = basepath;
            String gender = System.getProperty("Gender");
            String apiParameters = apiDetailData.getParameter();
            String headers = apiDetailData.getHeader();
            Map HeaderMap = new HashMap<String, Object>();
            HeaderMap.put("Authorization", "Bearer " + token);
//System.out.println(apiDetailData.getPath());
/* if (!(headers.contains("null"))) {
String[] keyValuePair = headers.split("&");
for (String keyValue : keyValuePair) {
String[] keyValueNamePair = keyValue.split("=");
String key = keyValueNamePair[0];
String value = keyValueNamePair[1];
HeaderMap.put(key, value);
}
}*/
            if (gender != null) {
                System.out.println("Getting Value from Jenkins");
                System.out.println(System.getProperty("Gender"));
                String APP_version = System.getProperty("App_Version");
                String Platfrom = System.getProperty("Platfrom");
                System.out.println(Platfrom);
                String Header_Value;

                if(!(gender.contains("Blank"))) {
                    if (gender == "") {
                        Header_Value = apiDetailData.getHeader();
                    } else {
                        String gender_id;
                        if (gender.contains("Male")) {
                            System.out.println("Getting Male gender");
                            gender_id = "2741";
                        } else {
                            System.out.println("Getting Female gender");
                            gender_id = "4194";
                        }
                        if(Platfrom.contains("web")) {
                            System.out.println("Excueting Web API");
                            Header_Value = "\"{'gender':' \"" + gender_id + "\" ', app_version' :'\"" + APP_version + "\" ',platfrom' :'\"" + Platfrom + "}";
                        }else {
                            Header_Value = "\"{'gender':' \"" + gender_id + "\" ', app_version' :'\"" + APP_version + "}";
                        }
                    }
                    HeaderMap.put("Param", Header_Value);
                }
                else{
                    System.out.println("Getting Blank Gender");
                }
            } else {
                System.out.println("Getting Value from Google-Sheet");
                if (!(headers.contains("null")|| headers.equalsIgnoreCase(""))) {
                    String[] keyValuePair = headers.split("&");
                    for (String keyValue : keyValuePair) {
                        String[] keyValueNamePair = keyValue.split("=");
                        String key = keyValueNamePair[0];
                        String value = keyValueNamePair[1];
                        HeaderMap.put(key, value);
                    }
                }
            }
            Map keyValuePairMap = new HashMap<String, Object>();
//System.out.println(apiDetailData.getPath());
            if (!(apiParameters.contains("null")||(apiParameters.equalsIgnoreCase("")))) {
                String[] keyValuePair = apiParameters.split("&");
                for (String keyValue : keyValuePair) {
                    String[] keyValueNamePair = keyValue.split("=");
                    String key = keyValueNamePair[0];
                    String value = keyValueNamePair[1];
                    keyValuePairMap.put(key, value);
                }
            }
            String Cache = System.getProperty("Cache");
            if(Cache == null || Cache.equalsIgnoreCase("TRUE")){
                System.out.println("Taking Cache Response");
                respEnv = RestAssured.given().headers(HeaderMap).contentType("application/json").when().get();
            }else{
                System.out.println("Taking non-cache Value");
                respEnv = RestAssured.given().headers(HeaderMap).param("current_time", System.currentTimeMillis()).contentType("application/json").when().get();
            }
            System.out.println(respEnv.asString());
            int StatucCode = respEnv.statusCode();
/* if(StatucCode!=200){
childTest1.log(LogStatus.INFO, "Exception or Assert Error " + respEnv.asString());
Assert.fail("Failed Testcase Please check log");
}else{
Reporter.log(respEnv.asString());
}*/
// Assert.assertEquals(StatucCode, 200);
//respEnv.then().assertThat().statusCode(200);
            Reporter.log(respEnv.asString());
            return respEnv;
        } catch (Exception e) {
            childTest1.log(LogStatus.FAIL, "Exception or Assert Error " + respEnv.toString());
// Assert.fail("Failed Testcase Please check log");
        }
        return respEnv;
    }

    public Response PutMethod(APIDetailData apiDetailData, EnvironmentParameterData environmentData, String basepath, String token) throws IOException {
        try {
            RestAssured.baseURI = environmentData.getBaseurl();
            // RestAssured.basePath = basepath;
            String gender = System.getProperty("Gender");
            //  System.out.println(Parameter);
            Map HeaderMap = new HashMap<String, Object>();
            HeaderMap.put("Authorization", "Bearer " + token);
            String headers = apiDetailData.getHeader();
            String Parameter = apiDetailData.getParameter();
            JSONObject keyValuePairMap = new JSONObject();

            // System.out.println(apiDetailData.getPath());
            if (!(Parameter.contains("null"))) {
                String[] keyValuePair = Parameter.split("&");
                for (String keyValue : keyValuePair) {
                    String[] keyValueNamePair = keyValue.split("=");
                    String key = keyValueNamePair[0];
                    String value = keyValueNamePair[1];
                    if(value.startsWith("\"")&& value.endsWith("\"")){
                        value = value.replace("\"", "");
                    }
                    keyValuePairMap.put(key, value);

                }
            }
            if (gender != null) {
                System.out.println("Getting Value from Jenkins");
                String APP_version = System.getProperty("App_Version");
                String Header_Value;
                System.out.println(gender);
                if(!(gender.contains("Blank"))) {
                    if (gender =="") {
                        Header_Value = apiDetailData.getHeader();
                    } else {
                        String gender_id;
                        if (gender == "Male") {
                            System.out.println("Gender is Male");
                            gender_id = "2791";
                        } else {
                            System.out.println("Gender is Female");
                            gender_id = "4194";
                        }
                        Header_Value = "{\"gender\":\"" + gender_id + "\" , \"app_version\" :\"" + APP_version +  "\"}";
                    }
                    HeaderMap.put("Param", Header_Value);
                }else{
                    System.out.println("Getting Blank Gender");
                }
            }
            else {
                System.out.println("Getting Value from Google-Sheet");
                if (!(headers.contains("null"))) {
                    String[] keyValuePair = headers.split("&");
                    for (String keyValue : keyValuePair) {
                        String[] keyValueNamePair = keyValue.split("=");
                        String key = keyValueNamePair[0];
                        String value = keyValueNamePair[1];
                        HeaderMap.put(key, value);
                    }
                }
            }
            // String baseURL = environmentData.getBaseurl();
            if (!(Parameter.contains("null"))) {
                String[] keyValuePair = Parameter.split("&");
                for (String keyValue : keyValuePair) {
                    String[] keyValueNamePair = keyValue.split("=");
                    String key = keyValueNamePair[0];
                    String value = keyValueNamePair[1];
                    if(value.startsWith("\"")&& value.endsWith("\"")){
                        value = value.replace("\"", "");
                    }
                    keyValuePairMap.put(key, value);

                }
            }
            String s = "[" + keyValuePairMap.toString() + "]";
            respEnv = RestAssured.given().contentType("application/json").body(s).put();
            String response = respEnv.asString();
            System.out.println(response);
           /* int StatucCode = respEnv.statusCode();
            if(StatucCode!=200){
                childTest1.log(LogStatus.FAIL, "Exception or Assert Error " + respEnv.toString());
                Assert.fail("Failed Testcase Please check log");
            }else{
                Reporter.log(respEnv.asString());
            }*/
            // Assert.assertEquals(StatucCode, 200);
            //respEnv.then().assertThat().statusCode(200);
            Reporter.log(respEnv.asString());
            return respEnv;
        } catch (Exception e) {
            childTest1.log(LogStatus.FAIL, "Exception or Assert Error " + respEnv.toString());
            Assert.fail("Failed Testcase Please check log");
        }
        return respEnv;
    }



    public Response PrerequisiteGetMethod(APIDetailData apiDetailData, String basepath, String token) {
        try {


//RestAssured.baseURI = baseURL;
            RestAssured.basePath = basepath;
            String gender = System.getProperty("Gender");
            String apiParameters = apiDetailData.getParameter();
            String headers = apiDetailData.getHeader();
            Map HeaderMap = new HashMap<String, Object>();
            HeaderMap.put("Authorization", "Bearer " + token);
            if (gender != null) {
                System.out.println("Getting Value from Jenkins");
                System.out.println(System.getProperty("Gender"));
                String APP_version = System.getProperty("App_Version");
                String Platfrom = System.getProperty("Platfrom");
                System.out.println(Platfrom);
                String Header_Value;

                if(!(gender.contains("Blank"))) {
                    if (gender == "") {
                        Header_Value = apiDetailData.getHeader();
                    } else {
                        String gender_id;
                        if (gender.contains("Male")) {
                            System.out.println("Getting Male gender");
                            gender_id = "2741";
                        } else {
                            System.out.println("Getting Female gender");
                            gender_id = "4194";
                        }
                        if(Platfrom.contains("web")) {
                            System.out.println("Excueting Web API");
                            Header_Value = "\"{'gender':' \"" + gender_id + "\" ', app_version' :'\"" + APP_version + "\" ',platfrom' :'\"" + Platfrom + "}";
                        }else {
                            Header_Value = "\"{'gender':' \"" + gender_id + "\" ', app_version' :'\"" + APP_version + "}";
                        }
                    }
                    HeaderMap.put("Param", Header_Value);
                }
                else{
                    System.out.println("Getting Blank Gender");
                }
            } else {
                System.out.println("Getting Value from Google-Sheet");
                if (!(headers.contains("null")|| headers.equalsIgnoreCase(""))) {
                    String[] keyValuePair = headers.split("&");
                    for (String keyValue : keyValuePair) {
                        String[] keyValueNamePair = keyValue.split("=");
                        String key = keyValueNamePair[0];
                        String value = keyValueNamePair[1];
                        HeaderMap.put(key, value);
                    }
                }
            }
            Map keyValuePairMap = new HashMap<String, Object>();
//System.out.println(apiDetailData.getPath());
            if (!(apiParameters.contains("null")||(apiParameters.equalsIgnoreCase("")))) {
                String[] keyValuePair = apiParameters.split("&");
                for (String keyValue : keyValuePair) {
                    String[] keyValueNamePair = keyValue.split("=");
                    String key = keyValueNamePair[0];
                    String value = keyValueNamePair[1];
                    keyValuePairMap.put(key, value);
                }
            }
            String Cache = System.getProperty("Cache");
            if(Cache == null || Cache.equalsIgnoreCase("TRUE")){
                System.out.println("Taking Cache Response");
                respEnv = RestAssured.given().headers(HeaderMap).contentType("application/json").when().get();
            }else{
                System.out.println("Taking non-cache Value");
                respEnv = RestAssured.given().headers(HeaderMap).param("current_time", System.currentTimeMillis()).contentType("application/json").when().get();
            }
            System.out.println(respEnv.asString());
            int StatucCode = respEnv.statusCode();
/* if(StatucCode!=200){
childTest1.log(LogStatus.INFO, "Exception or Assert Error " + respEnv.asString());
Assert.fail("Failed Testcase Please check log");
}else{
Reporter.log(respEnv.asString());
}*/
// Assert.assertEquals(StatucCode, 200);
//respEnv.then().assertThat().statusCode(200);
            Reporter.log(respEnv.asString());
            return respEnv;
        } catch (Exception e) {
            childTest1.log(LogStatus.FAIL, "Exception or Assert Error " + respEnv.toString());
// Assert.fail("Failed Testcase Please check log");
        }
        return respEnv;
    }

    public synchronized String GetToken(EnvironmentParameterData environmentParameterData, Framework frameWork) throws InterruptedException {
        APIData apiData1 = frameWork.getData(APIData.class, "TC4");
        EnvironmentParameterData environmentParameterData1;
        String Parameter = "";
        if (environmentParameterData.getBaseurl().contains("www") || environmentParameterData.getBaseurl().contains("uat") || environmentParameterData.getBaseurl().contains("gama") || environmentParameterData.getBaseurl().contains("preprod")) {
            environmentParameterData1 = frameWork.getData(EnvironmentParameterData.class, "api");
            Parameter = "username=apiUser&password=btqsource@0321";
        } else {
            environmentParameterData1 = frameWork.getData(EnvironmentParameterData.class, "api");
            Parameter = "username=apiUser&password=qwert@#123";
        }
// EnvironmentParameterData environmentParameterData1 =frameWork.getData(EnvironmentParameterData.class, "ProduAPI");
        RestAssured.baseURI = environmentParameterData1.getBaseurl();
        RestAssured.basePath = apiData1.getPath();

//String Parameter = apiData1.getParameter();
        Map<String, String> Parammap = new HashMap();
        if (!(Parameter.contains("null"))) {
            String[] keyValuePair = Parameter.split("&");
            for (String keyValue : keyValuePair) {
                String[] keyValueNamePair = keyValue.split("=");
                String key = keyValueNamePair[0];
                String value = keyValueNamePair[1];
                Parammap.put(key, value);
            }
        }
        Response resp = RestAssured.given().contentType("application/json").headers("params", "gender:4194,app_version:5.8.1").body(Parammap).when().post("");
        String response = resp.asString();
        System.out.println(response);
        resp.then().assertThat().statusCode(200);
/*Reporter.log(resp.asString());
String tempResponse = resp.asString().substring(1);
if (!tempResponse.startsWith("{")) {
tempResponse = "{" + tempResponse;
}
JsonPath jsonPath = new JsonPath(tempResponse.trim());
token = jsonPath.get("response.authentication_token");
customer_id = jsonPath.get("response.customer_id");



String result = token + "abcd123" + customer_id;
Thread.sleep(2000);
*/
        return response;
    }

    public synchronized String GetField(Response resp, String FieldName) throws InterruptedException {
        String response = resp.asString();
//System.out.println(response);
        resp.then().assertThat().statusCode(200);
        Reporter.log(resp.asString());
        String tempResponse = resp.asString().substring(1);
        if (!tempResponse.startsWith("{")) {
            tempResponse = "{" + tempResponse;
        }
        JsonPath jsonPath = new JsonPath(tempResponse.trim());
        String result = jsonPath.get(FieldName).toString();
        return result;
    }

    public synchronized HashMap<String, Object> Bodyparameter(String[] keyValuePair){
        HashMap keyValuePairMap = new HashMap<String, Object>();
        for (Object keyValue : keyValuePair) {
            if(keyValue instanceof Map){

            }
            String[] keyValueNamePair = keyValue.toString().split("=");
            String key = keyValueNamePair[0];
            Object value = keyValueNamePair[1];
            if(value.toString().startsWith("\"")&& value.toString().endsWith("\"")){
                value = value.toString().replace("\"", "");
            }
            if(value.toString().startsWith("[")&&value.toString().endsWith("]")&& value.toString().length()<3) {
                String[] strArray = new String[] {};
                value = strArray;
            }
            else if (value.toString().startsWith("[")&&value.toString().endsWith("]")&& value.toString().length()>2){
                String[] ChildkeyValuePair = value.toString().split("&");
                value = Bodyparameter(ChildkeyValuePair);
            }


            keyValuePairMap.put(key, value);
        }
        return keyValuePairMap;
    }


}
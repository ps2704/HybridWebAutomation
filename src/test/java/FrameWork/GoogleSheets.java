package FrameWork;

import com.google.api.client.googleapis.auth.oauth2.GoogleCredential;
import com.google.api.client.googleapis.javanet.GoogleNetHttpTransport;
import com.google.api.client.http.HttpTransport;
import com.google.api.client.json.jackson2.JacksonFactory;
import com.google.gdata.client.spreadsheet.SpreadsheetService;
import com.google.gdata.data.spreadsheet.CellFeed;
import com.google.gdata.data.spreadsheet.SpreadsheetEntry;
import com.google.gdata.data.spreadsheet.SpreadsheetFeed;
import com.google.gdata.util.ServiceException;
import org.testng.Reporter;
import org.testng.annotations.Test;

import java.io.File;
import java.io.IOException;
import java.net.URISyntaxException;
import java.net.URL;
import java.security.GeneralSecurityException;
import java.util.Arrays;
import java.util.List;

public class GoogleSheets {

    private final String CLIENT_ID = "automationdata@automation-193805.iam.gserviceaccount.com";
    // Add requested scopes.
    private final List<String> SCOPES = Arrays
            .asList("https://spreadsheets.google.com/feeds");
    private final String P12FILE = "./Automation-c78cfc6f1fe7.p12";
    private static volatile SpreadsheetService service = null;

    public SpreadsheetService getService() throws GeneralSecurityException, IOException, URISyntaxException {
        SpreadsheetService service;
        service = new SpreadsheetService(
                "google-spreadsheet");
        service.setProtocolVersion(SpreadsheetService.Versions.V3);
        GoogleCredential credential = getCredentials();
        service.setOAuth2Credentials(credential);
        return service;
    }

    private GoogleCredential getCredentials() throws GeneralSecurityException,
            IOException, URISyntaxException {
        JacksonFactory JSON_FACTORY = JacksonFactory.getDefaultInstance();
        HttpTransport httpTransport = GoogleNetHttpTransport
                .newTrustedTransport();

        // URL fileUrl = this.getClass().getResource(P12FILE);
        URL fileUrl = getClass().getClassLoader().getResource(P12FILE);
        Reporter.log("verifying path");
        Reporter.log(fileUrl.toURI().getPath());
        System.out.println(fileUrl.toURI());

        // URL fileUrl = file:/C:/Users/Sourabh Shreemali/Desktop/P12Folder/NeoProject-680b4448d369.p12;
        GoogleCredential credential = new GoogleCredential.Builder()
                .setTransport(httpTransport)
                .setJsonFactory(JSON_FACTORY)
                .setServiceAccountId(CLIENT_ID)
                .setServiceAccountPrivateKeyFromP12File(
                        new File(fileUrl.toURI()))
                .setServiceAccountScopes(SCOPES).build();

        return credential;
    }


    @Test
    public static SpreadsheetEntry getSpreadSheet(String SpreadSheetName) throws GeneralSecurityException,
            IOException, ServiceException, URISyntaxException {
        CellFeed cellFeed = null;
        SpreadsheetEntry spreadSheet = null;
        URL SPREADSHEET_FEED_URL = null;

        try {


            SPREADSHEET_FEED_URL = new URL(
                    "https://spreadsheets.google.com/feeds/spreadsheets/private/full");
            //://docs.google.com/spreadsheets/d
            //https://spreadsheets.google.com/feeds/worksheets/1hLTDrzuVz9ls0OPnxCIa3RLofF35W9xOl_89A2begl4/private/basic
            SpreadsheetFeed feed = service.getFeed(SPREADSHEET_FEED_URL,
                    SpreadsheetFeed.class);
            List<SpreadsheetEntry> spreadsheets = feed.getEntries();


            for (SpreadsheetEntry GspreadSheet : spreadsheets) {
                String spreadsheetName = GspreadSheet.getTitle().getPlainText().trim();
                if (spreadsheetName.equalsIgnoreCase(SpreadSheetName)) {
                    spreadSheet = GspreadSheet;
                    break;
                }
            }


        } catch (Exception e) {
            // TODO Auto-generated catch block
            e.printStackTrace();

        }

        return spreadSheet;
    }


}

package FrameWork;

import com.google.api.client.googleapis.auth.oauth2.GoogleCredential;
import com.google.gdata.client.spreadsheet.SpreadsheetService;
import com.google.gdata.data.spreadsheet.ListEntry;
import com.google.gdata.data.spreadsheet.ListFeed;
import com.google.gdata.data.spreadsheet.SpreadsheetEntry;
import com.google.gdata.data.spreadsheet.WorksheetEntry;
import org.apache.poi.ss.usermodel.DataFormatter;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.testng.annotations.Test;

import java.io.File;
import java.io.FileInputStream;
import java.io.FilenameFilter;
import java.io.IOException;
import java.net.URISyntaxException;
import java.net.URL;
import java.security.GeneralSecurityException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;


public class DataBaseActivity {

    GoogleSheets GWorkSheet;
    static List<XSSFSheet> listOfshits;
    static Connection conn = null;

    public void dropTableFromGsheet() throws IOException, URISyntaxException, Throwable {
        GWorkSheet = new GoogleSheets();
        SpreadsheetService service = GWorkSheet.getService();
        SpreadsheetEntry GWorkbook = GWorkSheet.getSpreadSheet("Demo");
        List<WorksheetEntry> Sheets = GWorkbook.getWorksheets();
        for (WorksheetEntry sheet : Sheets) {
            String Tablename = sheet.getTitle().getPlainText();
            dropTable(Tablename);
        }
}

    List<XSSFSheet> getSheetsFromExcel() throws IOException {

        listOfshits = new ArrayList<XSSFSheet>();
        String Path = new File("File").getAbsolutePath();

        File[] files = getAllExcelsFromFolder(Path);

        for (File file : files) {
            FileInputStream fileInput = new FileInputStream(file);
            XSSFWorkbook workbook = new XSSFWorkbook(fileInput);
            int sheetNumber = workbook.getNumberOfSheets();
            for (int x = 0; x < sheetNumber; x++) {
                XSSFSheet sheet = workbook.getSheetAt(x);
                listOfshits.add(sheet);
            }
        }
        return listOfshits;
    }

    private static File[] getAllExcelsFromFolder(String dirName) {

        File dir = new File(dirName);

        return dir.listFiles(new FilenameFilter() {
            public boolean accept(File dir, String filename) {
                return filename.endsWith(".xlsx");
            }
        });

    }


    public static void dropTable(String tableName) throws SQLException {
        Statement statement = conn.createStatement();
        String sql = "drop table " + tableName + "";
        try {
            statement.executeUpdate(sql);
        } catch (Exception e) {
            // TODO Auto-generated catch block
            //e.printStackTrace();
        }
    }


    public static void StartConnection() throws SQLException {
        try {
            conn = DriverManager.getConnection("jdbc:derby:testmdb1;create=true", "test", "test");
        } catch (Exception e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
            System.out.println(e.getCause().getMessage());
            if (e.getCause().getMessage().equalsIgnoreCase("Failed to start database"))
                conn = DriverManager.getConnection("jdbc:derby:;shutdown=true");//closing database
            conn = DriverManager.getConnection("jdbc:derby:testmdb1;create=true", "test", "test");//starting database
        }
    }

    @Test
    public void createTableFromGSpreadsheets() throws Throwable {
        StartConnection();

        GWorkSheet = new GoogleSheets();
        SpreadsheetService service = GWorkSheet.getService();
        SpreadsheetEntry GWorkbook = GWorkSheet.getSpreadSheet("Demo");
        List<WorksheetEntry> Sheets = GWorkbook.getWorksheets();
        for (WorksheetEntry sheet : Sheets) {
            String Tablename = sheet.getTitle().getPlainText();
            System.out.println(Tablename);
            URL SheetlistFeedUrl = sheet.getListFeedUrl();
            ListFeed RowList = service.getFeed(SheetlistFeedUrl, ListFeed.class);
            ListEntry headers = RowList.getEntries().get(0);
            StringBuilder HeaderCellValue = new StringBuilder();
            String headerrow = getAppendedGHeaderData(HeaderCellValue, headers);
            StringBuilder headerrows = new StringBuilder(headerrow);
            creatHeader(headerrows, Tablename);
            StringBuilder RowCellsheet = new StringBuilder();

            for (ListEntry row : RowList.getEntries()) {

                String Row = getAppendedGRowData(RowCellsheet, row);

                StringBuilder rowValue = new StringBuilder(Row);
                insertDataIntoTable(rowValue, Tablename);

            }

        }
    }

    public static void dumpDataIntoDatabase() throws Throwable {

        StartConnection();


        listOfshits = new ArrayList<XSSFSheet>();
        //	String Path = "C:/Users/Sourabh Shreemali/Desktop/ExcelWorkbook";
        String Path = new File("File").getAbsolutePath();

        File[] files = getAllExcelsFromFolder(Path);

        for (File file : files) {
            FileInputStream fileInput = new FileInputStream(file);
            XSSFWorkbook workbook = new XSSFWorkbook(fileInput);
            int sheetNumber = workbook.getNumberOfSheets();
            for (int x = 0; x < sheetNumber; x++) {
                XSSFSheet sheet = workbook.getSheetAt(x);
                listOfshits.add(sheet);
            }
            for (XSSFSheet sheet1 : listOfshits) {
                String Tablename = sheet1.getSheetName();
                StringBuilder HeaderCellValue = new StringBuilder();
                int physicalrows = sheet1.getPhysicalNumberOfRows();
                XSSFRow header = sheet1.getRow(0);
                int Physicalcolumns = header.getPhysicalNumberOfCells();
                HeaderCellValue = getAppendedHeaderData(HeaderCellValue, header);
                creatHeader(HeaderCellValue, Tablename);
                int NoOfPhysicalRow = sheet1.getPhysicalNumberOfRows();

                for (int x1 = 1; x1 < NoOfPhysicalRow; x1++) {
                    XSSFRow Row = sheet1.getRow(x1);
                    StringBuilder RowCellValue = new StringBuilder();
                    RowCellValue = getAppendedRowData(RowCellValue, Row, Physicalcolumns);
                    insertDataIntoTable(RowCellValue, Tablename);

                }

            }

            // create table "+sheet+" ();

        }


    }

    private static StringBuilder getAppendedRowData(StringBuilder rowNameOfsheet, XSSFRow row, int physicalcolumns) {

        DataFormatter formatter = new DataFormatter();
        rowNameOfsheet = rowNameOfsheet.append("'" + formatter.formatCellValue(row.getCell(0)) + "'");
        for (int x = 1; x < physicalcolumns; x++) {
            String rowName = formatter.formatCellValue(row.getCell(x));

            rowNameOfsheet = rowNameOfsheet.append(",'" + rowName + "'");

        }
        return rowNameOfsheet;
    }

    private static StringBuilder getAppendedHeaderData(StringBuilder headerNameOfsheet, XSSFRow header) {
        int NoOfcells = header.getPhysicalNumberOfCells();
        headerNameOfsheet = headerNameOfsheet.append(header.getCell(0).getStringCellValue() + " varchar(30000)");
        for (int x = 1; x < NoOfcells; x++) {
            String headerName = "" + header.getCell(x).getStringCellValue() + " varchar(30000)";
            headerNameOfsheet = headerNameOfsheet.append("," + headerName);
        }
        return headerNameOfsheet;
    }

    private static void creatHeader(StringBuilder headerCellValue, String Tablename) throws SQLException {
        // TODO Auto-generated method stub
        String createSQL = "create table " + Tablename + " (" + headerCellValue + ")";
        System.out.println(createSQL);

        try {
            conn.createStatement().execute(createSQL);
        } catch (SQLException e) {

            e.printStackTrace();
        }
    }

    private static String getAppendedGHeaderData(StringBuilder headerNameOfsheet, ListEntry headers) {
        Set<String> tags = headers.getCustomElements().getTags();

        for (String tag : tags) {
            String headerName = "" + tag + " varchar(30000)";
            headerNameOfsheet = headerNameOfsheet.append(headerName + ",");
        }
        return headerNameOfsheet.substring(0, headerNameOfsheet.length() - 1);
    }

    private static String getAppendedGRowData(StringBuilder rowNameOfsheet, ListEntry row) {
        rowNameOfsheet = new StringBuilder();
        for (String tag : row.getCustomElements().getTags()) {
            rowNameOfsheet = rowNameOfsheet.append("'" + row.getCustomElements().getValue(tag) + "',");
        }


        return rowNameOfsheet.substring(0, rowNameOfsheet.length() - 1);
    }

    private static void insertDataIntoTable(StringBuilder rowCellValue, String Tablename) throws SQLException {
        String insertTabledata = "insert into " + Tablename + " values(" + rowCellValue + ")";
        System.out.println(insertTabledata);
        conn.createStatement().execute(insertTabledata);

    }

}

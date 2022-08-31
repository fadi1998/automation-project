package Utils;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;

public class ExcelUtils {

    // Read all rows from Excel, for each row create an object of type
    // "RegistraionData" (firstName, lastName, password, confirmPassword, ...)
    // add all objects to a List


    // This method handles the excel - opens it and reads the data from the
    // respective cells using a for-loop & returns it in the form of a string array
    public String[][] getExcelData(String fileName, String sheetName) throws IOException {
        String[][] data = null;
        try {

            FileInputStream fis = new FileInputStream(fileName);
            XSSFWorkbook workbook = new XSSFWorkbook(fis);
            XSSFSheet sheet = workbook.getSheet(sheetName);
            XSSFRow row = sheet.getRow(0);
            int noOfRows = sheet.getPhysicalNumberOfRows();
            int noOfCols = 5;
            Cell cell;

            data = new String[noOfRows - 1][noOfCols];

            for (int i = 1; i < noOfRows; i++) {
                for (int j = 0; j < noOfCols; j++) {
                    row = sheet.getRow(i);
                    cell = row.getCell(j);
                    if(! cell.getCellType().equals("BLANK")) {
                        data[i - 1][j] = cell.getStringCellValue();
                    }
                }
            }
        } catch (Exception e) {
            System.out.println("The exception is: " + e.getMessage());
        }
        return data;
    }

      public  void setCellValue ( int row, int col, String fileName,String sheetName, String value){
            FileInputStream inStream = null;
            FileOutputStream outStream = null;
            XSSFWorkbook workbook = null;
          try {
                inStream = new FileInputStream(fileName);
                workbook = new XSSFWorkbook(inStream);
              XSSFSheet sheet = workbook.getSheet(sheetName);
                Cell cell = sheet.getRow(row).getCell(col);
                    cell.setCellValue(value);
                outStream = new FileOutputStream(fileName);
                workbook.write(outStream);
                outStream.close();
                inStream.close();
            } catch (FileNotFoundException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            }
    }

}

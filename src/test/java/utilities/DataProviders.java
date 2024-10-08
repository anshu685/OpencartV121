package utilities;

import org.testng.annotations.DataProvider;

import java.io.IOException;

public class DataProviders {

    //DataProvider 1 Login data

    @DataProvider(name = "LoginData")
    public String [][] getData() throws IOException{
//        String path = ".\\testData\\OpenCart_LoginData.xlxs";
//        String path = "testData/OpenCart_LoginData.xlsx";
        String path = "C:\\Users\\Anshu\\IdeaProjects\\PracticeSelenium\\OpencartV121\\testData\\OpenCart_LoginData.xlsx";

        ExcelUtilities xlutil= new ExcelUtilities(path); // creating an object for xlutility

        int totalRows=xlutil.getRowCount("sheet1");
        int totalCols=xlutil.getCellCount("sheet1",1);

        String[][] logindata=new String[totalRows][totalCols];

        for(int i=1;i<=totalRows;i++){
            for(int j=0;j<totalCols;j++){
                logindata[i-1][j]= xlutil.getCellData("sheet1",i,j);
            }
        }
        return logindata; // returning dimension array
    }

}

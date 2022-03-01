import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;


import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class ReadExcelFile {

    public static ArrayList<String> ReadExcel(String filePath, String fileName, String sheetName) throws InterruptedException, IOException, FileNotFoundException {
        List<String> arrName = new ArrayList<String>();
        File file = new File(filePath+"\\"+fileName);
        FileInputStream inputStream = new FileInputStream(file);
        Workbook AddCatalog = null;
        String fileExtensionName = fileName.substring(fileName.indexOf("."));
        if(fileExtensionName.equals(".xls")){
            AddCatalog = new HSSFWorkbook(inputStream);
        }
        else if(fileExtensionName.equals(".xlsx")){
            AddCatalog = new XSSFWorkbook(inputStream);
        }
        Sheet AddCatalogSheet = AddCatalog.getSheet(sheetName);
        int rowcount = AddCatalogSheet.getLastRowNum()- AddCatalogSheet.getFirstRowNum();
        System.out.println("Total row number: "+rowcount);
        for(int i=1; i<rowcount+1; i++){
            Row row = AddCatalogSheet.getRow(i);

            for(int j=0; j<row.getLastCellNum(); j++){
                Cell cell = row.getCell(j);
                arrName.add(cell.getStringCellValue());
            }
            System.out.println(arrName);
            System.out.println("Size of the arrayList: "+arrName.size());
            Iterator<String> itr = arrName.iterator();
            while(itr.hasNext()){
                System.out.println("arrayList values: "+itr.next());
            }
        }
        return (ArrayList<String>) arrName;
    }

}

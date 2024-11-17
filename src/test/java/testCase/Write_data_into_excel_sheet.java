package testCase;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.sql.Driver;

import org.apache.poi.EncryptedDocumentException;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.usermodel.WorkbookFactory;
import org.openqa.selenium.OutputType;

public class Write_data_into_excel_sheet {

	public static void main(String[] args) throws EncryptedDocumentException, IOException {
		FileInputStream fis=new FileInputStream("./testData//Opencart_LoginData.xlsx");
		 Workbook workbook = WorkbookFactory.create(fis);
		 workbook.getSheet("sheet1").getRow(6).getCell(0).setCellValue("Hover dam");
		 FileOutputStream fos=new FileOutputStream("./testData//Opencart_LoginData.xlsx");
		 workbook.write(fos);
		 workbook.close();
		 
	}

}

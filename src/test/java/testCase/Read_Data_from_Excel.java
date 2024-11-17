package testCase;

import java.io.FileInputStream;
import java.io.IOException;

import org.apache.poi.EncryptedDocumentException;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.usermodel.WorkbookFactory;

public class Read_Data_from_Excel {

	public static void main(String[] args) throws EncryptedDocumentException, IOException  {
		FileInputStream fis=new FileInputStream("./testData//Opencart_LoginData.xlsx");
		Workbook workbook = WorkbookFactory.create(fis);
		String data=workbook.getSheet("sheet1").getRow(1).getCell(1).getStringCellValue();
		System.out.println(data);
	}

}

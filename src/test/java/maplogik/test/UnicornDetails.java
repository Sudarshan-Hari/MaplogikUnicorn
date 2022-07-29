package maplogik.test;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.LinkedHashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

import org.apache.poi.ss.usermodel.DataFormatter;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.openqa.selenium.By;


import maven.Base;
import utility.Utilities;

public class UnicornDetails extends Base {


	Utilities util;
	public UnicornDetails() {
		
		util = new Utilities(driver);
	
	}

	private  UnicornDetails loginPage = new  UnicornDetails();

	private By logout = By.xpath("//*[@href='http://maplogik.com/index.php/student/logout']");
	
	static int i=1;
	
	List<List<String>> unicDet = new LinkedList<>();

	Map<String, String> loginCredentials;

	public void loginMultipleStudents(String details) {
		loginCredentials = readExcelSheet();
		loginCredentials.forEach((k, v) -> {
			Login sl = new Login();
			sl.stdLogin(k, v);
			
			String studNameXpath = "(//*[@id=\\\"apexchart\\\"]/div[1]/div["+i+"]/div/div[2]/div/div[2]/p/text()[2])";
			String collegeXpath = "(//*[@id=\\\"apexchart\\\"]/div[1]/div["+i+"]/div/div[2]/div/div[2]/p/text()[3])";
			String distXpath = "(//*[@id=\\\"apexchart\\\"]/div[1]/div["+i+"]/div/div[2]/div/div[2]/p/text()[6])";
			
			By nameofStudent = By.xpath(studNameXpath);
			By nameofcollege = By.xpath(collegeXpath);
			By nameofdistrict = By.xpath(distXpath);
			String stdname = util.waitForElementPresent(nameofStudent).getText();
			String colname = util.waitForElementPresent(nameofcollege).getText();
			String dist = util.waitForElementPresent(nameofdistrict).getText();
			
			List<String> li = new LinkedList<>();
			li.add(stdname);
			li.add(colname);
			li.add(dist);
			
			unicDet.add(li);
			logOut();
		});
	
		System.out.println(unicDet);
		i++;
	}


	public Map<String, String> readExcelSheet() {
		XSSFWorkbook wb = null;
		try {
			wb = new XSSFWorkbook(new FileInputStream("./src/main/resources/Login_data.xlsx"));
		} catch (IOException e) {
			e.printStackTrace();
		}
		Map<String, String> loginCredentials = new LinkedHashMap<>();
		XSSFSheet sheetAt = wb.getSheetAt(0);
		int lastRow = sheetAt.getLastRowNum();
		DataFormatter format = new DataFormatter();
		for (int i = 1; i <= lastRow; i++) {
			String StuID = format.formatCellValue(sheetAt.getRow(i).getCell(0));
			String Phno = format.formatCellValue(sheetAt.getRow(i).getCell(1));
			loginCredentials.put(StuID, Phno);
		}
		return loginCredentials;
	}

	int rowInc = 1;

	public void excelWrite(String sheetName) {
		XSSFWorkbook wb = null;
		try {
			wb = new XSSFWorkbook(new FileInputStream("./maven/Excel Files/UnicornRankingApp.xlsx"));
		} catch (Exception e) {
			e.printStackTrace();
		}
		// wb.createSheet(sheetName);
		XSSFSheet sheet = wb.getSheet(sheetName);
		XSSFRow headerRow = sheet.createRow(0);
		headerRow.createCell(0).setCellValue("Std_Name");
		headerRow.createCell(1).setCellValue("Std_col");
		headerRow.createCell(2).setCellValue("Std_Dist");
		String stdname = unicDet.get(0).get(0);
		String colname = unicDet.get(1).get(1);
		String dist = unicDet.get(2).get(2);
		
		{
			XSSFRow createdRow = sheet.createRow(rowInc);
			createdRow.createCell(0).setCellValue(stdname);
			createdRow.createCell(1).setCellValue(colname);
			createdRow.createCell(2).setCellValue(dist);
			rowInc++;
		};
		rowInc = 1;
		loginCredentials.forEach((k, v) -> {
			sheet.getRow(rowInc).createCell(2).setCellValue(k);
			rowInc++;
		});
		rowInc = 1;
		try {
			wb.write(new FileOutputStream("./maven/Excel Files/UnicornRankingApp.xlsx"));
			wb.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}


	public void logOut() {
		util.waitForElementClickable(logout).click();
	}


	public UnicornDetails getLoginPage() {
		return loginPage;
	}


	public void setLoginPage(UnicornDetails loginPage) {
		this.loginPage = loginPage;
	}
}


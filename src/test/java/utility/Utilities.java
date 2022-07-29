package utility;

import java.io.File;
import java.io.IOException;
import java.time.Duration;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.regex.Pattern;

import org.apache.poi.ss.usermodel.DataFormatter;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.io.FileHandler;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

public class Utilities {
	
	private WebDriverWait wait;
	private Actions actions;
	private WebDriver driver;
	
	public Utilities(WebDriver driver) {
		this.driver = driver;
	}

	/**
	 * 
	 * @param locator
	 * @return WebElement
	 */
	public WebElement waitForElementClickable(By locator) {
		wait = new WebDriverWait(driver, Duration.ofSeconds(10));
		return wait.until(ExpectedConditions.elementToBeClickable(locator));
	}

	/**
	 * 
	 * @param locator
	 * @return
	 */
	public WebElement waitForElementPresent(By locator) {
		wait = new WebDriverWait(driver, Duration.ofSeconds(10));
		return wait.until(ExpectedConditions.presenceOfElementLocated(locator));
	}
	
	/**
	 * Wait for the given locator contains any number
	 * 
	 * @param locator
	 * @return
	 */
	public boolean waitForTextMatch(By locator) {
		wait = new WebDriverWait(driver, Duration.ofSeconds(10));
		return wait.until(ExpectedConditions.textMatches(locator, Pattern.compile("\\d+")));
	}

	/**
	 * TakesScreenshot of the given locator
	 * 
	 * @param locator
	 * @param storeLocation
	 * @param id
	 */
	public void takesScreenShot(WebElement locator, String storeLocation, String id) {
		File screenShot = locator.getScreenshotAs(OutputType.FILE);
		String directory = storeLocation + id + ".png";
		File finalDirectory = new File(directory);
		try {
			FileHandler.copy(screenShot, finalDirectory);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	/**
	 * 
	 * @param storeLocation
	 * @param dateAndTime
	 */
	public String takesScreenShotPage(String storeLocation, String dateAndTime) {
		File screenShot = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);
		String directory = storeLocation+dateAndTime+ ".png";
		File finalDirectory = new File(directory);
		try {
			FileHandler.copy(screenShot, finalDirectory);
		} catch (IOException e) {
			e.printStackTrace();
		}
		return directory;
	}

	/**
	 * 
	 * @param locator
	 * @return String
	 */
	public String getText(By locator) {
		return driver.findElement(locator).getText();
	}

	/**
	 * Click action by locator
	 * 
	 * @param locator
	 */
	public void click(By locator) {
		driver.findElement(locator).click();
	}

	/**
	 * SendKey Action
	 * 
	 * @param locator
	 * @param keyValue
	 */
	public void sendKey(By locator, String keyValue) {
		driver.findElement(locator).sendKeys(keyValue);
	}

	/**
	 * 
	 * @param locator
	 * @param text
	 */
	public void selectByText(By locator, String text) {
		WebElement element = driver.findElement(locator);
		Select select = new Select(element);
		select.selectByVisibleText(text);
	}

	/**
	 * Upload the file the given location
	 * 
	 * @param locator
	 * @param directory
	 */
	public void uploadFile(By locator, String directory) {
		driver.findElement(locator).sendKeys(new File(directory).getAbsolutePath());
	}
	
	/**
	 * 
	 * @param locator
	 * @return List<WebElement>
	 */
	public List<WebElement> findElements(By locator) {
		return driver.findElements(locator);
	}
	
	/**
	 * 
	 * @param locator
	 */
	public void clear(By locator) {
		driver.findElement(locator).clear();
	}

	/**
	 * 
	 * @param locator
	 * @return List<WebElement>
	 */
	public List<WebElement> waitNumberOfElementsToBeMoreThan(By locator) {
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofMillis(500));
		return wait.until(ExpectedConditions.numberOfElementsToBeMoreThan(locator, 1));
	}
	
	/**
	 * 
	 * @param locator
	 */
	public void clickMoveToElement(By locator) {
		actions = new Actions(driver);
		actions.click(driver.findElement(locator)).perform();;
	}
	
	/**
	 * 
	 * @return
	 */
	public String pageTitle() {
		return driver.getTitle();
	}
	
	/**
	 * 
	 * @param columnNum
	 * @param excelLocation
	 * @return
	 */
	public Map<String,String> readExcelFileOnlyByRow(int columnNum,String excelLocation) {
		XSSFWorkbook wb = null;
		try {
			wb = new XSSFWorkbook(excelLocation);
		} catch (IOException e) {
			e.printStackTrace();
		}
		 XSSFSheet sheet = wb.getSheet("Sheet1");
		 DataFormatter format = new DataFormatter();
		int firstRow = sheet.getFirstRowNum();
		int lastRow = sheet.getLastRowNum();
		Map<String,String> certifiDetail = new HashMap<String,String>();
		for(int i=firstRow;i<=lastRow;i++) {
			XSSFRow row = sheet.getRow(i);
			String key = format.formatCellValue(row.getCell(0));
			String value = format.formatCellValue(row.getCell(columnNum));
			certifiDetail.put(key, value);
		}
		return certifiDetail;
	}
	
	/**
	 * 
	 * @param cssSelector
	 */
	public void jsClick(String cssSelector) {
		((JavascriptExecutor) driver).executeScript("document.querySelector("+cssSelector+").click();");
	}
	
	
	public WebElement findElement(By locator) {
		return driver.findElement(locator);
	}
}

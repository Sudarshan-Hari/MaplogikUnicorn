package maven;


import java.io.FileInputStream;
import java.util.Properties;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

public class Base{

	public static WebDriver driver;
	public Properties prop;

	public Base() {
		this.prop = readProp();
	}

	public WebDriver initBrowser() {
		System.setProperty("webdriver.chrome.driver", "C:\\Users\\admin\\Desktop\\chromedriver.exe");
		driver = new ChromeDriver();
		driver.manage().window().maximize();
		driver.manage().deleteAllCookies();
		return driver;
	}

	public Properties readProp() {
		Properties prop = new Properties();
		try {
			prop.load(new FileInputStream("./src/main/resources/configFiles/configuration.properties"));
		} catch (Exception e) {
			e.printStackTrace();
		}
		return prop;
	}

	public static void tearDown() {
		driver.quit();
	}

}

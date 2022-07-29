package maplogik.test;


import org.openqa.selenium.By;

import maven.Base;
import utility.Utilities;


public class Login extends Base{

	Utilities util;
	
	public Login() {
		util = new Utilities(driver);
	}
	
	// By locators
	private By StuIDTextBox = By.id("login-student-id");
	private By PhnoTextBox = By.id("login-mobile");
	private By loginButton = By.xpath("//*[text()='Log in']");
	private By testOtp = By.id("test_otp");
	private By loginOtpTextBox = By.id("login-otp");
	private By submitButton = By.xpath("//button[text()='Submit']");
	
	/**
	 * Student Login Method
	 */
	public void stdLogin(String loginId, String phoneNum) {
		util.sendKey(StuIDTextBox, loginId);
		util.sendKey(PhnoTextBox, phoneNum);
		util.click(loginButton);
		util.waitForTextMatch(testOtp);
		String otp = util.getText(testOtp);
		util.sendKey(loginOtpTextBox, otp);
		util.click(submitButton);
	}
	
	public void loadpage() {
		driver.get(prop.getProperty("LoginUrlStudent"));
	}
	
}

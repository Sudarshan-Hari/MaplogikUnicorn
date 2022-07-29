package stepdef;

import maplogik.test.Login;
import maplogik.test.UnicornDetails;

import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;

public class UnicornRankingStepDef {

	Login stulogin;
	UnicornDetails unicorndash;
	
	@Given("open maplogik student login url")
	public void open_maplogik_student_login_url() {
		stulogin = new Login ();
		stulogin.loadpage();		
	}

	@When("enter multiple student ID and password and fetch Student name college name and district")
	public void enter_multiple_student_ID_and_password_and_fetch_Student_name_college_name_and_district(String details){
		unicorndash = new UnicornDetails();
		unicorndash.loginMultipleStudents(details);
	}
	
	@Then("save that data to an excel file")
	public void save_that_data_to_an_excel_file (String sheetName) {
		unicorndash.excelWrite(sheetName);
	}}

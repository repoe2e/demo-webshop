package testes;



import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.WebDriver;

import drivers.Drivers;
import pages.HomePage;
import utils.ScreenshotUtil;

public class LoginTest {

	private WebDriver driver;
	private HomePage home;

	@BeforeEach
	public void setup() {
		driver = Drivers.createDriver();
		home = new HomePage(driver);
	}

	@AfterEach
	public void tearDown() {
		if(driver != null) {
			driver.quit();
		}
	}

	@Test
	public void senhaInvalida() {
		home.login("testetestes@gmail.com", "", "The credentials provided are incorrect");
		ScreenshotUtil.screenShot(driver, "Login", "senhaInvalida");	
	}

	@Test
	public void emailForaDoPadrao() {
		home.login("testetestesgmail.com", "", "Please enter a valid email address.");
	}

	@Test
	public void emailInexistente() {
		home.login("10testetestes@gmail.com", "", "Login was unsuccessful. Please correct the errors and try again.");
	}

	@Test
	public void senhaBranco() {
		home.login("testetestes@gmail.com", "", "The credentials provided are incorrect");
	}

	@Test
	public void emailBranco() {
		home.login("", "B?0P248kEf-P", "No customer account found");
	}
	
	@Test
	public void login() {
		home.login("testetestes@gmail.com", "B?0P248kEf-P");
	}

}

package testes;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.WebDriver;

import drivers.Drivers;
import pages.HomePage;
import utils.ScreenshotUtil;

public class ComprarProdutoTest {

	private WebDriver driver;
	private HomePage home;

	@BeforeEach
	public void setup() {

		driver = Drivers.createDriver();
		home = new HomePage(driver);
		home.login("testetestes@gmail.com", "B?0P248kEf-P");
	}

	@AfterEach
	public void tearDown() {
		if (driver != null) {
			driver.quit();
		}
	}

	@Test
	public void comprarProduto() {
		home.limparCarrinho();
		home.headerMenu("Computer");
		ScreenshotUtil.screenShot(driver, "comprarProduto", "comprarProduto");
		home.addCarrinho("Desk", "1");
		ScreenshotUtil.screenShot(driver, "comprarProduto", "comprarProduto");
		home.validarCarrinho();
		ScreenshotUtil.screenShot(driver, "comprarProduto", "comprarProduto");
	}

}

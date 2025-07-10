package stepdefinitions;

import org.junit.jupiter.api.AfterEach;
import org.openqa.selenium.WebDriver;

import drivers.Drivers;
import io.cucumber.java.pt.Dado;
import io.cucumber.java.pt.Então;
import io.cucumber.java.pt.Quando;
import pages.HomePage;

public class LoginTest {

	private WebDriver driver;
	private HomePage home;
	
	@AfterEach
	public void tearDown() {
		if(driver != null) {
			driver.quit();
		}
	}


	@Dado("que o usuário acesso o formulário de login")
	public void que_o_usuário_acesso_o_formulário_de_login() {
		driver = Drivers.createDriver();
		home = new HomePage(driver);
	}

	@Quando("enviar os dados {string} e {string} corretamente")
	public void enviar_os_dados_e_corretamente(String email, String passoword) {
		home.login(email, passoword);
	}

	@Então("então valida o {string} logado")
	public void então_valida_o_logado(String email) {
		home.validaLogin(email);
	}

	@Quando("enviar os dados {string} e {string} incorretamente")
	public void enviar_os_dados_e_incorretamente(String string, String string2) {

	}

	@Então("então valida a {string} com a inconsistência")
	public void então_valida_a_com_a_inconsistência(String string) {

	}

}

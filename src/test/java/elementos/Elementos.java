package elementos;



import org.openqa.selenium.By;
import lombok.Getter;

@Getter
public class Elementos {
	
	/*
	 * Elementos para registrar usuário
	 */
	 private By menuRegister = By.xpath("//a[@href='/register']");
	 private By firstName = By.id("FirstName");
	 private By lastName = By.id("LastName");
	 private By email = By.id("Email");
	 private By password = By.id("Password");
	 private By confirmPassword = By.id("ConfirmPassword");
	 private By btnRegister = By.id("register-button");
	 
	 
	 
	 /*
	  * Elementos para login
	  */
	 private By menuLogin = By.xpath("//a[@href='/login']");
	 private By btnLogin = By.xpath("//input[@class='button-1 login-button']");
	
	 
	 
	 //Elementos cliente logado
	  private By menuLogout = By.xpath("//a[@href='/logout']");
	  
	  
	  // Carrinho
	  private  By acessarCarrinho = By.xpath("//span[text()='Shopping cart']");
	  private By removeCart = By.name("removefromcart");
	  private By updateCart = By.name("updatecart");
	  
	  
	  By valorTotal = By.xpath("//span[@class='product-price order-total']//strong");
	  By taxa = By.xpath("//tbody//tr[3]//span[@class='product-price']");
	  By totalComTaxa = By.xpath("//span[@class='product-price order-total']");
	 
}

package pages;

import java.text.NumberFormat;
import java.util.Locale;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

import elementos.Elementos;
import metodos.Metodos;

public class HomePage {

	private WebDriver driver;
	private Metodos metodo;
	private Elementos el = new Elementos();

	public HomePage(WebDriver driver) {
		this.driver = driver;
		this.metodo = new Metodos(driver);// injeta o driver
	}

	public void enviarDadosDeLogin(String email, String senha) {
		metodo.clicar(el.getMenuLogin());
		metodo.escrever(el.getEmail(), email);
		metodo.escrever(el.getPassword(), senha);
		metodo.clicar(el.getBtnLogin());
	}

	public void login(String email, String senha) {
		enviarDadosDeLogin(email, senha);
		metodo.validarTexto(email);
	}

	public void login(String email, String senha, String msgError) {
		enviarDadosDeLogin(email, senha);
		metodo.validarTexto(msgError);
	}
	
	public void validaLogin(String email) {
		metodo.validarTexto(email);
	}

	public void login(String email, String senha, String msgError, String msgErro1) {
		enviarDadosDeLogin(email, senha);
		metodo.validarTexto(msgError);
		metodo.validarTexto(msgErro1);
	}

	/**
	 * Se não possuir subcategoria informar no segundo parametro null
	 * 
	 * @param categoria
	 * @param subCategoria
	 */
	public void headerMenu(String categoria) {
		metodo.clicar("@class='top-menu'", categoria);
	}

	public void addCarrinho(String subMenu, String posicao) {
		By menu = By.xpath("//div[@class='sub-category-item']//a[contains(text(),'" + subMenu + "')]");
		By produto = By.xpath("//div[@class='product-grid']//div[@class='item-box'][" + posicao + "]");
		metodo.clicar(menu);
		metodo.clicar(produto);
		metodo.clicar(By.xpath("//input[@id='add-to-cart-button-72']"));
	}

	public void validarCarrinho() {
		metodo.clicar("span", "Shopping cart");
		metodo.clicar(By.xpath("//*[@id='termsofservice']"));
		String valorTotal = metodo.capturaTexto(el.getValorTotal());
		System.out.println("Valor total: " + valorTotal);
		metodo.clicar(By.xpath("//*[@id='checkout']"));
		metodo.clicar(By.xpath("//*[@onclick='Billing.save()']"));
		metodo.clicar(By.xpath("//*[@onclick='Shipping.save()']"));
		metodo.clicar(By.xpath("//*[@onclick='ShippingMethod.save()']"));
		metodo.clicar(By.xpath("//*[@onclick='PaymentMethod.save()']"));
		metodo.clicar(By.xpath("//*[@onclick='PaymentInfo.save()']"));

		metodo.scrollDown(450);
		metodo.esperarElementoVisivel(el.getTaxa(), 10);
		String valorTaxa = metodo.capturaTexto(el.getTaxa());
		System.out.println("Taxa: " + valorTaxa);

		double valorTotalConvertivo = parseParaDouble(valorTotal);
		double valorTaxaConvertido = parseParaDouble(valorTaxa);
		double totalComTaxaConvertido = valorTotalConvertivo + valorTaxaConvertido;

		// Converter para String no formato exibido
		String totalFormatado = formatarParaMoeda(totalComTaxaConvertido);

		System.out.println("Valor somado: " + totalFormatado);

		metodo.validarTextoValor(el.getTotalComTaxa(), totalFormatado);

		metodo.clicar(By.xpath("//*[@onclick='ConfirmOrder.save()']"));
	}

	private double parseParaDouble(String valor) {
		// Remove símbolos como R$, $, espaços, substitui vírgula por ponto
		return Double.parseDouble(valor.replaceAll("[^0-9,.-]", "").replace(",", "."));
	}

	// Método responsável por formatar um número decimal como moeda no padrão
	// brasileiro (ex: 1234.5 -> "1.234,50")
	private String formatarParaMoeda(double valor) {

		// Define o locale (localização) como Brasil, para garantir o uso do formato "R$
		// 1.234,56"
		Locale localeBR = new Locale("pt", "BR");

		// Cria uma instância de formatador de número com estilo de moeda para o Brasil
		NumberFormat formato = NumberFormat.getCurrencyInstance(localeBR);

		// Formata o valor passado como moeda, incluindo símbolo (ex: "R$ 822,00")
		String comSimbolo = formato.format(valor);

		// Remove o símbolo "R$" e espaços extras caso não seja necessário compará-lo no
		// teste
		return comSimbolo.replace("R$", "").trim();
	}

	public void limparCarrinho() {
		By empetyCart = By.xpath("//div[normalize-space(text())='Your Shopping Cart is empty!']");
		metodo.clicar(el.getAcessarCarrinho());
		if (!metodo.existText(empetyCart, "Your Shopping Cart is empty!")) {
			metodo.clicar(el.getRemoveCart());
			metodo.clicar(el.getUpdateCart());
		}
	}

}

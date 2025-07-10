package metodos;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.time.Duration;
import java.util.List;
import java.util.NoSuchElementException;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.NotFoundException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.opentest4j.AssertionFailedError;

public class Metodos {

	private WebDriver driver;

	public Metodos(WebDriver driver) {
		this.driver = driver;
	}

	public void clicar(By locator) {
		try {
			esperarElementoClicavel(locator, 5);
			driver.findElement(locator).click();
		} catch (Exception e) {
			throw new NoSuchElementException("Elemento não encontrado " + locator);
		}

	}

	public void clicar(String elemento, String texto) {
		try {
			driver.findElement(By.xpath("//*[" + elemento + "]//*[contains(text(),'" + texto + "')]")).click();
		} catch (Exception e) {
			throw new NoSuchElementException("Elemento não encontrado com o texto " + texto);
		}

	}

	public void escrever(By locator, String texto) {
		try {
			driver.findElement(locator).sendKeys(texto);
		} catch (Exception e) {
			throw new NoSuchElementException("Elemento não encontrado " + locator);
		}
	}

	public void validarTexto(By locator, String textoDesejado) {
		try {
			String textoCapturado = driver.findElement(locator).getText();
			assertEquals(textoDesejado, textoCapturado);
		} catch (Exception e) {
			throw new AssertionFailedError("Texto em branco ou errado!");
		}
	}

	public void validarTexto(String textoDesejado) {
		try {
			esperarElementoVisivel(By.xpath("//*[text()='" + textoDesejado + "']"), 5);
			String textoCapturado = driver.findElement(By.xpath("//*[text()='" + textoDesejado + "']")).getText();
			assertEquals(textoDesejado, textoCapturado);
		} catch (Exception e) {
			throw new AssertionFailedError("Texto em branco ou errado!");
		}
	}

	public void esperarElementoClicavel(By locator, int segundos) {
		try {
			WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(segundos));
			wait.until(ExpectedConditions.elementToBeClickable(locator));
		} catch (Exception e) {
			throw new NoSuchElementException("Elemento não encontrado " + locator);
		}
	}

	public void esperarElementoVisivel(By locator, int segundos) {
		try {
			WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(segundos));
			wait.until(ExpectedConditions.visibilityOfElementLocated(locator));
		} catch (Exception e) {
			throw new NoSuchElementException("Elemento não encontrado " + locator);
		}
	}

	public void validarUrl(String urlDesejada) {
		try {
			assertEquals(urlDesejada, driver.getCurrentUrl());
		} catch (Exception e) {
			throw new NotFoundException(urlDesejada + " não encontrada.");
		}
	}

	public String alrt() {

		return driver.switchTo().alert().getText();

	}

	public boolean existText(By elemento, String textoEsperado) {
		List<WebElement> elementos = driver.findElements(elemento);
		if (elementos.isEmpty()) {
			return false;
		}
		String textoNaTela = elementos.get(0).getText().trim();
		return textoNaTela.equalsIgnoreCase(textoEsperado);
	}

	public String capturaTexto(By elemento) {
		String texto = driver.findElement(elemento).getText();
		return texto;
	}

	public void scrollDown(int pixels) {
		JavascriptExecutor js = (JavascriptExecutor) driver;
		js.executeScript("window.scrollBy(0," + pixels + ")");
	}

	// Método para validar se o texto presente em um elemento da página é igual ao
	// texto esperado, tratando espaços invisíveis e formatos de número
	public void validarTextoValor(By elemento, String textoEsperado) {

		// Captura o texto visível no elemento e remove espaços no início e no fim
		String textoAtual = driver.findElement(elemento).getText().trim();

		// Aplica normalizações no texto capturado da tela
		textoAtual = textoAtual.replace('\u00A0', ' ') // Substitui espaço não separável (&nbsp;) por espaço comum
				.replaceAll("\\s+", "") // Remove todos os espaços (incluindo tabulações e múltiplos espaços)
				.replace(".", ","); // Converte ponto decimal para vírgula, alinhando com o formato monetário
									// brasileiro

		// Aplica as mesmas normalizações no texto esperado
		textoEsperado = textoEsperado.replace('\u00A0', ' ') // Substitui espaço não separável
				.replaceAll("\\s+", ""); // Remove todos os espaços

		// Exibe os textos que estão sendo comparados no console para facilitar
		// depuração
		System.out.println("Comparando: '" + textoAtual + "' com '" + textoEsperado + "'");

		// Realiza a comparação e falha o teste se os textos forem diferentes
		assertEquals(textoEsperado, textoAtual);
	}

}
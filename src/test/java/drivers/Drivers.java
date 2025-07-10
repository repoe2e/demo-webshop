package drivers;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;

public class Drivers {

	public static WebDriver createDriver() {
		ChromeOptions options = new ChromeOptions();
		// options.addArguments("--headless"); // opcional
		WebDriver driver = new ChromeDriver(options);
		driver.manage().window().maximize();
		driver.get("https://demowebshop.tricentis.com/");
		return driver;
	}
}

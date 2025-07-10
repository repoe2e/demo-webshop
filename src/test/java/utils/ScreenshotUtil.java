package utils;



import java.io.File;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.text.SimpleDateFormat;
import java.util.Date;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import drivers.Drivers;

public class ScreenshotUtil {
	
	
	public static void screenShot(WebDriver driver,String featureName, String testCaseName) {

		// gerar timestamp
		String timestamp = new SimpleDateFormat("yyyyMMdd_"
				+ "HHmmss").format(new Date());
		String fileName = testCaseName + "_" + timestamp + ".png";
		String dirPath = "target/evidencias/" + featureName;

		try {
			Path directory = Paths.get(dirPath);
			if (!Files.exists(directory)) {
				Files.createDirectories(directory);
			}

			// Tirar foto
			File screenshot = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);
			Path destinationPath = Paths.get(dirPath, fileName);
			Files.copy(screenshot.toPath(), destinationPath);
			System.out.println("Screenshot salva em: " + destinationPath.toString());
		} catch (Exception e) {
			System.err.println("Erro ao salvar screenshot: " + e.getMessage());
		}
	}

}

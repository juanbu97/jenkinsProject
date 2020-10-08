package testCases;


import java.time.Duration;

import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.ui.FluentWait;
import org.openqa.selenium.support.ui.Wait;

import io.github.bonigarcia.wdm.WebDriverManager;

public class Test {

	/*
	 * Adding some commets for jenkins 
	 * 
	 * 
	 */
	static final String BROWSER = "chrome"; // Constante navegador
	public static WebDriver driver; // Objeto WebDriver
	public static int resultado;

	/**
	 * Main method
	 * 
	 * @param args
	 */
	public static void main(String[] args) {

		if (BROWSER == "firefox") {
			WebDriverManager.firefoxdriver().setup();
			driver = new FirefoxDriver();
		} else if (BROWSER == "chrome") {
			WebDriverManager.chromedriver().setup();
			driver = new ChromeDriver();
		} else if (BROWSER == "edge") {
			WebDriverManager.edgedriver().setup();
			driver = new EdgeDriver();
		}

		Wait<WebDriver> wait = new FluentWait<WebDriver>(driver).withTimeout(Duration.ofSeconds(10))
				.pollingEvery(Duration.ofSeconds(2)).ignoring(NoSuchElementException.class);

		driver.get("https://timesofindia.indiatimes.com/poll.cms");
		driver.findElement(By.xpath("//*[@id='pollform']/table/tbody/tr[2]/td/input[1]")).click();
		String cadena = driver.findElement(By.id("mathq2")).getText();

		String[] cadenaArray = cadena.split(" ");
		int valor1 = Integer.parseInt(cadenaArray[0]);
		int valor2 = Integer.parseInt(cadenaArray[2]);

		if (cadenaArray[1].equals("+")) {
			resultado = valor1 + valor2;
		} else if (cadenaArray[1].equals("x")) {
			resultado = valor1 * valor2;
		} else {
			resultado = valor1 - valor2;
		}
		
		driver.findElement(By.id("mathuserans2")).sendKeys(Integer.toString(resultado));
		driver.findElement(By.xpath("//*[@id='pollform']/table/tbody/tr[4]/td/div")).click();
		
	}// end main method
}

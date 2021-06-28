package base;

import java.io.File;
import java.io.IOException;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

import com.google.common.io.Files;

import pages.HomePage;

public class BaseTests {

	private static WebDriver driver; // variavel driver
	protected HomePage homePage; // homePage é objeto da classe HomePage

	// Instanciar o "driver"
	@BeforeAll	//anotação
    public static void inicializar() { //Metodo que executa antes de cada classes de testes
       System.setProperty("webdriver.chrome.driver", "C:\\Webdrivers\\chromedriver\\89\\chromedriver.exe"); ////configurar a propriedade de sistema; fazer referencia no meu webdriver
       driver = new ChromeDriver();//criar objeto driver; estou instaciando o objeto para inicializar a variavel driver
	}
	
	@BeforeEach
	public void carregarPaginaInicial() {
		driver.get("https://marcelodebittencourt.com/demoprestashop/"); //carregar página inicial
		homePage = new HomePage(driver); //inicializar homepage
	}
	
	public void capturarTela(String nomeTeste, String resultado) {
		var camera = (TakesScreenshot) driver;
		File capturaDeTela = camera.getScreenshotAs(OutputType.FILE); //salva num local temporario
		
		//defini local que vai salvar, criar na raiz da pasta do projeto uma pasta nome "resources", dentro dessa uma "screenshoots"
		try {  //try  catch excecao de entrada e saida(teste envolvendo arquivo)
			Files.move(capturaDeTela, new File("resources/screenshots/" + nomeTeste + "_" + resultado + ".png")); //o nome do arquivo ficara => "" 
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} 
	}

	
	@AfterAll
	public static void finalizar() {
		driver.quit();
	}
	

}

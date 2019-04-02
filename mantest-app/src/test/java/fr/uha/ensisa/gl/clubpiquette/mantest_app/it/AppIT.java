package fr.uha.ensisa.gl.clubpiquette.mantest_app.it;

import static org.junit.Assert.*;

import java.io.File;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;

import org.junit.*;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;

public class AppIT{

	public static WebDriver driver;
	public static String port, name;


	@BeforeClass
	public static void setupWebDriver() {
		try {
			String mvnit = System.getProperty("phantomjs.binary.path");
			if (mvnit != null && mvnit.length() > 0) {
				driver =
						Class.forName("org.openqa.selenium.phantomjs.PhantomJSDriver")
						.asSubclass(WebDriver.class).newInstance();
			}
		} catch (Exception x) {}
		if (driver == null) {
			// Looking for marionette in PATH
			String ext = System.getProperty("os.name", "")
					.toLowerCase().startsWith("win") ? ".exe" : "";
			String geckodrivername = "geckodriver" + ext;
			Collection<String> pathes = new ArrayList<>();
			for (String source : new String [] {
					System.getProperty("PATH") /* posix */,
					System.getenv().get("Path") /* win < 10 */,
					System.getenv().get("PATH") /* win >= 10 */,
			"/usr/local/bin"}) {
				if (source != null) {
					pathes.addAll(Arrays.asList(source.trim().split(File.pathSeparator)));
				} }
			File geckoDriver = null;
			for (String path : pathes) {
				File f = new File(path, geckodrivername);
				if (f.exists() && f.canExecute()) {
					System.setProperty(
							"webdriver.gecko.driver",
							f.getAbsolutePath());
					geckoDriver = f;
					break; }
			}
			if (geckoDriver == null)
				throw new IllegalStateException(
						"Cannot find geckodriver on the PATH");
			driver = new FirefoxDriver();
		}
		port = System.getProperty("servlet.port", "8080");
		name = System.getProperty("name", "mantest-app");
	}

	@AfterClass
	public static void shutdownWebDriver() {
		if (driver != null) driver.quit();
	}
	@Test
	public void testCreateTestBook() throws Exception {
		driver.get("http://localhost:" + port + '/' + name);
		driver.findElement(By.id("inputNew")).clear();
	    driver.findElement(By.id("inputNew")).sendKeys("test book 1");
	    driver.findElement(By.xpath("(.//*[normalize-space(text()) and normalize-space(.)='Tests Books'])[1]/following::img[1]")).click();
	    assertEquals("test book 1", driver.findElement(By.xpath("(.//*[normalize-space(text()) and normalize-space(.)='Tests Books'])[1]/following::h2[1]")).getText());
	}

}
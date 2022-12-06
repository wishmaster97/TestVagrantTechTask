package pageObject;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import java.time.Duration;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Arrays;
import java.util.List;
import java.util.Locale;

import org.openqa.selenium.By;

public class pageIMDB {
	
	WebDriver driver;
	
	String extractedDate = null;
	String extractedCountry = null;
	
	By imdbSearch = By.id("suggestion-search");
	
	By imdbAutoSuggestTab = By.xpath("//ul[@class='react-autosuggest__suggestions-list anim-enter-done']/li");
	
	By imdbFilmDetails = By.xpath("//div[contains(@data-testid,'title-details-section')]//ul/li");
	
	
	public pageIMDB(WebDriver driver) {
		
		this.driver = driver;
	}
	
	
	public void launchIMDB(String URL) {
		
		driver.get(URL);
	}
	
	public void enterIMDBSearch(String searchKey) throws InterruptedException {
		
		driver.findElement(imdbSearch).sendKeys(searchKey);
		Thread.sleep(3000);
	}
	
	public void selectFromAutoSuggestTab(String searchKey) throws InterruptedException {
		
		List<WebElement> searchEl = driver.findElements(imdbAutoSuggestTab);
		
		for(WebElement s: searchEl) {
			
			if(s.getText().contains(searchKey)) {
				s.click();
				break;
			}
			
			
		}
		Thread.sleep(3000);
	}
	
	
	public List<String> extractIMDBFilmDetails() {
		
		
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
		wait.until(ExpectedConditions.visibilityOfAllElements(driver.findElements(imdbFilmDetails)));
		
		List<WebElement> liElements = driver.findElements(imdbFilmDetails);
		
		for(WebElement li:liElements) {

			if(li.getText().contains("Release date")){
					
				//System.out.println(li.getText());
				extractedDate  = li.getText().split("Release date")[1].trim().split("\\(")[0].trim();
					
			}
			
			if(li.getText().contains("Country")){
				
				//System.out.println(li.getText());
				extractedCountry = li.getText().split("Country of origin")[1].trim();
				
			}		
			
		}
		
		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("MMMM d, yyyy", Locale.ENGLISH);
		LocalDate date = LocalDate.parse(extractedDate, formatter);
		System.out.println(date.toString());
		
		
		
		
		return Arrays.asList(date.toString(),extractedCountry);
		
	}
	
	


	public void closeIMDBSession() {
		
		driver.close();
	}
	
	
}

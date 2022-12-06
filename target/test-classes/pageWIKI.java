package pageObject;

import java.time.Duration;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Arrays;
import java.util.List;
import java.util.Locale;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class pageWIKI {
	

	WebDriver driver;
	
	String extractedDate = null;
	String extractedCountry = null;
	
	By wikiSearch = By.id("searchInput");
	
	By wikiAutoSuggestTab = By.xpath("//div[contains(@class,'suggestions-result')][1]");
	
	By wikiFilmDetails = By.xpath("//table[@class='infobox vevent']/tbody/tr");
	
	
public pageWIKI(WebDriver driver) {
		
		this.driver = driver;
	}
	
	
	public void launchWIKI(String URL) {
		
		driver.get(URL);
	}
	
	public void enterWIKISearch(String searchKey) throws InterruptedException {
		
		driver.findElement(wikiSearch).sendKeys(searchKey);
		Thread.sleep(3000);
	}
	
	public void selectFromAutoSuggestTab(String searchKey) throws InterruptedException {
		
		driver.findElement(wikiAutoSuggestTab).click();
		
		/*List<WebElement> searchEl = driver.findElements(wikiAutoSuggestTab);
		
		for(WebElement s: searchEl) {
			
			if(s.getText().contains(searchKey)) {
				s.click();
				break;
			}
			
			
		}*/
		Thread.sleep(3000);
	}
	
	
	public List<String> extractWIKIFilmDetails() {
		
		
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
		wait.until(ExpectedConditions.visibilityOfAllElements(driver.findElements(wikiFilmDetails)));
		
		List<WebElement> liElements = driver.findElements(wikiFilmDetails);
		
		for(WebElement li:liElements) {

			if(li.getText().contains("Release date")){
				
				System.out.println(li.getText());
				extractedDate = li.getText().split("Release date")[1].trim();
				
			}
			
			if(li.getText().contains("Country")){
				
				System.out.println(li.getText());
				extractedCountry = li.getText().split("Country")[1].trim();
				
			}
	
			
		}
		
		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("d MMMM yyyy", Locale.ENGLISH);
		LocalDate date = LocalDate.parse(extractedDate, formatter);
		//System.out.println(date.toString());
		
		
		
		
		return Arrays.asList(date.toString(),extractedCountry);
		
	}
	
	


	public void closeWIKISession() {
		
		driver.close();
	}
	

}

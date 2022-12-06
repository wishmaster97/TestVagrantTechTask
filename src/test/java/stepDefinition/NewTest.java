package stepDefinition;

import org.testng.annotations.Test;
import java.util.List;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.annotations.Test;

import pageObject.pageIMDB;
import pageObject.pageWIKI;

public class NewTest {
  
@Test	
public void comparisonCheck() throws InterruptedException {
	
	System.setProperty("webdriver.chrome.driver", "/Users/wishmaster97/Downloads/chromedriver");
	 
	 //String searchKey = "Pushpa: The Rise";
	 //String searchKey = "Brahmastra Part One";
	 //String searchKey = "Dil Chahta Hai";
	
	
	//Please add list of movies to check  
	 String []listMovies = {"Pushpa: The Rise", "Brahmastra Part One", "Dil Chahta Hai"};
	 
	//getIMDBData();
	//getWIKIData();
	 
	 for(String data:listMovies) {
		 
		 Assert.assertEquals(getIMDBData(data), getWIKIData(data));
		  
	 }
	 
	 //Assert.assertEquals(getIMDBData(searchKey), getWIKIData(searchKey));
}

	
  public List<String> getIMDBData(String searchKey) throws InterruptedException {
	  
	  String imdbURL = "https://www.imdb.com/";
	 
	  WebDriver driver = new ChromeDriver();  	  
	  driver.manage().window().maximize();
	    
	  pageIMDB imdb = new pageIMDB(driver);
	  
	  imdb.launchIMDB(imdbURL);
	  imdb.enterIMDBSearch(searchKey);
	  imdb.selectFromAutoSuggestTab(searchKey);
	  List<String> imdbDetails = imdb.extractIMDBFilmDetails();
	  
	  System.out.println("IMDB Detials ->" + imdbDetails);
	  imdb.closeIMDBSession();
	  
	  return imdbDetails;
		
	  
  }
  
  public List<String> getWIKIData(String searchKey) throws InterruptedException{
	  
	  String wikiURL = "https://en.wikipedia.org/";	  
	  
	  WebDriver driver = new ChromeDriver();  	  
	  driver.manage().window().maximize();
	  
	  pageWIKI wiki = new pageWIKI(driver);
	  
	  wiki.launchWIKI(wikiURL);
	  wiki.enterWIKISearch(searchKey);
	  wiki.selectFromAutoSuggestTab(searchKey);
	  List<String> wikiDetails = wiki.extractWIKIFilmDetails();
	  
	  System.out.println("WIKI Detials ->" + wikiDetails);
	  wiki.closeWIKISession();
	  
	  return wikiDetails;
		
	  
  }
  
  
}

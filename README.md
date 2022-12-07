# TestVagrantTechTask
Test to extract data(Release date &amp; Country)  from IMDB and WIKI and compare, validate if extracted data matches. 

#Runner Class Path -> src/test/java/stepDefinition/NewTest
or
Run with TestNG -> testng.xml (run with TestNG)

#Path of PageObject Clasess -> 
1. src/main/java/pageObject/pageIMDB
2. src/main/java/pageObject/pageWIKI

#TestClass Implementation ->
1. used Assert.assertEquals(collection<list>,collection<List>), to compare the extracted data (Release Date and Country).
2. Return type of both page is List<String>
3. Release Date Format => [YYY-MM-DD]
4. Country Format => <Country> ex. India
  
  #Comparison Type example below:
  
  IMDB Detials ->[2001-08-10, India]
  WIKI Details ->[2001-08-10, India]
  
#Date Formator used for IMDB Class ->
  DateTimeFormatter formatter = DateTimeFormatter.ofPattern("MMMM d, yyyy", Locale.ENGLISH);
	LocalDate date = LocalDate.parse(extractedDate, formatter);
  
#Date Formated used for WIKI Class ->
  DateTimeFormatter formatter = DateTimeFormatter.ofPattern("d MMMM yyyy", Locale.ENGLISH);
	LocalDate date = LocalDate.parse(extractedDate, formatter);
  
  
#Final Validation Snippet ->  

String []listMovies = {"Pushpa: The Rise", "Brahmastra Part One", "Dil Chahta Hai"};
	 
	//getIMDBData();
	//getWIKIData();
	 
	 for(String data:listMovies) {
		 
		 Assert.assertEquals(getIMDBData(data), getWIKIData(data));
		  
	 }

IMG of result -->

<img width="1440" alt="Screenshot 2022-12-06 at 7 27 52 PM" src="https://user-images.githubusercontent.com/36235341/205931554-b7734ee4-3642-4bb7-b88b-b4759a93b84c.png">

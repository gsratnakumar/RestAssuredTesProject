package com.utils;
import org.testng.annotations.DataProvider;;
public class DataProviderClass {
	
	@DataProvider(name="addbook")
	public Object[][] getAddBookPayLoad() {
		
		return new Object[][] {
			{"testone","201"},
			{"testtwo","202"},
			{"testthree","203"}
			
		};
	}

}

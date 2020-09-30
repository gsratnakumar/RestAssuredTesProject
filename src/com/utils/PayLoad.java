package com.utils;

import java.io.IOException;
import java.nio.file.Paths;

public class PayLoad {
	
	public static String getAddPlaceData() {
		return "{\r\n" + 
				"  \"location\": {\r\n" + 
				"    \"lat\": -38.383495,\r\n" + 
				"    \"lng\": 33.427363\r\n" + 
				"  },\r\n" + 
				"  \"accuracy\": 44,\r\n" + 
				"  \"name\": \"My Place Four\",\r\n" + 
				"  \"phone_number\": \"(+91) 983 893 3934\",\r\n" + 
				"  \"address\": \"29, side layout, cohen 224\",\r\n" + 
				"  \"types\": [\r\n" + 
				"    \"shoe park\",\r\n" + 
				"    \"shop\"\r\n" + 
				"  ],\r\n" + 
				"  \"website\": \"http://google.com\",\r\n" + 
				"  \"language\": \"French-IN\"\r\n" + 
				"}\r\n" + 
				"";
	}
	
	public static String getCourceData() {
		return "{\r\n" + 
				"\r\n" + 
				"\"dashboard\": {\r\n" + 
				"\r\n" + 
				"\"purchaseAmount\": 910,\r\n" + 
				"\r\n" + 
				"\"website\": \"rahulshettyacademy.com\"\r\n" + 
				"\r\n" + 
				"},\r\n" + 
				"\r\n" + 
				"\"courses\": [\r\n" + 
				"\r\n" + 
				"{\r\n" + 
				"\r\n" + 
				"\"title\": \"Selenium Python\",\r\n" + 
				"\r\n" + 
				"\"price\": 50,\r\n" + 
				"\r\n" + 
				"\"copies\": 6\r\n" + 
				"\r\n" + 
				"},\r\n" + 
				"\r\n" + 
				"{\r\n" + 
				"\r\n" + 
				"\"title\": \"Cypress\",\r\n" + 
				"\r\n" + 
				"\"price\": 40,\r\n" + 
				"\r\n" + 
				"\"copies\": 4\r\n" + 
				"\r\n" + 
				"},\r\n" + 
				"\r\n" + 
				"{\r\n" + 
				"\r\n" + 
				"\"title\": \"RPA\",\r\n" + 
				"\r\n" + 
				"\"price\": 45,\r\n" + 
				"\r\n" + 
				"\"copies\": 10\r\n" + 
				"\r\n" + 
				"}\r\n" + 
	         	"\r\n" + 
				"]\r\n" + 
				"\r\n" + 
				"}\r\n" + 
				"\r\n" + 
				"";
	}
	
	public static String getAddBookPayLoad(String isbn,String aisle) {
		return "{\r\n" + 
				"\r\n" + 
				"\"name\":\"Learn Appium Automation with Java\",\r\n" + 
				"\"isbn\":\""+isbn+"\",\r\n" + 
				"\"aisle\":\""+aisle+"\",\r\n" + 
				"\"author\":\"John foe\"\r\n" + 
				"}";
	}
	
	public static String getStringFromJsonFile(String path) {
		String payload = null;
		try {
			payload =  new String(java.nio.file.Files.readAllBytes(Paths.get(path)));
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return payload;
	}
	
	public static String getCutAutBody() {
		return "{\r\n" + 
				"	\"userName\": \"admin@sureify.com\",\r\n" + 
				"	\"password\": \"$ure!fY@345#\"\r\n" + 
				"}";
	}
	
	public static String getCutApiBody() {
		return "{\r\n" + 
				"  \"user\": {\r\n" + 
				"    \"first_name\": \"Harish\",\r\n" + 
				"    \"last_name\": \"Yanapu\",\r\n" + 
				"    \"dob\": \"2002-08-10\",\r\n" + 
				"    \"email\": \"harish@sureify.com\",\r\n" + 
				"    \"phone_number\": \"1234567890\",\r\n" + 
				"    \"isd_code\": \"+1\",\r\n" + 
				"    \"gender\": \"Male\",\r\n" + 
				"    \"height\": {\r\n" + 
				"      \"feet\": 5,\r\n" + 
				"      \"inches\": 2\r\n" + 
				"    },\r\n" + 
				"    \"nicotine_use\": \"NeverInpast12Months\",\r\n" + 
				"    \"is_nicotine_user\": false,\r\n" + 
				"    \"replace_existing_insurance\": false,\r\n" + 
				"    \"state\": \"AL\",\r\n" + 
				"    \"weight\": \"160\",\r\n" + 
				"    \"zipcode\": \"12345\",\r\n" + 
				"    \"city\": \"San Jose\"\r\n" + 
				"  },\r\n" + 
				"  \"quote\": {\r\n" + 
				"    \"product_name\": \"Express Term\",\r\n" + 
				"    \"company\": 110,\r\n" + 
				"    \"coverage_amount\": 250000,\r\n" + 
				"    \"premium_frequency\": \"Monthly\",\r\n" + 
				"    \"plan\": \"20 Years\",\r\n" + 
				"    \"premium_amount\": \"55.11\",\r\n" + 
				"    \"agent_id\": \"106901\"\r\n" + 
				"  },\r\n" + 
				"  \"tracking\": {\r\n" + 
				"    \"lead_source\": \"\",\r\n" + 
				"    \"campaign_code\": \"\"\r\n" + 
				"  },\r\n" + 
				"  \"client_payload\": {\r\n" + 
				"    \"address\": \"1100 Lincoln Ave, Suite 240, San Jose, United States.\"\r\n" + 
				"  }\r\n" + 
				"}";
	}

}

package com.demos;

//package general;

import java.util.Base64;
import java.util.HashMap;

//import Sureify.AutomationTemplate.Suite;
//import dataProviders.ExcelFileReader;
import io.restassured.RestAssured;
import io.restassured.filter.session.SessionFilter;
import io.restassured.parsing.Parser;
import io.restassured.path.json.JsonPath;

/**
 * @author madhubabu
 * @date 28-Aug-2020
 * @desc It will return the Prod URL
 */

public class GetURLProd 
{
	static String ACCESS_TOKEN = null;
	private static  SessionFilter SESSIONID = null;
	
	public static HashMap<String, String> dataHash;
	
	public static String getURL()
	{
		try {
	    	dataHash = new HashMap<String, String>();
	    	//dataHash = ExcelFileReader.readExcel(Suite.properties.getProperty("TestData"), "TelephonicInterviewTestData");
		    
	    }
	    catch(Exception e)
	    {
	    	System.out.println(e.getMessage());
	    }
		
		RestAssured.baseURI="https://quilityapi.sureify.com";
		
		String authCookie = ("admin" + ":" + "R8xHNjzh*NSw");
		String authCookieEncoded = new String(Base64.getEncoder().encode(authCookie.getBytes()));
		
		String response1 =  RestAssured.given().relaxedHTTPSValidation().auth().preemptive().basic("admin", "R8xHNjzh*NSw").
//		String response1 =  RestAssured.given()..header("Authorization", "Basic Auth" + authCookieEncoded).
		                     header("Authorization", "Basic YWRtaW46NyVkUkdyZVQ=").
		                     header("organization_id","99088807sdsdh87884d992").
		                     header("organization_access_token","0A6B0FC9EHDJ717DWOOW8AF923B79S4").
		body(getCutAutBody()).
	    when().post("/v1/auth").
	    then().extract().asString();
		
		System.out.println(response1);
		JsonPath js1 = getRawToJson(response1);
		ACCESS_TOKEN = js1.getString("access_token");
		
		System.out.println(js1.getString("access_token"));
		
		String response2 = RestAssured.given().urlEncodingEnabled(false).
				 header("content-type","application/json").
				 header("organization_id","7sdsdh87884d9929908880").
	             header("organization_access_token","HDJ717DWOOW8AF923B79S40A6B0FC9E").
	             header("Authorization", "Bearer "+ACCESS_TOKEN).
				 body(getCutApiBody()).
		expect().
		defaultParser(Parser.JSON).
		when().post("/v1/quote/cutoverapi").then().extract().asString();
		JsonPath js2 = getRawToJson(response2);
		String url = js2.get("response.pathname");
	    System.out.println(url);
	    
	    return url;
	}
	
	public static String getURLs(int index)
	{
		try {
	    	dataHash = new HashMap<String, String>();
	    	//dataHash = ExcelFileReader.readExcel(Suite.properties.getProperty("TestData"), "TestDataTC103", index);
		    
	    }
	    catch(Exception e)
	    {
	    	System.out.println(e.getMessage());
	    }
		
	RestAssured.baseURI="https://quilityapi.sureify.com";

		
		String response1 =  RestAssured.given().urlEncodingEnabled(false).
		                     header("Authorization", "Basic YWRtaW46NyVkUkdyZVQ=").
		                     header("organization_id","7sdsdh87884d9929908880").
		                     header("organization_access_token","HDJ717DWOOW8AF923B79S40A6B0FC9E").
		body(getCutAutBody()).
	    when().post("/v1/auth").
	    then().extract().asString();
		JsonPath js1 = getRawToJson(response1);
		ACCESS_TOKEN = js1.getString("access_token");
		
		String response2 = RestAssured.given().urlEncodingEnabled(false).
				 header("content-type","application/json").
				 header("organization_id","99088807sdsdh87884d992").
	             header("organization_access_token","0A6B0FC9EHDJ717DWOOW8AF923B79S4").
	             header("Authorization", "Bearer "+ACCESS_TOKEN).
				 body(getCutApiBody()).
		expect().
		defaultParser(Parser.JSON).
		when().post("/v1/quote/cutoverapi").then().extract().asString();
		JsonPath js2 = getRawToJson(response2);
		String url = js2.get("response.pathname");
	    System.out.println(url);
	    
	  //  Suite.cutURL = true;
	    
	    return url;
	    
	}
	
	private static String getCutAutBody() 
	{
		return "{\r\n" + "	\"userName\": \"admin@sureify.com\",\r\n" + 
				"	\"password\": \"$ure!fY@345#\"\r\n" + 
				"}";
	}
	
	private static String getCutApiBody() 
	{
		System.out.println("URL for the Name & State : " +  dataHash.get("FirstName") + "  -  " + dataHash.get("State"));
		
		return "{\r\n" + 
		"  \"user\": {\r\n" + 
		"    \"first_name\": \""+ dataHash.get("FirstName") +"\",\r\n" + 
		"    \"last_name\": \""+ dataHash.get("LastName") + "\",\r\n" + 
		"    \"dob\": \""+ dataHash.get("DOB") + "\",\r\n" + 
		"    \"email\": \""+ dataHash.get("Email") + "\",\r\n" + 
		"    \"phone_number\": \"" + dataHash.get("Phone") + "\",\r\n" + 
		"    \"isd_code\": \"" + dataHash.get("ISDCode") +"\",\r\n" + 
		"    \"gender\": \"" + dataHash.get("Gender") +"\",\r\n" + 
		"    \"height\": {\r\n" + 
		"      \"feet\": "+ dataHash.get("HeightFt") +",\r\n" + 
		"      \"inches\": "+ dataHash.get("HeightInch") +"\r\n" + 
		"    },\r\n" + 
		"    \"nicotine_use\": \""+ dataHash.get("NicotineUse") + "\",\r\n" + 
		"    \"is_nicotine_user\": "+ dataHash.get("IsNicotineUser") +",\r\n" + 
		"    \"replace_existing_insurance\": " + dataHash.get("ExisitingInsurance") +",\r\n" + 
		"    \"state\": \""+ dataHash.get("State") + "\",\r\n" + 
		"    \"weight\": \""+ dataHash.get("Weight") +"\",\r\n" + 
		"    \"zipcode\": \"" + dataHash.get("Zipcode") + "\",\r\n" + 
		"    \"city\": \""+ dataHash.get("City") +"\"\r\n" + 
		"  },\r\n" + 
		"  \"quote\": {\r\n" + 
		"    \"product_name\": \""+ dataHash.get("ProductName") +"\",\r\n" + 
		"    \"company\": "+ dataHash.get("Company") +",\r\n" + 
		"    \"coverage_amount\": "+ dataHash.get("CoverageAmount") + ",\r\n" + 
		"    \"premium_frequency\": \""+ dataHash.get("PremiumFreq") +"\",\r\n" + 
		"    \"plan\": \""+ dataHash.get("PlanTenure") +"\",\r\n" + 
		"    \"premium_amount\": \""+ dataHash.get("PremiumAmount") +"\",\r\n" + 
		"    \"agent_id\": \""+ dataHash.get("AgentID") + "\"\r\n" + 
		"  },\r\n" + 
		"  \"tracking\": {\r\n" + 
		"    \"lead_source\": \"\",\r\n" + 
		"    \"campaign_code\": \"\"\r\n" + 
		"  },\r\n" + 
		"  \"client_payload\": {\r\n" + 
		"    \"address\": \""+ dataHash.get("Address") +"\"\r\n" + 
		"  }\r\n" + 
		"}";
		
	}	
	
    private static JsonPath getRawToJson(String response) 
    {
    	return new JsonPath(response);
	}
    
}


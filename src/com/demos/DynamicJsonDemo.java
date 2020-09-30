package com.demos;

import org.testng.annotations.Test;

import com.utils.DataProviderClass;
import com.utils.GenericUtils;
import com.utils.PayLoad;

import io.restassured.RestAssured;
import io.restassured.path.json.JsonPath;

import static io.restassured.RestAssured.*;

public class DynamicJsonDemo {
	
	@Test(description = "Add Palod with dynamic from test",enabled = false)
	public void addBookTypeOne() {
		RestAssured.baseURI = "http://216.10.245.166";
		String response = given().header("Content-Type", "application/json").
	    body(PayLoad.getAddBookPayLoad("testone","101")).
		when().post("Library/Addbook.php").
		then().log().all().assertThat().statusCode(200).extract().asString();
		
		JsonPath js = GenericUtils.getRawToJson(response);
		String id = js.get("ID");
		System.out.println("ID : "+id);
		
	}
	
	
	@Test(description = "Add Palod using dataprovider", dataProvider = "addbook",dataProviderClass = DataProviderClass.class,enabled = false)
	public void addBookTypeTwo(String isbn,String aisle) {
		RestAssured.baseURI = "http://216.10.245.166";
		String response = given().header("Content-Type", "application/json").
	    body(PayLoad.getAddBookPayLoad(isbn,aisle)).
		when().post("Library/Addbook.php").
		then().log().all().assertThat().statusCode(200).extract().asString();
		
		JsonPath js = GenericUtils.getRawToJson(response);
		String id = js.get("ID");
		System.out.println("ID : "+id);
		
	}
	
	@Test(description = "Add Palod  from file .Json",enabled = true)
	public void addBookTypeThree() {
		RestAssured.baseURI = "http://216.10.245.166";
		String response = given().header("Content-Type", "application/json").
	    body(PayLoad.getStringFromJsonFile("staticdata.json")).
		when().post("Library/Addbook.php").
		then().log().all().assertThat().statusCode(200).extract().asString();
		
		JsonPath js = GenericUtils.getRawToJson(response);
		String id = js.get("ID");
		System.out.println("ID : "+id);
		
	}

	
	
	
}

package com.demos;

import io.restassured.RestAssured;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import static io.restassured.RestAssured.*;
import static org.hamcrest.Matchers.*;

import java.util.ArrayList;

import com.pojo.AddPlacePojo;
import com.pojo.Location;
import com.utils.PayLoad;


class Place{


	public void addPlaceTypeOne() {
		RestAssured.baseURI = "https://rahulshettyacademy.com";
		given().log().all().
		queryParam("key", "qaclick123").
		header("Content-Type","application/json").
		body(PayLoad.getAddPlaceData()).
		when().post("/maps/api/place/add/json")
		.then().log().all().assertThat().statusCode(200).and().body("scope", equalTo("APP")).and().body("status", equalTo("OK")).
		header("Server", equalTo("Apache/2.4.18 (Ubuntu)")).header("Access-Control-Allow-Methods",equalTo("POST"));


	}

	public String addPlaceTypeTwo() {
		System.out.println("==== PSOST() Add place api =====");
		RestAssured.baseURI = "https://rahulshettyacademy.com";
		String response = given().
	                       queryParam("key", "qaclick123").
				           header("Content-Type","application/json").
				           body(PayLoad.getAddPlaceData()).
				           when().post("/maps/api/place/add/json")
				          .then().assertThat().statusCode(200).extract().asString();
		System.out.println("Response :"+response);
		JsonPath js = new JsonPath(response);
		String new_place_id = js.getString("place_id");
		System.out.println("Added new place ID :"+new_place_id);
		
		return new_place_id;
		
	}
	
	public String addPlaceThree() {
		System.out.println("==== PSOST() Add place api =====");
		AddPlacePojo ap = new AddPlacePojo();
		ap.setAccuracy(100);
		ap.setName("Red rose house");
		ap.setPhone_number("(+91) 983 893 3937");
		ap.setAddress("29, high layout, cohen 09");
		ap.setWebsite("http://google.com");
		ap.setLanguage("Spanish");
		
		ArrayList<String> types = new ArrayList<String>();
		types.add("shoe park");
		types.add("Shop");
		types.add("Restrent");
		ap.setTypes(types);
		
		Location l = new Location();
		l.setLat(-38.383500);
		l.setLng(33.427362);
		ap.setLocation(l);
		
		RestAssured.baseURI = "https://rahulshettyacademy.com";
		Response response = given().
	                       queryParam("key", "qaclick123").
				           header("Content-Type","application/json").
				           body(ap).log().all().
				           when().post("/maps/api/place/add/json")
				          .then().assertThat().statusCode(200).extract().response();
		System.out.println("Response :"+response);
		String res = response.asString();
		System.out.println("Response :"+res);
		JsonPath js = new JsonPath(res);
		String new_place_id = js.getString("place_id");
		System.out.println("Added new place ID :"+new_place_id);
		
		return new_place_id;
	}
	
	public void updatePlace(String place_id,String add) {
		System.out.println("==== PUT() Update place api =====");
		String expected_address = add;
		RestAssured.baseURI = "https://rahulshettyacademy.com";
		String res = given().log().all().
        queryParam("key", "qaclick123").
        header("Content-Type","application/json").
        body("{\r\n" + 
        		"\"place_id\":\""+place_id+"\",\r\n" + 
        		"\"address\":\""+expected_address+"\",\r\n" + 
        		"\"key\":\"qaclick123\"\r\n" + 
        		"}").
        when().put("/maps/api/place/update/json").
        then().assertThat().statusCode(200).extract().asString();
		
		JsonPath js = new JsonPath(res);
		String msg = js.getString("msg");
		System.out.println("Message :"+msg);
	}
	
	public void getPlace(String place_id,String expected_add) {
		System.out.println("==== GET() Update place api =====");
		String res = given().log().all().queryParam("key", "qaclick123").queryParam("place_id", place_id).
	    when().get("maps/api/place/get/json").
		then().log().all().assertThat().statusCode(200).extract().asString();
		JsonPath js = new JsonPath(res);
		String actual_add = js.getString("address");
		if(actual_add.equalsIgnoreCase(expected_add)) {
			System.out.println("Address update with :"+actual_add);
		}else {
			System.out.println("Address not update with :"+expected_add+": Actual:"+actual_add);
		}
		
	}

}

public class PlaceDemo {

	public static void main(String[] args) {
     
		Place place = new Place();
       // String place_id =  place.addPlaceTypeTwo();
       // String expected_add = "Moon walk,Africa";
       // place.updatePlace(place_id,expected_add);
       // place.getPlace(place_id, expected_add);
		place.addPlaceThree();
	}

}

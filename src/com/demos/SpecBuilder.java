package com.demos;

import static io.restassured.RestAssured.given;

import org.testng.annotations.Test;

import com.utils.PayLoad;

import io.restassured.builder.RequestSpecBuilder;
import io.restassured.builder.ResponseSpecBuilder;
import io.restassured.http.ContentType;
import io.restassured.path.json.JsonPath;
import io.restassured.specification.RequestSpecification;
import io.restassured.specification.ResponseSpecification;

public class SpecBuilder {
	
	@Test()
	public void addPlaceWithSpecBuild() {
		System.out.println("==== PSOST() Add place api =====");
		RequestSpecification reqsb = null;
		RequestSpecification req = null;
		ResponseSpecification ressb = null;
		reqsb = new RequestSpecBuilder().
				setBaseUri("https://rahulshettyacademy.com").
				addQueryParam("key", "qaclick123").
				setContentType(ContentType.JSON).build();
		req = given().spec(reqsb);

		ressb = new ResponseSpecBuilder().expectStatusCode(200).expectContentType(ContentType.JSON).build();


		String response = req.body(PayLoad.getAddPlaceData()).
				when().post("/maps/api/place/add/json")
				.then().spec(ressb).extract().asString();

		System.out.println("Response :"+response);
		JsonPath js = new JsonPath(response);
		String new_place_id = js.getString("place_id");
		System.out.println("Added new place ID :"+new_place_id);

		//return new_place_id;

	}
}

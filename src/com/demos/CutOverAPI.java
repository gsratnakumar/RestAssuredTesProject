package com.demos;

import org.testng.annotations.Test;

import com.pojo.GetCourse;
import com.pojo.WebAutomation;
import com.utils.GenericUtils;
import com.utils.PayLoad;

import io.restassured.RestAssured;
import io.restassured.filter.session.SessionFilter;
import io.restassured.parsing.Parser;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;

import static io.restassured.RestAssured.*;

import java.util.Base64;
import java.util.List;

public class CutOverAPI {

	private static String ACCESS_TOKEN = null;
	private static  SessionFilter SESSIONID = null;
	
	//@Test(priority = 0)
	public void getAccessToken() {
		RestAssured.baseURI="https://quilitystg.sureify.com";
		//RestAssured.baseURI="https://quilityapi.sureify.com";
				
		String response =  given().urlEncodingEnabled(false).
		                     header("Authorization", "Basic YWRtaW46NyVkUkdyZVQ=").
		                     header("organization_id","7sdsdh87884d9929908880").
		                     header("organization_access_token","HDJ717DWOOW8AF923B79S40A6B0FC9E").
		body(PayLoad.getCutAutBody()).filter(SESSIONID).
        when().post("/v1/auth").
        then().extract().asString();
		JsonPath js = GenericUtils.getRawToJson(response);
		ACCESS_TOKEN = js.getString("access_token");
		System.out.println("*** Access Token ***");
		System.out.println("ACCESS TOKEN :"+ACCESS_TOKEN);
        
	}
	
	//@Test(priority = 1)
	public void getCuttOverAPI() {
		RestAssured.baseURI = "https://quilitystg.sureify.com";
		String response = given().urlEncodingEnabled(false).
				 header("content-type","application/json").
				 header("organization_id","7sdsdh87884d9929908880").
                 header("organization_access_token","HDJ717DWOOW8AF923B79S40A6B0FC9E").
                 header("Authorization", "Bearer "+ACCESS_TOKEN).
				 body(PayLoad.getCutApiBody()).
		expect().
		defaultParser(Parser.JSON).
		when().post("/v1/quote/cutoverapi").then().extract().asString();
		JsonPath js = GenericUtils.getRawToJson(response);
		String url = js.get("response.pathname");
		System.out.println("response.pathname :"+url);
		
	    System.out.println("**********************************************");
	}
	
	@Test(priority = 3)
	public void getProdAccessToken() {
		RestAssured.baseURI="https://quilityapi.sureify.com";

		String authCookie = ("admin" + ":" + "R8xHNjzh*NSw");
		String authCookieEncoded = new String(Base64.getEncoder().encode(authCookie.getBytes()));

		String response1 =  RestAssured.given().auth().preemptive().basic("admin", "R8xHNjzh*NSw").
				//String response1 =  RestAssured.given().header("Authorization", "Basic " + authCookieEncoded).
				//header("Authorization", "Basic YWRtaW46NyVkUkdyZVQ=").
				header("organization_id","99088807sdsdh87884d992").
				header("organization_access_token","0A6B0FC9EHDJ717DWOOW8AF923B79S4").
				body(getCutAutBody()).
				when().post("/v1/auth").
				then().assertThat().statusCode(200).extract().asString();

		System.out.println(response1);
		JsonPath js1 = GenericUtils.getRawToJson(response1);
		ACCESS_TOKEN = js1.getString("access_token");

		System.out.println(js1.getString("access_token"));
	}
	
	private static String getCutAutBody() 
	{
		return "{\r\n" + "	\"userName\": \"admin@sureify.com\",\r\n" + 
				"	\"password\": \"$ure!fY@345#\"\r\n" + 
				"}";
	}

}

package com.demos;

import org.testng.annotations.Test;

import com.pojo.GetCourse;
import com.pojo.WebAutomation;
import com.utils.GenericUtils;

import io.restassured.RestAssured;
import io.restassured.parsing.Parser;
import io.restassured.path.json.JsonPath;

import static io.restassured.RestAssured.*;

import java.util.List;

public class OAuth2_0Demo {
	
	private static String AUT_CODE = "4%2F3AHXwZS__uUSYSQl5TcohgaShxVxogNudocQsYfXuZKY1a48Y4A0L9lbwZXnj_j70TT0dl7xREghgbILDwDk_u4";
	private static String ACCESS_TOKEN = null;
	
	@Test(priority = 0)
	public void getAccessToken() {
		String response = given().urlEncodingEnabled(false).
		queryParam("code", AUT_CODE).
		queryParam("client_id", "692183103107-p0m7ent2hk7suguv4vq22hjcfhcr43pj.apps.googleusercontent.com").
		queryParam("client_secret", "erZOWM9g3UtwNRj340YYaK_W").
	    queryParam("redirect_uri", "https://rahulshettyacademy.com/getCourse.php").
        queryParam("grant_type", "authorization_code").
        queryParam("state", "black").
        when().post("https://www.googleapis.com/oauth2/v4/token").
        then().extract().asString();
		JsonPath js = GenericUtils.getRawToJson(response);
		ACCESS_TOKEN = js.getString("access_token");
		System.out.println("*** Access Token ***");
		System.out.println("ACCESS TOKEN :"+ACCESS_TOKEN);
        
	}
	
	@Test(priority = 1)
	public void getCourseList() {
		RestAssured.baseURI = "https://rahulshettyacademy.com";
		GetCourse response = given().urlEncodingEnabled(false).header("content-type","application/json").
		queryParam("access_token", ACCESS_TOKEN).expect().defaultParser(Parser.JSON).
		when().get("/getCourse.php").as(GetCourse.class);
		
		System.out.println("*** Course Details ***");
		System.out.println("instructor :"+response.getInstructor());
	    System.out.println("Getting all courses title in WebAutomation"); 
	    List<WebAutomation> wa = response.getCourses().getWebAutomation();
	    for(int i=0;i<wa.size();i++) {
	    	System.out.println(wa.get(i).getCourseTitle());
	    }
	    
	    System.out.println("**********************************************");
	}

}

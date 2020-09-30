package com.utils;

import io.restassured.path.json.JsonPath;

public class GenericUtils {
	
	public static JsonPath getRawToJson(String response) {
		
		return new JsonPath(response);
	}

}

package com.demos;

import com.utils.GenericUtils;
import com.utils.PayLoad;

import io.restassured.path.json.JsonPath;


public class CourcePriceDemo {
	
	static int getPurchaseAmount(JsonPath js) {
		int pa = js.getInt("dashboard.purchaseAmount");
		System.out.println("Purchase Amount is :"+pa);
		return pa;
	}

	static void getNoOFCourceSize(JsonPath js) {
		int coursesize = js.getInt("courses.size()");
		System.out.println("Course size is :"+coursesize);
	}
	
	static void getFirstCourseName(JsonPath js) {
		String course_name = js.getString("courses[0].title");
		System.out.println("First course name :"+course_name);
	}
	
	
	static void getAllCoursesAndPrices(JsonPath js) {
		String name = null;
		int price = 0;
		int coursesize = js.getInt("courses.size()");
		for(int i=0;i<coursesize;i++) {
			name = js.getString("courses["+i+"].title");
			price = js.getInt("courses["+i+"].price");
			
			System.out.println("Course name :"+name+", Price :"+price);
		}
		
	}
	
	static void getNoOfCopiesSoldByCourse(JsonPath js,String coursename) {
	
		String name = null;
		int copies = 0;
		int coursesize = js.getInt("courses.size()");
		for(int i=0;i<coursesize;i++) {
			name = js.getString("courses["+i+"].title");
			if(name.equals(coursename)) {
				copies = js.get("courses["+i+"].copies");
				System.out.println("Course name :"+name+", sold copies :"+copies);
				break;
			}
		}
	}
	
	static void verifyNoOfCoursePriceEqualToPurchase(JsonPath js) {
		int coursesize = js.getInt("courses.size()");
		int total = 0;
		int course_total = 0;
		int copies =  0;
		for(int i=0;i<coursesize;i++) {
			course_total = js.getInt("courses["+i+"].price");
			copies = js.get("courses["+i+"].copies");
		    total = total+(course_total*copies);
		}
		int puramt = getPurchaseAmount(js);
		if(puramt == total) {
			System.out.println("Amount equal Purchase :" +puramt+", Course amount :"+total);
		}else {
			System.out.println("Amount not equal Purchase :" +puramt+", Course amount :"+total);
		}
		
	}
	
	public static void main(String[] args) {
		
       JsonPath js = GenericUtils.getRawToJson(PayLoad.getCourceData());
       
       getPurchaseAmount(js);
       
       getNoOFCourceSize(js);
       
       getFirstCourseName(js);
       
       getAllCoursesAndPrices(js);
       
    // print no of copies sold by RPA
       getNoOfCopiesSoldByCourse(js,"Cypress");
       
    // Check sum of all courses price is equal to punches price
       verifyNoOfCoursePriceEqualToPurchase(js);
       
	}

}

package com.demos;

import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import com.utils.GenericUtils;

import io.restassured.RestAssured;
import static io.restassured.RestAssured.*;

import java.io.File;

import io.restassured.filter.session.SessionFilter;
import io.restassured.path.json.JsonPath;

public class JiraAPIDemo {

	private static  SessionFilter SESSIONID = null;
	private static String issue_id = null;
	private static String issue_key = "MYS-8";	
	private static String comment_id = null;
	private static String comment = null;

    @BeforeTest
    public void login() {
    	System.out.println("***********Login into JIRA***************");
    	RestAssured.baseURI = "http://localhost:8080";
    	SESSIONID = new SessionFilter();
    	String response = given().header("content-type","application/json").
    	body("{ \"username\": \"sureshtest05\", \r\n" + 
    			"  \"password\": \"Vendus@123\" \r\n" + 
    			"}").filter(SESSIONID).
    	when().post("/rest/auth/1/session").
    	then().assertThat().statusCode(200).extract().asString();
    	JsonPath js = GenericUtils.getRawToJson(response);
    	System.out.println("Session name :"+js.get("session.name"));
    	System.out.println("Session ID :"+js.get("session.value"));
    }
    
    @Test(priority = 0,enabled = false)
    public void createIssue() {
    	System.out.println("***********Create a Issue****************");
    	RestAssured.baseURI = "http://localhost:8080";
    	String response = given().header("content-type","application/json").body("{\r\n" + 
    			"\"fields\": {\r\n" + 
    			"        \"project\": {\r\n" + 
    			"            \"id\": \"10001\"\r\n" + 
    			"        },\r\n" + 
    			"        \"summary\": \"8th bug\",\r\n" + 
    			"        \"issuetype\": {\r\n" + 
    			"            \"name\": \"Bug\"\r\n" + 
    			"        },\r\n" + 
    			"        \"assignee\": {\r\n" + 
    			"            \"name\": \"sureshtest05\"\r\n" + 
    			"        },\r\n" + 
    			"        \"reporter\": {\r\n" + 
    			"            \"name\": \"sureshtest05\"\r\n" + 
    			"        },\r\n" + 
    			"        \"priority\": {\r\n" + 
    			"            \"name\": \"Medium\"\r\n" + 
    			"        },\r\n" + 
    			"        \"description\": \"8th bug in sample work projct\",\r\n" + 
    			"        \"environment\": \"Staging\"\r\n" + 
    			"        \r\n" + 
    			"}\r\n" + 
    			" \r\n" + 
    			" } ").filter(SESSIONID).
    	when().post("/rest/api/2/issue").
    	then().assertThat().statusCode(201).extract().asString();
    	JsonPath js = GenericUtils.getRawToJson(response);
    	issue_id = js.get("id");
    	issue_key = js.get("key");
    	System.out.println("Issue Key :"+issue_key);
    	System.out.println("Issue ID :"+issue_id);
    	
    }
    
    @Test(priority = 1,enabled = true)
    public void addCommentToIssue() {
    	System.out.println("***********Add a comment to Issue****************");
    	RestAssured.baseURI = "http://localhost:8080";
    	String response = given().header("content-type","application/json").pathParam("key", issue_key).
    	body("{\r\n" + 
    			"    \"body\": \"This is 6th comment in issue 7th\"\r\n" + 
    			"   \r\n" + 
    			"}").filters(SESSIONID).
    	when().post("/rest/api/2/issue/{key}/comment").
    	then().assertThat().log().all().statusCode(201).extract().asString();
    	
    	JsonPath js = GenericUtils.getRawToJson(response);
    	comment_id = js.get("id");
    	String email = js.get("author.emailAddress");
    	comment = js.get("body");
    	System.out.println("Issue Commant ID :"+comment_id);
    	System.out.println("Issue Comment :"+comment);
    	System.out.println("Issue user emial :"+email);
    	
    }
    
    @Test(priority = 2,enabled = false)
    public void updateCommentToIssue() {
    	System.out.println("***********Update a comment to Issue****************");
    	RestAssured.baseURI = "http://localhost:8080";
    	String response = given().pathParam("key", issue_key).pathParam("commentid", comment_id).header("content-type","application/json").
    	body("{\r\n" + 
    			"    \"body\": \"This is first comment update in issue 8th\"\r\n" + 
    			"   \r\n" + 
    			"}").filters(SESSIONID).
    	when().put("/rest/api/2/issue/{key}/comment/{commentid}").
    	then().log().all().assertThat().statusCode(200).extract().asString();
    	
    	JsonPath js = GenericUtils.getRawToJson(response);
    	comment_id = js.get("id");
    	String comment = js.get("body");
    	String email = js.get("author.emailAddress");
    	System.out.println("Issue Commant ID :"+comment_id);
    	System.out.println("Issue Comment :"+comment);
    	System.out.println("Issue user emial :"+email);
    	
    }
    
    @Test(priority = 3,enabled = false)
    public void addAttachmentToIssue() {
    	System.out.println("***********Add attachment to to Issue****************");
    	RestAssured.baseURI = "http://localhost:8080";
    	given().pathParam("issueIdOrKey", "MYS-8").filter(SESSIONID).header("X-Atlassian-Token","no-check").header("content-type","multipart/form-data").
    	 multiPart("file",new File("test_one.jpg")).
    	when().post("/rest/api/2/issue/{issueIdOrKey}/attachments").
    	then().assertThat().statusCode(200);
    }
    
    @Test(priority = 4,enabled = true)
    public void getIssueDetails() {
    	System.out.println("***********Get Issue Details****************");
    	RestAssured.baseURI = "http://localhost:8080";
    	String response = given().filter(SESSIONID).pathParam("issueIdOrKey", issue_key).queryParam("fields", "comment").
    	when().get("/rest/api/2/issue/{issueIdOrKey}").
    	then().log().all().assertThat().statusCode(200).extract().response().asString();
    	JsonPath js = GenericUtils.getRawToJson(response);
    	int size = js.getInt("fields.comment.comments.size()");
    	for(int i=0;i<size;i++) {
    		String act_cmt_id = js.get("fields.comment.comments["+i+"].id");
    		String act_cmt = js.get("fields.comment.comments["+i+"].body");
    		if(act_cmt_id.equalsIgnoreCase(comment_id)) {
    			System.out.println("Comment ID matched :"+act_cmt_id);
    			if(act_cmt.equalsIgnoreCase(comment)) {
    				System.out.println("Comment Body matched :"+act_cmt);
    			}else {
    				System.out.println("Comment Body Not matched :Expected :"+comment+": Actual :"+act_cmt);
    			}

    		}else {
    			System.out.println("Comment ID not matched :Expected :"+comment_id+": Actual :"+act_cmt_id);
    		}
    	}
    }
}

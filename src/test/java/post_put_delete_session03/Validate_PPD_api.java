package post_put_delete_session03;

import org.json.JSONObject;
import org.testng.annotations.Test;

import io.restassured.http.ContentType;

import static io.restassured.RestAssured.*;
import static org.hamcrest.Matchers.*;

import java.io.File;
import java.util.concurrent.TimeUnit;


public class Validate_PPD_api {
  @Test
  public void Validate_POST_Request_Using_File() {
	  
	  
	  File file = new File("InputResources/CreateUser.json");
	  
	  int id = given()
	  	.baseUri("https://dummy.restapiexample.com/api/v1")
	  	.contentType(ContentType.JSON)
	  	.body(file).
	  when()
	  	.post("/create"). 
	  then()
	  	.log().all()
	  	.statusCode(200)
	  	.statusLine("HTTP/1.1 200 OK")
	  	.time(lessThan(3000l), TimeUnit.MILLISECONDS)
	  	.body("data.name", equalTo("sss"))
	  	.extract().path("data.id");	  
	  
	  System.out.println("The id of newly created user is:" +id);
  }
  
  @Test
  public void Post_Using_Json_Object() {
	  
	  JSONObject json = new JSONObject();
	  json.put("name", "sagar");
	  json.put("salary", "456");
	  json.put("age", "12");
	  
	  int id = given()
			  	.baseUri("https://dummy.restapiexample.com/api/v1")
			  	.contentType(ContentType.JSON)
			  	.body(json.toString()).
			  when()
			  	.post("/create"). 
			  then()
			  	.log().all()
			  	.statusCode(200)
			  	.statusLine("HTTP/1.1 200 OK")
			  	.time(lessThan(3000l), TimeUnit.MILLISECONDS)
			  	.body("data.name", equalTo("sagar"))
			  	.extract().path("data.id");	  
			  
			  System.out.println("The id of newly created user is:" +id);
  }
  
	  @Test
	public void Put_Using_Json_Object() {
		  
		  JSONObject json = new JSONObject();
		  json.put("name", "sagar");
		  json.put("salary", "456");
		  json.put("age", "12");
		  json.put("id", 5);
		  
		  int id = given()
				  	.baseUri("https://dummy.restapiexample.com/api/v1")
				  	.contentType(ContentType.JSON)
				  	.body(json.toString()).
				  when()
				  	.put("/update/5"). 
				  then()
				  	.log().all()
				  	.statusCode(200)
				  	.statusLine("HTTP/1.1 200 OK")
				  	.time(lessThan(3000l), TimeUnit.MILLISECONDS)
				  	.body("data.name", equalTo("sagar"))
				  	.extract().path("data.id");	  
				  
				  System.out.println("The id of newly created user is:" +id);
	  }
	  	
	  @Test
		public void Put_Using_File() {
			 
		  File file = new File("InputResources/UpdateUser.json");
			  
			  int id = given()
					  	.baseUri("https://dummy.restapiexample.com/api/v1")
					  	.contentType(ContentType.JSON)
					  	.body(file).
					  when()
					  	.put("/update/5"). 
					  then()
					  	.log().all()
					  	.statusCode(200)
					  	.statusLine("HTTP/1.1 200 OK")
					  	.time(lessThan(3000l), TimeUnit.MILLISECONDS)
					  	.body("data.name", equalTo("sss"))
					  	.extract().path("data.id");	  
					  
					  System.out.println("The id of newly created user is:" +id);
		  }
	  
  
}

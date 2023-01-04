package get_api_session02;

import org.testng.annotations.Test;

import io.restassured.response.Response;

import static io.restassured.RestAssured.*;
import static org.hamcrest.Matchers.*;
import java.util.concurrent.TimeUnit;

public class GET_Testing {
  @Test
  public void Validate_GET_Request() {
	  
	  /*GET API WITH JSON RESPONSE
	   *URL = https://reqres.in/api/users?page=2
	   */
	  
	  Response res = given()
	  	.baseUri("https://reqres.in/api")
	  	.queryParam("page", "2").
	  when()
	  	.get("/users").
	  then()
	  	.log().all()
	  	.statusCode(200)
	  	.statusLine("HTTP/1.1 200 OK")
	  	.time(lessThan(1600l), TimeUnit.MILLISECONDS)
	  	
	  	/*Validating Response Body Without Using Array Index, In which we print all the values 
	  	*of id, email, first_name, last_name and then validate if mentioned values are present 
	  	*in the Response or not
	  	*/
	  	
	  	.body(
	  			"data.id",hasItem(7),
	  			"data.email",hasItem("michael.lawson@reqres.in"),
	  			"data.first_name",hasItem("Lindsay"),
	  			"data.last_name",hasItem("Fields"),
	  			"page",equalTo(2)
	  		)
	  	
	  	/*OR Validate Item Value Directly Using Index Value*/
	  	
	  	.body(
	  			"data[0].id", equalTo(7),
	  			"data[1].email", equalTo("lindsay.ferguson@reqres.in"),
	  			"data[2].first_name",equalTo("Tobias"),
	  			"data[3].last_name",equalTo("Fields"),
	  			"page",equalTo(2)
	  			)
	  	.extract().response();
	  
	  System.out.println("Response of the API Is:" +res.asString());	  
	  		
  }
  
  /*VALIDATE GET API WITH XML BODY RESPONSE*/
  
  
  @Test
  public void Validate_XML_Response() {
	  
	  String name = given()
	  	.baseUri("http://restapi.adequateshop.com")
	  	.queryParam("page", "1")
	  	.queryParam("mode", "xml").	  	
	  when()
	  	.get("api/Traveler"). 
	  then()
	  	.log().all()
	  	.statusCode(200)
	  	.statusLine("HTTP/1.1 200 OK")
	  	.time(lessThan(200l), TimeUnit.SECONDS)
	  	.body(
	  			"TravelerinformationResponse.travelers.Travelerinformation[0].name", equalTo("Developer"),
	  			"TravelerinformationResponse.travelers.Travelerinformation[0].email", equalTo("Developer12@gmail.com")	  			
	  			)
	  //OR
	  
	  	.body(
	  			"TravelerinformationResponse.travelers.Travelerinformation.name", hasItem("Developer"),
	  			"TravelerinformationResponse.travelers.Travelerinformation.email", hasItem("Developer12@gmail.com")
	  			)
	  	.extract().path("TravelerinformationResponse.travelers.Travelerinformation[2].name");
	  
	  System.out.println("Traveler Name Of Index Value[2] Is:" +name.toString());
	  	
  }
  
  @Test
  public void Extract_Single_Value_Lesson14() {
	  
	  		String Email = given()
			  	.baseUri("https://reqres.in/api")
			  	.queryParam("page", "2").
			when()
			  	.get("/users").
			then()
			  	.log().all()
			  	.statusCode(200)
			  	.statusLine("HTTP/1.1 200 OK")
			  	.time(lessThan(1600l), TimeUnit.MILLISECONDS)			  	
			  	.body(
			  			"data[0].id", equalTo(7),
			  			"data[1].email", equalTo("lindsay.ferguson@reqres.in"),
			  			"data[2].first_name",equalTo("Tobias"),
			  			"data[3].last_name",equalTo("Fields"),
			  			"page",equalTo(2)
			  			)
			  	.extract().path("data[1].first_name"); 	
	  		
	  		System.out.println(Email.toString());
			  	
	  
	  
  }
}

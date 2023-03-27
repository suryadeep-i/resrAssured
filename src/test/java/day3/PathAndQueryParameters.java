package day3;

import org.testng.annotations.Test;
import static io.restassured.RestAssured.given;
import static io.restassured.RestAssured.when;
import static org.hamcrest.Matchers.equalTo;

public class PathAndQueryParameters {
	
	//https://reqres.in/api/users?page=2&id=5
	
	@Test
	public void testPathAndQueryParamters() {
		
		given()
			.pathParam("myPath", "users")
			.queryParams("page", 2)
			.queryParam("id", 5)
		
		
		.when()
			//The myPath is the Path Parameter and Query parameter is sent automatically with the request thats why the key values of query parameter 
			//should be exactly that is present in URL
			.get("https://reqres.in/api/{myPath}")
		
		.then()
			.statusCode(200)
			.log().all();
		
	}

}

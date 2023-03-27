package day1;

import org.testng.annotations.Test;
import static io.restassured.RestAssured.*;
import static io.restassured.matcher.RestAssuredMatchers.*;
import static org.hamcrest.Matchers.*;

import java.util.HashMap;

public class HTTPReqests {
	
	int id;
	
	@Test(priority=1)
	void getUsers()
	{
		given()
		
		
		.when()
			.get("https://reqres.in/api/users?page=2")
		
		
		.then()
			.statusCode(200)
			.body("page",equalTo(2))
			.log().all();
		
	}
	
	@Test(priority=2)
	void createUser()
	{
		
		HashMap<String,String> hm = new HashMap<String, String>();
		hm.put("name", "Deku");
		hm.put("job", "hero");
		
		id=given()
			.contentType("application/json")
			.body(hm)
			
		
		.when()
			.post("https://reqres.in/api/users/")
			.jsonPath().getInt("id");
		
		/*.then()
			.statusCode(201)
			.log().all();*/
	}
	
	@Test(priority=3,dependsOnMethods= {"createUser"})
	void updateUser()
	{
		HashMap<String,String> hm = new HashMap<String, String>();
		hm.put("name", "Deku");
		hm.put("job", "leader");
		
		given()
			.contentType("application/json")
			.body(hm)
			
		
		.when()
			.put("https://reqres.in/api/users/"+id)
		
		.then()
			.statusCode(200)
			.log().all();
	}
	
	@Test(priority=4,dependsOnMethods= {"createUser"})
	void deleteUser() 
	{
		when()
			.delete("https://reqres.in/api/users/"+id)
			
		.then()
			.statusCode(204)
			.log().all();
	}

}

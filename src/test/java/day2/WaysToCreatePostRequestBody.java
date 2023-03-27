package day2;

import static io.restassured.RestAssured.given;
import static io.restassured.RestAssured.when;
import static org.hamcrest.Matchers.equalTo;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.HashMap;

import org.json.JSONObject;
import org.json.JSONTokener;
import org.testng.annotations.Test;

import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;

/*1.Using Hashmap  

2.Using org.json 

3.Using POJO(Plain old Java Object)  

4.Using External file */
public class WaysToCreatePostRequestBody {
	int id;
	
	@Test(enabled=false)
	public void testPostUsingHashmap()
	{
		HashMap<String, Object> map = new HashMap<String, Object>();
		
		map.put("name", "Deku");
		map.put("location", "Japan");
		map.put("phone", "123456789");
		String[] courses = {"UA","UFT"};
		map.put("courses", courses);
		
		Response response =given()
			.contentType("application/json")
			.body(map)
		.when()
			.post("http://localhost:3000/students")
		
		.then()
			.statusCode(201)
			.body("name", equalTo("Deku"))
			.body("location",equalTo("Japan"))
			.body("courses[0]", equalTo("UA"))
			.extract().response();
		
		JsonPath jsonPathEvalator = response.jsonPath();
		id=jsonPathEvalator.getInt("id");
	}
	
	@Test(enabled=false)
	public void testPostUsingJson()
	{
		
		JSONObject data = new JSONObject();
		data.put("name", "Deku");
		data.put("location", "Japan");
		data.put("phone", "123456789");
		String[] courses = {"UA","UFT"};
		data.put("courses", courses);
		
		Response response =given()
				.contentType("application/json")
				.body(data.toString())
			.when()
				.post("http://localhost:3000/students")
			
			.then()
				.statusCode(201)
				.body("name", equalTo("Deku"))
				.body("location",equalTo("Japan"))
				.body("courses[0]", equalTo("UA"))
				.extract().response();
			
			JsonPath jsonPathEvalator = response.jsonPath();
			id=jsonPathEvalator.getInt("id");
	}
	@Test(enabled=true)
	public void testPostUsingExternalFile() throws FileNotFoundException {
		
		File f = new File("D:\\Restassured_api\\automation\\src\\test\\resources\\body.json");
		FileReader fr = new FileReader(f);
		JSONTokener to = new JSONTokener(fr);
		JSONObject data = new JSONObject(to);
		
		Response response =given()
				.contentType("application/json")
				.body(data.toString())
			.when()
				.post("http://localhost:3000/students")
			
			.then()
				.statusCode(201)
				.body("name", equalTo("Deku"))
				.body("location",equalTo("Japan"))
				.body("courses[0]", equalTo("UA"))
				.extract().response();
			
			JsonPath jsonPathEvalator = response.jsonPath();
			id=jsonPathEvalator.getInt("id");
		
		
	}
	
	@Test(priority=4)
	public void toDelete() {
		
		when()
			.delete("http://localhost:3000/students"+id)
		
		.then()
			.statusCode(404)
			.log().all();
	}

}

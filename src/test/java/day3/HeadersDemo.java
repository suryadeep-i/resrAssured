package day3;

import static io.restassured.RestAssured.given;

import org.testng.annotations.Test;

import io.restassured.http.Header;
import io.restassured.http.Headers;
import io.restassured.response.Response;

public class HeadersDemo {

	@Test(enabled=false)
	public void testHeaders() {
		given()

		.when()
			.get("https://www.google.com")

		.then()
			.header("Content-Type", "text/html; charset=ISO-8859-1")
			.header("Content-Encoding", "gzip" )
			.header("Server", "gws")
			.log().all();
	}
	
	@Test
	public void gettHeaders() {
		Response res=given()

		.when()
			.get("https://www.google.com");
		
		//get Single header info
		String hearderValue = res.getHeader("Content-Type");
		System.out.println("Value of Content-Type header is "+ hearderValue);
		
		//get All headers Info
		
		Headers allHeaders=res.getHeaders();
		
		for(Header k : allHeaders)
		{
			String value = k.getValue();
			System.out.println(k+"      "+value);
		}

	}

}

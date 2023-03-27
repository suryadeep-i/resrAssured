package day3;

import static io.restassured.RestAssured.given;

import java.util.Map;

import org.testng.annotations.Test;

import io.restassured.response.Response;

public class CookiesDemo {
	
	@Test(enabled=false)
	public void testCookies() {
		
		given()
		
		
		.when()
			.get("https://www.google.com")
		
		.then()
			//.cookies("AEC", "AUEFqZcnaYgdqyuMnyp6iEHXsweLYYqNqyNkRAkxntLTlz2qcVyj4gfPCOU")
			.log().all();
	}
	
	@Test
	public void cookiesInf() {
		
		Response res=given()
		
		.when()
			.get("https://www.google.com");
		
		
		String singleCookiee= res.getCookie("AEC");
		System.out.println(singleCookiee);
		
		//To capture all cookies
		Map<String,String>allCookies=res.cookies();
		System.out.println(allCookies.keySet());
		
		for(String k : allCookies.keySet())
		{
			 String value =allCookies.get(k);
			 
			 System.out.println(k+"        "+value);
		}
	}

}

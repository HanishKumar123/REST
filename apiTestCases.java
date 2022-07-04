package ibmfullstack.restassuredframework;

import org.json.simple.JSONObject;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import static io.restassured.RestAssured.*;

import io.restassured.RestAssured;

public class apiTestCases {
	
	@DataProvider(name="testdata")
	public Object[][] data()
	{
		Object[][] userdata = new Object[1][7];
		userdata[0][0]= "hani1234";
		userdata[0][1]= "Hani";
		userdata[0][2] = "koduru";
		userdata[0][3] = "hani@gmail.com";
		userdata[0][4] = "hani@1234";
		userdata[0][5] = 63055;
		userdata[0][6] = 0;
		
		return userdata;
		
	}
	
	@Test(enabled = true, dataProvider = "testdata")
	public void Acreateuser(String uname, String fname, String lname, String email, String pass, int phno, int status)
	{
		RestAssured.baseURI = "https://petstore.swagger.io/v2";
		JSONObject obj = new JSONObject();
		
		obj.put("username", uname);
		obj.put("firstName", fname);
		obj.put("lastName", lname);
		obj.put("email", email);
		obj.put("password", pass);
		obj.put("phone", phno);
		obj.put("userStatus", status);
		
		given()
		.header("content-type", "application/json")
		.body(obj.toJSONString())
		.when()
		.post("/user")
		.then()
		.statusCode(200)
		.log()
		.all();	
	}
	
	@Test(enabled = true)
	public void Bgetuser()
	{
		RestAssured.baseURI = "https://petstore.swagger.io/v2";
		
		given()
		.when()
		.get("/user/hani1234")
		.then()
		.statusCode(200)
		.log()
		.all();
	}
	
	@Test(enabled = true)
	public void Cloginuser()
	{
		RestAssured.baseURI = "https://petstore.swagger.io/v2";
		
		given()
		.queryParam("username", "hani1234")
		.queryParam("password", "hani@1234").log().all().
		when()
			.get("/user/login").
		then()
			.statusCode(200)
			.log().all();
		
	}
	
	@Test(enabled = true)
	public void Dmodify()
	{
		RestAssured.baseURI = "https://petstore.swagger.io/v2";
		JSONObject obj = new JSONObject();
		obj.put("firstName", "Hanish");
		obj.put("lastName", "Koduru");
		obj.put("email", "hani@example.com");
		obj.put("password", "hani@1234");
		obj.put("phone", "630");
		obj.put("userStatus", 0);
		
		given()
			.header("content-type","application/json")
			.body(obj.toJSONString())
			.when()
				.put("/user/hani1234")
			.then()	
				.statusCode(200)
				.log()
				.all();
	}
	
	@Test(enabled = true)
	public void Edelete()
	{
		RestAssured.baseURI = "https://petstore.swagger.io/v2";
		given()
		.when()
			.delete("/user/hani1234")
		.then()
			.statusCode(200)
			.log()
			.all();
		
	}
	

}
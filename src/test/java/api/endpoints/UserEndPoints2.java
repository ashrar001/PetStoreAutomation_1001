package api.endpoints;

import static io.restassured.RestAssured.given;

import java.util.ResourceBundle;

import com.aventstack.extentreports.gherkin.model.When;
import com.google.j2objc.annotations.Property;

import api.payload.User;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
public class UserEndPoints2 {

	
	static ResourceBundle getURL(){
		ResourceBundle resource=ResourceBundle.getBundle("routes");
		return resource;
	}
	
	//CREATE USER
	public static Response postUser(User payload){
		String post_url=getURL().getString("post_Url");
		
	Response response=	given()
		   .contentType(ContentType.JSON)
		   .accept(ContentType.JSON)
		   .body(payload)
		.when()
		   .post(post_url);
	return response;
	}
	
	//READ USER
	public static Response getUser(String userName) {
		String get_url=getURL().getString("get_Url");
		
		Response response=given()
		.pathParam("username", userName)
		.when()
		    .get(get_url);
		return response;
	}
	
	//UPDATE USER
	public static Response updateUser(String userName,User payload){
		String update_url=getURL().getString("update_Url");
		
		Response response=given()
				.contentType(ContentType.JSON)
				.accept(ContentType.JSON)
				.pathParam("username", userName)
				.body(payload)
			.when() 
				   .put(update_url);
		       return response;
	}
	public static Response deleteUser(String userName) {
		String delete_url=getURL().getString("delete_Url");
		Response response=given()
			.pathParam("username", userName)
			
		.when()
		.delete(delete_url);
		return response;
		}
	}


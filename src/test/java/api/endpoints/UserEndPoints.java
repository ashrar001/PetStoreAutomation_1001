package api.endpoints;

import static io.restassured.RestAssured.given;

import com.aventstack.extentreports.gherkin.model.When;

import api.payload.User;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
public class UserEndPoints {

	
	//CREATE USER
	public static Response postUser(User payload){
	Response response=	given()
		   .contentType(ContentType.JSON)
		   .accept(ContentType.JSON)
		   .body(payload)
		.when()
		   .post(Routes.post_Url);
	return response;
	}
	
	//READ USER
	public static Response getUser(String userName) {
		Response response=given()
		.pathParam("username", userName)
		.when()
		    .get(Routes.get_Url);
		return response;
	}
	
	//UPDATE USER
	public static Response updateUser(String userName,User payload){
		Response response=given()
				.contentType(ContentType.JSON)
				.accept(ContentType.JSON)
				.pathParam("username", userName)
				.body(payload)
			.when() 
				   .put(Routes.update_Url);
		       return response;
	}
	public static Response deleteUser(String userName) {
		Response response=given()
			.pathParam("username", userName)
			
		.when()
		.delete(Routes.delete_Url);
		return response;
		}
	}


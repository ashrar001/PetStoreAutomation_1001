package api.test;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import com.github.javafaker.Faker;

import api.endpoints.UserEndPoints;
import api.endpoints.UserEndPoints2;
import api.payload.User;
import io.restassured.response.Response;

public class UserTests2 {
    public Logger logger ;
	Faker faker;
	User payload;
	@BeforeClass
	public void setupData() {
		faker=new Faker();//create faker class object
		payload=new User(); //create User class object and faker data should pass
		logger=LogManager.getLogger(); 
		
		
		payload.setId(faker.idNumber().hashCode());
		payload.setUsername(faker.name().username());
		payload.setFirstName(faker.name().firstName());
		payload.setLastName(faker.name().lastName());
		payload.setEmail(faker.internet().emailAddress());
		payload.setPassword(faker.internet().password(5,10));
		payload.setPhone(faker.phoneNumber().cellPhone());
	}
	
	//Create the data to the user
	@Test(priority=1)
	public void postUser() {
		logger.info("***** Creating user *****");
		Response response=UserEndPoints2.postUser(payload);
		response.then().log().all();
		Assert.assertEquals(response.getStatusCode(),200);
		logger.info("***** user is Created *****");
	}
	
	//Read the data 
	@Test(priority=2)
	public void getUserByName() {
		logger.info("***** Reading user *****");
	Response response=	UserEndPoints2.getUser(this.payload.getUsername());
	response.then().log().all();
	Assert.assertEquals(response.getStatusCode(), 200);
	logger.info("***** user is readed *****");
	}
	
	//update the user
	@Test(priority=3)
	public void updateUserByName() {
		logger.info("***** Updating user *****");
		
		//update data by using payload
		payload.setFirstName(faker.name().firstName());
		payload.setLastName(faker.name().lastName());
		payload.setEmail(faker.internet().safeEmailAddress());
		
		Response response=UserEndPoints2.updateUser(this.payload.getUsername(), payload);
		response.then().log().body();
		Assert.assertEquals(response.getStatusCode(),200);
		logger.info("***** User is Updated *****");
		
		//checking data after update
		Response afterUpdateResponse=UserEndPoints.getUser(this.payload.getUsername());
		Assert.assertEquals(afterUpdateResponse.getStatusCode(), 200);
	} 
	
	//delete the user
	@Test(priority=4)
	public void deleteUserByName() {
		logger.info("***** user is deleting *****");
		
		Response response=UserEndPoints2.deleteUser(this.payload.getUsername());
		Assert.assertEquals(response.getStatusCode(), 200);
		logger.info("***** User is deleted *****");
	}
}

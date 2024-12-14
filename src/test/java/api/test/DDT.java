package api.test;

import org.testng.Assert;
import org.testng.annotations.Test;

import api.endpoints.UserEndPoints;
import api.payload.User;
import api.utilities.DataProviders;
import io.restassured.response.Response;

public class DDT {

	@Test(priority = 1,dataProvider="Data",dataProviderClass = DataProviders.class)
	void postUser(String userid,String username,String firstname,String lastname,String email,String password,String phone){
		User payload=new User();
		payload.setId(Integer.parseInt(userid));
		payload.setUsername(username);
		payload.setFirstName(firstname);
		payload.setLastName(lastname);
		payload.setEmail(email);
		payload.setPassword(password);
		payload.setPhone(phone);
		Response response=UserEndPoints.postUser(payload);
		Assert.assertEquals(response.getStatusCode(),200);
	}
	@Test(priority = 2,dataProvider="UserNames",dataProviderClass=DataProviders.class)
	public void deleteUser(String username) {
		Response response=UserEndPoints.deleteUser(username);
		Assert.assertEquals(response.getStatusCode(), 200);
	}
}

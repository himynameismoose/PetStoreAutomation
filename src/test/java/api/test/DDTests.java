package api.test;

import api.endpoints.UserEndPoints;
import api.payload.User;
import api.utilities.DataProviders;
import io.restassured.response.Response;
import org.testng.Assert;
import org.testng.annotations.Test;

// Data Driven Tests
public class DDTests {

    @Test(priority = 1, dataProvider = "Data", dataProviderClass = DataProviders.class)
    public void testPostUser(String userID, String username, String fName, String lName, String email, String password, String phone) {
        User userPayload = new User();
        userPayload.setId(Integer.parseInt(userID));
        userPayload.setUsername(username);
        userPayload.setFirstName(fName);
        userPayload.setLastName(lName);
        userPayload.setEmail(email);
        userPayload.setPassword(password);
        userPayload.setPhone(phone);

        Response response = UserEndPoints.createUser(userPayload);
        Assert.assertEquals(response.getStatusCode(), 200);
    }

    @Test(priority = 2, dataProvider = "UserNames", dataProviderClass = DataProviders.class)
    public void testDeleteUserByName(String username) {
        Response response = UserEndPoints.deleteUser(username);
        Assert.assertEquals(response.getStatusCode(), 200);
    }
}

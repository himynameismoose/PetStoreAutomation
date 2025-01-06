package api.test;

import api.endpoints.UserEndPoints;
import api.payload.User;
import com.github.javafaker.Faker;
import io.restassured.response.Response;
import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

public class UserTests {

    Faker faker;
    User userPayload;

    @BeforeClass
    public void setupData() {
        faker = new Faker();
        userPayload = new User();
        // generate user using data faker
        userPayload.setId(faker.idNumber().hashCode());
        userPayload.setUsername(faker.name().username());
        userPayload.setFirstName(faker.name().firstName());
        userPayload.setLastName(faker.name().lastName());
        userPayload.setEmail(faker.internet().safeEmailAddress());
        userPayload.setPassword(faker.internet().password(5, 10));
        userPayload.setPhone(faker.phoneNumber().cellPhone());
    }

    @Test(priority = 1)
    public void testPostUser() {
        Response response = UserEndPoints.createUser(userPayload);
        response.then().log().all();

        Assert.assertEquals(response.getStatusCode(), 200);
    }
}

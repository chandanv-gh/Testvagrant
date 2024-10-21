package com.testvagrant.test;

import com.testvagrant.endpoints.UserEndPoints;
import com.testvagrant.payload.User;
import com.testvagrant.utilities.DataFunc;
import com.testvagrant.utilities.DataProviders;
import io.restassured.response.Response;
import org.testng.Assert;
import org.testng.annotations.Test;

import javax.xml.crypto.Data;

import java.io.IOException;

import static org.hamcrest.Matchers.equalTo;

public class DDTests {

    DataFunc df = new DataFunc();

    @Test(priority = 1, dataProvider = "Data", dataProviderClass = DataProviders.class)
    public void testPostUser(String id,
                             String username,
                             String fName,
                             String lName,
                             String email,
                             String password,
                             String phone) {
        User userPayload = new User();
        userPayload.setId(Integer.parseInt(id));
        userPayload.setUsername(username);
        userPayload.setFirstName(fName);
        userPayload.setLastName(lName);
        userPayload.setEmail(email);
        userPayload.setPassword(password);
        userPayload.setPhone(phone);

        Response response = UserEndPoints.createUser(userPayload);
        response.then().log().all();
        Assert.assertEquals(response.getStatusCode(), 200);
    }

    @Test(priority = 2, dataProvider = "UserNames", dataProviderClass = DataProviders.class)
    public void testGetUserByName(String username) throws IOException {
        Response response = UserEndPoints.readUser(username);
        response.then().log().all();
        Assert.assertEquals(response.statusCode(), 200);
        Assert.assertEquals(response.jsonPath().getString("phone"), df.getDataByUserName(username).getPhone());
    }

    @Test(priority = 3, dataProvider = "UserNames", dataProviderClass = DataProviders.class)
    public void testDeleteUserByName(String username) {
        Response response = UserEndPoints.deleteUser(username);
        response.then().log().all();
        Assert.assertEquals(response.statusCode(), 200);
    }
}

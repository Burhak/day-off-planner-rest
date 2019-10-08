package com.evolveum.dayoffplannerrestapi;

import io.swagger.model.UserLoginApiModel;

import java.util.*;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.junit4.SpringRunner;

import static org.junit.Assert.assertEquals;

@RunWith(SpringRunner.class)
@SpringBootTest
public class LoginApiControllerIntegrationTest {

    @Autowired
    private LoginApi api;

    @Test
    public void loginUserTest() throws Exception {
        UserLoginApiModel body = new UserLoginApiModel();
        ResponseEntity<Void> responseEntity = api.loginUser(body);
        assertEquals(HttpStatus.NOT_IMPLEMENTED, responseEntity.getStatusCode());
    }

}

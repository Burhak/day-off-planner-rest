package com.evolveum.day_off_planner_rest_api.api;

import com.evolveum.day_off_planner_rest_api.model.UserApiModel;
import com.evolveum.day_off_planner_rest_api.model.UserCreateApiModel;

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
public class AdminApiControllerIntegrationTest {

    @Autowired
    private AdminApi api;

    @Test
    public void createUserTest() throws Exception {
        UserCreateApiModel body = new UserCreateApiModel();
        ResponseEntity<UserApiModel> responseEntity = api.createUser(body);
        assertEquals(HttpStatus.NOT_IMPLEMENTED, responseEntity.getStatusCode());
    }

}

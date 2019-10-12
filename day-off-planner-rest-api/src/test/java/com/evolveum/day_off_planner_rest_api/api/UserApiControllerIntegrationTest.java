package com.evolveum.day_off_planner_rest_api.api;

import com.evolveum.day_off_planner_rest_api.model.PasswordChangeApiModel;
import com.evolveum.day_off_planner_rest_api.model.PasswordResetApiModel;
import com.evolveum.day_off_planner_rest_api.model.UserApiModel;

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
public class UserApiControllerIntegrationTest {

    @Autowired
    private UserApi api;

    @Test
    public void changePasswordTest() throws Exception {
        PasswordChangeApiModel body = new PasswordChangeApiModel();
        ResponseEntity<Void> responseEntity = api.changePassword(body);
        assertEquals(HttpStatus.NOT_IMPLEMENTED, responseEntity.getStatusCode());
    }

    @Test
    public void getAllUsersTest() throws Exception {
        ResponseEntity<List<UserApiModel>> responseEntity = api.getAllUsers();
        assertEquals(HttpStatus.NOT_IMPLEMENTED, responseEntity.getStatusCode());
    }

    @Test
    public void getUserByIdTest() throws Exception {
        Long id = 789L;
        ResponseEntity<UserApiModel> responseEntity = api.getUserById(id);
        assertEquals(HttpStatus.NOT_IMPLEMENTED, responseEntity.getStatusCode());
    }

    @Test
    public void resetPasswordTest() throws Exception {
        PasswordResetApiModel body = new PasswordResetApiModel();
        ResponseEntity<Void> responseEntity = api.resetPassword(body);
        assertEquals(HttpStatus.NOT_IMPLEMENTED, responseEntity.getStatusCode());
    }

}

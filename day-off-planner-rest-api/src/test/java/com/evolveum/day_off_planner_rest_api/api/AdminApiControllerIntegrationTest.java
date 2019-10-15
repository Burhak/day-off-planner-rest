package com.evolveum.day_off_planner_rest_api.api;

import com.evolveum.day_off_planner_rest_api.model.LeaveTypeApiModel;
import com.evolveum.day_off_planner_rest_api.model.LeaveTypeCreateApiModel;
import java.util.UUID;
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
    public void createLeaveTypeTest() throws Exception {
        LeaveTypeCreateApiModel body = new LeaveTypeCreateApiModel();
        ResponseEntity<LeaveTypeApiModel> responseEntity = api.createLeaveType(body);
        assertEquals(HttpStatus.NOT_IMPLEMENTED, responseEntity.getStatusCode());
    }

    @Test
    public void createUserTest() throws Exception {
        UserCreateApiModel body = new UserCreateApiModel();
        ResponseEntity<UserApiModel> responseEntity = api.createUser(body);
        assertEquals(HttpStatus.NOT_IMPLEMENTED, responseEntity.getStatusCode());
    }

    @Test
    public void deleteLeaveTypeTest() throws Exception {
        UUID id = new UUID();
        ResponseEntity<Void> responseEntity = api.deleteLeaveType(id);
        assertEquals(HttpStatus.NOT_IMPLEMENTED, responseEntity.getStatusCode());
    }

    @Test
    public void deleteUserTest() throws Exception {
        UUID id = new UUID();
        ResponseEntity<Void> responseEntity = api.deleteUser(id);
        assertEquals(HttpStatus.NOT_IMPLEMENTED, responseEntity.getStatusCode());
    }

    @Test
    public void updateLeaveTypeTest() throws Exception {
        LeaveTypeCreateApiModel body = new LeaveTypeCreateApiModel();
        UUID id = new UUID();
        ResponseEntity<LeaveTypeApiModel> responseEntity = api.updateLeaveType(body, id);
        assertEquals(HttpStatus.NOT_IMPLEMENTED, responseEntity.getStatusCode());
    }

    @Test
    public void updateUserTest() throws Exception {
        UserCreateApiModel body = new UserCreateApiModel();
        UUID id = new UUID();
        ResponseEntity<UserApiModel> responseEntity = api.updateUser(body, id);
        assertEquals(HttpStatus.NOT_IMPLEMENTED, responseEntity.getStatusCode());
    }

}

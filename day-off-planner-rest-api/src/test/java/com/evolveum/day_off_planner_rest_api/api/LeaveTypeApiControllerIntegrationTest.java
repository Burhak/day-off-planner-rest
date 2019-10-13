package com.evolveum.day_off_planner_rest_api.api;

import com.evolveum.day_off_planner_rest_api.model.LeaveTypeApiModel;

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
public class LeaveTypeApiControllerIntegrationTest {

    @Autowired
    private LeaveTypeApi api;

    @Test
    public void getAllLeaveTypesTest() throws Exception {
        ResponseEntity<List<LeaveTypeApiModel>> responseEntity = api.getAllLeaveTypes();
        assertEquals(HttpStatus.NOT_IMPLEMENTED, responseEntity.getStatusCode());
    }

    @Test
    public void getLeaveTypeByIdTest() throws Exception {
        Long id = 789L;
        ResponseEntity<LeaveTypeApiModel> responseEntity = api.getLeaveTypeById(id);
        assertEquals(HttpStatus.NOT_IMPLEMENTED, responseEntity.getStatusCode());
    }

}

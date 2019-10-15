package com.evolveum.day_off_planner_rest_api.api;

import com.evolveum.day_off_planner_rest_api.model.LeaveRequestApiModel;
import com.evolveum.day_off_planner_rest_api.model.LeaveRequestCreateApiModel;
import java.util.UUID;

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
public class LeaveApiControllerIntegrationTest {

    @Autowired
    private LeaveApi api;

    @Test
    public void createLeaveRequestTest() throws Exception {
        LeaveRequestCreateApiModel body = new LeaveRequestCreateApiModel();
        ResponseEntity<LeaveRequestApiModel> responseEntity = api.createLeaveRequest(body);
        assertEquals(HttpStatus.NOT_IMPLEMENTED, responseEntity.getStatusCode());
    }

    @Test
    public void getLeaveRequestByIdTest() throws Exception {
        UUID id = new UUID();
        ResponseEntity<LeaveRequestApiModel> responseEntity = api.getLeaveRequestById(id);
        assertEquals(HttpStatus.NOT_IMPLEMENTED, responseEntity.getStatusCode());
    }

}

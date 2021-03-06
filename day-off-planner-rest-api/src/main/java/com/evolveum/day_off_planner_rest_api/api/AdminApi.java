/**
 * NOTE: This class is auto generated by the swagger code generator program (3.0.14).
 * https://github.com/swagger-api/swagger-codegen
 * Do not edit the class manually.
 */
package com.evolveum.day_off_planner_rest_api.api;

import com.evolveum.day_off_planner_rest_api.model.LeaveTypeApiModel;
import com.evolveum.day_off_planner_rest_api.model.LeaveTypeCreateApiModel;
import com.evolveum.day_off_planner_rest_api.model.LimitApiModel;
import com.evolveum.day_off_planner_rest_api.model.LimitUpdateApiModel;
import com.evolveum.day_off_planner_rest_api.model.SettingApiModel;
import com.evolveum.day_off_planner_rest_api.model.SettingUpdateApiModel;
import java.util.UUID;
import com.evolveum.day_off_planner_rest_api.model.UserApiModel;
import com.evolveum.day_off_planner_rest_api.model.UserCreateApiModel;
import com.fasterxml.jackson.databind.ObjectMapper;
import io.swagger.annotations.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.bind.annotation.CookieValue;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;
import javax.validation.constraints.*;
import java.io.IOException;
import java.util.List;
import java.util.Map;
import java.util.Optional;
@javax.annotation.Generated(value = "io.swagger.codegen.v3.generators.java.SpringCodegen", date = "2020-01-17T18:34:48.841Z[GMT]")
@Api(value = "admin", description = "the admin API")
public interface AdminApi {

    Logger log = LoggerFactory.getLogger(AdminApi.class);

    default Optional<ObjectMapper> getObjectMapper(){
        return Optional.empty();
    }

    default Optional<HttpServletRequest> getRequest(){
        return Optional.empty();
    }

    default Optional<String> getAcceptHeader() {
        return getRequest().map(r -> r.getHeader("Accept"));
    }

    @ApiOperation(value = "Create new leave type", nickname = "createLeaveType", notes = "", response = LeaveTypeApiModel.class, authorizations = {
        @Authorization(value = "bearerAuth")    }, tags={ "admin", })
    @ApiResponses(value = { 
        @ApiResponse(code = 201, message = "Created", response = LeaveTypeApiModel.class),
        @ApiResponse(code = 403, message = "Not an admin"),
        @ApiResponse(code = 409, message = "Given name already exists") })
    @RequestMapping(value = "/admin/leaveType",
        produces = { "application/json" }, 
        consumes = { "application/json" },
        method = RequestMethod.POST)
    default ResponseEntity<LeaveTypeApiModel> createLeaveType(@ApiParam(value = "Object of leave type to be created" ,required=true )  @Valid @RequestBody LeaveTypeCreateApiModel body) {
        if(getObjectMapper().isPresent() && getAcceptHeader().isPresent()) {
            if (getAcceptHeader().get().contains("application/json")) {
                try {
                    return new ResponseEntity<>(getObjectMapper().get().readValue("{\n  \"approvalNeeded\" : false,\n  \"color\" : \"color\",\n  \"name\" : \"name\",\n  \"limit\" : 0,\n  \"carryover\" : 6,\n  \"id\" : \"046b6c7f-0b8a-43b9-b35d-6489e6daee91\"\n}", LeaveTypeApiModel.class), HttpStatus.NOT_IMPLEMENTED);
                } catch (IOException e) {
                    log.error("Couldn't serialize response for content type application/json", e);
                    return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
                }
            }
        } else {
            log.warn("ObjectMapper or HttpServletRequest not configured in default AdminApi interface so no example is generated");
        }
        return new ResponseEntity<>(HttpStatus.NOT_IMPLEMENTED);
    }


    @ApiOperation(value = "Create new user", nickname = "createUser", notes = "", response = UserApiModel.class, authorizations = {
        @Authorization(value = "bearerAuth")    }, tags={ "admin", })
    @ApiResponses(value = { 
        @ApiResponse(code = 201, message = "Created", response = UserApiModel.class),
        @ApiResponse(code = 403, message = "Not an admin"),
        @ApiResponse(code = 409, message = "Given email already exists") })
    @RequestMapping(value = "/admin/user",
        produces = { "application/json" }, 
        consumes = { "application/json" },
        method = RequestMethod.POST)
    default ResponseEntity<UserApiModel> createUser(@ApiParam(value = "Object of user to be created" ,required=true )  @Valid @RequestBody UserCreateApiModel body) {
        if(getObjectMapper().isPresent() && getAcceptHeader().isPresent()) {
            if (getAcceptHeader().get().contains("application/json")) {
                try {
                    return new ResponseEntity<>(getObjectMapper().get().readValue("{\n  \"firstName\" : \"firstName\",\n  \"lastName\" : \"lastName\",\n  \"phone\" : \"phone\",\n  \"admin\" : false,\n  \"approvers\" : [ \"046b6c7f-0b8a-43b9-b35d-6489e6daee91\", \"046b6c7f-0b8a-43b9-b35d-6489e6daee91\" ],\n  \"jobDescription\" : \"jobDescription\",\n  \"id\" : \"046b6c7f-0b8a-43b9-b35d-6489e6daee91\",\n  \"email\" : \"\",\n  \"supervisor\" : \"046b6c7f-0b8a-43b9-b35d-6489e6daee91\"\n}", UserApiModel.class), HttpStatus.NOT_IMPLEMENTED);
                } catch (IOException e) {
                    log.error("Couldn't serialize response for content type application/json", e);
                    return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
                }
            }
        } else {
            log.warn("ObjectMapper or HttpServletRequest not configured in default AdminApi interface so no example is generated");
        }
        return new ResponseEntity<>(HttpStatus.NOT_IMPLEMENTED);
    }


    @ApiOperation(value = "Delete existing leave type", nickname = "deleteLeaveType", notes = "", authorizations = {
        @Authorization(value = "bearerAuth")    }, tags={ "admin", })
    @ApiResponses(value = { 
        @ApiResponse(code = 200, message = "OK"),
        @ApiResponse(code = 403, message = "Not an admin"),
        @ApiResponse(code = 404, message = "Not found") })
    @RequestMapping(value = "/admin/leaveType/{id}",
        method = RequestMethod.DELETE)
    default ResponseEntity<Void> deleteLeaveType(@ApiParam(value = "ID of the leave type to be deleted",required=true) @PathVariable("id") UUID id) {
        if(getObjectMapper().isPresent() && getAcceptHeader().isPresent()) {
        } else {
            log.warn("ObjectMapper or HttpServletRequest not configured in default AdminApi interface so no example is generated");
        }
        return new ResponseEntity<>(HttpStatus.NOT_IMPLEMENTED);
    }


    @ApiOperation(value = "Delete user individual limit", nickname = "deleteLimit", notes = "", authorizations = {
        @Authorization(value = "bearerAuth")    }, tags={ "admin", })
    @ApiResponses(value = { 
        @ApiResponse(code = 200, message = "OK"),
        @ApiResponse(code = 403, message = "Not an admin"),
        @ApiResponse(code = 404, message = "Not found") })
    @RequestMapping(value = "/admin/user/{userId}/limit/{leaveTypeId}",
        method = RequestMethod.DELETE)
    default ResponseEntity<Void> deleteLimit(@ApiParam(value = "ID of the user",required=true) @PathVariable("userId") UUID userId,@ApiParam(value = "ID of the leave type",required=true) @PathVariable("leaveTypeId") UUID leaveTypeId) {
        if(getObjectMapper().isPresent() && getAcceptHeader().isPresent()) {
        } else {
            log.warn("ObjectMapper or HttpServletRequest not configured in default AdminApi interface so no example is generated");
        }
        return new ResponseEntity<>(HttpStatus.NOT_IMPLEMENTED);
    }


    @ApiOperation(value = "Delete existing user", nickname = "deleteUser", notes = "", authorizations = {
        @Authorization(value = "bearerAuth")    }, tags={ "admin", })
    @ApiResponses(value = { 
        @ApiResponse(code = 200, message = "OK"),
        @ApiResponse(code = 403, message = "Not an admin"),
        @ApiResponse(code = 404, message = "Not found") })
    @RequestMapping(value = "/admin/user/{id}",
        method = RequestMethod.DELETE)
    default ResponseEntity<Void> deleteUser(@ApiParam(value = "ID of the user to be deleted",required=true) @PathVariable("id") UUID id) {
        if(getObjectMapper().isPresent() && getAcceptHeader().isPresent()) {
        } else {
            log.warn("ObjectMapper or HttpServletRequest not configured in default AdminApi interface so no example is generated");
        }
        return new ResponseEntity<>(HttpStatus.NOT_IMPLEMENTED);
    }


    @ApiOperation(value = "Update existing leave type", nickname = "updateLeaveType", notes = "", response = LeaveTypeApiModel.class, authorizations = {
        @Authorization(value = "bearerAuth")    }, tags={ "admin", })
    @ApiResponses(value = { 
        @ApiResponse(code = 200, message = "OK", response = LeaveTypeApiModel.class),
        @ApiResponse(code = 403, message = "Not an admin"),
        @ApiResponse(code = 404, message = "Not found") })
    @RequestMapping(value = "/admin/leaveType/{id}",
        produces = { "application/json" }, 
        consumes = { "application/json" },
        method = RequestMethod.PUT)
    default ResponseEntity<LeaveTypeApiModel> updateLeaveType(@ApiParam(value = "Object of leave type to be updated" ,required=true )  @Valid @RequestBody LeaveTypeCreateApiModel body,@ApiParam(value = "ID of the leave type to be updated",required=true) @PathVariable("id") UUID id) {
        if(getObjectMapper().isPresent() && getAcceptHeader().isPresent()) {
            if (getAcceptHeader().get().contains("application/json")) {
                try {
                    return new ResponseEntity<>(getObjectMapper().get().readValue("{\n  \"approvalNeeded\" : false,\n  \"color\" : \"color\",\n  \"name\" : \"name\",\n  \"limit\" : 0,\n  \"carryover\" : 6,\n  \"id\" : \"046b6c7f-0b8a-43b9-b35d-6489e6daee91\"\n}", LeaveTypeApiModel.class), HttpStatus.NOT_IMPLEMENTED);
                } catch (IOException e) {
                    log.error("Couldn't serialize response for content type application/json", e);
                    return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
                }
            }
        } else {
            log.warn("ObjectMapper or HttpServletRequest not configured in default AdminApi interface so no example is generated");
        }
        return new ResponseEntity<>(HttpStatus.NOT_IMPLEMENTED);
    }


    @ApiOperation(value = "Update user individual limit", nickname = "updateLimit", notes = "", response = LimitApiModel.class, authorizations = {
        @Authorization(value = "bearerAuth")    }, tags={ "admin", })
    @ApiResponses(value = { 
        @ApiResponse(code = 200, message = "OK", response = LimitApiModel.class),
        @ApiResponse(code = 403, message = "Not an admin"),
        @ApiResponse(code = 404, message = "Not found") })
    @RequestMapping(value = "/admin/user/{userId}/limit/{leaveTypeId}",
        produces = { "application/json" }, 
        consumes = { "application/json" },
        method = RequestMethod.PUT)
    default ResponseEntity<LimitApiModel> updateLimit(@ApiParam(value = "Object of limit to be set" ,required=true )  @Valid @RequestBody LimitUpdateApiModel body,@ApiParam(value = "ID of the user",required=true) @PathVariable("userId") UUID userId,@ApiParam(value = "ID of the leave type",required=true) @PathVariable("leaveTypeId") UUID leaveTypeId) {
        if(getObjectMapper().isPresent() && getAcceptHeader().isPresent()) {
            if (getAcceptHeader().get().contains("application/json")) {
                try {
                    return new ResponseEntity<>(getObjectMapper().get().readValue("{\n  \"leaveType\" : \"046b6c7f-0b8a-43b9-b35d-6489e6daee91\",\n  \"limit\" : 0,\n  \"user\" : \"046b6c7f-0b8a-43b9-b35d-6489e6daee91\"\n}", LimitApiModel.class), HttpStatus.NOT_IMPLEMENTED);
                } catch (IOException e) {
                    log.error("Couldn't serialize response for content type application/json", e);
                    return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
                }
            }
        } else {
            log.warn("ObjectMapper or HttpServletRequest not configured in default AdminApi interface so no example is generated");
        }
        return new ResponseEntity<>(HttpStatus.NOT_IMPLEMENTED);
    }


    @ApiOperation(value = "Update setting with given key", nickname = "updateSetting", notes = "", response = SettingApiModel.class, authorizations = {
        @Authorization(value = "bearerAuth")    }, tags={ "admin", })
    @ApiResponses(value = { 
        @ApiResponse(code = 200, message = "OK", response = SettingApiModel.class),
        @ApiResponse(code = 403, message = "Not an admin"),
        @ApiResponse(code = 404, message = "Not found") })
    @RequestMapping(value = "/admin/setting/{key}",
        produces = { "application/json" }, 
        consumes = { "application/json" },
        method = RequestMethod.PUT)
    default ResponseEntity<SettingApiModel> updateSetting(@ApiParam(value = "New value of the setting" ,required=true )  @Valid @RequestBody SettingUpdateApiModel body,@ApiParam(value = "Key of the setting to be updated",required=true) @PathVariable("key") String key) {
        if(getObjectMapper().isPresent() && getAcceptHeader().isPresent()) {
            if (getAcceptHeader().get().contains("application/json")) {
                try {
                    return new ResponseEntity<>(getObjectMapper().get().readValue("{\n  \"min\" : 6,\n  \"max\" : 1,\n  \"description\" : \"description\",\n  \"value\" : 0,\n  \"key\" : \"key\"\n}", SettingApiModel.class), HttpStatus.NOT_IMPLEMENTED);
                } catch (IOException e) {
                    log.error("Couldn't serialize response for content type application/json", e);
                    return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
                }
            }
        } else {
            log.warn("ObjectMapper or HttpServletRequest not configured in default AdminApi interface so no example is generated");
        }
        return new ResponseEntity<>(HttpStatus.NOT_IMPLEMENTED);
    }


    @ApiOperation(value = "Update existing user", nickname = "updateUser", notes = "", response = UserApiModel.class, authorizations = {
        @Authorization(value = "bearerAuth")    }, tags={ "admin", })
    @ApiResponses(value = { 
        @ApiResponse(code = 200, message = "OK", response = UserApiModel.class),
        @ApiResponse(code = 403, message = "Not an admin"),
        @ApiResponse(code = 404, message = "Not found") })
    @RequestMapping(value = "/admin/user/{id}",
        produces = { "application/json" }, 
        consumes = { "application/json" },
        method = RequestMethod.PUT)
    default ResponseEntity<UserApiModel> updateUser(@ApiParam(value = "Object of user to be updated" ,required=true )  @Valid @RequestBody UserCreateApiModel body,@ApiParam(value = "ID of the user to be updated",required=true) @PathVariable("id") UUID id) {
        if(getObjectMapper().isPresent() && getAcceptHeader().isPresent()) {
            if (getAcceptHeader().get().contains("application/json")) {
                try {
                    return new ResponseEntity<>(getObjectMapper().get().readValue("{\n  \"firstName\" : \"firstName\",\n  \"lastName\" : \"lastName\",\n  \"phone\" : \"phone\",\n  \"admin\" : false,\n  \"approvers\" : [ \"046b6c7f-0b8a-43b9-b35d-6489e6daee91\", \"046b6c7f-0b8a-43b9-b35d-6489e6daee91\" ],\n  \"jobDescription\" : \"jobDescription\",\n  \"id\" : \"046b6c7f-0b8a-43b9-b35d-6489e6daee91\",\n  \"email\" : \"\",\n  \"supervisor\" : \"046b6c7f-0b8a-43b9-b35d-6489e6daee91\"\n}", UserApiModel.class), HttpStatus.NOT_IMPLEMENTED);
                } catch (IOException e) {
                    log.error("Couldn't serialize response for content type application/json", e);
                    return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
                }
            }
        } else {
            log.warn("ObjectMapper or HttpServletRequest not configured in default AdminApi interface so no example is generated");
        }
        return new ResponseEntity<>(HttpStatus.NOT_IMPLEMENTED);
    }

}

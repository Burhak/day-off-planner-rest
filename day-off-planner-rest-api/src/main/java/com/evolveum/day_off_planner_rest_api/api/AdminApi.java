/**
 * NOTE: This class is auto generated by the swagger code generator program (3.0.11).
 * https://github.com/swagger-api/swagger-codegen
 * Do not edit the class manually.
 */
package com.evolveum.day_off_planner_rest_api.api;

import com.evolveum.day_off_planner_rest_api.model.LeaveTypeApiModel;
import com.evolveum.day_off_planner_rest_api.model.UserApiModel;
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

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;
import javax.validation.constraints.*;
import java.io.IOException;
import java.util.List;
import java.util.Map;
import java.util.Optional;
@javax.annotation.Generated(value = "io.swagger.codegen.v3.generators.java.SpringCodegen", date = "2019-10-14T09:01:14.193Z[GMT]")
@Api(value = "admin", description = "the admin API")
public interface AdminApi {

    Logger log = LoggerFactory.getLogger(AdminApi.class);

    default Optional<ObjectMapper> getObjectMapper() {
        return Optional.empty();
    }

    default Optional<HttpServletRequest> getRequest() {
        return Optional.empty();
    }

    default Optional<String> getAcceptHeader() {
        return getRequest().map(r -> r.getHeader("Accept"));
    }

    @ApiOperation(value = "Create new leave type", nickname = "createLeaveType", notes = "", response = LeaveTypeApiModel.class, authorizations = {
        @Authorization(value = "bearerAuth"),
@Authorization(value = "oAuthNoScopes", scopes = {
                        })    }, tags={ "admin", })
    @ApiResponses(value = { 
        @ApiResponse(code = 201, message = "Created", response = LeaveTypeApiModel.class),
        @ApiResponse(code = 403, message = "Not an admin"),
        @ApiResponse(code = 409, message = "Given name already exists") })
    @RequestMapping(value = "/admin/leaveType",
        produces = { "application/json" }, 
        consumes = { "application/json" },
        method = RequestMethod.POST)
    default ResponseEntity<LeaveTypeApiModel> createLeaveType(@ApiParam(value = "Object of leave type to be created" ,required=true )  @Valid @RequestBody LeaveTypeApiModel body) {
        if(getObjectMapper().isPresent() && getAcceptHeader().isPresent()) {
            if (getAcceptHeader().get().contains("application/json")) {
                try {
                    return new ResponseEntity<>(getObjectMapper().get().readValue("{\n  \"approvalNeeded\" : false,\n  \"limited\" : false,\n  \"name\" : \"name\",\n  \"halfDayAllowed\" : false,\n  \"id\" : 0\n}", LeaveTypeApiModel.class), HttpStatus.NOT_IMPLEMENTED);
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
        @Authorization(value = "bearerAuth"),
@Authorization(value = "oAuthNoScopes", scopes = {
                        })    }, tags={ "admin", })
    @ApiResponses(value = { 
        @ApiResponse(code = 201, message = "Created", response = UserApiModel.class),
        @ApiResponse(code = 403, message = "Not an admin"),
        @ApiResponse(code = 409, message = "Given email already exists") })
    @RequestMapping(value = "/admin/user",
        produces = { "application/json" }, 
        consumes = { "application/json" },
        method = RequestMethod.POST)
    default ResponseEntity<UserApiModel> createUser(@ApiParam(value = "Object of user to be created" ,required=true )  @Valid @RequestBody UserApiModel body) {
        if(getObjectMapper().isPresent() && getAcceptHeader().isPresent()) {
            if (getAcceptHeader().get().contains("application/json")) {
                try {
                    return new ResponseEntity<>(getObjectMapper().get().readValue("{\n  \"firstName\" : \"firstName\",\n  \"lastName\" : \"lastName\",\n  \"admin\" : false,\n  \"id\" : 0,\n  \"email\" : \"\",\n  \"supervisor\" : 6\n}", UserApiModel.class), HttpStatus.NOT_IMPLEMENTED);
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
        @Authorization(value = "bearerAuth"),
@Authorization(value = "oAuthNoScopes", scopes = {
                        })    }, tags={ "admin", })
    @ApiResponses(value = { 
        @ApiResponse(code = 200, message = "OK"),
        @ApiResponse(code = 403, message = "Not an admin"),
        @ApiResponse(code = 404, message = "Not found") })
    @RequestMapping(value = "/admin/leaveType/{id}",
        method = RequestMethod.DELETE)
    default ResponseEntity<Void> deleteLeaveType(@ApiParam(value = "ID of the leave type to be deleted",required=true) @PathVariable("id") Long id) {
        if(getObjectMapper().isPresent() && getAcceptHeader().isPresent()) {
        } else {
            log.warn("ObjectMapper or HttpServletRequest not configured in default AdminApi interface so no example is generated");
        }
        return new ResponseEntity<>(HttpStatus.NOT_IMPLEMENTED);
    }


    @ApiOperation(value = "Delete existing user", nickname = "deleteUser", notes = "", authorizations = {
        @Authorization(value = "bearerAuth"),
@Authorization(value = "oAuthNoScopes", scopes = {
                        })    }, tags={ "admin", })
    @ApiResponses(value = { 
        @ApiResponse(code = 200, message = "OK"),
        @ApiResponse(code = 403, message = "Not an admin"),
        @ApiResponse(code = 404, message = "Not found") })
    @RequestMapping(value = "/admin/user/{id}",
        method = RequestMethod.DELETE)
    default ResponseEntity<Void> deleteUser(@ApiParam(value = "ID of the user to be deleted",required=true) @PathVariable("id") Long id) {
        if(getObjectMapper().isPresent() && getAcceptHeader().isPresent()) {
        } else {
            log.warn("ObjectMapper or HttpServletRequest not configured in default AdminApi interface so no example is generated");
        }
        return new ResponseEntity<>(HttpStatus.NOT_IMPLEMENTED);
    }


    @ApiOperation(value = "Update existing leave type", nickname = "updateLeaveType", notes = "", response = LeaveTypeApiModel.class, authorizations = {
        @Authorization(value = "bearerAuth"),
@Authorization(value = "oAuthNoScopes", scopes = {
                        })    }, tags={ "admin", })
    @ApiResponses(value = { 
        @ApiResponse(code = 200, message = "OK", response = LeaveTypeApiModel.class),
        @ApiResponse(code = 403, message = "Not an admin"),
        @ApiResponse(code = 404, message = "Not found") })
    @RequestMapping(value = "/admin/leaveType/{id}",
        produces = { "application/json" }, 
        consumes = { "application/json" },
        method = RequestMethod.PUT)
    default ResponseEntity<LeaveTypeApiModel> updateLeaveType(@ApiParam(value = "Object of leave type to be updated" ,required=true )  @Valid @RequestBody LeaveTypeApiModel body,@ApiParam(value = "ID of the leave type to be updated",required=true) @PathVariable("id") Long id) {
        if(getObjectMapper().isPresent() && getAcceptHeader().isPresent()) {
            if (getAcceptHeader().get().contains("application/json")) {
                try {
                    return new ResponseEntity<>(getObjectMapper().get().readValue("{\n  \"approvalNeeded\" : false,\n  \"limited\" : false,\n  \"name\" : \"name\",\n  \"halfDayAllowed\" : false,\n  \"id\" : 0\n}", LeaveTypeApiModel.class), HttpStatus.NOT_IMPLEMENTED);
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
        @Authorization(value = "bearerAuth"),
@Authorization(value = "oAuthNoScopes", scopes = {
                        })    }, tags={ "admin", })
    @ApiResponses(value = { 
        @ApiResponse(code = 200, message = "OK", response = UserApiModel.class),
        @ApiResponse(code = 403, message = "Not an admin"),
        @ApiResponse(code = 404, message = "Not found") })
    @RequestMapping(value = "/admin/user/{id}",
        produces = { "application/json" }, 
        consumes = { "application/json" },
        method = RequestMethod.PUT)
    default ResponseEntity<UserApiModel> updateUser(@ApiParam(value = "Object of user to be updated" ,required=true )  @Valid @RequestBody UserApiModel body,@ApiParam(value = "ID of the user to be updated",required=true) @PathVariable("id") Long id) {
        if(getObjectMapper().isPresent() && getAcceptHeader().isPresent()) {
            if (getAcceptHeader().get().contains("application/json")) {
                try {
                    return new ResponseEntity<>(getObjectMapper().get().readValue("{\n  \"firstName\" : \"firstName\",\n  \"lastName\" : \"lastName\",\n  \"admin\" : false,\n  \"id\" : 0,\n  \"email\" : \"\",\n  \"supervisor\" : 6\n}", UserApiModel.class), HttpStatus.NOT_IMPLEMENTED);
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

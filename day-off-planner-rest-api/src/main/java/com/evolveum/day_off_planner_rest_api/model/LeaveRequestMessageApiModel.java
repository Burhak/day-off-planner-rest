package com.evolveum.day_off_planner_rest_api.model;

import java.util.Objects;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonCreator;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import java.time.LocalDateTime;
import java.util.UUID;
import org.springframework.validation.annotation.Validated;
import javax.validation.Valid;
import javax.validation.constraints.*;

/**
 * LeaveRequestMessageApiModel
 */
@Validated
@javax.annotation.Generated(value = "io.swagger.codegen.v3.generators.java.SpringCodegen", date = "2019-11-20T17:21:05.197Z[GMT]")
public class LeaveRequestMessageApiModel   {
  @JsonProperty("leaveRequest")
  private UUID leaveRequest = null;

  @JsonProperty("approver")
  private UUID approver = null;

  @JsonProperty("message")
  private String message = null;

  @JsonProperty("timestamp")
  private LocalDateTime timestamp = null;

  public LeaveRequestMessageApiModel leaveRequest(UUID leaveRequest) {
    this.leaveRequest = leaveRequest;
    return this;
  }

  /**
   * Get leaveRequest
   * @return leaveRequest
  **/
  @ApiModelProperty(required = true, readOnly = true, value = "")
      @NotNull

    @Valid
    public UUID getLeaveRequest() {
    return leaveRequest;
  }

  public void setLeaveRequest(UUID leaveRequest) {
    this.leaveRequest = leaveRequest;
  }

  public LeaveRequestMessageApiModel approver(UUID approver) {
    this.approver = approver;
    return this;
  }

  /**
   * Get approver
   * @return approver
  **/
  @ApiModelProperty(required = true, readOnly = true, value = "")
      @NotNull

    @Valid
    public UUID getApprover() {
    return approver;
  }

  public void setApprover(UUID approver) {
    this.approver = approver;
  }

  public LeaveRequestMessageApiModel message(String message) {
    this.message = message;
    return this;
  }

  /**
   * Get message
   * @return message
  **/
  @ApiModelProperty(required = true, readOnly = true, value = "")
      @NotNull

    public String getMessage() {
    return message;
  }

  public void setMessage(String message) {
    this.message = message;
  }

  public LeaveRequestMessageApiModel timestamp(LocalDateTime timestamp) {
    this.timestamp = timestamp;
    return this;
  }

  /**
   * Get timestamp
   * @return timestamp
  **/
  @ApiModelProperty(required = true, readOnly = true, value = "")
      @NotNull

    @Valid
    public LocalDateTime getTimestamp() {
    return timestamp;
  }

  public void setTimestamp(LocalDateTime timestamp) {
    this.timestamp = timestamp;
  }


  @Override
  public boolean equals(java.lang.Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    LeaveRequestMessageApiModel leaveRequestMessageApiModel = (LeaveRequestMessageApiModel) o;
    return Objects.equals(this.leaveRequest, leaveRequestMessageApiModel.leaveRequest) &&
        Objects.equals(this.approver, leaveRequestMessageApiModel.approver) &&
        Objects.equals(this.message, leaveRequestMessageApiModel.message) &&
        Objects.equals(this.timestamp, leaveRequestMessageApiModel.timestamp);
  }

  @Override
  public int hashCode() {
    return Objects.hash(leaveRequest, approver, message, timestamp);
  }

  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append("class LeaveRequestMessageApiModel {\n");
    
    sb.append("    leaveRequest: ").append(toIndentedString(leaveRequest)).append("\n");
    sb.append("    approver: ").append(toIndentedString(approver)).append("\n");
    sb.append("    message: ").append(toIndentedString(message)).append("\n");
    sb.append("    timestamp: ").append(toIndentedString(timestamp)).append("\n");
    sb.append("}");
    return sb.toString();
  }

  /**
   * Convert the given object to string with each line indented by 4 spaces
   * (except the first line).
   */
  private String toIndentedString(java.lang.Object o) {
    if (o == null) {
      return "null";
    }
    return o.toString().replace("\n", "\n    ");
  }
}

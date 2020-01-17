package com.evolveum.day_off_planner_rest_api.model;

import java.util.Objects;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonCreator;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import java.util.UUID;
import org.springframework.validation.annotation.Validated;
import javax.validation.Valid;
import javax.validation.constraints.*;

/**
 * LeaveRequestApprovalApiModel
 */
@Validated
@javax.annotation.Generated(value = "io.swagger.codegen.v3.generators.java.SpringCodegen", date = "2020-01-17T18:34:48.841Z[GMT]")
public class LeaveRequestApprovalApiModel   {
  @JsonProperty("leaveRequest")
  private UUID leaveRequest = null;

  @JsonProperty("approver")
  private UUID approver = null;

  @JsonProperty("approved")
  private Boolean approved = null;

  public LeaveRequestApprovalApiModel leaveRequest(UUID leaveRequest) {
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

  public LeaveRequestApprovalApiModel approver(UUID approver) {
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

  public LeaveRequestApprovalApiModel approved(Boolean approved) {
    this.approved = approved;
    return this;
  }

  /**
   * Get approved
   * @return approved
  **/
  @ApiModelProperty(readOnly = true, value = "")
  
    public Boolean isApproved() {
    return approved;
  }

  public void setApproved(Boolean approved) {
    this.approved = approved;
  }


  @Override
  public boolean equals(java.lang.Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    LeaveRequestApprovalApiModel leaveRequestApprovalApiModel = (LeaveRequestApprovalApiModel) o;
    return Objects.equals(this.leaveRequest, leaveRequestApprovalApiModel.leaveRequest) &&
        Objects.equals(this.approver, leaveRequestApprovalApiModel.approver) &&
        Objects.equals(this.approved, leaveRequestApprovalApiModel.approved);
  }

  @Override
  public int hashCode() {
    return Objects.hash(leaveRequest, approver, approved);
  }

  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append("class LeaveRequestApprovalApiModel {\n");
    
    sb.append("    leaveRequest: ").append(toIndentedString(leaveRequest)).append("\n");
    sb.append("    approver: ").append(toIndentedString(approver)).append("\n");
    sb.append("    approved: ").append(toIndentedString(approved)).append("\n");
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

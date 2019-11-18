package com.evolveum.day_off_planner_rest_api.model;

import java.util.Objects;
import com.evolveum.day_off_planner_rest_api.model.LeaveRequestApiModel;
import com.evolveum.day_off_planner_rest_api.model.LeaveRequestApprovalApiModel;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonCreator;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import java.util.ArrayList;
import java.util.List;
import org.springframework.validation.annotation.Validated;
import javax.validation.Valid;
import javax.validation.constraints.*;

/**
 * LeaveRequestWithApprovalsApiModel
 */
@Validated
@javax.annotation.Generated(value = "io.swagger.codegen.v3.generators.java.SpringCodegen", date = "2019-11-18T07:49:35.675Z[GMT]")
public class LeaveRequestWithApprovalsApiModel   {
  @JsonProperty("leaveRequest")
  private LeaveRequestApiModel leaveRequest = null;

  @JsonProperty("approvals")
  @Valid
  private List<LeaveRequestApprovalApiModel> approvals = new ArrayList<>();

  public LeaveRequestWithApprovalsApiModel leaveRequest(LeaveRequestApiModel leaveRequest) {
    this.leaveRequest = leaveRequest;
    return this;
  }

  /**
   * Get leaveRequest
   * @return leaveRequest
  **/
  @ApiModelProperty(required = true, value = "")
      @NotNull

    @Valid
    public LeaveRequestApiModel getLeaveRequest() {
    return leaveRequest;
  }

  public void setLeaveRequest(LeaveRequestApiModel leaveRequest) {
    this.leaveRequest = leaveRequest;
  }

  public LeaveRequestWithApprovalsApiModel approvals(List<LeaveRequestApprovalApiModel> approvals) {
    this.approvals = approvals;
    return this;
  }

  public LeaveRequestWithApprovalsApiModel addApprovalsItem(LeaveRequestApprovalApiModel approvalsItem) {
    this.approvals.add(approvalsItem);
    return this;
  }

  /**
   * Get approvals
   * @return approvals
  **/
  @ApiModelProperty(required = true, readOnly = true, value = "")
      @NotNull
    @Valid
    public List<LeaveRequestApprovalApiModel> getApprovals() {
    return approvals;
  }

  public void setApprovals(List<LeaveRequestApprovalApiModel> approvals) {
    this.approvals = approvals;
  }


  @Override
  public boolean equals(java.lang.Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    LeaveRequestWithApprovalsApiModel leaveRequestWithApprovalsApiModel = (LeaveRequestWithApprovalsApiModel) o;
    return Objects.equals(this.leaveRequest, leaveRequestWithApprovalsApiModel.leaveRequest) &&
        Objects.equals(this.approvals, leaveRequestWithApprovalsApiModel.approvals);
  }

  @Override
  public int hashCode() {
    return Objects.hash(leaveRequest, approvals);
  }

  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append("class LeaveRequestWithApprovalsApiModel {\n");
    
    sb.append("    leaveRequest: ").append(toIndentedString(leaveRequest)).append("\n");
    sb.append("    approvals: ").append(toIndentedString(approvals)).append("\n");
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

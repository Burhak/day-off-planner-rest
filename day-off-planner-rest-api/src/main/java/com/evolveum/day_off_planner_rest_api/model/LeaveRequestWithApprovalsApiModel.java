package com.evolveum.day_off_planner_rest_api.model;

import java.util.Objects;
import com.evolveum.day_off_planner_rest_api.model.LeaveRequestApiModel;
import com.evolveum.day_off_planner_rest_api.model.LeaveRequestApprovalApiModel;
import com.evolveum.day_off_planner_rest_api.model.LeaveRequestMessageApiModel;
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
@javax.annotation.Generated(value = "io.swagger.codegen.v3.generators.java.SpringCodegen", date = "2020-01-17T18:34:48.841Z[GMT]")
public class LeaveRequestWithApprovalsApiModel   {
  @JsonProperty("leaveRequest")
  private LeaveRequestApiModel leaveRequest = null;

  @JsonProperty("approvals")
  @Valid
  private List<LeaveRequestApprovalApiModel> approvals = new ArrayList<>();

  @JsonProperty("messages")
  @Valid
  private List<LeaveRequestMessageApiModel> messages = new ArrayList<>();

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

  public LeaveRequestWithApprovalsApiModel messages(List<LeaveRequestMessageApiModel> messages) {
    this.messages = messages;
    return this;
  }

  public LeaveRequestWithApprovalsApiModel addMessagesItem(LeaveRequestMessageApiModel messagesItem) {
    this.messages.add(messagesItem);
    return this;
  }

  /**
   * Get messages
   * @return messages
  **/
  @ApiModelProperty(required = true, readOnly = true, value = "")
      @NotNull
    @Valid
    public List<LeaveRequestMessageApiModel> getMessages() {
    return messages;
  }

  public void setMessages(List<LeaveRequestMessageApiModel> messages) {
    this.messages = messages;
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
        Objects.equals(this.approvals, leaveRequestWithApprovalsApiModel.approvals) &&
        Objects.equals(this.messages, leaveRequestWithApprovalsApiModel.messages);
  }

  @Override
  public int hashCode() {
    return Objects.hash(leaveRequest, approvals, messages);
  }

  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append("class LeaveRequestWithApprovalsApiModel {\n");
    
    sb.append("    leaveRequest: ").append(toIndentedString(leaveRequest)).append("\n");
    sb.append("    approvals: ").append(toIndentedString(approvals)).append("\n");
    sb.append("    messages: ").append(toIndentedString(messages)).append("\n");
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

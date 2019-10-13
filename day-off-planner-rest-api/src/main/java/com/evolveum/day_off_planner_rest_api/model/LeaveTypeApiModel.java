package com.evolveum.day_off_planner_rest_api.model;

import java.util.Objects;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonCreator;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import org.springframework.validation.annotation.Validated;
import javax.validation.Valid;
import javax.validation.constraints.*;

/**
 * LeaveTypeApiModel
 */
@Validated
@javax.annotation.Generated(value = "io.swagger.codegen.v3.generators.java.SpringCodegen", date = "2019-10-13T17:14:27.870Z[GMT]")
public class LeaveTypeApiModel   {
  @JsonProperty("id")
  private Long id = null;

  @JsonProperty("name")
  private String name = null;

  @JsonProperty("approvalNeeded")
  private Boolean approvalNeeded = false;

  @JsonProperty("limited")
  private Boolean limited = false;

  @JsonProperty("halfDayAllowed")
  private Boolean halfDayAllowed = false;

  public LeaveTypeApiModel id(Long id) {
    this.id = id;
    return this;
  }

  /**
   * Get id
   * @return id
  **/
  @ApiModelProperty(required = true, value = "")
  @NotNull

  public Long getId() {
    return id;
  }

  public void setId(Long id) {
    this.id = id;
  }

  public LeaveTypeApiModel name(String name) {
    this.name = name;
    return this;
  }

  /**
   * Get name
   * @return name
  **/
  @ApiModelProperty(required = true, value = "")
  @NotNull

  public String getName() {
    return name;
  }

  public void setName(String name) {
    this.name = name;
  }

  public LeaveTypeApiModel approvalNeeded(Boolean approvalNeeded) {
    this.approvalNeeded = approvalNeeded;
    return this;
  }

  /**
   * Get approvalNeeded
   * @return approvalNeeded
  **/
  @ApiModelProperty(required = true, value = "")
  @NotNull

  public Boolean isApprovalNeeded() {
    return approvalNeeded;
  }

  public void setApprovalNeeded(Boolean approvalNeeded) {
    this.approvalNeeded = approvalNeeded;
  }

  public LeaveTypeApiModel limited(Boolean limited) {
    this.limited = limited;
    return this;
  }

  /**
   * Get limited
   * @return limited
  **/
  @ApiModelProperty(required = true, value = "")
  @NotNull

  public Boolean isLimited() {
    return limited;
  }

  public void setLimited(Boolean limited) {
    this.limited = limited;
  }

  public LeaveTypeApiModel halfDayAllowed(Boolean halfDayAllowed) {
    this.halfDayAllowed = halfDayAllowed;
    return this;
  }

  /**
   * Get halfDayAllowed
   * @return halfDayAllowed
  **/
  @ApiModelProperty(required = true, value = "")
  @NotNull

  public Boolean isHalfDayAllowed() {
    return halfDayAllowed;
  }

  public void setHalfDayAllowed(Boolean halfDayAllowed) {
    this.halfDayAllowed = halfDayAllowed;
  }


  @Override
  public boolean equals(java.lang.Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    LeaveTypeApiModel leaveTypeApiModel = (LeaveTypeApiModel) o;
    return Objects.equals(this.id, leaveTypeApiModel.id) &&
        Objects.equals(this.name, leaveTypeApiModel.name) &&
        Objects.equals(this.approvalNeeded, leaveTypeApiModel.approvalNeeded) &&
        Objects.equals(this.limited, leaveTypeApiModel.limited) &&
        Objects.equals(this.halfDayAllowed, leaveTypeApiModel.halfDayAllowed);
  }

  @Override
  public int hashCode() {
    return Objects.hash(id, name, approvalNeeded, limited, halfDayAllowed);
  }

  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append("class LeaveTypeApiModel {\n");
    
    sb.append("    id: ").append(toIndentedString(id)).append("\n");
    sb.append("    name: ").append(toIndentedString(name)).append("\n");
    sb.append("    approvalNeeded: ").append(toIndentedString(approvalNeeded)).append("\n");
    sb.append("    limited: ").append(toIndentedString(limited)).append("\n");
    sb.append("    halfDayAllowed: ").append(toIndentedString(halfDayAllowed)).append("\n");
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
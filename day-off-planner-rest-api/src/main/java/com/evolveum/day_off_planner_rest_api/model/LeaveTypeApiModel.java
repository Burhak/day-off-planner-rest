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
 * LeaveTypeApiModel
 */
@Validated
@javax.annotation.Generated(value = "io.swagger.codegen.v3.generators.java.SpringCodegen", date = "2019-12-17T13:24:29.081Z[GMT]")
public class LeaveTypeApiModel   {
  @JsonProperty("id")
  private UUID id = null;

  @JsonProperty("name")
  private String name = null;

  @JsonProperty("color")
  private String color = null;

  @JsonProperty("approvalNeeded")
  private Boolean approvalNeeded = false;

  @JsonProperty("limit")
  private Integer limit = null;

  @JsonProperty("carryover")
  private Integer carryover = null;

  public LeaveTypeApiModel id(UUID id) {
    this.id = id;
    return this;
  }

  /**
   * Get id
   * @return id
  **/
  @ApiModelProperty(required = true, readOnly = true, value = "")
      @NotNull

    @Valid
    public UUID getId() {
    return id;
  }

  public void setId(UUID id) {
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
  @ApiModelProperty(required = true, readOnly = true, value = "")
      @NotNull

    public String getName() {
    return name;
  }

  public void setName(String name) {
    this.name = name;
  }

  public LeaveTypeApiModel color(String color) {
    this.color = color;
    return this;
  }

  /**
   * Get color
   * @return color
  **/
  @ApiModelProperty(required = true, readOnly = true, value = "")
      @NotNull

    public String getColor() {
    return color;
  }

  public void setColor(String color) {
    this.color = color;
  }

  public LeaveTypeApiModel approvalNeeded(Boolean approvalNeeded) {
    this.approvalNeeded = approvalNeeded;
    return this;
  }

  /**
   * Get approvalNeeded
   * @return approvalNeeded
  **/
  @ApiModelProperty(required = true, readOnly = true, value = "")
      @NotNull

    public Boolean isApprovalNeeded() {
    return approvalNeeded;
  }

  public void setApprovalNeeded(Boolean approvalNeeded) {
    this.approvalNeeded = approvalNeeded;
  }

  public LeaveTypeApiModel limit(Integer limit) {
    this.limit = limit;
    return this;
  }

  /**
   * Get limit
   * @return limit
  **/
  @ApiModelProperty(readOnly = true, value = "")
  
    public Integer getLimit() {
    return limit;
  }

  public void setLimit(Integer limit) {
    this.limit = limit;
  }

  public LeaveTypeApiModel carryover(Integer carryover) {
    this.carryover = carryover;
    return this;
  }

  /**
   * Get carryover
   * @return carryover
  **/
  @ApiModelProperty(readOnly = true, value = "")
  
    public Integer getCarryover() {
    return carryover;
  }

  public void setCarryover(Integer carryover) {
    this.carryover = carryover;
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
        Objects.equals(this.color, leaveTypeApiModel.color) &&
        Objects.equals(this.approvalNeeded, leaveTypeApiModel.approvalNeeded) &&
        Objects.equals(this.limit, leaveTypeApiModel.limit) &&
        Objects.equals(this.carryover, leaveTypeApiModel.carryover);
  }

  @Override
  public int hashCode() {
    return Objects.hash(id, name, color, approvalNeeded, limit, carryover);
  }

  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append("class LeaveTypeApiModel {\n");
    
    sb.append("    id: ").append(toIndentedString(id)).append("\n");
    sb.append("    name: ").append(toIndentedString(name)).append("\n");
    sb.append("    color: ").append(toIndentedString(color)).append("\n");
    sb.append("    approvalNeeded: ").append(toIndentedString(approvalNeeded)).append("\n");
    sb.append("    limit: ").append(toIndentedString(limit)).append("\n");
    sb.append("    carryover: ").append(toIndentedString(carryover)).append("\n");
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

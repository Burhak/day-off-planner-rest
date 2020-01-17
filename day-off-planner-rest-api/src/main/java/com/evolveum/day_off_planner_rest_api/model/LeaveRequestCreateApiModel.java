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
 * LeaveRequestCreateApiModel
 */
@Validated
@javax.annotation.Generated(value = "io.swagger.codegen.v3.generators.java.SpringCodegen", date = "2020-01-17T18:34:48.841Z[GMT]")
public class LeaveRequestCreateApiModel   {
  @JsonProperty("leaveType")
  private UUID leaveType = null;

  @JsonProperty("fromDate")
  private LocalDateTime fromDate = null;

  @JsonProperty("toDate")
  private LocalDateTime toDate = null;

  public LeaveRequestCreateApiModel leaveType(UUID leaveType) {
    this.leaveType = leaveType;
    return this;
  }

  /**
   * Get leaveType
   * @return leaveType
  **/
  @ApiModelProperty(required = true, value = "")
      @NotNull

    @Valid
    public UUID getLeaveType() {
    return leaveType;
  }

  public void setLeaveType(UUID leaveType) {
    this.leaveType = leaveType;
  }

  public LeaveRequestCreateApiModel fromDate(LocalDateTime fromDate) {
    this.fromDate = fromDate;
    return this;
  }

  /**
   * Get fromDate
   * @return fromDate
  **/
  @ApiModelProperty(required = true, value = "")
      @NotNull

    @Valid
    public LocalDateTime getFromDate() {
    return fromDate;
  }

  public void setFromDate(LocalDateTime fromDate) {
    this.fromDate = fromDate;
  }

  public LeaveRequestCreateApiModel toDate(LocalDateTime toDate) {
    this.toDate = toDate;
    return this;
  }

  /**
   * Get toDate
   * @return toDate
  **/
  @ApiModelProperty(required = true, value = "")
      @NotNull

    @Valid
    public LocalDateTime getToDate() {
    return toDate;
  }

  public void setToDate(LocalDateTime toDate) {
    this.toDate = toDate;
  }


  @Override
  public boolean equals(java.lang.Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    LeaveRequestCreateApiModel leaveRequestCreateApiModel = (LeaveRequestCreateApiModel) o;
    return Objects.equals(this.leaveType, leaveRequestCreateApiModel.leaveType) &&
        Objects.equals(this.fromDate, leaveRequestCreateApiModel.fromDate) &&
        Objects.equals(this.toDate, leaveRequestCreateApiModel.toDate);
  }

  @Override
  public int hashCode() {
    return Objects.hash(leaveType, fromDate, toDate);
  }

  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append("class LeaveRequestCreateApiModel {\n");
    
    sb.append("    leaveType: ").append(toIndentedString(leaveType)).append("\n");
    sb.append("    fromDate: ").append(toIndentedString(fromDate)).append("\n");
    sb.append("    toDate: ").append(toIndentedString(toDate)).append("\n");
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

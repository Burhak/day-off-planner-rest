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
 * RequestedHoursApiModel
 */
@Validated
@javax.annotation.Generated(value = "io.swagger.codegen.v3.generators.java.SpringCodegen", date = "2019-11-18T08:11:22.026Z[GMT]")
public class RequestedHoursApiModel   {
  @JsonProperty("user")
  private UUID user = null;

  @JsonProperty("leaveType")
  private UUID leaveType = null;

  @JsonProperty("year")
  private Integer year = null;

  @JsonProperty("requestedHours")
  private Integer requestedHours = null;

  public RequestedHoursApiModel user(UUID user) {
    this.user = user;
    return this;
  }

  /**
   * Get user
   * @return user
  **/
  @ApiModelProperty(required = true, readOnly = true, value = "")
      @NotNull

    @Valid
    public UUID getUser() {
    return user;
  }

  public void setUser(UUID user) {
    this.user = user;
  }

  public RequestedHoursApiModel leaveType(UUID leaveType) {
    this.leaveType = leaveType;
    return this;
  }

  /**
   * Get leaveType
   * @return leaveType
  **/
  @ApiModelProperty(required = true, readOnly = true, value = "")
      @NotNull

    @Valid
    public UUID getLeaveType() {
    return leaveType;
  }

  public void setLeaveType(UUID leaveType) {
    this.leaveType = leaveType;
  }

  public RequestedHoursApiModel year(Integer year) {
    this.year = year;
    return this;
  }

  /**
   * Get year
   * @return year
  **/
  @ApiModelProperty(required = true, readOnly = true, value = "")
      @NotNull

    public Integer getYear() {
    return year;
  }

  public void setYear(Integer year) {
    this.year = year;
  }

  public RequestedHoursApiModel requestedHours(Integer requestedHours) {
    this.requestedHours = requestedHours;
    return this;
  }

  /**
   * Get requestedHours
   * @return requestedHours
  **/
  @ApiModelProperty(required = true, readOnly = true, value = "")
      @NotNull

    public Integer getRequestedHours() {
    return requestedHours;
  }

  public void setRequestedHours(Integer requestedHours) {
    this.requestedHours = requestedHours;
  }


  @Override
  public boolean equals(java.lang.Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    RequestedHoursApiModel requestedHoursApiModel = (RequestedHoursApiModel) o;
    return Objects.equals(this.user, requestedHoursApiModel.user) &&
        Objects.equals(this.leaveType, requestedHoursApiModel.leaveType) &&
        Objects.equals(this.year, requestedHoursApiModel.year) &&
        Objects.equals(this.requestedHours, requestedHoursApiModel.requestedHours);
  }

  @Override
  public int hashCode() {
    return Objects.hash(user, leaveType, year, requestedHours);
  }

  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append("class RequestedHoursApiModel {\n");
    
    sb.append("    user: ").append(toIndentedString(user)).append("\n");
    sb.append("    leaveType: ").append(toIndentedString(leaveType)).append("\n");
    sb.append("    year: ").append(toIndentedString(year)).append("\n");
    sb.append("    requestedHours: ").append(toIndentedString(requestedHours)).append("\n");
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

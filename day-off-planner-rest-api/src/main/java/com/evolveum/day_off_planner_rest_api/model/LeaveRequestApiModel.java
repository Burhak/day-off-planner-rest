package com.evolveum.day_off_planner_rest_api.model;

import java.util.Objects;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonValue;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import java.time.LocalDateTime;
import java.util.UUID;
import org.springframework.validation.annotation.Validated;
import javax.validation.Valid;
import javax.validation.constraints.*;

/**
 * LeaveRequestApiModel
 */
@Validated
@javax.annotation.Generated(value = "io.swagger.codegen.v3.generators.java.SpringCodegen", date = "2019-11-19T16:35:41.943Z[GMT]")
public class LeaveRequestApiModel   {
  @JsonProperty("id")
  private UUID id = null;

  @JsonProperty("leaveType")
  private UUID leaveType = null;

  @JsonProperty("user")
  private UUID user = null;

  @JsonProperty("fromDate")
  private LocalDateTime fromDate = null;

  @JsonProperty("toDate")
  private LocalDateTime toDate = null;

  /**
   * Gets or Sets status
   */
  public enum StatusEnum {
    APPROVED("APPROVED"),
    
    REJECTED("REJECTED"),
    
    PENDING("PENDING"),
    
    CANCELLED("CANCELLED");

    private String value;

    StatusEnum(String value) {
      this.value = value;
    }

    @Override
    @JsonValue
    public String toString() {
      return String.valueOf(value);
    }

    @JsonCreator
    public static StatusEnum fromValue(String text) {
      for (StatusEnum b : StatusEnum.values()) {
        if (String.valueOf(b.value).equals(text)) {
          return b;
        }
      }
      return null;
    }
  }
  @JsonProperty("status")
  private StatusEnum status = null;

  public LeaveRequestApiModel id(UUID id) {
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

  public LeaveRequestApiModel leaveType(UUID leaveType) {
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

  public LeaveRequestApiModel user(UUID user) {
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

  public LeaveRequestApiModel fromDate(LocalDateTime fromDate) {
    this.fromDate = fromDate;
    return this;
  }

  /**
   * Get fromDate
   * @return fromDate
  **/
  @ApiModelProperty(required = true, readOnly = true, value = "")
      @NotNull

    @Valid
    public LocalDateTime getFromDate() {
    return fromDate;
  }

  public void setFromDate(LocalDateTime fromDate) {
    this.fromDate = fromDate;
  }

  public LeaveRequestApiModel toDate(LocalDateTime toDate) {
    this.toDate = toDate;
    return this;
  }

  /**
   * Get toDate
   * @return toDate
  **/
  @ApiModelProperty(required = true, readOnly = true, value = "")
      @NotNull

    @Valid
    public LocalDateTime getToDate() {
    return toDate;
  }

  public void setToDate(LocalDateTime toDate) {
    this.toDate = toDate;
  }

  public LeaveRequestApiModel status(StatusEnum status) {
    this.status = status;
    return this;
  }

  /**
   * Get status
   * @return status
  **/
  @ApiModelProperty(required = true, readOnly = true, value = "")
      @NotNull

    public StatusEnum getStatus() {
    return status;
  }

  public void setStatus(StatusEnum status) {
    this.status = status;
  }


  @Override
  public boolean equals(java.lang.Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    LeaveRequestApiModel leaveRequestApiModel = (LeaveRequestApiModel) o;
    return Objects.equals(this.id, leaveRequestApiModel.id) &&
        Objects.equals(this.leaveType, leaveRequestApiModel.leaveType) &&
        Objects.equals(this.user, leaveRequestApiModel.user) &&
        Objects.equals(this.fromDate, leaveRequestApiModel.fromDate) &&
        Objects.equals(this.toDate, leaveRequestApiModel.toDate) &&
        Objects.equals(this.status, leaveRequestApiModel.status);
  }

  @Override
  public int hashCode() {
    return Objects.hash(id, leaveType, user, fromDate, toDate, status);
  }

  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append("class LeaveRequestApiModel {\n");
    
    sb.append("    id: ").append(toIndentedString(id)).append("\n");
    sb.append("    leaveType: ").append(toIndentedString(leaveType)).append("\n");
    sb.append("    user: ").append(toIndentedString(user)).append("\n");
    sb.append("    fromDate: ").append(toIndentedString(fromDate)).append("\n");
    sb.append("    toDate: ").append(toIndentedString(toDate)).append("\n");
    sb.append("    status: ").append(toIndentedString(status)).append("\n");
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

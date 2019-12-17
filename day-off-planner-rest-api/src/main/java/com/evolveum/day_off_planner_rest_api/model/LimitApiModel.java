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
 * LimitApiModel
 */
@Validated
@javax.annotation.Generated(value = "io.swagger.codegen.v3.generators.java.SpringCodegen", date = "2019-12-17T13:24:29.081Z[GMT]")
public class LimitApiModel   {
  @JsonProperty("user")
  private UUID user = null;

  @JsonProperty("leaveType")
  private UUID leaveType = null;

  @JsonProperty("limit")
  private Integer limit = null;

  public LimitApiModel user(UUID user) {
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

  public LimitApiModel leaveType(UUID leaveType) {
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

  public LimitApiModel limit(Integer limit) {
    this.limit = limit;
    return this;
  }

  /**
   * Get limit
   * @return limit
  **/
  @ApiModelProperty(required = true, readOnly = true, value = "")
      @NotNull

    public Integer getLimit() {
    return limit;
  }

  public void setLimit(Integer limit) {
    this.limit = limit;
  }


  @Override
  public boolean equals(java.lang.Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    LimitApiModel limitApiModel = (LimitApiModel) o;
    return Objects.equals(this.user, limitApiModel.user) &&
        Objects.equals(this.leaveType, limitApiModel.leaveType) &&
        Objects.equals(this.limit, limitApiModel.limit);
  }

  @Override
  public int hashCode() {
    return Objects.hash(user, leaveType, limit);
  }

  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append("class LimitApiModel {\n");
    
    sb.append("    user: ").append(toIndentedString(user)).append("\n");
    sb.append("    leaveType: ").append(toIndentedString(leaveType)).append("\n");
    sb.append("    limit: ").append(toIndentedString(limit)).append("\n");
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

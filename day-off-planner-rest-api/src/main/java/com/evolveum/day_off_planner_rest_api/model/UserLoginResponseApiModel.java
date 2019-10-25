package com.evolveum.day_off_planner_rest_api.model;

import java.util.Objects;
import com.evolveum.day_off_planner_rest_api.model.UserApiModel;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonCreator;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import java.time.LocalDateTime;
import org.springframework.validation.annotation.Validated;
import javax.validation.Valid;
import javax.validation.constraints.*;

/**
 * UserLoginResponseApiModel
 */
@Validated
@javax.annotation.Generated(value = "io.swagger.codegen.v3.generators.java.SpringCodegen", date = "2019-10-25T08:08:46.968Z[GMT]")
public class UserLoginResponseApiModel   {
  @JsonProperty("token")
  private String token = null;

  @JsonProperty("expiresAt")
  private LocalDateTime expiresAt = null;

  @JsonProperty("tokenType")
  private String tokenType = null;

  @JsonProperty("user")
  private UserApiModel user = null;

  public UserLoginResponseApiModel token(String token) {
    this.token = token;
    return this;
  }

  /**
   * Get token
   * @return token
  **/
  @ApiModelProperty(required = true, readOnly = true, value = "")
  @NotNull

  public String getToken() {
    return token;
  }

  public void setToken(String token) {
    this.token = token;
  }

  public UserLoginResponseApiModel expiresAt(LocalDateTime expiresAt) {
    this.expiresAt = expiresAt;
    return this;
  }

  /**
   * Get expiresAt
   * @return expiresAt
  **/
  @ApiModelProperty(required = true, readOnly = true, value = "")
  @NotNull

  @Valid
  public LocalDateTime getExpiresAt() {
    return expiresAt;
  }

  public void setExpiresAt(LocalDateTime expiresAt) {
    this.expiresAt = expiresAt;
  }

  public UserLoginResponseApiModel tokenType(String tokenType) {
    this.tokenType = tokenType;
    return this;
  }

  /**
   * Get tokenType
   * @return tokenType
  **/
  @ApiModelProperty(required = true, readOnly = true, value = "")
  @NotNull

  public String getTokenType() {
    return tokenType;
  }

  public void setTokenType(String tokenType) {
    this.tokenType = tokenType;
  }

  public UserLoginResponseApiModel user(UserApiModel user) {
    this.user = user;
    return this;
  }

  /**
   * Get user
   * @return user
  **/
  @ApiModelProperty(required = true, value = "")
  @NotNull

  @Valid
  public UserApiModel getUser() {
    return user;
  }

  public void setUser(UserApiModel user) {
    this.user = user;
  }


  @Override
  public boolean equals(java.lang.Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    UserLoginResponseApiModel userLoginResponseApiModel = (UserLoginResponseApiModel) o;
    return Objects.equals(this.token, userLoginResponseApiModel.token) &&
        Objects.equals(this.expiresAt, userLoginResponseApiModel.expiresAt) &&
        Objects.equals(this.tokenType, userLoginResponseApiModel.tokenType) &&
        Objects.equals(this.user, userLoginResponseApiModel.user);
  }

  @Override
  public int hashCode() {
    return Objects.hash(token, expiresAt, tokenType, user);
  }

  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append("class UserLoginResponseApiModel {\n");
    
    sb.append("    token: ").append(toIndentedString(token)).append("\n");
    sb.append("    expiresAt: ").append(toIndentedString(expiresAt)).append("\n");
    sb.append("    tokenType: ").append(toIndentedString(tokenType)).append("\n");
    sb.append("    user: ").append(toIndentedString(user)).append("\n");
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

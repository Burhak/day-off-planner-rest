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
 * UserCreateApiModel
 */
@Validated
@javax.annotation.Generated(value = "io.swagger.codegen.v3.generators.java.SpringCodegen", date = "2019-11-11T13:37:50.153Z[GMT]")
public class UserCreateApiModel   {
  @JsonProperty("firstName")
  private String firstName = null;

  @JsonProperty("lastName")
  private String lastName = null;

  @JsonProperty("email")
  private String email = null;

  @JsonProperty("supervisor")
  private UUID supervisor = null;

  @JsonProperty("admin")
  private Boolean admin = false;

  @JsonProperty("jobDescription")
  private String jobDescription = null;

  @JsonProperty("phone")
  private String phone = null;

  public UserCreateApiModel firstName(String firstName) {
    this.firstName = firstName;
    return this;
  }

  /**
   * Get firstName
   * @return firstName
  **/
  @ApiModelProperty(required = true, value = "")
      @NotNull

    public String getFirstName() {
    return firstName;
  }

  public void setFirstName(String firstName) {
    this.firstName = firstName;
  }

  public UserCreateApiModel lastName(String lastName) {
    this.lastName = lastName;
    return this;
  }

  /**
   * Get lastName
   * @return lastName
  **/
  @ApiModelProperty(required = true, value = "")
      @NotNull

    public String getLastName() {
    return lastName;
  }

  public void setLastName(String lastName) {
    this.lastName = lastName;
  }

  public UserCreateApiModel email(String email) {
    this.email = email;
    return this;
  }

  /**
   * Get email
   * @return email
  **/
  @ApiModelProperty(required = true, value = "")
      @NotNull

    public String getEmail() {
    return email;
  }

  public void setEmail(String email) {
    this.email = email;
  }

  public UserCreateApiModel supervisor(UUID supervisor) {
    this.supervisor = supervisor;
    return this;
  }

  /**
   * Get supervisor
   * @return supervisor
  **/
  @ApiModelProperty(value = "")
  
    @Valid
    public UUID getSupervisor() {
    return supervisor;
  }

  public void setSupervisor(UUID supervisor) {
    this.supervisor = supervisor;
  }

  public UserCreateApiModel admin(Boolean admin) {
    this.admin = admin;
    return this;
  }

  /**
   * Get admin
   * @return admin
  **/
  @ApiModelProperty(required = true, value = "")
      @NotNull

    public Boolean isAdmin() {
    return admin;
  }

  public void setAdmin(Boolean admin) {
    this.admin = admin;
  }

  public UserCreateApiModel jobDescription(String jobDescription) {
    this.jobDescription = jobDescription;
    return this;
  }

  /**
   * Get jobDescription
   * @return jobDescription
  **/
  @ApiModelProperty(required = true, value = "")
      @NotNull

    public String getJobDescription() {
    return jobDescription;
  }

  public void setJobDescription(String jobDescription) {
    this.jobDescription = jobDescription;
  }

  public UserCreateApiModel phone(String phone) {
    this.phone = phone;
    return this;
  }

  /**
   * Get phone
   * @return phone
  **/
  @ApiModelProperty(value = "")
  
    public String getPhone() {
    return phone;
  }

  public void setPhone(String phone) {
    this.phone = phone;
  }


  @Override
  public boolean equals(java.lang.Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    UserCreateApiModel userCreateApiModel = (UserCreateApiModel) o;
    return Objects.equals(this.firstName, userCreateApiModel.firstName) &&
        Objects.equals(this.lastName, userCreateApiModel.lastName) &&
        Objects.equals(this.email, userCreateApiModel.email) &&
        Objects.equals(this.supervisor, userCreateApiModel.supervisor) &&
        Objects.equals(this.admin, userCreateApiModel.admin) &&
        Objects.equals(this.jobDescription, userCreateApiModel.jobDescription) &&
        Objects.equals(this.phone, userCreateApiModel.phone);
  }

  @Override
  public int hashCode() {
    return Objects.hash(firstName, lastName, email, supervisor, admin, jobDescription, phone);
  }

  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append("class UserCreateApiModel {\n");
    
    sb.append("    firstName: ").append(toIndentedString(firstName)).append("\n");
    sb.append("    lastName: ").append(toIndentedString(lastName)).append("\n");
    sb.append("    email: ").append(toIndentedString(email)).append("\n");
    sb.append("    supervisor: ").append(toIndentedString(supervisor)).append("\n");
    sb.append("    admin: ").append(toIndentedString(admin)).append("\n");
    sb.append("    jobDescription: ").append(toIndentedString(jobDescription)).append("\n");
    sb.append("    phone: ").append(toIndentedString(phone)).append("\n");
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

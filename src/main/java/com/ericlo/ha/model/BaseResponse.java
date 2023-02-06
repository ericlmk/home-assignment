package com.ericlo.ha.model;

import java.util.Objects;
import com.fasterxml.jackson.annotation.JsonProperty;
import io.swagger.v3.oas.annotations.media.Schema;
import org.springframework.validation.annotation.Validated;

/**
 * BaseResponse
 */
@Validated
@javax.annotation.Generated(value = "io.swagger.codegen.v3.generators.java.SpringCodegen", date = "2023-02-05T17:40:00.126024615Z[GMT]")


public class BaseResponse   {
  @JsonProperty("code")
  private String code = null;

  @JsonProperty("message")
  private String message = null;

  public BaseResponse code(String code) {
    this.code = code;
    return this;
  }

  /**
   * The result code
   * @return code
   **/
  @Schema(description = "The result code")
  
    public String getCode() {
    return code;
  }

  public void setCode(String code) {
    this.code = code;
  }

  public BaseResponse message(String message) {
    this.message = message;
    return this;
  }

  /**
   * The description for the result
   * @return message
   **/
  @Schema(description = "The description for the result")
  
    public String getMessage() {
    return message;
  }

  public void setMessage(String message) {
    this.message = message;
  }


  @Override
  public boolean equals(java.lang.Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    BaseResponse baseResponse = (BaseResponse) o;
    return Objects.equals(this.code, baseResponse.code) &&
        Objects.equals(this.message, baseResponse.message);
  }

  @Override
  public int hashCode() {
    return Objects.hash(code, message);
  }

  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append("class BaseResponse {\n");
    
    sb.append("    code: ").append(toIndentedString(code)).append("\n");
    sb.append("    message: ").append(toIndentedString(message)).append("\n");
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

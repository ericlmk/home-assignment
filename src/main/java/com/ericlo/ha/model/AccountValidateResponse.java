package com.ericlo.ha.model;

import java.util.Objects;
import com.fasterxml.jackson.annotation.JsonProperty;
import io.swagger.v3.oas.annotations.media.Schema;
import java.util.ArrayList;
import java.util.List;
import org.springframework.validation.annotation.Validated;
import javax.validation.Valid;

/**
 * AccountValidateResponse
 */
@Validated
@javax.annotation.Generated(value = "io.swagger.codegen.v3.generators.java.SpringCodegen", date = "2023-02-05T18:24:36.296183089Z[GMT]")


public class AccountValidateResponse   {
  @JsonProperty("result")
  @Valid
  private List<ProviderValidateResult> result = null;

  public AccountValidateResponse result(List<ProviderValidateResult> result) {
    this.result = result;
    return this;
  }

  public AccountValidateResponse addResultItem(ProviderValidateResult resultItem) {
    if (this.result == null) {
      this.result = new ArrayList<ProviderValidateResult>();
    }
    this.result.add(resultItem);
    return this;
  }

  /**
   * Get result
   * @return result
   **/
  @Schema(description = "")
      @Valid
    public List<ProviderValidateResult> getResult() {
    return result;
  }

  public void setResult(List<ProviderValidateResult> result) {
    this.result = result;
  }


  @Override
  public boolean equals(java.lang.Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    AccountValidateResponse accountValidateResponse = (AccountValidateResponse) o;
    return Objects.equals(this.result, accountValidateResponse.result);
  }

  @Override
  public int hashCode() {
    return Objects.hash(result);
  }

  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append("class AccountValidateResponse {\n");
    
    sb.append("    result: ").append(toIndentedString(result)).append("\n");
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

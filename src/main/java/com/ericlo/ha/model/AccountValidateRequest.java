package com.ericlo.ha.model;

import java.util.Objects;
import com.fasterxml.jackson.annotation.JsonProperty;
import io.swagger.v3.oas.annotations.media.Schema;
import java.util.ArrayList;
import java.util.List;
import org.springframework.validation.annotation.Validated;
import javax.validation.Valid;
import javax.validation.constraints.*;

/**
 * AccountValidateRequest
 */
@Validated
@javax.annotation.Generated(value = "io.swagger.codegen.v3.generators.java.SpringCodegen", date = "2023-02-05T17:40:00.126024615Z[GMT]")


public class AccountValidateRequest   {
  @JsonProperty("accountNumber")
  private String accountNumber = null;

  @JsonProperty("providers")
  @Valid
  private List<String> providers = null;

  public AccountValidateRequest accountNumber(String accountNumber) {
    this.accountNumber = accountNumber;
    return this;
  }

  /**
   * Get accountNumber
   * @return accountNumber
   **/
  @Schema(example = "12345678", description = "")
  
  @Size(min=1)   public String getAccountNumber() {
    return accountNumber;
  }

  public void setAccountNumber(String accountNumber) {
    this.accountNumber = accountNumber;
  }

  public AccountValidateRequest providers(List<String> providers) {
    this.providers = providers;
    return this;
  }

  public AccountValidateRequest addProvidersItem(String providersItem) {
    if (this.providers == null) {
      this.providers = new ArrayList<String>();
    }
    this.providers.add(providersItem);
    return this;
  }

  /**
   * Get providers
   * @return providers
   **/
  @Schema(description = "")
  
    public List<String> getProviders() {
    return providers;
  }

  public void setProviders(List<String> providers) {
    this.providers = providers;
  }


  @Override
  public boolean equals(java.lang.Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    AccountValidateRequest accountValidateRequest = (AccountValidateRequest) o;
    return Objects.equals(this.accountNumber, accountValidateRequest.accountNumber) &&
        Objects.equals(this.providers, accountValidateRequest.providers);
  }

  @Override
  public int hashCode() {
    return Objects.hash(accountNumber, providers);
  }

  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append("class AccountValidateRequest {\n");
    
    sb.append("    accountNumber: ").append(toIndentedString(accountNumber)).append("\n");
    sb.append("    providers: ").append(toIndentedString(providers)).append("\n");
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

package com.ericlo.ha.model;

import java.util.Objects;
import com.fasterxml.jackson.annotation.JsonProperty;
import io.swagger.v3.oas.annotations.media.Schema;
import org.springframework.validation.annotation.Validated;

import javax.validation.constraints.*;

/**
 * ProviderValidResult
 */
@Validated
@javax.annotation.Generated(value = "io.swagger.codegen.v3.generators.java.SpringCodegen", date = "2023-02-05T18:24:36.296183089Z[GMT]")


public class ProviderValidateResult {
  @JsonProperty("provider")
  private String provider = null;

  @JsonProperty("isValid")
  private Boolean isValid = null;

  public ProviderValidateResult provider(String provider) {
    this.provider = provider;
    return this;
  }

  /**
   * Get provider
   * @return provider
   **/
  @Schema(example = "provider1", description = "")
  
  @Size(min=1)   public String getProvider() {
    return provider;
  }

  public void setProvider(String provider) {
    this.provider = provider;
  }

  public ProviderValidateResult isValid(Boolean isValid) {
    this.isValid = isValid;
    return this;
  }

  /**
   * Get isValid
   * @return isValid
   **/
  @Schema(description = "")
  
    public Boolean isIsValid() {
    return isValid;
  }

  public void setIsValid(Boolean isValid) {
    this.isValid = isValid;
  }


  @Override
  public boolean equals(java.lang.Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    ProviderValidateResult providerValidateResult = (ProviderValidateResult) o;
    return Objects.equals(this.provider, providerValidateResult.provider) &&
        Objects.equals(this.isValid, providerValidateResult.isValid);
  }

  @Override
  public int hashCode() {
    return Objects.hash(provider, isValid);
  }

  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append("class ProviderValidResult {\n");
    
    sb.append("    provider: ").append(toIndentedString(provider)).append("\n");
    sb.append("    isValid: ").append(toIndentedString(isValid)).append("\n");
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

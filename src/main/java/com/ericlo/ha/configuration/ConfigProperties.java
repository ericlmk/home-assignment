package com.ericlo.ha.configuration;

import com.ericlo.ha.model.ProviderConfig;
import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@ConfigurationProperties(prefix = "config")
@Data
public class ConfigProperties {

  private List<ProviderConfig> providers;

  private Integer requestMaxTime;
  private Integer providerMaxTime;

  private static Map<String, String> providerUrlMap = null;
  public synchronized Map<String, String> getProviderUrlMap() {
    if (providerUrlMap==null && this.getProviders()!=null) {
      providerUrlMap = this.getProviders().stream().collect(Collectors.toMap(ProviderConfig::getName, ProviderConfig::getUrl));
    }
    return providerUrlMap;
  }
}

package com.ericlo.ha.service;

import com.ericlo.ha.configuration.ConfigProperties;
import com.ericlo.ha.service.MockProviderService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.HashMap;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.when;


@ExtendWith(MockitoExtension.class)
public class ProviderServiceTest {

  @Mock
  private ConfigProperties configProperties;

  @InjectMocks
  private MockProviderService mockProviderService;

  @BeforeEach
  public void setup() {
    // Mock configProperties
    Map<String, String> providerUrlMap = new HashMap<>();
    providerUrlMap.put("provider1", "http://sampleurl");
    when(configProperties.getProviderUrlMap()).thenReturn(providerUrlMap);
    when(configProperties.getProviderMaxTime()).thenReturn(1000);
  }

  @Test
  public void testValidAccount() {
    boolean result = mockProviderService.validateAccountNumber("provider1", "12345678");
    assertTrue(true==result);
  }

  @Test
  public void testInvalidAccount() {
    boolean result = mockProviderService.validateAccountNumber("provider1", "12345679");
    assertTrue(false==result);
  }

}

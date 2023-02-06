package com.ericlo.ha.api;

import com.ericlo.ha.api.AccountApiController;
import com.ericlo.ha.configuration.ConfigProperties;
import com.ericlo.ha.model.AccountValidateRequest;
import com.ericlo.ha.model.AccountValidateResponse;
import com.ericlo.ha.service.IProviderService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;


@ExtendWith(MockitoExtension.class)
public class AccountApiTest {

  @Mock
  private ConfigProperties configProperties;
  @Mock
  private IProviderService providerService;

  @InjectMocks
  AccountApiController accountApiController;

  @BeforeEach
  public void setup() {
  }

  @Test
  public void testValidAccount() {
    when(configProperties.getRequestMaxTime()).thenReturn(2000);
    when(providerService.validateAccountNumber(any(String.class), any(String.class))).thenReturn(true);
    AccountValidateRequest avRequest = new AccountValidateRequest();
    avRequest.setProviders(Arrays.asList("provider1"));
    avRequest.setAccountNumber("12345678");
    ResponseEntity<AccountValidateResponse> res = (ResponseEntity<AccountValidateResponse>) accountApiController.accountValidatePost(avRequest);
    assertThat(res.getStatusCodeValue()).isEqualTo(HttpStatus.OK.value());
    assertThat(res.getBody().getResult().size()).isEqualTo(1);
    assertThat(res.getBody().getResult().get(0).getProvider()).isEqualTo("provider1");
    assertThat(res.getBody().getResult().get(0).isIsValid()).isEqualTo(true);
  }

  @Test
  public void testValidAccountWithoutProvider() {
    Map<String, String> providerUrlMap = new HashMap<>();
    providerUrlMap.put("provider1", "http://sampleurl1");
    providerUrlMap.put("provider2", "http://sampleurl2");
    when(configProperties.getProviderUrlMap()).thenReturn(providerUrlMap);
    when(configProperties.getRequestMaxTime()).thenReturn(2000);
    when(providerService.validateAccountNumber(any(String.class), any(String.class))).thenReturn(true);
    AccountValidateRequest avRequest = new AccountValidateRequest();
    avRequest.setProviders(Arrays.asList());
    avRequest.setAccountNumber("12345678");
    ResponseEntity<AccountValidateResponse> res = (ResponseEntity<AccountValidateResponse>) accountApiController.accountValidatePost(avRequest);
    assertThat(res.getStatusCodeValue()).isEqualTo(HttpStatus.OK.value());
    assertThat(res.getBody().getResult().size()).isEqualTo(2);
  }

  @Test
  public void testEmptyRequest() {
    when(configProperties.getRequestMaxTime()).thenReturn(2000);
    AccountValidateRequest avRequest = new AccountValidateRequest();
    ResponseEntity<AccountValidateResponse> res = (ResponseEntity<AccountValidateResponse>) accountApiController.accountValidatePost(avRequest);
    assertThat(res.getStatusCodeValue()).isEqualTo(HttpStatus.BAD_REQUEST.value());
  }


}

package com.ericlo.ha.service;

import org.springframework.stereotype.Service;

@Service
public interface IProviderService {

  /**
   * Validate Account Number
   * @param providerName
   * @param accountNumber
   * @return
   */
  public boolean validateAccountNumber(String providerName, String accountNumber);

}

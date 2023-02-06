package com.ericlo.ha.service;

import com.ericlo.ha.configuration.ConfigProperties;
import com.ericlo.ha.util.TimeoutUtil;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.concurrent.*;

@Service
public class MockProviderService implements IProviderService {

  private static final Logger log = LoggerFactory.getLogger(MockProviderService.class);

  @Autowired
  private ConfigProperties configProperties;

  public boolean validateAccountNumber(String providerName, String accountNumber) {
    String url = this.configProperties.getProviderUrlMap().get(providerName);
    if (StringUtils.isNotBlank(url)) {
      return this.validateAccountNumberHandler(url, providerName, accountNumber);
    } else {
      // If provider doesn't exist in config, return false
      return false;
    }
  }

  /**
   * Handle the REST call to provider for account number validation
   * @param url
   * @param providerName
   * @param accountNumber
   * @return boolean result
   */
  private boolean validateAccountNumberHandler(String url, String providerName, String accountNumber) {

    Callable<Boolean> task = () -> {
      try {
        // Call Mock after 500-1000ms
        TimeUnit.MILLISECONDS.sleep(500 + (int) Math.floor( Math.random()*500 ));
        return this.validateAccountNumberMockData(providerName, accountNumber);
      } catch (InterruptedException e) {
        return false;
      }
    };

    return TimeoutUtil.run(task, this.configProperties.getProviderMaxTime());
  }

  /**
   * To mock the response from Provider
   * @param providerName
   * @param accountNumber
   * @return boolean result
   */
  private boolean validateAccountNumberMockData(String providerName, String accountNumber) {
    if ( ("provider1".equals(providerName) && "12345678".equals(accountNumber))
        || ("provider2".equals(providerName) && "12345679".equals(accountNumber)) ) {
      return true;
    }
    return false;
  }

}

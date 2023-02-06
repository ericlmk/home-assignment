package com.ericlo.ha.util;


import org.apache.commons.lang3.StringUtils;

import java.util.List;

public class AccountValidateUtil {

  public static boolean isValidFormat(String accountNumber) {
    if (StringUtils.isBlank(accountNumber)) {
      // Blank Account Number
      return false;
    }
    return true;
  }

  public static boolean isValidProvider(String provider) {
    if (StringUtils.isBlank(provider)) {
      // Blank Provider
      return false;
    }
    return true;
  }

  public static boolean isValidProviderList(List<String> providerList) {
    if (providerList==null || providerList.size()==0) {
      // Empty Provider List
      return true;
    } else {
      for (String provider: providerList) {
        if (isValidProvider(provider)==false) {
          return false;
        }
      }
    }
    return true;
  }

}

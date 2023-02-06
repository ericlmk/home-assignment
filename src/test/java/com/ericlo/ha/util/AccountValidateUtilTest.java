package com.ericlo.ha.util;

import org.junit.jupiter.api.Test;

import java.util.Arrays;

import static org.junit.jupiter.api.Assertions.assertTrue;

public class AccountValidateUtilTest {

  @Test
  public void testIsValidFormat() {
    assertTrue(false==AccountValidateUtil.isValidFormat(null));
    assertTrue(false==AccountValidateUtil.isValidFormat(""));
    assertTrue(false==AccountValidateUtil.isValidFormat("  "));
    assertTrue(true==AccountValidateUtil.isValidFormat("123"));
  }

  @Test
  public void testIsValidProvider() {
    assertTrue(false==AccountValidateUtil.isValidProvider(null));
    assertTrue(false==AccountValidateUtil.isValidProvider(""));
    assertTrue(false==AccountValidateUtil.isValidProvider("  "));
    assertTrue(true==AccountValidateUtil.isValidProvider("provider"));
  }

  @Test
  public void testIsValidProviderList() {
    assertTrue(true==AccountValidateUtil.isValidProviderList(null));
    assertTrue(false==AccountValidateUtil.isValidProviderList(Arrays.asList(" ")));
    assertTrue(false==AccountValidateUtil.isValidProviderList(Arrays.asList("1", " ")));
    assertTrue(true==AccountValidateUtil.isValidProviderList(Arrays.asList("1")));
    assertTrue(true==AccountValidateUtil.isValidProviderList(Arrays.asList("1", "2", "3")));
  }

}

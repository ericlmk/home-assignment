package com.ericlo.ha.util;

import org.junit.jupiter.api.Test;

import java.util.concurrent.Callable;
import java.util.concurrent.TimeUnit;

import static org.junit.jupiter.api.Assertions.assertTrue;

public class TimeoutUtilTest {

  @Test
  public void testTimeout() {
    Callable<Boolean> task = () -> {
      TimeUnit.MILLISECONDS.sleep(120);
      return true;
    };
    Boolean result = TimeoutUtil.run(task, 100);
    assertTrue(result==null);
  }

  @Test
  public void testWithinTime() {
    Callable<Boolean> task = () -> {
      TimeUnit.MILLISECONDS.sleep(80);
      return true;
    };
    Boolean result = TimeoutUtil.run(task, 100);
    assertTrue(result);
  }

  @Test
  public void testNullCallable() {
    Boolean result = TimeoutUtil.run(null, 100);
    assertTrue(result==null);
  }

}

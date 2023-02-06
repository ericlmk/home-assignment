package com.ericlo.ha.util;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.concurrent.*;

public class TimeoutUtil {

  private static final Logger log = LoggerFactory.getLogger(TimeoutUtil.class);

  public static <T> T run(Callable<T> callable, int timeout) {
    if (callable!=null) {
      ExecutorService executor = Executors.newSingleThreadExecutor();
      Future future = executor.submit(callable);
      try {
        return (T) future.get(timeout, TimeUnit.MILLISECONDS);
      } catch (TimeoutException e) {
        log.error("validateAccountNumber(): REST request timeout: " + e.getMessage());
        future.cancel(true);
      } catch (Exception e) {
        log.error("validateAccountNumber(): Exception: " + e.getMessage());
      } finally {
        executor.shutdownNow();
      }
    }
    return null;
  }

}

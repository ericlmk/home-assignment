package com.ericlo.ha.api;

import com.ericlo.ha.configuration.ConfigProperties;
import com.ericlo.ha.model.AccountValidateRequest;
import com.ericlo.ha.model.AccountValidateResponse;
import com.ericlo.ha.model.ErrorResponse;
import com.ericlo.ha.model.ProviderValidateResult;
import com.ericlo.ha.service.IProviderService;
import com.ericlo.ha.util.AccountValidateUtil;
import com.ericlo.ha.util.TimeoutUtil;
import com.fasterxml.jackson.databind.ObjectMapper;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.enums.ParameterIn;
import io.swagger.v3.oas.annotations.media.Schema;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;
import java.util.List;
import java.util.concurrent.Callable;
import java.util.stream.Collectors;

@javax.annotation.Generated(value = "io.swagger.codegen.v3.generators.java.SpringCodegen", date = "2023-02-05T17:40:00.126024615Z[GMT]")
@RestController
public class AccountApiController implements AccountApi {

  private static final Logger log = LoggerFactory.getLogger(AccountApiController.class);

  @Autowired
  private ObjectMapper objectMapper;

  @Autowired
  private HttpServletRequest request;

  @Autowired
  private ConfigProperties configProperties;

  @Autowired
  private IProviderService providerService;

  public ResponseEntity<?> accountValidatePost(@Parameter(in = ParameterIn.DEFAULT, description = "", schema = @Schema()) @Valid @RequestBody AccountValidateRequest body) {
    try {
      Callable<ResponseEntity<AccountValidateResponse>> task = () -> {
        if (AccountValidateUtil.isValidFormat(body.getAccountNumber()) && AccountValidateUtil.isValidProviderList(body.getProviders())) {
          List<String> providerList;
          if (body.getProviders()==null || body.getProviders().size()==0) {
            // All
            providerList = this.configProperties.getProviderUrlMap().keySet().stream().collect(Collectors.toList());
          } else {
            providerList = body.getProviders();
          }
          List<ProviderValidateResult> resultList = providerList.parallelStream()
            .map(provider -> {
              boolean result = false;
              try {
                result = this.providerService.validateAccountNumber(provider, body.getAccountNumber());
              } catch (Exception e) {
                log.error("accountValidatePost(): Stream Exception: " + e.getMessage());
              }
              ProviderValidateResult pvr = new ProviderValidateResult();
              pvr.setProvider(provider);
              pvr.setIsValid(result);
              return pvr;
            })
            .collect(Collectors.toList());

          // Create Response
          AccountValidateResponse response = new AccountValidateResponse();
          response.setResult(resultList);
          return new ResponseEntity<AccountValidateResponse>(response, HttpStatus.OK);
        } else {
          // Handle Incorrect Request Body (e.g. missing account number)
          return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
      };
      // Run the callable
      return TimeoutUtil.run(task, this.configProperties.getRequestMaxTime());
    } catch (Exception e) {
      log.error("accountValidatePost(): Exception: " + e.getMessage());
      ErrorResponse res = new ErrorResponse();
      res.setCode("999");
      res.setMessage("System Error");
      return new ResponseEntity<ErrorResponse>(res, HttpStatus.INTERNAL_SERVER_ERROR);
    }

  }

}

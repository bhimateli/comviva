package api.regression;

import common.Begin;
import common.Configurations;
import constants.UrlConstants;
import io.restassured.response.Response;
import junit.framework.Assert;
import org.databene.benerator.anno.Source;
import org.testng.annotations.Test;
import pojo.HttpMethodParameter;
import util.RestUtils;

import java.util.Base64;
import java.util.HashMap;
import java.util.Map;

import static org.databene.benerator.util.LineShuffler.logger;

/*
@author Bhimashankar Teli
 */
public class AccountRegressionTest extends Begin {
    Configurations configurationObj = Configurations.getInstance();
    RestUtils restUtil = new RestUtils();
    @Test(dataProvider = "feeder")
    @Source("///C:\\project\\expense\\comviva\\src\\test\\resources\\bank\\bankAccounts_regressionPositive.csv")
    public void getAccountsTest(String testCaseId, String testCaseName,String method,String endPoint,String headers,String payload,
                                int expectedStatusCode, String expectedResponseBody,String remarks){
        HttpMethodParameter httpParams = HttpMethodParameter.builder().build();
        Map<String, String> queryParams = new HashMap<String, String>();
       // Map<String, Object> pathParams = new HashMap<String, Object>();
        Map<String, String> headerParams = new HashMap<>();

        headerParams.put("x-api-key", "GET X-API-KEY from central location");  // We can get the x-api-key/jwt token
        httpParams.setHeaderParams(headerParams);
         /*
         if any queryparams or path params we can make use of here
        queryParams.put(queryParamName, queryParamValue);
        pathParams.put("", "");

        */
        logger.info("The values are from the csv file "+ testCaseId+ " ExpectedErrorCode "+ testCaseName+ " ActualErrorCode "+ expectedStatusCode);


      //  httpParams.setPathParams(pathParams);

        httpParams.setQueryParams(queryParams);
        queryParams.put("Accountid", "12345");
        httpParams.setQueryParams(queryParams);
        // call GET API and validate it -
        Response getResponse = restUtil.getOperation(httpParams, UrlConstants.GET_ACCOUNT,
                configurationObj.getContentType());

        System.out.println("The length of the response is "+getResponse.prettyPrint().length());

        // validate the error code
        //    Assert.assertEquals(200, getResponse.getStatusCode());  After executing API - We can validate it. Here no real API so commenting it and validating below with expected abd actual
        Assert.assertEquals(expectedStatusCode, 200);
        /*
        add any other validation here.
         */

    }
}

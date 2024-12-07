package api.smoke;

import common.Begin;
import common.Configurations;
import common.WireMockServerSetup;
import constants.UrlConstants;
import io.restassured.RestAssured;
import io.restassured.response.Response;
import junit.framework.Assert;
import org.databene.benerator.anno.Source;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;
import pojo.HttpMethodParameter;
import util.RestUtils;

import java.util.HashMap;
import java.util.Map;

/*
@author Bhimashankar Teli
 */
public class LoanTest extends Begin {
    Configurations configurationObj = Configurations.getInstance();
    RestUtils restUtil = new RestUtils();
    @Test(dataProvider = "feeder")
    @Source("///C:\\project\\expense\\comviva\\src\\test\\resources\\loan\\loan_smoke.csv")
    public void loanApiValidationTest(String testCaseId, String testCaseName, int statusCode){

        HttpMethodParameter httpParams = HttpMethodParameter.builder().build();
        Map<String, String> queryParams = new HashMap<String, String>();
        Map<String, Object> pathParams = new HashMap<String, Object>();

        /*
         if any queryparams or path params we can make use of here
        queryParams.put(queryParamName, queryParamValue);
        pathParams.put("", "");

        */
        logger.info("The values are from the csv file "+ testCaseId+ " testCaseName "+ testCaseName+ " statusCode "+ statusCode);
        Map<String, String> headerParams = new HashMap<>();
        headerParams.put("Authorization", "Bearer .*");
        httpParams.setHeaderParams(headerParams);
        httpParams.setPathParams(pathParams);
        httpParams.setQueryParams(queryParams);

        // call GET API and validate it -
        Response getResponse = restUtil.getOperation(httpParams, UrlConstants.GET_LOAN,
                configurationObj.getContentType());

        System.out.println("The length of the response is "+getResponse.prettyPrint().length());

        // validate the error code
        //    Assert.assertEquals(200, getResponse.getStatusCode());  After executing API - We can validate it. Here no real API so commenting it and validating below with expected abd actual
        Assert.assertEquals(200, getResponse.statusCode());

    }

   // @BeforeClass
    public static void setup() {
        // Start WireMock server before all tests
        WireMockServerSetup.startServer();
        RestAssured.baseURI = "http://localhost:8083";
    }

   // @AfterClass
    public static void tearDown() {
        // Stop WireMock server after all tests
        WireMockServerSetup.stopServer();
    }
}

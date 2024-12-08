package performanceTesting.api;

/*
@author Bhimashankar Teli
 */
import common.Begin;
import common.Configurations;
import common.WireMockServerSetup;
import constants.UrlConstants;
import io.restassured.RestAssured;
import io.restassured.response.Response;
import junit.framework.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;
import pojo.HttpMethodParameter;
import util.RestUtils;

import java.util.HashMap;
import java.util.Map;

import static org.databene.benerator.util.LineShuffler.logger;

public class AccountApiPerfTest extends Begin {
    Configurations configurationObj = Configurations.getInstance();
    RestUtils restUtil = new RestUtils();
    @Test
    public void accountApiPerfTest(){


            HttpMethodParameter httpParams = HttpMethodParameter.builder().build();
            Map<String, String> queryParams = new HashMap<String, String>();
            Map<String, String> headerParams = new HashMap<>();
            headerParams.put("Content-Type","application/json");
            httpParams.setHeaderParams(headerParams);
             httpParams.setQueryParams(queryParams);

            // call GET API and validate it -
            long startTime = System.currentTimeMillis();
            Response getResponse = restUtil.getOperation(httpParams, UrlConstants.GET_ACCOUNT,
                    configurationObj.getContentType());
        long endTime = System.currentTimeMillis();
        long responseTime = endTime - startTime;
            System.out.println("The response time is  "+responseTime);

        Assert.assertTrue(responseTime < 1000);
        Assert.assertEquals(200,getResponse.statusCode());



        }
    @BeforeClass
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

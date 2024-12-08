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
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;
import pojo.HttpMethodParameter;
import util.RestUtils;

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class ConcurrentUsersAccountApiPerformanceTest extends Begin {

    private static final int NUM_USERS = 100; // Number of concurrent users

    Configurations configurationObj = Configurations.getInstance();
    RestUtils restUtil = new RestUtils();
    @Test
    public void testAccountApiPerformanceWithConcurrentUsers() {
        ExecutorService executorService = Executors.newFixedThreadPool(NUM_USERS);
        for (int i = 0; i < NUM_USERS; i++) {
            executorService.submit(new Runnable() {
                @Override
                public void run() {
                    long startTime = System.currentTimeMillis();
                    HttpMethodParameter httpParams = HttpMethodParameter.builder().build();
                    Map<String, String> queryParams = new HashMap<String, String>();
                    Map<String, String> headerParams = new HashMap<>();
                    headerParams.put("Content-Type","application/json");
                    httpParams.setHeaderParams(headerParams);
                    httpParams.setQueryParams(queryParams);

                    // call GET API and validate it -
                    Response getResponse = restUtil.getOperation(httpParams, UrlConstants.GET_ACCOUNT,
                            configurationObj.getContentType());

                    long endTime = System.currentTimeMillis();
                    long responseTime = endTime - startTime;

                    // Print response time for each request
                    System.out.println("Response Time: " + responseTime + " ms");

                    // Optional: Validate the response status code
                    if (getResponse.getStatusCode() == 200) {
                        System.out.println("Request succeeded with status code: " + getResponse.getStatusCode());
                    } else {
                        System.out.println("Request failed with status code: " + getResponse.getStatusCode());
                    }
                }
            });
        }
        executorService.shutdown();
        while (!executorService.isTerminated()) {
            // Wait for all tasks to complete
        }

    }

  //  @BeforeClass
    public static void setup() {
        // Start WireMock server before all tests
        WireMockServerSetup.startServer();
        RestAssured.baseURI = "http://localhost:8083";
    }

    @AfterClass
    public static void tearDown() {
        // Stop WireMock server after all tests
        WireMockServerSetup.stopServer();
    }
}

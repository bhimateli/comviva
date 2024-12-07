package api.smoke;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import common.Begin;
import common.Configurations;
import common.WireMockServerSetup;
import constants.UrlConstants;
import io.restassured.response.Response;
import org.databene.benerator.anno.Source;
import org.json.JSONObject;
import org.junit.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;
import pojo.AuthRequest;
import pojo.AuthResponse;
import pojo.HttpMethodParameter;
import util.RestUtils;

import java.util.HashMap;
import java.util.Map;

import static org.testng.Assert.assertTrue;
import static org.testng.AssertJUnit.assertEquals;
import static org.testng.AssertJUnit.assertNotNull;

/*
@author Bhimashankar Teli
 */
public class AuthorizationSmokeTest extends Begin {

    Configurations configurationObj = Configurations.getInstance();
    RestUtils restUtil = new RestUtils();
    @Test(dataProvider = "feeder")
    @Source("///C:\\project\\expense\\comviva\\src\\test\\resources\\authapi\\auth_smoke.csv")
    public void authorizationApiValidationTest(String testcaseID,String testCaseName,String statusCode){


        HttpMethodParameter httpParams =  HttpMethodParameter.builder().build();
        Map<String, String> bodyParams = new HashMap<>();
        bodyParams.put("username","test_user");
        bodyParams.put("password", "test_password");

        JSONObject jsonObject = new JSONObject(bodyParams);
        httpParams.setBodyParams(jsonObject.toString());

        Response postResponse = restUtil.PostOpertion(httpParams, UrlConstants.POST_OAUTH_API,
                configurationObj.getContentType());
        System.out.println("The response body is "+postResponse.getBody().asString());
        logger.info("Validating the status code"+postResponse.getBody().asString());
        Assert.assertEquals(201, postResponse.statusCode());

    }

    /*
    Using pojo class - convert json response to pojo class. After getting the response , assign all values to AuthResponse pojo class
     */
    @Test(dataProvider = "feeder")
    @Source("///C:\\project\\expense\\comviva\\src\\test\\resources\\authapi\\auth_smoke.csv")
    public void testWithPojoClass(String testcaseID,String testCaseName,String statusCode) throws JsonProcessingException {

        HttpMethodParameter httpParams = HttpMethodParameter.builder().build();
        AuthRequest request = new AuthRequest("test_user", "test_password");
        JSONObject jsonObject = new JSONObject(request);
        httpParams.setBodyParams(jsonObject.toString());

        Response postResponse = restUtil.PostOpertion(httpParams, UrlConstants.POST_OAUTH_API,
                configurationObj.getContentType());

        String responseBody = postResponse.getBody().asString();
        ObjectMapper objectMapper = new ObjectMapper();
        System.out.println("Response Body: " + postResponse.getBody().asString());
        System.out.println("The response is "+ responseBody);
        AuthResponse authResponse = objectMapper.readValue(responseBody, AuthResponse.class);
// Step 5: Validate the response fields
        assertNotNull(authResponse.getAccessToken(), "Access token should not be null");
        assertEquals("Bearer", authResponse.getTokenType(), "Bearer");
        assertTrue(authResponse.getExpiresIn() > 0, "Token expiration time should be positive");
    }


 //  @BeforeClass
    public static void setup() {
        // Start WireMock server before all tests
        WireMockServerSetup.startServer();

    }

   // @AfterClass
    public static void tearDown() {
        // Stop WireMock server after all tests
        WireMockServerSetup.stopServer();
    }
}

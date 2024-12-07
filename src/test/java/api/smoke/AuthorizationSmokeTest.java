package api.smoke;

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
import pojo.HttpMethodParameter;
import util.RestUtils;

import java.util.HashMap;
import java.util.Map;

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

        logger.info("Validating the status code");
        Assert.assertEquals(201, postResponse.statusCode());

    }

   // @BeforeClass
    public static void setup() {
        // Start WireMock server before all tests
        WireMockServerSetup.startServer();

    }

    @AfterClass
    public static void tearDown() {
        // Stop WireMock server after all tests
        WireMockServerSetup.stopServer();
    }
}

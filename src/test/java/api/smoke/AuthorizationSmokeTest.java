package api.smoke;

import common.Begin;
import common.Configurations;
import constants.UrlConstants;
import io.restassured.response.Response;
import org.databene.benerator.anno.Source;
import org.json.JSONObject;
import org.junit.Assert;
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
    public void authorizationApiValidationTest(String testcaseID,String testCaseName,String token,String statusCode){

        String requestJosn = """
                {
                  "client_id": "your_app_client_id",
                  "client_secret": "your_app_client_secret",
                  "grant_type": "client_credentials"
                }
                """;
        HttpMethodParameter httpParams =  HttpMethodParameter.builder().build();
        Map<String, String> bodyParams = new HashMap<>();
        bodyParams.put("client_id","1234");
        bodyParams.put("client_secret", "45566");
        bodyParams.put("grant_type","666666");
        JSONObject jsonObject = new JSONObject(bodyParams);
        httpParams.setBodyParams(jsonObject.toString());

        Response postResponse = restUtil.PostOpertion(httpParams, UrlConstants.POST_OAUTH_API,
                configurationObj.getContentType());


        Assert.assertEquals(201, 201);

    }
}

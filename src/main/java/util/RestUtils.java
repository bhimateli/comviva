package util;

/*
@author Bhimashankar Teli
 */

import common.Configurations;
import io.restassured.RestAssured;
import io.restassured.builder.RequestSpecBuilder;
import io.restassured.config.RestAssuredConfig;
import io.restassured.config.SSLConfig;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import pojo.HttpMethodParameter;


public class RestUtils {
    Configurations configurationObj = Configurations.getInstance();
    String url;

    /*
     * GENERIC:  We can write code here for supporting pathparam, formparam ets. Now written code for body and queryparam
     */

    public Response getOperation(HttpMethodParameter httpParams, String endPoint, String contentType) {
        url = configurationObj.getUrlBasePath() + endPoint;

        RequestSpecification requestSpecification = RestAssured.given().log().all().urlEncodingEnabled(false)
                .config(RestAssuredConfig.config().sslConfig(new SSLConfig().relaxedHTTPSValidation())).queryParams(httpParams.getQueryParams()).contentType(contentType);
        Response response = requestSpecification.request("GET", url);
        System.out.println("The response is "+response.prettyPrint());
        return response;
    }

    public Response PostOpertion(HttpMethodParameter httpParams,String endPoint,String contentType) {
        url = configurationObj.getUrlBasePath() + endPoint;

        RequestSpecification requestSpecification = RestAssured.given().log().all()
                .config(RestAssuredConfig.config().sslConfig(new SSLConfig().relaxedHTTPSValidation())).body(httpParams.getBodyParams()).contentType(contentType);
        Response response = requestSpecification.request("POST", url);
        return response;
    }


}

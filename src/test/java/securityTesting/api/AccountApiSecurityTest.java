package securityTesting.api;
/*
@author Bhimashankar Teli
 */
import common.Begin;
import common.WireMockServerSetup;
import io.restassured.RestAssured;
import io.restassured.response.Response;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;
import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.*;

public class AccountApiSecurityTest extends Begin {

    // Base URI for the Account API
    static {
        RestAssured.baseURI = "http://localhost:8083";
    }

    /**
     * Test to ensure unauthorized access is restricted.
     */
    @Test
    public void testUnauthorizedAccess() {
        given()
                .when()
                .get("/accounts/12345") // Replace with actual endpoint
                .then()
                .assertThat()
                .statusCode(401) // 401 Unauthorized
                .body("message", containsString("Unauthorized"));
    }

    /**
     * Test to ensure SQL Injection attempts are mitigated.
     */
    @Test
    public void testSqlInjectionPrevention() {
        String maliciousPayload = "' OR '1'='1";

        given()
                .header("Authorization", "Bearer valid_token") // Replace with actual token
                .queryParam("accountId", maliciousPayload)
                .when()
                .get("/accounts") // Replace with actual endpoint
                .then()
                .assertThat()
                .statusCode(400) // Assuming API rejects the bad request
                .body("error", containsString("Invalid input"));
    }

    /**
     * Test to ensure secure headers are present in the response.
     */
    @Test
    public void testSecureHeadersPresence() {
        Response response = given()
                .header("Authorization", "Bearer valid_token")
                .when()
                .get("/accounts/12345"); // Replace with actual endpoint

        // Assert security headers
        response.then()
                .assertThat()
                .statusCode(200) // Assuming valid request
                .header("X-Content-Type-Options", "nosniff")
                .header("X-Frame-Options", "DENY")
                .header("Strict-Transport-Security", containsString("max-age="));
    }

    /**
     * Test to ensure proper validation of request payload.
     */
    @Test
    public void testInvalidRequestPayload() {
        String invalidPayload = "{ \"accountId\": \"<script>alert(1)</script>\" }";

        given()
                .header("Authorization", "Bearer valid_token")
                .header("Content-Type", "application/json")
                .body(invalidPayload)
                .when()
                .post("/accounts") // Replace with actual endpoint
                .then()
                .assertThat()
                .statusCode(400) // Bad Request
                .body("error", containsString("Invalid input"));
    }

    /**
     * Test to ensure brute force attempts are mitigated.
     */
    @Test
    public void testBruteForceMitigation() {
        for (int i = 0; i < 5; i++) { // Simulating multiple failed attempts
            given()
                    .header("Authorization", "Bearer invalid_token")
                    .when()
                    .get("/accounts/12345")
                    .then()
                    .assertThat()
                    .statusCode(401); // Unauthorized
        }

        // Assume that after multiple attempts, the account is locked or response changes
        given()
                .header("Authorization", "Bearer invalid_token")
                .when()
                .get("/accounts/12345")
                .then()
                .assertThat()
                .statusCode(429); // Too Many Requests (if implemented)
    }

    @BeforeClass
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

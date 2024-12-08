package common;
/*
@author Bhimashankar Teli
 */
import com.github.tomakehurst.wiremock.WireMockServer;
import com.github.tomakehurst.wiremock.client.WireMock;
import com.github.tomakehurst.wiremock.common.ClasspathFileSource;
import com.github.tomakehurst.wiremock.core.WireMockConfiguration;

public  class WireMockServerSetup {
    private static WireMockServer wireMockServer;
    public static void startServer() {
        // Initialize and start WireMock server

        if (wireMockServer == null) {
            ClasspathFileSource fileSource = new ClasspathFileSource("C:\\project\\expense\\comviva\\src\\test\\resources\\wiremock");
            WireMockConfiguration options = WireMockConfiguration.options()
                    .port(8083)  // Specify the desired port here
                    .fileSource(fileSource);

            WireMockServer wireMockServer = new WireMockServer(options);
            wireMockServer.start();

            // Configure WireMock client
            WireMock.configureFor("localhost", 8083);

            // Set up stubs for all APIs
            WireMock.stubFor(WireMock.get(WireMock.urlEqualTo("/loan"))
                    .willReturn(WireMock.aResponse()
                            .withStatus(200)
                            .withHeader("Content-Type", "application/json")
                            .withBodyFile("loan-response.json")));

            WireMock.stubFor(WireMock.get(WireMock.urlEqualTo("/investment"))
                    .willReturn(WireMock.aResponse()
                            .withStatus(200)
                            .withHeader("Content-Type", "application/json")
                            .withBodyFile("investments-response.json")));

            WireMock.stubFor(WireMock.get(WireMock.urlEqualTo("/account?accountId=456789"))
                    .willReturn(WireMock.aResponse()
                            .withStatus(200)
                            .withHeader("Content-Type", "application/json")
                            .withBodyFile("account_response_noAccountID.json")));

            WireMock.stubFor(WireMock.get(WireMock.urlEqualTo("/account"))
                    .willReturn(WireMock.aResponse()
                            .withStatus(200)
                            .withHeader("Content-Type", "application/json")
                            .withBodyFile("account_response.json")));

            WireMock.stubFor(WireMock.get(WireMock.urlEqualTo("/epfbalance"))
                    .willReturn(WireMock.aResponse()
                            .withStatus(200)
                            .withHeader("Content-Type", "application/json")
                            .withBodyFile("epf_balance_response.json")));

            WireMock.stubFor(WireMock.get(WireMock.urlEqualTo("/liabilities"))
                    .willReturn(WireMock.aResponse()
                            .withStatus(200)
                            .withHeader("Content-Type", "application/json")
                            .withBodyFile("liabilities-response.json")));

           WireMock.stubFor(WireMock.post(WireMock.urlEqualTo("/auth/token"))
                    .withHeader("Content-Type", WireMock.equalTo("application/json"))
                    .withRequestBody(WireMock.equalToJson("{ \"username\": \"test_user\", \"password\": \"test_password\" }"))
                    .willReturn(WireMock.aResponse()
                            .withStatus(201)
                            .withHeader("Content-Type", "application/json")
                            .withBodyFile("auth-response.json")));
        }

            System.out.println("WireMock server is running at http://localhost:8083");

    }
    public static void stopServer() {
        if (wireMockServer != null) {
            wireMockServer.stop();
        }
    }
}
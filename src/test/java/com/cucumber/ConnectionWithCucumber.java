package com.cucumber;

import com.github.tomakehurst.wiremock.WireMockServer;
import com.github.tomakehurst.wiremock.client.WireMock;
import com.github.tomakehurst.wiremock.junit.WireMockRule;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import io.cucumber.spring.CucumberContextConfiguration;
import org.junit.Rule;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.context.SpringBootTest;

import static com.github.tomakehurst.wiremock.client.WireMock.*;
import static com.github.tomakehurst.wiremock.core.WireMockConfiguration.wireMockConfig;
import static io.restassured.RestAssured.given;
import static org.junit.jupiter.api.Assertions.assertEquals;

@SpringBootTest
@CucumberContextConfiguration
public class ConnectionWithCucumber {

    @Value("${another.server.port}")
    private int PORT;

    @Rule
    private WireMockRule wireMockRule = new WireMockRule(PORT);

    private WireMockServer wireMockServer = new WireMockServer(PORT);


    private String messageResult;

    @Given("I want to read a message")
    public void iWantToRead() {
        wireMockServer.start();
        wireMockRule.start();
        wireMockRule.isRunning();
        wireMockServer.isRunning();
        // Start the stub
        createMessageStubServer();
        createMessageStub();

        wireMockServer.getStubMappings();
        wireMockRule.getStubMappings();
    }

    @When("I send the request")
    public void iSendTheRequest() {
        messageResult = given().get("localhost:8082/message").getBody().asString();
    }

    @Then("I should be able to read the word {string}")
    public void iShouldBeAbleToReadTheWord(String arg0) {
        assertEquals(arg0, messageResult);
    }

    private void createMessageStub() {
        wireMockRule.stubFor(get(urlEqualTo("/message"))
                .withHeader("Accept", equalTo("application/json"))
                .willReturn(aResponse()
                        .withStatus(200)
                        .withHeader("Content-Type", "application/json")
                        .withBody("message")));
    }

    private void createMessageStubServer() {
        wireMockServer.stubFor(get(urlEqualTo("/message"))
                .withHeader("Accept", equalTo("application/json"))
                .willReturn(aResponse()
                        .withStatus(200)
                        .withHeader("Content-Type", "application/json")
                        .withBody("message")));
    }
}

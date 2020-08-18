package com.cucumber;

import com.github.tomakehurst.wiremock.core.WireMockConfiguration;
import com.github.tomakehurst.wiremock.junit.WireMockRule;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import io.cucumber.junit.Cucumber;
import io.cucumber.spring.CucumberContextConfiguration;
import io.restassured.http.Header;
import org.junit.Rule;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.cloud.contract.wiremock.AutoConfigureWireMock;

import static com.github.tomakehurst.wiremock.client.WireMock.*;
import static com.github.tomakehurst.wiremock.core.WireMockConfiguration.options;
import static io.restassured.RestAssured.given;
import static org.junit.jupiter.api.Assertions.assertEquals;

@SpringBootTest
@CucumberContextConfiguration
@AutoConfigureWireMock
public class ConnectionWithCucumber {

    @Value("${another.server.port}")
    private int PORT;

    @Rule
    public WireMockRule wireMockRule = new WireMockRule(options().port(PORT));

    private String messageResult;

    @Given("I want to read a message")
    public void iWantToRead() {
        createMessageStub();
    }

    @When("I send the request")
    public void iSendTheRequest() {
        Header header = new Header("Content-Type","application/json");
        messageResult = given().header(header).get("/message").getBody().asString();
    }

    @Then("I should be able to read the word {string}")
    public void iShouldBeAbleToReadTheWord(String arg0) {
        assertEquals(arg0, messageResult);
    }

    private void createMessageStub() {
        stubFor(get(urlEqualTo("/message"))
                .willReturn(aResponse()
                        .withStatus(200)
                        .withHeader("Content-Type", "application/json")
                        .withBody("Response")));
    }
}

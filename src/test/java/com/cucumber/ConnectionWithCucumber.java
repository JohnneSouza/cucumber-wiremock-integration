package com.cucumber;

import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import io.restassured.http.Header;
import org.springframework.boot.test.context.SpringBootTest;

import static com.github.tomakehurst.wiremock.client.WireMock.*;
import static io.restassured.RestAssured.given;
import static org.junit.jupiter.api.Assertions.assertEquals;

@SpringBootTest
public class ConnectionWithCucumber {

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

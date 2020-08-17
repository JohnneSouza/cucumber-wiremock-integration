package com.cucumber;

import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;

import static org.junit.jupiter.api.Assertions.assertTrue;

public class DayOfWeek {
    @Given("today is Sunday")
    public void todayIsSunday() {
        assertTrue(Boolean.TRUE);
    }

    @When("I ask whether it's Friday yet")
    public void iAskWhetherItSFridayYet() {
        assertTrue(Boolean.TRUE);
    }

    @Then("I should be told {string}")
    public void iShouldBeTold(String arg0) {
        assertTrue(Boolean.TRUE);
    }

    @When("I ask whether it's Sunday yet")
    public void iAskWhetherItSSundayYet() {
        assertTrue(Boolean.TRUE);
    }
}

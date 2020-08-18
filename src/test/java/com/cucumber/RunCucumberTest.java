package com.cucumber;

import com.github.tomakehurst.wiremock.junit.WireMockRule;
import io.cucumber.junit.Cucumber;
import io.cucumber.spring.CucumberContextConfiguration;
import org.junit.Rule;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.contract.wiremock.AutoConfigureWireMock;

import static com.github.tomakehurst.wiremock.core.WireMockConfiguration.options;

@RunWith(Cucumber.class)
@CucumberContextConfiguration
@AutoConfigureWireMock
public class RunCucumberTest {

    @Value("${another.server.port}")
    private int PORT;

    @Rule
    public WireMockRule wireMockRule = new WireMockRule(options().port(PORT));

}

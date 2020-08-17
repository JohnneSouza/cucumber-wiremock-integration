package com.cucumber;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.context.SpringBootTest;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest
public class SpringValueTest {

    @Value("${test.word}")
    private String word;

    @Value("${test.number}")
    private String number;

    @Test
    public void testWord(){
        assertThat(word).isEqualTo("word");
        assertThat(number).isEqualTo("number");
    }

}

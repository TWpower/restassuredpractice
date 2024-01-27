package me.twpower.restassuredpractice;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import static io.restassured.RestAssured.*;
import static org.hamcrest.Matchers.equalTo;

@SpringBootTest
public class RestAssuredPracticeTest {
    @Test
    void restAssuredPracticeTest() {
        // JSON Example
        // Call http://echo.jsontest.com/key1/value1/key2/value2/key3/3
        /*
        {
            "key1": "value1",
                "key2": "value2",
                "key3": "3"
        }
        */

        // given(): Start building the request part of the test io.restassured.specification.
        // when(): Start building the DSL expression by sending a request without any parameters or headers etc.
        // then(): Returns a validatable response that's lets you validate the response.

        given().
                header("Content-Type", "application/json").
                baseUri("http://echo.jsontest.com/").
        when().
                get("/key1/value1/key2/value2/key3/3").
        then().
                body("key1", equalTo("value1"));
    }
}

package me.twpower.restassuredpractice;

import io.restassured.RestAssured;
import io.restassured.response.ExtractableResponse;
import io.restassured.response.Response;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import static io.restassured.RestAssured.*;
import static org.hamcrest.Matchers.equalTo;

@SpringBootTest
public class RestAssuredPracticeTest {

    @BeforeAll
    static void beforeAll(){
        // Setting BaseURI
        RestAssured.baseURI = "http://echo.jsontest.com/";
    }

    @Test
    void restAssuredPracticeTest() {
        // JSON Example
        // Request Method: GET
        // Call http://echo.jsontest.com/key1/value1/key2/value2/key3/3?queryParameterKey=queryParameterValue
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

        // given() and when() returns RequestSpecification object
        ExtractableResponse<Response> extractableResponse = given().log().all().
            header("Content-Type", "application/json"). // Specify the headers that'll be sent with the request.
            pathParam("pathParameter", 3). // Specify a path parameter.
            queryParam("queryParameterKey", "queryParameterValue"). // Specify a query parameter that'll be sent with the request.
            //body(). // Specify request body.
        when().
            get("/key1/value1/key2/value2/key3/{pathParameter}").
        then().
            body("key1", equalTo("value1")).
            extract();

        Assertions.assertEquals(200, extractableResponse.statusCode());
        Assertions.assertEquals("value1", extractableResponse.jsonPath().getString("key1"));
    }
}

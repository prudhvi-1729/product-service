package com.capstone.product_service;

import io.restassured.RestAssured;
import org.hamcrest.Matchers;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.server.LocalServerPort;
import org.springframework.boot.testcontainers.service.connection.ServiceConnection;
import org.springframework.context.annotation.Import;
import org.testcontainers.containers.MongoDBContainer;

@Import(TestcontainersConfiguration.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
class ProductServiceApplicationTests {
    @ServiceConnection
	static MongoDBContainer mongoDBContainer = new MongoDBContainer("mongo:latest");
    @LocalServerPort
	private Integer port;

	@BeforeEach
	void setup(){
		RestAssured.baseURI = "http://localhost";
		RestAssured.port = port;
	}

	static {
		mongoDBContainer.start();
	}

	@Test
	void shouldCreateProduct() {
		String requestBody = """
		{
		"name": "iPhone", 
		"description": "iPhone",
		"price": 1000
		}
		""";
		RestAssured.given()
			.header("Content-Type", "application/json")
			.body(requestBody).when()
			.post("/api/product")
			.then()
			.statusCode(201)
			.body("id", Matchers.notNullValue())
			.body("name", Matchers.equalTo("iPhone"))
			.body("description", Matchers.equalTo("iPhone"))
			.body("price", Matchers.equalTo(1000));
	}

}

package com.petstore.PetTests;
import java.io.IOException;
import java.util.Collections;

import org.apache.commons.lang3.RandomStringUtils;
import org.testng.Assert;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import com.petstore.controllers.controller;
import com.petstore.domain.Category;
import com.petstore.domain.Pet;
import com.petstore.domain.Status;
import com.petstore.domain.Tag;

import io.restassured.response.Response;

public class petStoreTest {
	controller petController;
	String projectDir=System.getProperty("user.dir");	
	String PHOTO_URL = "\\myFido.jpg";
	Pet pet = new Pet.Builder()
            .withId(RandomStringUtils.randomNumeric(10))
            .withName("My pet")
            .withPhotoUrls(Collections.singletonList(projectDir+PHOTO_URL))
            .withStatus(Status.available)
            .withTags(Collections.singletonList(new Tag(1, "golden-retriever")))
            .inCategory(new Category(1, "dogs")).build();

	@BeforeTest
	public void beforeTest() {
		petController=new controller();
	}

	@Test
	void addNewPet() throws IOException {			
		Response response = petController.addNewPet(pet);
		String responseBody = response.getBody().asString();
		System.out.println("Response Body is:" + responseBody);
		// status code validation
		int statusCode = response.getStatusCode();
		String contentType = response.getContentType();
		System.out.println(contentType);
		System.out.println("Status code is: " + statusCode);
		Assert.assertEquals(statusCode, 200);
		String statusLine = response.getStatusLine();
		System.out.println(statusLine);
		Assert.assertEquals(statusLine /* actual value */, "HTTP/1.1 200 OK" /* expected value */,"Correct status line returned");
		Assert.assertEquals(contentType, "application/json");

	}

	@Test(priority = 1)
	public void verifyNewPet() {
		
		Response response = petController.verifyNewPet(pet);
		String body = response.getBody().asString();
		// Retrieving Status Code of response
		int status = response.getStatusCode();
		// Retrieving Status Line
		String statusLine = response.getStatusLine();
		Assert.assertEquals(status, 200);
		Assert.assertEquals(statusLine /* actual value */, "HTTP/1.1 200 OK" /* expected value */,"Correct status line returned");
		// Printing the response
		System.out.println("Response Body is " + body);
		System.out.println("Status code is " + status);
		System.out.println("Status line is " + statusLine);
	}
	

}
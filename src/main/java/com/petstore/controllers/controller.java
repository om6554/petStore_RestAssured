package com.petstore.controllers;

import com.petstore.domain.Pet;

import io.restassured.RestAssured;
import io.restassured.http.Method;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;

public class controller {
	
	public Response addNewPet(Pet pet) {
		RestAssured.baseURI = "https://petstore.swagger.io/v2";// Specify base URI
		RequestSpecification httpRequest = RestAssured.given();// Request object
		httpRequest.header("Content-Type", "application/json");
		httpRequest.body(pet); // attach above data to the request
		Response response = httpRequest.request(Method.POST, "/pet");// Response object
		
		return response;
    	
    }

	public Response verifyNewPet(Pet pet) {
		 RestAssured.baseURI = "https://petstore.swagger.io/v2";
		// Getting the RequestSpecification of the request
		RequestSpecification httpRequest = RestAssured.given();
		        // Making GET request directly by RequestSpecification.get() method
		Response response = httpRequest.pathParam("petId", pet.getId()).get("/pet/{petId}");
		return response;
	}

}

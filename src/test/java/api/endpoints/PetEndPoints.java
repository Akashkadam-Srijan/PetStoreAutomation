package api.endpoints;

import static io.restassured.RestAssured.given;

import api.payload.Pet;
import io.restassured.http.ContentType;
import io.restassured.response.Response;

public class PetEndPoints {
	
	public static Response createPet(Pet userPayload)
	{
		Response response=given()
			.contentType(ContentType.JSON)
			.accept(ContentType.JSON)
			.body(userPayload)
		
		.when()
			.post(Routes.Pet_Post_Url);
		
		return response;
	}
	
	public static Response readPet(int id)
	{
		Response response=given()
			.pathParam("id", id)
		
		.when()
			.get(Routes.Pet_Get_Url);
		
		return response;
	}
	
	public static Response updatePet(int id, Pet userPayload)
	{
		Response response=given()
			.contentType(ContentType.JSON)
			.accept(ContentType.JSON)
			.pathParam("id", id)
			.body(userPayload)
		
		.when()
			.put(Routes.Pet_Update_Url);
		
		return response;
	}

	public static Response deletePet(int id)
	{
		Response response=given()
			.pathParam("id", id)
		
		.when()
			.delete(Routes.Pet_Delete_Url);
		
		return response;
	}
}

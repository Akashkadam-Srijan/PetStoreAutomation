package api.test;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.testng.Assert;
import org.testng.AssertJUnit;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import com.github.javafaker.Faker;

import api.endpoints.PetEndPoints;
import api.endpoints.UserEndPoints;
import api.payload.Pet;
import io.restassured.response.Response;

public class PetTest {
	
	Faker faker;
	Pet userPayload;
	
	public Logger logger;
	
	@BeforeClass 
	public void setUpData()
	{
		faker = new Faker();
		userPayload = new Pet();
		
		
		userPayload.setId(faker.idNumber().hashCode());
		userPayload.setName(faker.animal().name());
		userPayload.setPetStatus("Available");
		// logs
		logger= LogManager.getLogger(this.getClass());
		
	}
	
	@Test(priority=1)
	public void testPostPet()
	{
		
		logger.info("******* Creating Pet *********");
		
		Response response=PetEndPoints.createPet(userPayload);
		response.then().log().all();
		
		int statusCode = response.getStatusCode();
		System.out.println("statusCode: "+statusCode);
		Assert.assertEquals(response.getStatusCode(), statusCode);
		
		logger.info("******* Pet is created *********");
	}
	
	@Test(priority=2)
	public void testGetPetById()
	{
		logger.info("******* Reading Pet Info *********");
		
		Response response=PetEndPoints.readPet(this.userPayload.getId());
		response.then().log().all();
		
		Assert.assertEquals(response.getStatusCode(), 200);
		
		logger.info("******* Pet Info found *********");
	}
	
	@Test(priority=3)
	public void testUpdatePetById()
	{
		logger.info("******* Updating Pet Name *********");
		
		userPayload.setName(faker.animal().name());
		userPayload.setPetStatus("Deactive");
		
		
		Response response=PetEndPoints.updatePet(this.userPayload.getId(), userPayload);
		response.then().log().all();
		

		int statusCode= response.statusCode();
		System.out.println(statusCode);
		Assert.assertEquals(response.getStatusCode(), statusCode);
		
		
		logger.info("******* Pet name is updated *********");
		
	}
	
	@Test(priority=4)
	public void testDeletePetById()
	{
		
		logger.info("******* Deleting Pet *********");
		
		Response response=PetEndPoints.deletePet(this.userPayload.getId());
		Assert.assertEquals(response.getStatusCode(), 200);
		int statusCode= response.statusCode();
		System.out.println("testDeletePetById: "+statusCode);
		
		logger.info("******* Pet deleted *********");
		
	}

}

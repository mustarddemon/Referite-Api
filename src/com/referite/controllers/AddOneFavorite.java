package com.referite.controllers;

import java.io.IOException;
import java.net.UnknownHostException;
import java.util.LinkedList;
import java.util.List;
import java.util.UUID;

import javax.ws.rs.BadRequestException;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import org.codehaus.jackson.JsonGenerationException;
import org.codehaus.jackson.map.JsonMappingException;
import org.codehaus.jackson.map.ObjectMapper;

import com.mongodb.DB;
import com.mongodb.DBCollection;
import com.mongodb.DBCursor;
import com.mongodb.DBObject;
import com.mongodb.MongoClient;
import com.mongodb.util.JSON;
import com.referite.models.FavoriteCollectionModel;
import com.referite.models.FavoriteModel;

@Path("favorites")
public class AddOneFavorite extends RestServiceBaseClass {

	@POST
	@Produces(MediaType.APPLICATION_JSON)
	public String addOneFavorite(String favorite)
			throws JsonGenerationException, JsonMappingException, IOException {

		// convert string to object

		FavoriteModel model = null;
		try {
			// if its valid assign an id to be inserted
			// TODO: add
			model = getMapper().readValue(favorite, FavoriteModel.class);
			model.setId(UUID.randomUUID().toString());

			if (model.isContractValid() == false) {
				throw new BadRequestException(
						"Data sent does not meet contract specifications");
			}
		} catch (BadRequestException be) {
			throw be;
		} catch (Exception e) {
			throw new BadRequestException(
					"Request sent is either not valid Json, or does not contain correct fields or contains fields that cannot be mapped");
		}

		// insert object into mongo
		DBCollection coll = getFavoriteCollection();

		// convert the model back to json
		String newModel = getMapper().writeValueAsString(model);

		DBObject insertObject = (DBObject) JSON.parse(newModel);

		coll.insert(insertObject);

		// return object from mongo with id
		return getMapper().writeValueAsString(newModel);
	}
}

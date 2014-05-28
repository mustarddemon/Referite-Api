package com.referite.controllers;

import java.io.IOException;
import java.util.LinkedList;
import java.util.List;

import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import org.codehaus.jackson.JsonGenerationException;
import org.codehaus.jackson.map.JsonMappingException;

import com.mongodb.DBCursor;
import com.mongodb.DBObject;
import com.referite.models.FavoriteCollectionModel;
import com.referite.models.FavoriteModel;

@Path("favorites")
public class GetFavorites extends RestServiceBaseClass {

	@GET
	@Produces(MediaType.APPLICATION_JSON)
	public String getAllFavorites() throws JsonGenerationException,
			JsonMappingException, IOException {

		try {
			List<FavoriteModel> foundFavorites = new LinkedList<FavoriteModel>();

			DBCursor cursor = getFavoriteCollection().find();

			while (cursor.hasNext()) {
				DBObject favoriteObject = cursor.next();

				String jsonObject = getMapper().writeValueAsString(
						favoriteObject);
				
				System.out.println(jsonObject);

				// map it back to our object
				FavoriteModel favObject = getMapper().readValue(jsonObject,
						FavoriteModel.class);

				foundFavorites.add(favObject);
			}

			// this is probably inefficent, but now we
			FavoriteCollectionModel collection = new FavoriteCollectionModel();
			collection.setItems(foundFavorites);

			return getMapper().writeValueAsString(collection);
		} catch (Exception e) {
			return e.getMessage();
		}

	}



}

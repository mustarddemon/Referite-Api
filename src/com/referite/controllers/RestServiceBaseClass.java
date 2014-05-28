package com.referite.controllers;

import java.io.IOException;
import java.net.UnknownHostException;
import java.util.UUID;

import javax.ws.rs.BadRequestException;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import org.codehaus.jackson.JsonGenerationException;
import org.codehaus.jackson.map.JsonMappingException;
import org.codehaus.jackson.map.ObjectMapper;

import com.mongodb.DB;
import com.mongodb.DBCollection;
import com.mongodb.DBObject;
import com.mongodb.MongoClient;
import com.mongodb.util.JSON;
import com.referite.constants.ServiceConstants;
import com.referite.models.FavoriteModel;

@Path("favorites")
public class RestServiceBaseClass {
	
	private MongoClient client;
	private DB db;
	private DBCollection favoriteCollection;
	private ObjectMapper mapper;
	
	public ObjectMapper getMapper() {
		if (mapper == null) {
			mapper = new ObjectMapper();
		}
		return mapper;
	}
	
	public MongoClient getClient() throws UnknownHostException {
		if (client == null) {
			client = new MongoClient(ServiceConstants.DB_LOCATION, ServiceConstants.DB_PORT);
		}
		return client;
	}
	
	public DB getDb() throws UnknownHostException {
		if (db == null) {
			db = getClient().getDB(ServiceConstants.DB_NAME);
		}
		return db;
	}
	
	public DBCollection getFavoriteCollection() throws UnknownHostException {
		//for now we'll get it fresh each time
		return getDb().getCollection(ServiceConstants.COLLECTION_NAME);
	}
	


}

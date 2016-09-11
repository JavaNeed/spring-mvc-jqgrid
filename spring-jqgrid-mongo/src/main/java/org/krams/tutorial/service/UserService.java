package org.krams.tutorial.service;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import org.krams.tutorial.mongo.MongoDBFactory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.mongodb.BasicDBObject;
import com.mongodb.DBCollection;
import com.mongodb.DBCursor;
import com.mongodb.DBObject;


@Service("userService")
@Transactional
public class UserService implements IUserService   {
	
	protected static Logger logger = LoggerFactory.getLogger("service");

	// Initialize our database
	public UserService() {
		init();
	}
	

	public List<DBObject> getAll() {
		logger.debug("Retrieving all users");
		
		// Retrieve collection
    	DBCollection coll = MongoDBFactory.getCollection("mydb","mycollection");
    
    	// Retrieve cursor for iterating records
    	DBCursor cur = coll.find();
    	
    	// Create new list
		List<DBObject> items = new ArrayList<DBObject>();
		
		// Iterate cursor
        while(cur.hasNext()) {
        	// Add to new list
        	items.add(cur.next());
        }
        
        // Return list
		return items;
	}
	
	/**
	 * Retrieves a single user
	 */
	public DBObject get( String id ) {
		logger.debug("Retrieving an existing user");
		
		// Retrieve collection
		DBCollection coll = MongoDBFactory.getCollection("mydb","mycollection");
		// Create a new object
		DBObject doc = new BasicDBObject();
		// Put id to search
        doc.put("id", id);
        
     // Find and return the user with the given id
		return coll.findOne(doc);
	}

	/**
	 * Adds a new user
	 */
	public Boolean add( String firstName, String lastName ) {
		logger.debug("Adding a new user");
		
		try {
			// Retrieve collection
			DBCollection coll = MongoDBFactory.getCollection("mydb","mycollection");
			// Create a new object
			BasicDBObject doc = new BasicDBObject();
			// Assign a new id using UUID type 4
			// See http://en.wikipedia.org/wiki/Universally_unique_identifier
	        doc.put("id", UUID.randomUUID().toString() ); 
	        doc.put("firstName", firstName);
	        doc.put("lastName", lastName);
	        // Save new user
	        coll.insert(doc);
	        
			return true;
			
		} catch (Exception e) {
			logger.error("An error has occurred while trying to add new user", e);
			return false;
		}
	}
	
	/**
	 * Deletes an existing person
	 */
	public Boolean delete( String id ) {
		logger.debug("Deleting an existing user");
		
		try {
			// Retrieve item to delete
			BasicDBObject item = (BasicDBObject) get( id);
			// Retrieve collection
			DBCollection coll = MongoDBFactory.getCollection("mydb","mycollection");
			// Delete retrieved user
	        coll.remove(item);
	        
			return true;
			
		} catch (Exception e) {
			logger.error("An error has occurred while trying to delete new user", e);
			return false;
		}
	}
 	
	/**
	 * Edits an existing person
	 */
	public Boolean edit( String id, String firstName, String lastName ) {
		logger.debug("Editing an existing user");
		
		try {
			// Retrieve item to edit
			BasicDBObject existing = (BasicDBObject) get( id );
			// Retrieve collection
			DBCollection coll = MongoDBFactory.getCollection("mydb","mycollection");
			
			// Create new object
			BasicDBObject edited = new BasicDBObject();
			// Assign existing details
			edited.put("id", id); 
			edited.put("firstName", firstName);
			edited.put("lastName", lastName);
			// Update existing user
	        coll.update(existing, edited);
	        
			return true;
		} catch (Exception e) {
			return false;
		}
		
	}
	
	private void init() {
		logger.debug("~~~~~~~~~~~~~~~~ Init MongoDB users ~~~~~~~~~~~~~~~~~~~~");
		
		// Drop existing collection
		MongoDBFactory.getCollection("mydb","mycollection").drop();
		
		// Retrieve collection. If not existing, create a new one
		DBCollection coll = MongoDBFactory.getCollection("mydb","mycollection");
		
		// Create new object
		BasicDBObject doc = new BasicDBObject();
		doc.put("id", UUID.randomUUID().toString());
        doc.put("firstName", "John");
        doc.put("lastName", "Smith");
        coll.insert(doc);
		
        // Create new object
        doc = new BasicDBObject();
        doc.put("id", UUID.randomUUID().toString());
        doc.put("firstName", "Jane");
        doc.put("lastName", "Adams");
        coll.insert(doc);
        
        // Create new object
        doc = new BasicDBObject();
        doc.put("id", UUID.randomUUID().toString());
        doc.put("firstName", "Jeff");
        doc.put("lastName", "Mayer");
        coll.insert(doc);
	}
}

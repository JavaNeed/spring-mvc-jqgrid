package org.krams.tutorial.service;

import java.util.List;

import com.mongodb.DBObject;

public interface IUserService {

	public List<DBObject> getAll();

	public DBObject get(String id);

	public Boolean add(String firstName, String lastName);

	public Boolean delete(String id);

	public Boolean edit(String id, String firstName, String lastName);
}
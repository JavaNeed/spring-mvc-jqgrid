package org.krams.tutorial.service;

import java.util.List;

import org.krams.tutorial.domain.User;

public interface IUserService {
	public List<User> getAll();
	public User get(String id);
	public Boolean add(User user);
	public Boolean delete(User user);
	public Boolean edit(User user);
}
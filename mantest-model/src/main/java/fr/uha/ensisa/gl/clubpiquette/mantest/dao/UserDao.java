package fr.uha.ensisa.gl.clubpiquette.mantest.dao;

import java.security.NoSuchProviderException;
import java.util.Collection;

import fr.uha.ensisa.gl.clubpiquette.mantest.User;

public interface UserDao {
	public void persist(User user);
	public void remove(User user);
	public User find(long id);
	public Collection<User> findAll();
	public long count();
	public boolean isAnyConnected();
	public User whoIsConnected();
	public boolean connect(String uname, String pass) throws NoSuchProviderException;
	public void disconnect();
}

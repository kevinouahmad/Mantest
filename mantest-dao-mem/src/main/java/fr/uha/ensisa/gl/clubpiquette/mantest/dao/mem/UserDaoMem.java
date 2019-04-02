package fr.uha.ensisa.gl.clubpiquette.mantest.dao.mem;

import java.security.NoSuchProviderException;
import java.util.Collection;
import java.util.Collections;
import java.util.Iterator;
import java.util.Map;
import java.util.TreeMap;

import fr.uha.ensisa.gl.clubpiquette.mantest.User;
import fr.uha.ensisa.gl.clubpiquette.mantest.dao.UserDao;

public class UserDaoMem implements UserDao {
	private final Map<Long, User> store = Collections.synchronizedMap(new TreeMap<Long, User>());
	private User connectedUser = null;

	@Override
	public void persist(User user) {
		this.store.put(user.getId(), user);
	}

	@Override
	public void remove(User user) {
		this.store.remove(user.getId());
	}

	@Override
	public User find(long id) {
		return this.store.get(id);
	}

	@Override
	public Collection<User> findAll() {
		return this.store.values();
	}

	@Override
	public long count() {
		return this.store.size();
	}

	@Override
	public boolean isAnyConnected() {
		return (this.connectedUser != null);
	}

	@Override
	public User whoIsConnected() {
		return this.connectedUser;
	}

	@Override
	public boolean connect(String uname, String pass) throws NoSuchProviderException {
		Iterator<User> it = this.findAll().iterator();
		while (it.hasNext()) {
			User current = it.next();
			if (current.getUsername().compareTo(uname) == 0) {
				if (current.comparePassword(pass)) {
					this.connectedUser = current;
					return true;
				}
			}
		}
		return false;
	}

	@Override
	public void disconnect() {
		this.connectedUser = null;
	}

}

package fr.uha.ensisa.gl.clubpiquette.mantest.dao;

public interface DaoFactory {
	public TestBookDao getTestBookDao();
	public UserDao getUserDao();
}

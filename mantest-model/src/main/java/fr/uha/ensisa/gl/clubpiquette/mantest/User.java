package fr.uha.ensisa.gl.clubpiquette.mantest;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.security.NoSuchProviderException;
import java.security.SecureRandom;

public class User {
	private long id;
	private String username;
	private String password;
	private String fName;
	private String lName;
	private byte[] salt;
	
	public User(long id, String username, String password, String fName, String lName) {
		super();
		this.id = id;
		this.username = username;
		this.fName = fName;
		this.lName = lName;
		try {
			this.salt = User.newSalt();
			this.setPassword(password);
		}
		catch (Exception e) {
			
		}
	}

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	protected String getPassword() {
		return password;
	}

	public void setPassword(String password) throws NoSuchProviderException {
		this.password = User.crypt(password, this.getSalt());
	}
	
	private byte[] getSalt() {
		return this.salt;
	}
	
	public boolean comparePassword(String pass) throws NoSuchProviderException {
		return (this.getPassword().compareTo(User.crypt(pass, this.getSalt())) == 0);
	}

	public static String crypt(String pass, byte[] salt) throws NoSuchProviderException {
		String generatedPassword = null;
        try {
            // Create MessageDigest instance for MD5
            MessageDigest md = MessageDigest.getInstance("MD5");
            //Add password bytes to digest
            md.update(salt);
            //Get the hash's bytes
            byte[] bytes = md.digest(pass.getBytes());
            //This bytes[] has bytes in decimal format;
            //Convert it to hexadecimal format
            StringBuilder sb = new StringBuilder();
            for(int i=0; i< bytes.length ;i++)
            {
                sb.append(Integer.toString((bytes[i] & 0xff) + 0x100, 16).substring(1));
            }
            //Get complete hashed password in hex format
            generatedPassword = sb.toString();
        }
        catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        }
        return generatedPassword;
	}
	
	private static byte[] newSalt() throws NoSuchAlgorithmException, NoSuchProviderException
    {
        //Always use a SecureRandom generator
        SecureRandom sr = SecureRandom.getInstance("SHA1PRNG", "SUN");
        //Create array for salt
        byte[] salt = new byte[16];
        //Get a random salt
        sr.nextBytes(salt);
        //return salt
        return salt;
    }

	public String getfName() {
		return fName;
	}

	public void setfName(String fName) {
		this.fName = fName;
	}

	public String getlName() {
		return lName;
	}

	public void setlName(String lName) {
		this.lName = lName;
	}
}

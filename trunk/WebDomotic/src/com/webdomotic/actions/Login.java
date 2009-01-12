package com.webdomotic.actions;

import com.opensymphony.xwork2.ActionSupport;
import com.opensymphony.xwork2.ActionContext;
import com.webdomotic.core.*;

import java.security.*;
import java.math.*;
import java.util.*;

/**
 * <p>
 * Validate a user login.
 * </p>
 */
public class Login extends ActionSupport {

	public String execute() throws Exception {
		System.out.println("Validating login");
		Map session = ActionContext.getContext().getSession();
		if(checkLogin()){
	        session.put("name",getUsername());
	        session.put("authorized","yes");
	        System.out.println("Session cree pour "+getUsername());
			return SUCCESS;
		}else{
			System.out.println("login refuse pour "+getUsername());
			addActionError("Invalid user name or password! Please try again!");
			return ERROR;
		}
	}
	/**
	 * Retrouve le login et le mot de passe de tous les utilisateurs
	 */
	private String[][] retrouverUtilisateurs(){
		ServerDB db = new ServerDB(Constants.DBurl, Constants.DBuser, Constants.DBpass);
		String [][] result = db.queryDB("SELECT login, motdepasse FROM utilisateurs");
		db.close();
		return result;
	}
	/**
	 * Verifie le login et le mot de passe et retourne true si c'est ok
	 */
	private Boolean checkLogin(){
		String[][] result=retrouverUtilisateurs();
		for(int i=0; i<result.length-1; i++){
			if(getUsername().equals(result[i+1][0])){
				if(toMD5(getPassword()).equals(result[i+1][1])){
					return true;
				}else{
					return false;
				}
			}
		}
		return false;
	}
	private String toMD5(String value){
		try{
		    MessageDigest m=MessageDigest.getInstance("MD5");
		    m.update(value.getBytes(),0,value.length());
		    return new BigInteger(1,m.digest()).toString(16);
		}catch(Exception e){
			e.printStackTrace();
			addActionError("Java error in toMD5.");
			return "";
		}
	}
	// ---- Username property ----

	/**
	 * <p>
	 * Field to store User username.
	 * </p>
	 * <p/>
	 */
	private String username = null;

	/**
	 * <p>
	 * Provide User username.
	 * </p>
	 * 
	 * @return Returns the User username.
	 */
	public String getUsername() {
		return username;
	}

	/**
	 * <p>
	 * Store new User username
	 * </p>
	 * 
	 * @param value
	 *            The username to set.
	 */
	public void setUsername(String value) {
		username = value;
	}

	// ---- Username property ----

	/**
	 * <p>
	 * Field to store User password.
	 * </p>
	 * <p/>
	 */
	private String password = null;

	/**
	 * <p>
	 * Provide User password.
	 * </p>
	 * 
	 * @return Returns the User password.
	 */
	public String getPassword() {
		return password;
	}

	/**
	 * <p>
	 * Store new User password
	 * </p>
	 * 
	 * @param value
	 *            The password to set.
	 */
	public void setPassword(String value) {
		password = value;
	}

}
package com.webdomotic.actions;

import com.opensymphony.xwork2.ActionSupport;
import com.opensymphony.xwork2.ActionContext;
import com.webdomotic.core.*;

import java.security.*;
import java.util.*;

/**
 * Validate a user login.
 */
public class Login extends ActionSupport {

	private String userid;
	private String isadmin;
	
	public String execute() throws Exception {
		System.out.println("Validating login");
		Map session = ActionContext.getContext().getSession();
		if(checkLogin()){
	        session.put("username",getUsername());
	        session.put("userid",userid);
	        session.put("isadmin",isadmin);
	        session.put("authorized","yes");
	        System.out.println("Session cree pour "+getUsername());
			return SUCCESS;
		}else{
			System.out.println("login refuse pour "+getUsername());
			addActionError("Mot de passe ou nom d'utilisateur errone!");
			return ERROR;
		}
	}
	/**
	 * Retrouve le login et le mot de passe de tous les utilisateurs
	 */
	private String[][] retrouverUtilisateur(String utilisateur){
		ServerDB db = new ServerDB();
		String [][] result = db.queryDB("SELECT login, motdepasse, id, statut FROM utilisateurs WHERE login='"+utilisateur+"'");
		db.close();
		return result;
	}
	/**
	 * Verifie le login et le mot de passe et retourne true si c'est ok
	 */
	private Boolean checkLogin(){
		String[][] result=retrouverUtilisateur(getUsername());
		if(toMD5(getPassword()).equals(result[1][1])){
			userid=result[1][2];
			if(result[1][3] == "administrateur"){
				isadmin="true";
			}else{
				isadmin="false";
			}
			return true;
		}else{
			userid="";
			return false;
		}
	}
	private String toMD5(String value){
		try{
		    MessageDigest m=MessageDigest.getInstance("MD5");
		    m.update(value.getBytes(),0,value.length());
		    return toHex(m.digest());
		}catch(Exception e){
			e.printStackTrace();
			addActionError("Java error in toMD5.");
			return "";
		}
	}
	
	
	
	//helper function
	final private static String digits="0123456789abcdef";
	private static String toHex(byte[] data)
	{
		StringBuffer buf = new StringBuffer();
		for (int i = 0; i != data.length; i++){
			int	v = data[i] & 0xff;
			buf.append(digits.charAt(v >> 4));
			buf.append(digits.charAt(v & 0xf));
		}
		return buf.toString();
	}



	// ---- Username property ----

	/**
	 * Field to store User username.
	 */
	private String username = null;

	/**
	 * Provide User username
	 * @return Returns the User username.
	 */
	public String getUsername() {
		return username;
	}

	/**
	 * Store new User username
	 * @param value
	 *            The username to set.
	 */
	public void setUsername(String value) {
		username = value;
	}

	// ---- Username property ----

	/**
	 * Field to store User password.
	 */
	private String password = null;

	/**
	 * Provide User password.
	 * @return Returns the User password.
	 */
	public String getPassword() {
		return password;
	}

	/**
	 * Store new User password
	 * @param value
	 *            The password to set.
	 */
	public void setPassword(String value) {
		password = value;
	}

}
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
		for(int i=0; i<result.length; i++){
			for(int j=0; j<result[0].length; j++){
				System.out.print(result[i][j]+"\t");
			}
			System.out.println();
		}
		System.out.println(toMD5(getPassword()));
		System.out.println();
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
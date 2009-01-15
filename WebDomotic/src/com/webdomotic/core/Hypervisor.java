package com.webdomotic.core;

import java.util.Map;

import com.opensymphony.xwork2.ActionContext;

public class Hypervisor {
	private static ServerDB db;

	public Hypervisor() {
	}
	/**
	 * Action Edition et Detail
	 */
	public static String[][] getDataArray(String module, String id, String extraWhere){

		String query = genQuery(module, id, extraWhere);
		db = new ServerDB();
		String [][] queryResult = db.queryDB(query);
		db.close();
		//if query is null only have the columns, used by "nouveau"
		if(queryResult.length==1){
			String[][] returnArray = new String[queryResult[0].length][5];
			for(int j=0; j<queryResult[0].length; j++){
				returnArray[j][0]=queryResult[0][j];
				returnArray[j][1]="";
				returnArray[j][2]=getLabel_DB(queryResult[0][j]);
				returnArray[j][3]=getType_DB(queryResult[0][j]);
				returnArray[j][4]=getEditRight_DB(queryResult[0][j]);
			}
			return returnArray;
		}
		//build data array
		String[][] returnArray = new String[(queryResult.length-1)*queryResult[0].length][5];
		//for all columns in queryResult
		for(int j=0; j<queryResult[0].length; j++){
			//for all rows in queryResult
			for(int i=1,k=0; i<queryResult.length; i++, k=k+4){
				returnArray[j+k][0]=queryResult[0][j];
				returnArray[j+k][1]=queryResult[i][j];
				returnArray[j+k][2]=getLabel_DB(queryResult[0][j]);
				returnArray[j+k][3]=getType_DB(queryResult[0][j]);
				returnArray[j+k][4]=getEditRight_DB(queryResult[0][j]);
			}
		}
		return returnArray;
	}

	/**
	 * Retourne un tableau d'objet contenant un string formate en JS Array pour YUI et un tableau 2D
	 * contenant le nom, le label, le type et le view right.
	 * 
	 * Utilise par Liste.java
	 */
	public static Object[] getDataJSArray(String module, String id, String extraWhere){
		String query = genQuery(module, id, extraWhere);
		db = new ServerDB();
		String [][] queryResult = db.queryDB(query);
		db.close();

		Object[] returnObject = new Object[2];
		if(queryResult.length>1){
			//build JSArray
			StringBuffer js_array = new StringBuffer("[");
			for(int i=1; i<queryResult.length;i++){
				js_array.append("{");
				for(int j=0; j<queryResult[0].length; j++){
					js_array.append(queryResult[0][j]+": ");
					js_array.append("\'"+queryResult[i][j]+"\', ");
				}
				js_array.append("edit_button: \'Edition\', ");
				js_array.append("detail_button: \'Detail\', ");
				js_array.append("delete_button: \'Supprimer\' ");
				//js_array.deleteCharAt(js_array.lastIndexOf(","));
				js_array.append("},");

			}
			js_array.deleteCharAt(js_array.lastIndexOf(","));
			js_array.append("]");

			returnObject[0]=js_array.toString();
		}
		else{
			System.err.println("getDataJSArray: Pas de donnees retournee.");
			returnObject[0]="";
		}

		if(queryResult[0].length>0){
			//build mapping
			String [][] mapping = new String[queryResult[0].length][4];
			//for all the columns
			for(int i=0; i<queryResult[0].length; i++){
				mapping[i][0]=queryResult[0][i];
				mapping[i][1]=getLabel_DB(queryResult[0][i]);
				mapping[i][2]=getType_DB(queryResult[0][i]);
				if(!isAdmin()){
					mapping[i][3]=getViewRight_DB(queryResult[0][i]);
				}else{
					mapping[i][3]="true";
				}
			}
			returnObject[1]=mapping;
		}else{
			System.err.println("getDataJSArray: Pas de Header de colonne retourne.");
			returnObject[1]=null;
		}
		return returnObject;
	}
	
	
	public static boolean saveQuery(Map response,String module){
		StringBuffer query = new StringBuffer("UPDATE "+getDBTableName_mod(module)+" SET ");
		//build query
		for(int i=0; i<Constants.g_mapping_DB_col.length; i++){
			if(response.containsKey(Constants.g_mapping_DB_col[i][0])){
				
				query.append(Constants.g_mapping_DB_col[i][0]+" = "); //append column name
				query.append("\'"+(String)response.get(Constants.g_mapping_DB_col[i][0])+"\',"); //append value
			}
		}
		query.deleteCharAt(query.lastIndexOf(","));
		query.append(" WHERE id = "+response.get("id"));
		
		System.out.println(query.toString());
		
		//send update query
		/*db = new ServerDB();
		int count = db.UpdateDB(query.toString());
		db.close();*/
		return true;
		
	}
	
	
	public static Boolean deleteRow(String id, String module){
		if(!isOwner(id, module)){
			return false;
		}
		String query="DELETE FROM "+getDBTableName_mod(module)+" WHERE id = '"+id+"' LIMIT 1";
		db = new ServerDB();
		int queryResult = db.UpdateDB(query);
		db.close();
		if(queryResult > -1){
			return true;
		}
		return false;
	}
	private static Boolean isOwner(String id, String module){
		if(isAdmin()){
			return true;
		}
		String query=genQuery(module, id, "");
		db = new ServerDB();
		String [][] queryResult = db.queryDB(query);
		db.close();
		if(queryResult.length>1){
			return true;
		}
		return false;
	}
	private static Boolean isAdmin(){
		Map session = ActionContext.getContext().getSession();
		String isadmin = (String)session.get("isadmin");
		if(isadmin.equals("true")){
			return true;
		}
		return false;
	}

	private static String genQuery(String module, String id, String extraWhere){
		boolean where=false;
		String query="SELECT "+getDBTableName_mod(module)+".* FROM "+getDBTableName_mod(module);
		String privilegeQuery=privilegeQuery(module);
		if(privilegeQuery.toUpperCase().contains("WHERE")){
			where=true;
		}
		query+=privilegeQuery;       
		if(id!=null && !id.equals("")){
			if(where){
				query+=" AND id='"+id+"'";
			}else{
				query+=" WHERE id='"+id+"'";
				where=true;
			}
		}
		if(extraWhere != null && !extraWhere.equals("")){
			if(where){
				query+=" AND "+extraWhere;
			}else{
				query+=" WHERE "+extraWhere;
			}
		}
		return query;
	}

	private static String privilegeQuery(String module){
		if(!isAdmin()){
			Map session = ActionContext.getContext().getSession();
			String userid = (String)session.get("userid");
			if(module.equals("maison") || module.equals("profil") || module.equals("regle")){
				return " WHERE utilisateur_id = '"+userid+"'";
			}else if(module.equals("piece") || module.equals("console")){
				return " INNER JOIN maisons ON pieces.maison_id = maisons.id WHERE maisons.utilisateur_id = '"+userid+"'";
			}else if(module.equals("peripherique")){
				return " INNER JOIN pieces ON peripheriques.piece_id = piece.id INNER JOIN maisons ON pieces.maison_id = maisons.id WHERE maisons.utilisateur_id = '"+userid+"'";
			}else if(module.equals("utilisateur")){
				return " WHERE id = '"+userid+"'";
			}else{
				return "";
			}
		}else{
			return "";
		}
	}


	public static String getModuleName_mod(String input){
		return searchMapping(Constants.g_mapping_mod, input, Constants.MODULE);
	}

	public static String getLabel_mod(String input){
		return searchMapping(Constants.g_mapping_mod, input, Constants.LABEL);
	}

	public static String getAdminLabel_mod(String input){
		return searchMapping(Constants.g_mapping_mod, input, Constants.ADMIN_LABEL);
	}

	public static String getDBTableName_mod(String input){
		return searchMapping(Constants.g_mapping_mod, input, Constants.DB_TABLE);
	}


	public static String getFieldName_DB(String input){
		return searchMapping(Constants.g_mapping_DB_col, input, Constants.FIELD_NAME);
	}

	public static String getLabel_DB(String input){
		return searchMapping(Constants.g_mapping_DB_col, input, Constants.LABEL);
	}

	public static String getType_DB(String input){
		return searchMapping(Constants.g_mapping_DB_col, input, Constants.TYPE);
	}

	public static String getEditRight_DB(String input){
		return searchMapping(Constants.g_mapping_DB_col, input, Constants.EDIT_RIGHT);
	}

	public static String getViewRight_DB(String input){
		return searchMapping(Constants.g_mapping_DB_col, input, Constants.VIEW_RIGHT);
	}



	private static String searchMapping(String[][] mapping_table, String input,int return_type){

		//for all the rows
		for(int i=0; i<mapping_table.length; i++){
			//for all the columns
			for(int j=0; j<mapping_table[0].length; j++){
				if(mapping_table[i][j].equals(input) && j!=return_type)
					return mapping_table[i][return_type];
			}
		}
		return null;
	}

}

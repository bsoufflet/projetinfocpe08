package com.webdomotic.core;

import java.util.Map;

import com.opensymphony.xwork2.ActionContext;

public class Hypervisor {
	private static ServerDB db;

	public Hypervisor() {
	}
	/**
	 * Action Edition et Detail
	 * Cette fonction retourne les donnees necessaires pour la creation 
	 * des formulaire de detail et d'edition.
	 * Elle retourne les donnees pour 1 element.
	 * 
	 * Retourne pour 1 ID et 1 module et pour chaque champ de la DB:
	 * nom		valeur		label		type	editRight
	 * nom		valeur		label		type	editRight
	 * nom		valeur		label		type	editRight
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
	 * Object[0]=(String)JavaScriptArray cf yui
	 * Object[1]=(String[][])={nom, label, type, view right}
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
	/**
	 * Retourne un tableau d'objet contenant un string formate en JS Array pour YUI et un tableau 2D
	 * contenant le nom, le label, le type et le view right.
	 * 
	 * Utilise par le panel de detail (regle)
	 */
	public static Object[] getDataPanelJSArray(String module, String id){
		String query="SELECT peripherique_id FROM regles_peripheriques WHERE regle_id='"+id+"'";
		db = new ServerDB();
		String [][] queryResult = db.queryDB(query);
		db.close();
		Object[] returnObject = new Object[2];
		if(queryResult.length<=1){
			query="SELECT * FROM peripheriques WHERE 1=2";
		}else{
			query="SELECT * FROM peripheriques WHERE ";
			for(int i=1; i<queryResult.length; i++){
				query+="id='"+queryResult[i][0]+"' OR ";
			}
			query=query.substring(0, query.lastIndexOf(" OR "));
		}
		db = new ServerDB();
		String[][] queryResult2 = db.queryDB(query);
		db.close();
		if(queryResult2.length>1){
			//build JSArray
			StringBuffer js_array = new StringBuffer("[");
			for(int i=1; i<queryResult2.length;i++){
				js_array.append("{");
				for(int j=0; j<queryResult2[0].length; j++){
					js_array.append(queryResult2[0][j]+": ");
					js_array.append("\'"+queryResult2[i][j]+"\', ");
				}
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

		if(queryResult2[0].length>0){
			//build mapping
			String [][] mapping = new String[queryResult2[0].length][4];
			//for all the columns
			for(int i=0; i<queryResult2[0].length; i++){
				mapping[i][0]=queryResult2[0][i];
				mapping[i][1]=getLabel_DB(queryResult2[0][i]);
				mapping[i][2]=getType_DB(queryResult2[0][i]);
				if(!isAdmin()){
					mapping[i][3]=getViewRight_DB(queryResult2[0][i]);
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
	/**
	 * Cette methode retourne un tableau [id, nom, identifiant(seulement pour peripherique)] contenant
	 * tous les elements d'un module.
	 * Cette fonction ne renvoie que les elements appartenant a l'utilisateur courant.
	 */
	public static synchronized String[][] getData(String module){
		String query = genQuery(module, "", "");
		db = new ServerDB();
		String [][] queryResult = db.queryDB(query);
		db.close();
		String[][] retourArray = new String[queryResult.length-1][3];
		if(queryResult.length>1){
			for(int i=1; i<queryResult.length; i++){
				for(int j=0; j<queryResult[0].length; j++){
					if(queryResult[0][j].equals("id")){
						retourArray[i-1][0]=queryResult[i][j];
					}else if(queryResult[0][j].equals("nom")){
						retourArray[i-1][1]=queryResult[i][j];
					}else if(queryResult[0][j].equals("identifiant")){
						retourArray[i-1][2]=queryResult[i][j];
					}
				}
			}
		}else{
			System.err.println("getData: Pas de donnees retourne.");
			retourArray=null;
		}
		return retourArray;
	}
	
	/**
	 * saves query and returns the new/updated ID
	 */
	public static String saveQuery(Map request,String module, String itemId){

		StringBuffer query;
		//test if new
		if(itemId.equals("0"))
			query = new StringBuffer("INSERT INTO "+getDBTableName_mod(module)+" SET ");
		else
			query = new StringBuffer("UPDATE "+getDBTableName_mod(module)+" SET ");

		//build query
		for(int i=0; i<Constants.g_mapping_DB_col.length; i++){
			if(request.containsKey(Constants.g_mapping_DB_col[i][0])){
				if(Constants.g_mapping_DB_col[i][0].equals("motdepasse")){
					if(((String[])request.get("passchange"))[0].equals("true")){
						query.append(Constants.g_mapping_DB_col[i][0]+" = MD5("); //append column name
						query.append("\'"+fixAp(((String[])request.get(Constants.g_mapping_DB_col[i][0]))[0])+"\'),"); //append value
					}
				}else if(Constants.g_mapping_DB_col[i][0].contains("_id")){
					if(!isOwner(((String[])request.get(Constants.g_mapping_DB_col[i][0]))[0], getModuleName_mod((Constants.g_mapping_DB_col[i][0])))){
						return "-1";
					}else{
						query.append(Constants.g_mapping_DB_col[i][0]+" = "); //append column name
						query.append("\'"+fixAp(((String[])request.get(Constants.g_mapping_DB_col[i][0]))[0])+"\',"); //append value
					}
				}else{//general names
					query.append(Constants.g_mapping_DB_col[i][0]+" = "); //append column name
					query.append("\'"+fixAp(((String[])request.get(Constants.g_mapping_DB_col[i][0]))[0])+"\',"); //append value
				}
			}
		}
		query.deleteCharAt(query.lastIndexOf(","));
		//if not a new element
		if(!itemId.equals("0")){
			query.append(" WHERE id = "+((String[])request.get("id"))[0]);
		}
		
		//send update query
		db = new ServerDB();
		String new_id = db.UpdateDB(query.toString());
		db.close();
		
		if(itemId.equals("0"))
			return new_id;
		else
			return ((String[])request.get("id"))[0];
	}
	/**
	 * Cette methode permet de sauver une nouvelle relation n-n.
	 * Le formulaire doit posseder un champ relationship_name et les deux champs de la relation n-n.
	 * Return: ID de la record cree
	 */
	public static String addRelationship(Map request){
		String rel_name=((String[])request.get("relationship_name"))[0];
		String query="INSERT INTO "+getDBTable_Rel(rel_name)+
			" SET "+getField1_Rel(rel_name)+"='"+((String[])request.get(getField1_Rel(rel_name)))[0]+"', "+
			getField2_Rel(rel_name)+"='"+((String[])request.get(getField2_Rel(rel_name)))[0]+"'";
		//send update query
		db = new ServerDB();
		String new_id = db.UpdateDB(query);
		db.close();
		return new_id;
	}
	/**
	 * Supprime de la DB l'element d'id id et de module module et 
	 * tous les elements associes (par une relation 1-n) a cet element.
	 */
	public static Boolean deleteRow(String id, String module){
		if(!isOwner(id, module)){
			return false;
		}
		String StringRelatedModules=getRelatedModules_mod(module);
		if(!StringRelatedModules.equals("")){
			String[] relatedModules=StringRelatedModules.split(",");
			for(int i=0; i<relatedModules.length; i++){
				db = new ServerDB();
				String [][] queryResult = db.queryDB("SELECT id FROM "+getDBTableName_mod(relatedModules[i])+" WHERE "+getRelationField_mod(module)+"='"+id+"'");
				db.close();
				if(queryResult.length>1){
					for(int j=1; j<queryResult.length; j++){
						if(deleteRow(queryResult[j][0], relatedModules[i]) == false) return false;
					}
				}
				
			}
		}
		String query="DELETE FROM "+getDBTableName_mod(module)+" WHERE id='"+id+"'";
		db = new ServerDB();
		String st = db.UpdateDB(query);
		db.close();
		if(st.equals(""))
			return false;
		else
			return true;
	}
	
	/**
	 * Cette methode supprime une relation n-n entre les champs id1 et id2 dans la relation relationship_name
	 * ATTENTION: cette fonction n'est pas prete a recevoir d'autres relation que regles_peripheriques
	 */
	public static Boolean deleteRelationship(String id2, String id1, String relationship_name){
		String query="DELETE FROM "+getDBTable_Rel(relationship_name)+" WHERE "+getField1_Rel(relationship_name)+"='"+id1+"' AND "+getField2_Rel(relationship_name)+"='"+id2+"'";
		db = new ServerDB();
		String st = db.UpdateDB(query);
		db.close();
		if(st.equals(""))
			return false;
		else
			return true;
	}
	/**
	 * Retourne true si l'utilisateur courant possede l'element id du module module.
	 * Retourne false sinon
	 * Retourne true si l'utilisateur est admin.
	 */
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
	/**
	 * Retourne true si l'utilisateur courant est administrateur.
	 */
	private static Boolean isAdmin(){
		Map session = ActionContext.getContext().getSession();
		String isadmin = (String)session.get("isadmin");
		if(isadmin.equals("true")){
			return true;
		}
		return false;
	}
	/**
	 * Cette methode retourne un string contenant la query permettant de retouver l'element id ou
	 * tous les elements si id est null du module module.
	 * Cette methode prend en compte les droits d'acces a l'element via isowner.
	 */
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
				query+=" AND "+getDBTableName_mod(module)+".id='"+id+"'";
			}else{
				query+=" WHERE "+getDBTableName_mod(module)+".id='"+id+"'";
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
	/**
	 * Cette methode renvoie le string a rajouter a une query pour qu'elle n'affiche que les elements possedes.
	 */
	private static String privilegeQuery(String module){
		if(!isAdmin()){
			Map session = ActionContext.getContext().getSession();
			String userid = (String)session.get("userid");
			if(module.equals("maison") || module.equals("profil") || module.equals("regle")){
				return " WHERE utilisateur_id = '"+userid+"'";
			}else if(module.equals("piece") || module.equals("console")){
				return " INNER JOIN maisons ON "+getDBTableName_mod(module)+".maison_id = maisons.id WHERE maisons.utilisateur_id = '"+userid+"'";
			}else if(module.equals("peripherique")){
				return " INNER JOIN pieces ON peripheriques.piece_id = pieces.id INNER JOIN maisons ON pieces.maison_id = maisons.id WHERE maisons.utilisateur_id = '"+userid+"'";
			}else if(module.equals("compte")){
				return " WHERE id = '"+userid+"'";
			}else{
				return "";
			}
		}else{
			return "";
		}
	}
	/**
	 * remplace les ' par des " "
	 */
	private static String fixAp(String s){
		return s.replace("'"," ");
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
	
	public static String getRelatedModules_mod(String input){
		return searchMapping(Constants.g_mapping_mod, input, Constants.RELATED_MODULES);
	}
	
	public static String getRelationField_mod(String input){
		return searchMapping(Constants.g_mapping_mod, input, Constants.RELATION_FIELD);
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
	public static String getField1_Rel(String input){
		return searchMapping(Constants.g_mapping_DB_rel, input, Constants.FIELD1);
	}
	public static String getField2_Rel(String input){
		return searchMapping(Constants.g_mapping_DB_rel, input, Constants.FIELD2);
	}
	public static String getDBTable_Rel(String input){
		return searchMapping(Constants.g_mapping_DB_rel, input, Constants.DB_TABLE);
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

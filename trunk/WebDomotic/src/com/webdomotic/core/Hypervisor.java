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
	public static String[][] getDataArray(String module, String action, String id, String extraWhere){
	
		String query = genQuery(module, action, id, extraWhere);
		db = new ServerDB();
		String [][] queryResult = db.queryDB(query);
		db.close();
		
		if(queryResult.length==1)
			return null;

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
	 * Action List
	 */
	public static Object[] getDataJSArray(String module, String action, String id, String extraWhere){

		String query = genQuery(module, action, id, extraWhere);
		db = new ServerDB();
		String [][] queryResult = db.queryDB(query);
		db.close();

		//build JSArray
		StringBuffer js_array = new StringBuffer("[\n");
		for(int i=1; i<queryResult.length;i++){
			js_array.append("{");
			for(int j=0; j<queryResult[0].length; j++){
				js_array.append(queryResult[0][j]+": ");
				js_array.append("\'"+queryResult[i][j]+"\', ");
			}
			js_array.deleteCharAt(js_array.lastIndexOf(","));
			js_array.append("},\n");
			
		}
		js_array.deleteCharAt(js_array.lastIndexOf(","));
		js_array.append("]");
		
		//build mapping
		String [][] mapping = new String[queryResult[0].length][3];
		//for all the columns
		for(int i=0; i<queryResult[0].length; i++){
			mapping[i][0]=queryResult[0][i];
			mapping[i][1]=getLabel_DB(queryResult[0][i]);
			mapping[i][2]=getType_DB(queryResult[0][i]);
		}
		
		//map results to returnObject 
		Object[] returnObject = new Object[2];
		returnObject[0]=js_array.toString();
		returnObject[1]=mapping;
		
		return returnObject;
	}

    private static String genQuery(String module, String action, String id, String extraWhere){
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
        Map session = ActionContext.getContext().getSession();
        String isadmin = (String)session.get("isadmin");
        if(!isadmin.equals("true")){
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

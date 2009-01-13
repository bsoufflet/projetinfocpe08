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

		//build data array
		
		
		
		
		
		
		return null;
	}
	
	/**
	 * Action List
	 */
	public static Object[] getDataXML(String module, String action, String id, String extraWhere){

		String query = genQuery(module, action, id, extraWhere);
		db = new ServerDB();
		String [][] queryResult = db.queryDB(query);
		db.close();

		//build XML document
		
		
		return null;
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
                return " WHERE utilisateurs_id = '"+userid+"'";
            }else if(module.equals("piece") || module.equals("console")){
                return "INNER JOIN maisons ON pieces.maisons_id = maisons.id WHERE maisons.utilisateurs_id = '"+userid+"'";
            }else if(module.equals("peripherique")){
                return "INNER JOIN pieces ON peripheriques.pieces_id = pieces.id INNER JOIN maisons ON pieces.maisons_id = maisons.id WHERE maisons.utilisateurs_id = '"+userid+"'";
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

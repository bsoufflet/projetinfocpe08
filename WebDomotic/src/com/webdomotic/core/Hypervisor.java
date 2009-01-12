package com.webdomotic.core;

import java.util.Map;

import com.opensymphony.xwork2.ActionContext;

public class Hypervisor {
	private static ServerDB db;
	private static String query;
	private static String [][] queryResult;
	
	
	public Hypervisor() {
	}
	
	public static String[][] getDataArray(String module, String action, String id, String extraWhere){
	
		return null;
	}
	public static String getDataXML(String module, String action, String id, String extraWhere){
		
		return null;
	}
	
    private static void genQuery(String module, String action, String id, String extraWhere){
    	boolean where=false;
        query="SELECT "+getDBTableName(module)+".* FROM "+getDBTableName(module);
        String privilegeQuery=privilegeQuery(module);
        if(privilegeQuery.toUpperCase().contains("WHERE")){
        	where=true;
        }
        query+=privilegeQuery;       
        if(id!=null && id!=""){
            if(where){
            	query+=" AND id='"+id+"'";
            }else{
            	query+=" WHERE id='"+id+"'";
            }
        }
        if(where){
        	query+=" AND "+extraWhere;
        }else{
        	query+=" WHERE "+extraWhere;
        }
    }

    private static String privilegeQuery(String module){
        Map session = ActionContext.getContext().getSession();
        String isadmin = (String)session.get("isadmin");
        if(isadmin == "true"){
            String userid = (String)session.get("userid");
            if(module == "maison"){
                return " WHERE utilisateurs_id = '"+userid+"'";
            }else if(module == "piece" || module == "console"){
                return "INNER JOIN maisons ON pieces.maisons_id = maisons.id WHERE maisons.utilisateurs_id = '"+userid+"'";
            }else if(module == "peripherique"){
                return "INNER JOIN pieces ON peripheriques.pieces_id = pieces.id INNER JOIN maisons ON pieces.maisons_id = maisons.id WHERE maisons.utilisateurs_id = '"+userid+"'";
            }else if(module == "utilisateur"){
                return " WHERE id = '"+userid+"'";
            }else{
                return "";
            }
        }else{
            return "";
        }
    }
	
	private static void performQuery(){
		queryResult=db.queryDB(query);
	}
	

	
	public static String getModuleName(String input){
		return searchMapping(input, Constants.MODULE);
	}

	public static String getLabelName(String input){
		return searchMapping(input, Constants.LABEL);
	}

	public static String getAdminLabelName(String input){
		return searchMapping(input, Constants.ADMIN_LABEL);
	}

	public static String getDBTableName(String input){
		return searchMapping(input, Constants.DB_TABLE);
	}

	
	private static String searchMapping(String input,int return_type){
		
		//for all the rows
		for(int i=0; i<Constants.g_mapping_mod.length; i++){
			//for all the columns
			for(int j=0; j<Constants.g_mapping_mod[0].length; j++){
				if(Constants.g_mapping_mod[i][j].equals(input) && j!=return_type)
					return Constants.g_mapping_mod[i][return_type];
			}
		}
		return null;
	}
	
}

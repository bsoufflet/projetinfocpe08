package com.webdomotic.core;

import java.util.Map;

import com.opensymphony.xwork2.ActionContext;

public class Hypervisor {
	private ServerDB db;
	private String query;
	private String [][] queryResult;
	
	
	public Hypervisor() {
	}
	
	public String[][] getDataArray(){
	
		return null;
	}
	public String getDataXML(){
		
		return null;
	}
	
    private void genQuery(String module, String action, String id){
        query="SELECT "+getDBTableName(module)+".* FROM "+getDBTableName(module);
        String privilegeQuery=privilegeQuery(module);
        query+=privilegeQuery;       
        if(id!=null && id!=""){
            if(privilegeQuery == ""){
                query+=" WHERE id='"+id+"'";
            }else{
                query+=" AND id='"+id+"'";
            }
        } 
    }

    private String privilegeQuery(String module){
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
	
	private void performQuery(){
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
		for(int i=0; i<Constants.g_mapping.length; i++){
			//for all the columns
			for(int j=0; j<Constants.g_mapping[0].length; j++){
				if(Constants.g_mapping[i][j].equals(input) && j!=return_type)
					return Constants.g_mapping[i][return_type];
			}
		}
		return null;
	}
	
}
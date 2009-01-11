package com.webdomotic.objects;


import com.webdomotic.core.Constants;

public class DomoticObject {


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
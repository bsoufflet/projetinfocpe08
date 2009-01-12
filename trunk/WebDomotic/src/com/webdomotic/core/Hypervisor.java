package com.webdomotic.core;

import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

import com.webdomotic.objects.*;

public class Hypervisor {
	private ServerDB db;
	private DomoticObject [] t_domObj;
	private String query;
	private String [][] queryResult;
	public Hypervisor() {
		// TODO Auto-generated constructor stub
	}
	public DomoticObject[] init(String module, String action, String id) throws SecurityException, IllegalArgumentException, NoSuchMethodException, InvocationTargetException{
		genQuery(module, action, id);
		db=new ServerDB(Constants.DBurl, Constants.DBuser, Constants.DBpass);
		performQuery();
		try {
			populateDomObjs(module);
		} catch (InstantiationException e) {
			e.printStackTrace();
		} catch (IllegalAccessException e) {
			e.printStackTrace();
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
		return t_domObj;
	}
	private void genQuery(String module, String action, String id){
		query="SELECT * FROM "+DomoticObject.getDBTableName(module);
		
	}
	private void performQuery(){
		queryResult=db.queryDB(query);
		t_domObj=new DomoticObject [queryResult.length-1];
	}
	private void populateDomObjs(String module) throws InstantiationException, IllegalAccessException, ClassNotFoundException, SecurityException, NoSuchMethodException, IllegalArgumentException, InvocationTargetException{
		Class myClass = Class.forName("com.webdomotic.objects."+module);
		for(int i=1; i<queryResult.length; i++){
/*			
			Constructor con = myClass.getConstructor(Integer.TYPE, String.class);
			DomoticObject o = (DomoticObject) con.newInstance(new Object[]{new Integer(27), "Hi !"});
*/
			DomoticObject o = (DomoticObject) myClass.newInstance();
			for(int j=0; j<queryResult[0].length; j++){
				String methodName = "set"+queryResult[0][j];
				Method method = myClass.getMethod(methodName, String.class);
				method.invoke(o, queryResult[i][j]);
			}
			t_domObj[i]=(DomoticObject)o;
		}
	}
}

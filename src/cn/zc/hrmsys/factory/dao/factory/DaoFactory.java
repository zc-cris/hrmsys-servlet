package cn.zc.hrmsys.factory.dao.factory;

import java.util.HashMap;
import java.util.Map;

public class DaoFactory {
	
	private static DaoFactory daoFactory = null;
	private DaoFactory() {}
	public static DaoFactory newInstance() {
		if(daoFactory == null) {
			daoFactory = new DaoFactory();
		}
		return daoFactory;
	}
	//专门用来存储配置参数的map集合
	private Map<String, String> map = new HashMap<>();
	public void setKey(String key,String value) {
		this.map.put(key, value);
	}
	public String getKey(String key) {
		return this.map.get(key);
	}
	
	public Object getDaoEntity(String daoName){
		Object obj = null;
		try {
			obj = Class.forName(this.map.get(daoName)).newInstance();
		} catch (ClassNotFoundException | InstantiationException | IllegalAccessException e) {
			e.printStackTrace();
		}
		return obj;
	}
}

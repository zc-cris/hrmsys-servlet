package cn.zc.hrmsys.factory.service.factory;

import java.util.HashMap;
import java.util.Map;

public class ServiceFactory {
	
	private static ServiceFactory serviceFactory = null;
	private ServiceFactory() {}
	public static ServiceFactory newInstance() {
		if(serviceFactory == null) {
			serviceFactory = new ServiceFactory();
		}
		return serviceFactory;
	}
	//专门用来存储配置参数的map集合
	private Map<String, String> map = new HashMap<>();
	public void setKey(String key,String value) {
		this.map.put(key, value);
	}
	public String getKey(String key) {
		return this.map.get(key);
	}
	public Object getServiceEntity(String serviceName) {
		Object obj = null;
		try {
			obj = Class.forName(this.map.get(serviceName)).newInstance();
		} catch (ClassNotFoundException | InstantiationException | IllegalAccessException e) {
			e.printStackTrace();
		}
		return obj;
	}
}

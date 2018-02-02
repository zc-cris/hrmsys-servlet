package cn.zc.hrmsys.listener;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Map.Entry;
import java.util.Properties;

import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import javax.servlet.annotation.WebListener;

import cn.zc.hrmsys.factory.dao.factory.DaoFactory;
import cn.zc.hrmsys.factory.service.factory.ServiceFactory;
import cn.zc.hrmsys.util.UploadParamsUtils;

/**
 * 	
 * @ClassName：SettingParamsListener.java
 * @Description：TODO (创建一个监听器初始化配置参数)
 * @Project Name：hrmsys
 * @Package Name: cn.zc.hrmsys.listener
 * @Author：zc-cris
 * @Create Date：2018年2月1日下午3:05:36
 * @version: v1.0
 * @Copyright: zc-cris
 * @mail: 17623887386@163.com
 */
@WebListener
public class SettingParamsListener implements ServletContextListener {
	
	/**
	 * 
	 * @MethodName: contextInitialized
	 * @Description: TODO (应用加载的时候就配置系统需要的参数)
	 * @see javax.servlet.ServletContextListener#contextInitialized(javax.servlet.ServletContextEvent)
	 * @Author：zc-cris
	 * @Create Date：2018年2月1日下午3:08:35
	 */
    public void contextInitialized(ServletContextEvent sce)  { 
    	Properties pro = new Properties();
    	initParams("/uploadParams.properties",UploadParamsUtils.newInstance(),pro);
    	initParams("/service.properties",ServiceFactory.newInstance(),pro);
    	initParams("/daoParams.properties",DaoFactory.newInstance(),pro);
//    	System.out.println(DaoFactory.newInstance().getKey("UserDao"));
//    	System.out.println(ServiceFactory.newInstance().getKey("UserService"));
//    	System.out.println(UploadParamsUtils.newInstance().getKey("type"));
    	
    }
    /**
     * 
     * @MethodName: initParams
     * @Description: TODO (获取上传文件的参数限制)
     * @param path
     * @param uploadParamsUtils
     * @param pro
     * @param in
     * @Return Type: void
     * @Author: zc-cris
     * @Create Date：2018年2月1日下午3:07:11
     * @since
     * @throws
     */
	public void initParams(String path, UploadParamsUtils uploadParamsUtils, Properties pro) {
		//可以把配置文件放在javaee工程的根目录下的source folder下，即和src同级，系统会自动扫描这个文件夹
    	//所以路径名和放在src文件夹下一样
    	
    	try(InputStream in = getClass().getClassLoader().getResourceAsStream(path)) {
			pro.load(in);
				for (Entry<Object, Object> entry : pro.entrySet()) {
					String key = (String) entry.getKey();
					String value = (String) entry.getValue();
					uploadParamsUtils.setKey(key, value);
				}
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	/**
	 * 
	 * @MethodName: initParams
	 * @Description: TODO (获取dao层的路径参数)
	 * @param path
	 * @param daoFactory
	 * @param pro
	 * @param in
	 * @Return Type: void
	 * @Author: zc-cris
	 * @Create Date：2018年2月1日下午3:07:39
	 * @since
	 * @throws
	 */
	public void initParams(String path, DaoFactory daoFactory, Properties pro) {
		//可以把配置文件放在javaee工程的根目录下的source folder下，即和src同级，系统会自动扫描这个文件夹
    	//所以路径名和放在src文件夹下一样
    	
    	try (InputStream in = getClass().getClassLoader().getResourceAsStream(path)){
			pro.load(in);
				for (Entry<Object, Object> entry : pro.entrySet()) {
					String key = (String) entry.getKey();
					String value = (String) entry.getValue();
					daoFactory.setKey(key, value);
				}
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	/**
	 * 
	 * @MethodName: initParams
	 * @Description: TODO (获取service层的路径参数)
	 * @param path
	 * @param serviceFactory
	 * @param pro
	 * @param in
	 * @Return Type: void
	 * @Author: zc-cris
	 * @Create Date：2018年2月1日下午3:08:05
	 * @since
	 * @throws
	 */
	public void initParams(String path, ServiceFactory serviceFactory, Properties pro) {
		//可以把配置文件放在javaee工程的根目录下的source folder下，即和src同级，系统会自动扫描这个文件夹
		//所以路径名和放在src文件夹下一样
		
		try (InputStream in = getClass().getClassLoader().getResourceAsStream(path)){
			pro.load(in);
			for (Entry<Object, Object> entry : pro.entrySet()) {
				String key = (String) entry.getKey();
				String value = (String) entry.getValue();
				serviceFactory.setKey(key, value);
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}

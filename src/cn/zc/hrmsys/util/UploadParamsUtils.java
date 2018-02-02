package cn.zc.hrmsys.util;

import java.util.HashMap;
import java.util.Map;

/**
 * 	
 * @ClassName：UploadParamsUtils.java
 * @Description：TODO (存储上传文件的参数的工具类)
 * @Project Name：hrmsys
 * @Package Name: cn.zc.hrmsys.util
 * @Author：zc-cris
 * @Create Date：2018年2月1日下午2:01:51
 * @version: v1.0
 * @Copyright: zc-cris
 * @mail: 17623887386@163.com
 */
public class UploadParamsUtils {
	
	private static UploadParamsUtils uploadParamsUtils = null;
	private UploadParamsUtils() {}
	public static UploadParamsUtils newInstance() {
		if(uploadParamsUtils == null) {
			uploadParamsUtils = new UploadParamsUtils();
		}
		return uploadParamsUtils;
	}
	
	//专门用来存储配置参数的map集合
	private Map<String, String> map = new HashMap<>();
	
	public void setKey(String key,String value) {
		this.map.put(key, value);
	}
	public String getKey(String key) {
		return this.map.get(key);
	}
}

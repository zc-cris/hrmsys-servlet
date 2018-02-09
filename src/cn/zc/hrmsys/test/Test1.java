package cn.zc.hrmsys.test;

import java.lang.reflect.InvocationTargetException;

import org.apache.commons.beanutils.BeanUtils;
import org.junit.jupiter.api.Test;

import cn.zc.hrmsys.pojo.entity.FileBean;

public class Test1 {

	@Test
	void test2() throws IllegalAccessException, InvocationTargetException, NoSuchMethodException {
		FileBean bean = new FileBean();
//		fun1(bean);
		fun2(bean);
		System.out.println(bean.getFileDesc());
	}
	void fun1(FileBean bean) {
//		bean.setFileDesc("fafaffa");
	}
	void fun2(FileBean bean) throws IllegalAccessException, InvocationTargetException, NoSuchMethodException {
		BeanUtils.setProperty(bean, "fileDesc", "james");
	}
	
}

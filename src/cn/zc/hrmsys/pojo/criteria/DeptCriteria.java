package cn.zc.hrmsys.pojo.criteria;

/**
 * 	
 * @ClassName：DeptCriteria.java
 * @Description：TODO (根据部门名称和部门有效状态进行部门查询的对象)
 * @Project Name：hrmsys
 * @Package Name: cn.zc.hrmsys.pojo.Criteria
 * @Author：zc-cris
 * @Create Date：2018年1月31日上午9:04:13
 * @version: v1.0
 * @Copyright: zc-cris
 * @mail: 17623887386@163.com
 */
public class DeptCriteria {

	private String deptName;
	private Boolean deptState;
	
	public DeptCriteria() {
		super();
		
	}
	public DeptCriteria(String deptName, Boolean deptState) {
		super();
		this.deptName = deptName;
		this.deptState = deptState;
	}
	public String getDeptName() {
		return deptName == null ?"%%":"%"+deptName+"%";
	}
	public void setDeptName(String deptName) {
		this.deptName = deptName;
	}
	public Boolean getDeptState() {
		return deptState == null ?true:deptState;
	}
	public void setDeptState(Boolean deptState) {
		this.deptState = deptState;
	}
	
}

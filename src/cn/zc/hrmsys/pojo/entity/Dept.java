package cn.zc.hrmsys.pojo.entity;

/**
 * 
 * @ClassName：Dept.java
 * @Description：TODO (部门表对应的实例)
 * @Project Name：hrmsys
 * @Package Name: cn.zc.hrmsys.pojo
 * @Author：zc-cris
 * @Create Date：2018年1月30日下午9:10:42
 * @version: v1.0
 * @Copyright: zc-cris
 * @mail: 17623887386@163.com
 */
public class Dept {

	private Integer deptId;
	private String deptName;
	private String deptDesc;
	private Boolean deptState;
	public Dept() {
		super();
	}
	public Dept( String deptName, String deptDesc) {
		super();
		this.deptName = deptName;
		this.deptDesc = deptDesc;
	}
	public Dept(String deptName, String deptDesc, Integer deptId, boolean deptState) {
		this.deptName = deptName;
		this.deptId = deptId;
		this.deptState = deptState;
		this.deptDesc = deptDesc;
	}
	public Integer getDeptId() {
		return deptId;
	}
	public void setDeptId(Integer deptId) {
		this.deptId = deptId;
	}
	public String getDeptName() {
		return deptName;
	}
	public void setDeptName(String deptName) {
		this.deptName = deptName;
	}
	public String getDeptDesc() {
		return deptDesc;
	}
	public void setDeptDesc(String deptDesc) {
		this.deptDesc = deptDesc;
	}
	public Boolean getDeptState() {
		return deptState;
	}
	public void setDeptState(Boolean deptState) {
		this.deptState = deptState;
	}
	@Override
	public String toString() {
		return "Dept [deptId=" + deptId + ", deptName=" + deptName + ", deptDesc=" + deptDesc + ", deptState="
				+ (deptState==true?"有效部门":"已解散部门") + "]";
	}
}

package cn.zc.hrmsys.pojo.entity;

import java.util.Date;

/**
 * 
 * @ClassName：Emp.java
 * @Description：TODO (员工表对应的实例)
 * @Project Name：hrmsys
 * @Package Name: cn.zc.hrmsys.pojo
 * @Author：zc-cris
 * @Create Date：2018年1月30日下午9:10:23
 * @version: v1.0
 * @Copyright: zc-cris
 * @mail: 17623887386@163.com
 */
public class Emp {

	private Integer empId;
	private String empName;
	private Boolean empGender;
	private String empTel;
	private String empEmail;
	private Integer empEdu;		//教育程度用数字代替
	private Integer empIdCard;
	private Date empRegDate;
	private String empAddress;
	private Integer jobId;
	private Integer deptId;
	
	public Emp() {
		super();
	}
			
	public Emp(Integer empId, String empTel, String empEmail, Integer empEdu, String empAddress, Integer jobId,
			Integer deptId) {
		super();
		this.empId = empId;
		this.empTel = empTel;
		this.empEmail = empEmail;
		this.empEdu = empEdu;
		this.empAddress = empAddress;
		this.jobId = jobId;
		this.deptId = deptId;
	}
	public Emp( String empName, Boolean empGender, String empTel, String empEmail, Integer empEdu,
			Integer empIdCard, String empAddress, Integer jobId, Integer deptId) {
		super();
		this.empName = empName;
		this.empGender = empGender;
		this.empTel = empTel;
		this.empEmail = empEmail;
		this.empEdu = empEdu;
		this.empIdCard = empIdCard;
		this.empAddress = empAddress;
		this.jobId = jobId;
		this.deptId = deptId;
	}
	public Integer getEmpId() {
		return empId;
	}
	public void setEmpId(Integer empId) {
		this.empId = empId;
	}
	public String getEmpName() {
		return empName;
	}
	public void setEmpName(String empName) {
		this.empName = empName;
	}
	public Boolean getEmpGender() {
		return empGender;
	}
	public void setEmpGender(Boolean empGender) {
		this.empGender = empGender;
	}
	public String getEmpTel() {
		return empTel;
	}
	public void setEmpTel(String empTel) {
		this.empTel = empTel;
	}
	public String getEmpEmail() {
		return empEmail;
	}
	public void setEmpEmail(String empEmail) {
		this.empEmail = empEmail;
	}
	public Integer getEmpEdu() {
		return empEdu;
	}
	public void setEmpEdu(Integer empEdu) {
		this.empEdu = empEdu;
	}
	public Integer getEmpIdCard() {
		return empIdCard;
	}
	public void setEmpIdCard(Integer empIdCard) {
		this.empIdCard = empIdCard;
	}
	public Date getEmpRegDate() {
		return empRegDate;
	}
	public void setEmpRegDate(Date empRegDate) {
		this.empRegDate = empRegDate;
	}
	public String getEmpAddress() {
		return empAddress;
	}
	public void setEmpAddress(String empAddress) {
		this.empAddress = empAddress;
	}
	public Integer getJobId() {
		return jobId;
	}
	public void setJobId(Integer jobId) {
		this.jobId = jobId;
	}
	public Integer getDeptId() {
		return deptId;
	}
	public void setDeptId(Integer deptId) {
		this.deptId = deptId;
	}
	@Override
	public String toString() {
		return "Emp [empId=" + empId + ", empName=" + empName + ", empGender=" + empGender + ", empTel=" + empTel
				+ ", empEmail=" + empEmail + ", empEdu=" + empEdu + ", empIdCard=" + empIdCard + ", empRegDate="
				+ empRegDate + ", empAddress=" + empAddress + ", jobId=" + jobId + ", deptId=" + deptId + "]";
	}
}

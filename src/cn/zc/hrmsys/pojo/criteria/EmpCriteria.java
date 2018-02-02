package cn.zc.hrmsys.pojo.criteria;

public class EmpCriteria {
	
	private Integer jobId;
	private Boolean empGender;
	private String empName;
	private Integer empIdCard;
	private Integer deptId;
	public EmpCriteria() {
		super();
		
	}
	
	public EmpCriteria(Integer jobId, Boolean empGender, String empName, Integer empIdCard, Integer deptId) {
		super();
		this.jobId = jobId;
		this.empGender = empGender;
		this.empName = empName;
		this.empIdCard = empIdCard;
		this.deptId = deptId;
	}

	public Integer getJobId() {
		return jobId;
	}
	public void setJobId(Integer jobId) {
		this.jobId = jobId;
	}
	public Boolean getEmpGender() {
		return empGender==null?true:empGender;
	}
	public void setEmpGender(Boolean empGender) {
		this.empGender = empGender;
	}
	public String getEmpName() {
		return empName==null?"%%":"%"+empName+"%";
	}
	public void setEmpName(String empName) {
		this.empName = empName;
	}
	public Integer getEmpIdCard() {
		return empIdCard;
	}
	public void setEmpIdCard(Integer empIdCard) {
		this.empIdCard = empIdCard;
	}
	public Integer getDeptId() {
		return deptId;
	}
	public void setDeptId(Integer deptId) {
		this.deptId = deptId;
	}
	@Override
	public String toString() {
		return "EmpCriteria [jobId=" + jobId + ", empGender=" + empGender + ", empName=" + empName + ", empIdCard="
				+ empIdCard + ", deptId=" + deptId + "]";
	}
	

}

package cn.zc.hrmsys.pojo.criteria;


public class JobCriteria {
	
	private String jobName;
	private Boolean jobState;
	
	public JobCriteria() {
		super();
		
	}
	public JobCriteria(String jobName, Boolean jobState) {
		super();
		this.jobName = jobName;
		this.jobState = jobState;
	}
	public String getJobName() {
		return jobName==null?"%%":"%"+jobName+"%";
	}
	public void setJobName(String jobName) {
		this.jobName = jobName;
	}
	public Boolean getJobState() {
		return jobState==null?true:jobState;
	}
	public void setJobState(Boolean jobState) {
		this.jobState = jobState;
	}
}

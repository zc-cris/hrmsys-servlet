package cn.zc.hrmsys.pojo.entity;

/**
 * 
 * @ClassName：Job.java
 * @Description：TODO (职位表对应的实例)
 * @Project Name：hrmsys
 * @Package Name: cn.zc.hrmsys.pojo
 * @Author：zc-cris
 * @Create Date：2018年1月30日下午9:09:43
 * @version: v1.0
 * @Copyright: zc-cris
 * @mail: 17623887386@163.com
 */
public class Job {

	private Integer jobId;
	private String jobName;
	private String jobDesc;
	private Boolean jobState;
	
	public Job() {
		super();
	}
	public Job(String jobName, String jobDesc) {
		super();
		this.jobName = jobName;
		this.jobDesc = jobDesc;
	}
	public Integer getJobId() {
		return jobId;
	}
	public void setJobId(Integer jobId) {
		this.jobId = jobId;
	}
	public String getJobName() {
		return jobName;
	}
	public void setJobName(String jobName) {
		this.jobName = jobName;
	}
	public String getJobDesc() {
		return jobDesc;
	}
	public void setJobDesc(String jobDesc) {
		this.jobDesc = jobDesc;
	}
	public Boolean getJobState() {
		return jobState;
	}
	public void setJobState(Boolean jobState) {
		this.jobState = jobState;
	}
	@Override
	public String toString() {
		return "Job [jobId=" + jobId + ", jobName=" + jobName + ", jobDesc=" + jobDesc + ", jobState=" + (jobState==true?"有效职位":"无效职位") + "]";
	}
	
}

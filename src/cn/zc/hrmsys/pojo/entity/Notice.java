package cn.zc.hrmsys.pojo.entity;

import java.util.Date;

/**
 * 
 * @ClassName：Notice.java
 * @Description：TODO (公告表对应的实例)
 * @Project Name：hrmsys
 * @Package Name: cn.zc.hrmsys.pojo
 * @Author：zc-cris
 * @Create Date：2018年1月30日下午9:08:56
 * @version: v1.0
 * @Copyright: zc-cris
 * @mail: 17623887386@163.com
 */
public class Notice {

	private Integer noticeId;
	private String noticeName;
	private String noticeDesc;
	private String noticeContent;
	private Date noticeCreaTime;
	private String userName;
	private String  lastModifyUser;
	private Date lastModifyTime;
	private Boolean noticeState;
	
	public Notice() {
		super();
		
	}

	public Notice(Integer noticeId, String noticeName, String noticeDesc, String noticeContent, String lastModifyUser) {
		super();
		this.noticeId = noticeId;
		this.noticeName = noticeName;
		this.noticeDesc = noticeDesc;
		this.noticeContent = noticeContent;
		this.lastModifyUser = lastModifyUser;
	}
	public Notice(String noticeName, String noticeDesc, String noticeContent, 
			String userName) {
		super();
		this.noticeName = noticeName;
		this.noticeDesc = noticeDesc;
		this.noticeContent = noticeContent;
		this.userName = userName;
	}

	public String getNoticeDesc() {
		return noticeDesc;
	}

	public void setNoticeDesc(String noticeDesc) {
		this.noticeDesc = noticeDesc;
	}

	public Integer getNoticeId() {
		return noticeId;
	}
	public void setNoticeId(Integer noticeId) {
		this.noticeId = noticeId;
	}
	public String getNoticeName() {
		return noticeName;
	}
	public void setNoticeName(String noticeName) {
		this.noticeName = noticeName;
	}
	public String getNoticeContent() {
		return noticeContent;
	}
	public void setNoticeContent(String noticeContent) {
		this.noticeContent = noticeContent;
	}
	public Date getNoticeCreaTime() {
		return noticeCreaTime;
	}
	public void setNoticeCreaTime(Date noticeCreaTime) {
		this.noticeCreaTime = noticeCreaTime;
	}
	public String getUserName() {
		return userName;
	}
	public void setUserName(String userName) {
		this.userName = userName;
	}
	public Boolean getNoticeState() {
		return noticeState;
	}
	public void setNoticeState(Boolean noticeState) {
		this.noticeState = noticeState;
	}
	
	public String getLastModifyUser() {
		return lastModifyUser;
	}

	public void setLastModifyUser(String lastModifyUser) {
		this.lastModifyUser = lastModifyUser;
	}

	public Date getLastModifyTime() {
		return lastModifyTime;
	}

	public void setLastModifyTime(Date lastModifyTime) {
		this.lastModifyTime = lastModifyTime;
	}

	@Override
	public String toString() {
		return "Notice [noticeId=" + noticeId + ", noticeName=" + noticeName + ", noticeDesc=" + noticeDesc
				+ ", noticeContent=" + noticeContent + ", noticeCreaTime=" + noticeCreaTime + ", userName=" + userName
				+ ", lastModifyUser=" + (lastModifyUser==null?"无":lastModifyUser) + ", lastModifyTime=" + (lastModifyTime==null?"无":lastModifyTime) + ", noticeState="
				+ (noticeState==true?"有效通告":"无效通告") + "]";
	}
}

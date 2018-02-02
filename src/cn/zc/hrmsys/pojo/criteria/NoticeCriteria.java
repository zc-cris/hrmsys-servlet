package cn.zc.hrmsys.pojo.criteria;

public class NoticeCriteria {
	
	private String noticeName;
	private Boolean noticeState;
	
	public NoticeCriteria() {
		super();
		
	}
	public NoticeCriteria(String noticeName, Boolean noticeState) {
		super();
		this.noticeName = noticeName;
		this.noticeState = noticeState;
	}
	public String getNoticeName() {
		return noticeName==null?"%%":"%"+noticeName+"%";
	}
	public void setNoticeName(String noticeName) {
		this.noticeName = noticeName;
	}
	public Boolean getNoticeState() {
		return noticeState==null?true:noticeState;		//用户没有输入就默认查询有效通告
	}
	public void setNoticeState(Boolean noticeState) {
		this.noticeState = noticeState;
	}

}

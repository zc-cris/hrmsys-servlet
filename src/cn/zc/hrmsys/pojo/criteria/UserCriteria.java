package cn.zc.hrmsys.pojo.criteria;

public class UserCriteria {
	
	private String userName;
	private Boolean userState;
	
	public UserCriteria() {
		super();
		
	}
	public UserCriteria(String userName, Boolean userState) {
		super();
		this.userName = userName;
		this.userState = userState;
	}
	public String getUserName() {
		return userName==null?"%%":"%"+userName+"%";
	}
	public void setUserName(String userName) {
		this.userName = userName;
	}
	public Boolean getUserState() {
		return userState==null?true:userState;
	}
	public void setUserState(Boolean userState) {
		this.userState = userState;
	}
	@Override
	public String toString() {
		return "UserCriteria [userName=" + userName + ", userState=" + userState + "]";
	}
}

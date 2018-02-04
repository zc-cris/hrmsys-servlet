package cn.zc.hrmsys.pojo.entity;

import java.util.Date;

/**
 * 
 * @ClassName：User.java
 * @Description：TODO (用户表对应的实例)
 * @Project Name：hrmsys
 * @Package Name: cn.zc.hrmsys.pojo
 * @Author：zc-cris
 * @Create Date：2018年1月30日下午8:45:30
 * @version: v1.0
 * @Copyright: zc-cris
 * @mail: 17623887386@163.com
 */
public class User {
	private Integer userId;
	private String userName;
	private String userPwd;
	private Boolean userState;
	private Date regDate;
	public User() {
		super();
	}
	public User(String userName, String userPwd,Integer userId) {
		super();
		this.userId = userId;
		this.userName = userName;
		this.userPwd = userPwd;
	}
	public User(String userName, String userPwd) {
		super();
		this.userName = userName;
		this.userPwd = userPwd;
	}
	public User(String userName, String userPwd, Integer userId, boolean userState) {
		this.userName = userName;
		this.userPwd = userPwd;
		this.userId = userId;
		this.userState = userState;
	}
	public Integer getUserId() {
		return userId;
	}
	public void setUserId(Integer userId) {
		this.userId = userId;
	}
	public String getUserName() {
		return userName;
	}
	public void setUserName(String userName) {
		this.userName = userName;
	}
	public String getUserPwd() {
		return userPwd;
	}
	public void setUserPwd(String userPwd) {
		this.userPwd = userPwd;
	}
	public Boolean getUserState() {
		return userState;
	}
	public void setUserState(Boolean userState) {
		this.userState = userState;
	}
	public Date getRegDate() {
		return regDate;
	}
	public void setRegDate(Date regDate) {
		this.regDate = regDate;
	}
	@Override
	public String toString() {
		return "User [userId=" + userId + ", userName=" + userName + ", userPwd=" + userPwd + ", userState=" + userState
				+ ", regDate=" + regDate + "]";
	}
}

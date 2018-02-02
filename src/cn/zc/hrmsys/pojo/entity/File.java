package cn.zc.hrmsys.pojo.entity;

import java.util.Date;

/**
 * 
 * @ClassName：File.java
 * @Description：TODO (上传文件表对应的实例)
 * @Project Name：hrmsys
 * @Package Name: cn.zc.hrmsys.pojo
 * @Author：zc-cris
 * @Create Date：2018年1月30日下午9:10:02
 * @version: v1.0
 * @Copyright: zc-cris
 * @mail: 17623887386@163.com
 */
public class File {

	private Integer fileId;
	private String fileName;
	private Date uploadTime;
	private String filePath;
	private String fileDesc;
	private String userName;
	private String  lastModifyUser;
	private Date lastModifyTime;
	private Boolean fileState;
	public File() {
		super();
	}
	public File(Integer fileId, String fileName, String fileDesc, String lastModifyUser) {
		super();
		this.fileId = fileId;
		this.fileName = fileName;
		this.fileDesc = fileDesc;
		this.lastModifyUser = lastModifyUser;
	}


	public File(String fileName, String filePath, String fileDesc, String userName) {
		super();
		this.fileName = fileName;
		this.filePath = filePath;
		this.fileDesc = fileDesc;
		this.userName = userName;
	}

	public Integer getFileId() {
		return fileId;
	}
	public void setFileId(Integer fileId) {
		this.fileId = fileId;
	}
	public String getFileName() {
		return fileName;
	}
	public void setFileName(String fileName) {
		this.fileName = fileName;
	}
	public Date getUploadTime() {
		return uploadTime;
	}
	public void setUploadTime(Date uploadTime) {
		this.uploadTime = uploadTime;
	}
	public String getFilePath() {
		return filePath;
	}
	public void setFilePath(String filePath) {
		this.filePath = filePath;
	}
	public String getFileDesc() {
		return fileDesc;
	}
	public void setFileDesc(String fileDesc) {
		this.fileDesc = fileDesc;
	}
	public String getUserName() {
		return userName;
	}
	public void setUserName(String userName) {
		this.userName = userName;
	}
	public Boolean getFileState() {
		return fileState;
	}
	public void setFileState(Boolean fileState) {
		this.fileState = fileState;
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
		return "File [fileId=" + fileId + ", fileName=" + fileName + ", uploadTime=" + uploadTime + ", filePath="
				+ filePath + ", fileDesc=" + fileDesc + ", userName=" + userName + ", lastModifyUser=" + (lastModifyUser==null?"无":lastModifyUser)
				+ ", lastModifyTime=" + (lastModifyTime==null?"无":lastModifyTime) + ", fileState=" + (fileState==true?"有效文件":"无效文件") + "]";
	}
}

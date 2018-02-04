package cn.zc.hrmsys.pojo.entity;

import java.util.Date;

import org.apache.commons.fileupload.FileItem;

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
public class FileBean {

	private Integer fileId;
	private String fileName;
	private Date uploadTime;
	private String filePath;
	private String fileDesc;
	private String userName;
	private String  lastModifyUser;
	private Date lastModifyTime;
	private Boolean fileState;
	private FileItem fileItem;
	public FileBean() {
		super();
	}
	public FileBean(Integer fileId, String fileName, String fileDesc, String lastModifyUser) {
		super();
		this.fileId = fileId;
		this.fileName = fileName;
		this.fileDesc = fileDesc;
		this.lastModifyUser = lastModifyUser;
	}

	
	public FileBean(String fileName, String filePath, String fileDesc, FileItem fileItem) {
		super();
		this.fileName = fileName;
		this.filePath = filePath;
		this.fileDesc = fileDesc;
		this.fileItem = fileItem;
	}
	public FileBean(String fileName, String filePath, String fileDesc, String userName) {
		super();
		this.fileName = fileName;
		this.filePath = filePath;
		this.fileDesc = fileDesc;
		this.userName = userName;
	}

	public FileBean(String tempFilepath) {
		this.filePath = tempFilepath;
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
	
	public FileItem getFileItem() {
		return fileItem;
	}
	public void setFileItem(FileItem fileItem) {
		this.fileItem = fileItem;
	}
	@Override
	public String toString() {
		return "FileBean [fileId=" + fileId + ", fileName=" + fileName + ", uploadTime=" + uploadTime + ", filePath="
				+ filePath + ", fileDesc=" + fileDesc + ", userName=" + userName + ", lastModifyUser=" + lastModifyUser
				+ ", lastModifyTime=" + lastModifyTime + ", fileState=" + fileState + ", fileItem=" + fileItem + "]";
	}
	
}

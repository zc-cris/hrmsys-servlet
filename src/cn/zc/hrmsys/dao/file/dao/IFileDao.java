package cn.zc.hrmsys.dao.file.dao;

import java.sql.SQLException;
import java.util.List;

import cn.zc.hrmsys.pojo.criteria.NoticeCriteria;
import cn.zc.hrmsys.pojo.entity.FileBean;
import cn.zc.hrmsys.pojo.entity.Notice;

public interface IFileDao {
	
	List<FileBean> getAll() throws SQLException;
	
	List<FileBean> getAllWithFileName(String name) throws SQLException;
	
	void updateFile(FileBean file) throws SQLException;
	
	void addFile(FileBean file) throws SQLException;
	
	void deleteFileById(Integer id) throws SQLException;
	
	FileBean getFileById(Integer id) throws SQLException;

}

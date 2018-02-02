package cn.zc.hrmsys.dao.file.dao;

import java.sql.SQLException;
import java.util.List;

import cn.zc.hrmsys.pojo.criteria.NoticeCriteria;
import cn.zc.hrmsys.pojo.entity.File;
import cn.zc.hrmsys.pojo.entity.Notice;

public interface IFileDao {
	
	List<File> getAll() throws SQLException;
	
	List<File> getAllWithFileName(String name) throws SQLException;
	
	void updateFile(File file) throws SQLException;
	
	void addFile(File file) throws SQLException;
	
	void deleteFileById(Integer id) throws SQLException;
	
	File getFileById(Integer id) throws SQLException;
}

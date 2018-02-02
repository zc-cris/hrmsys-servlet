package cn.zc.hrmsys.service.file.service;

import java.sql.SQLException;
import java.util.List;

import cn.zc.hrmsys.pojo.entity.File;

public interface IFileService {

	List<File> getAll() throws SQLException;
	
	void delete(Integer id) throws SQLException;
	
	void update(File file) throws SQLException;
	
	void upload(File file) throws SQLException;
	
	void downLoad(Integer id) throws SQLException;
	
}

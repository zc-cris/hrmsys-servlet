package cn.zc.hrmsys.service.file.service;

import java.sql.SQLException;
import java.util.List;

import cn.zc.hrmsys.pojo.entity.FileBean;

public interface IFileService {

	List<FileBean> getAll() throws SQLException;
	
	void delete(Integer id) throws SQLException;
	
	void update(FileBean file) throws SQLException;
	
	void upload(FileBean file) throws SQLException;
	
	void downLoad(Integer id) throws SQLException;

	List<FileBean> getAllWithName(String fileName) throws SQLException;
	
}

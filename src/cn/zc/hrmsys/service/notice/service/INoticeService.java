package cn.zc.hrmsys.service.notice.service;

import java.sql.SQLException;
import java.util.List;

import cn.zc.hrmsys.pojo.entity.Notice;

public interface INoticeService {

	List<Notice> getAll() throws SQLException;
	
	void update(Notice notice) throws SQLException;
	
	void add(Notice notice) throws SQLException;
	
	void deleteById(Integer id) throws SQLException;
	
}

package cn.zc.hrmsys.dao.notice.dao;

import java.sql.SQLException;
import java.util.List;

import cn.zc.hrmsys.pojo.criteria.DeptCriteria;
import cn.zc.hrmsys.pojo.criteria.NoticeCriteria;
import cn.zc.hrmsys.pojo.entity.Dept;
import cn.zc.hrmsys.pojo.entity.Notice;

public interface INoticeDao {

	
	List<Notice> getAll() throws SQLException;
	
	List<Notice> getAllWithCriteria(NoticeCriteria noticeCriteria) throws SQLException;
	
	void addNotice(Notice notice) throws SQLException;
	
	void updateNotice(Notice notice) throws SQLException;
	
	void deleteNoticeById(Integer id) throws SQLException;
	
	Notice getNoticeById(Integer id) throws SQLException;
	
	long getCountWithName(String name) throws SQLException;
}

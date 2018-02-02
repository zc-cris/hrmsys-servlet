package cn.zc.hrmsys.dao.notice.dao;

import java.sql.SQLException;
import java.util.Date;
import java.util.List;

import cn.zc.hrmsys.dao.base.dao.BaseDao;
import cn.zc.hrmsys.pojo.criteria.NoticeCriteria;
import cn.zc.hrmsys.pojo.entity.Notice;

public class NoticeDao extends BaseDao<Notice> implements INoticeDao {

	/**
	 * 
	 * @MethodName: getAll
	 * @Description: TODO (查所有的公告记录)
	 * @see cn.zc.hrmsys.dao.notice.dao.INoticeDao#getAll()
	 * @Author：zc-cris
	 * @Create Date：2018年1月31日下午7:15:27
	 */
	@Override
	public List<Notice> getAll() throws SQLException {
		String sql = "select noticeId,noticeName,noticeDesc,noticeCreaTime,userName,noticeState from tb_notice";
		return queryList(sql);
	}

	/**
	 * 
	 * @MethodName: getAllWithCriteria
	 * @Description: TODO (模糊查询)
	 * @see cn.zc.hrmsys.dao.notice.dao.INoticeDao#getAllWithCriteria(cn.zc.hrmsys.pojo.criteria.NoticeCriteria)
	 * @Author：zc-cris
	 * @Create Date：2018年1月31日下午7:23:09
	 */
	@Override
	public List<Notice> getAllWithCriteria(NoticeCriteria noticeCriteria) throws SQLException {
		String sql = "select noticeId,noticeName,noticeDesc,noticeCreaTime,userName,noticeState from tb_notice where noticeName like ?"
				+ " and noticeState = ?";
		return queryList(sql, noticeCriteria.getNoticeName(), noticeCriteria.getNoticeState());
	}

	/**
	 * 
	 * @MethodName: addNotice
	 * @Description: TODO (新增一条公告记录)
	 * @see cn.zc.hrmsys.dao.notice.dao.INoticeDao#addNotice(cn.zc.hrmsys.pojo.entity.Notice)
	 * @Author：zc-cris
	 * @Create Date：2018年1月31日下午3:28:33
	 */
	@Override
	public void addNotice(Notice notice) throws SQLException {
		String sql = "insert into tb_notice (noticeName, noticeDesc, noticeContent, userName,noticeCreaTime) values (?,?,?,?,?)";
		update(sql, notice.getNoticeName(), notice.getNoticeDesc(), notice.getNoticeContent(), notice.getUserName(),
				new Date());
	}

	/**
	 * 
	 * @MethodName: updateNotice
	 * @Description: TODO (更新一条通知记录信息)
	 * @see cn.zc.hrmsys.dao.notice.dao.INoticeDao#updateNotice(cn.zc.hrmsys.pojo.entity.Notice)
	 * @Author：zc-cris
	 * @Create Date：2018年1月31日下午3:59:27
	 */
	@Override
	public void updateNotice(Notice notice) throws SQLException {
		String sql = "update tb_notice set noticeName=?,noticeDesc=?,noticeContent=?,lastModifyUser=?,lastModifyTime=? where noticeId=?";
		update(sql, notice.getNoticeName(), notice.getNoticeDesc(), notice.getNoticeContent(),
				notice.getLastModifyUser(), new Date(), notice.getNoticeId());
	}

	/**
	 * 
	 * @MethodName: deleteNoticeById
	 * @Description: TODO (注销一条公告记录)
	 * @see cn.zc.hrmsys.dao.notice.dao.INoticeDao#deleteNoticeById(java.lang.Integer)
	 * @Author：zc-cris
	 * @Create Date：2018年1月31日下午6:59:18
	 */
	@Override
	public void deleteNoticeById(Integer id) throws SQLException {
		String sql = "update tb_notice set noticeState = 0 where noticeId = ?";
		update(sql, id);
	}

	/**
	 * 
	 * @MethodName: getNoticeById
	 * @Description: TODO (根据id获取一条公告信息)
	 * @see cn.zc.hrmsys.dao.notice.dao.INoticeDao#getNoticeById(java.lang.Integer)
	 * @Author：zc-cris
	 * @Create Date：2018年1月31日下午7:04:40
	 */
	@Override
	public Notice getNoticeById(Integer id) throws SQLException {
		String sql = "select noticeId,noticeName,userName,noticeDesc,noticeContent,noticeCreaTime,lastModifyTime,lastModifyUser,noticeState from tb_notice where noticeId = ?";
		return queryOne(sql, id);
	}

	/**
	 * 
	 * @MethodName: getCountWithName
	 * @Description: TODO (根据通告名字查询有效通告的数量，防止添加和更新通告信息重复  )
	 * @see cn.zc.hrmsys.dao.notice.dao.INoticeDao#getCountWithName(java.lang.String)
	 * @Author：zc-cris
	 * @Create Date：2018年1月31日下午7:25:21
	 */
	@Override
	public long getCountWithName(String name) throws SQLException {
		String sql = "select count(noticeName) from tb_notice where noticeName=? and noticeState = 1";
		return queryValue(sql, name);
	}
}

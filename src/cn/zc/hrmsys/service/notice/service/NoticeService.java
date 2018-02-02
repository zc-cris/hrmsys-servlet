package cn.zc.hrmsys.service.notice.service;

import java.sql.SQLException;
import java.util.List;

import cn.zc.hrmsys.MyException.NoticeException;
import cn.zc.hrmsys.dao.notice.dao.INoticeDao;
import cn.zc.hrmsys.factory.dao.factory.DaoFactory;
import cn.zc.hrmsys.pojo.entity.Notice;

public class NoticeService implements INoticeService {

	INoticeDao noticeDao = (INoticeDao) DaoFactory.newInstance().getDaoEntity("NoticeDao");
	
	/**
	 * 
	 * @MethodName: getAll
	 * @Description: TODO (获取所有的公告记录)
	 * @see cn.zc.hrmsys.service.notice.service.INoticeService#getAll()
	 * @Author：zc-cris
	 * @Create Date：2018年2月1日下午6:59:04
	 */
	@Override
	public List<Notice> getAll() throws SQLException {
		return noticeDao.getAll();
	}

	/**
	 * 
	 * @MethodName: update
	 * @Description: TODO (更新一条公告记录)
	 * @see cn.zc.hrmsys.service.notice.service.INoticeService#update(cn.zc.hrmsys.pojo.entity.Notice)
	 * @Author：zc-cris
	 * @Create Date：2018年2月1日下午6:59:20
	 */
	@Override
	public void update(Notice notice) throws SQLException {
		long count = noticeDao.getCountWithName(notice.getNoticeName());
		if(count>0) {
			throw new NoticeException("存在相同名字的公告，请更改公告名字");
		}
		noticeDao.updateNotice(notice);
	}

	/**
	 * 
	 * @MethodName: add
	 * @Description: TODO (新增一条公告记录)
	 * @see cn.zc.hrmsys.service.notice.service.INoticeService#add(cn.zc.hrmsys.pojo.entity.Notice)
	 * @Author：zc-cris
	 * @Create Date：2018年2月1日下午6:59:35
	 */
	@Override
	public void add(Notice notice) throws SQLException {
		long count = noticeDao.getCountWithName(notice.getNoticeName());
		if(count>0) {
			throw new NoticeException("存在相同名字的公告，请更改公告名字");
		}
		noticeDao.addNotice(notice);
	}

	/**
	 * 
	 * @MethodName: deleteById
	 * @Description: TODO (注销一条公告记录)
	 * @see cn.zc.hrmsys.service.notice.service.INoticeService#deleteById(java.lang.Integer)
	 * @Author：zc-cris
	 * @Create Date：2018年2月1日下午6:59:51
	 */
	@Override
	public void deleteById(Integer id) throws SQLException {
		noticeDao.deleteNoticeById(id);
	}

}

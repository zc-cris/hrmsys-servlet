package cn.zc.hrmsys.test.dao;

import static org.junit.jupiter.api.Assertions.*;

import java.sql.SQLException;
import java.util.List;

import org.junit.jupiter.api.Test;

import cn.zc.hrmsys.dao.notice.dao.INoticeDao;
import cn.zc.hrmsys.dao.notice.dao.NoticeDao;
import cn.zc.hrmsys.pojo.criteria.NoticeCriteria;
import cn.zc.hrmsys.pojo.entity.Notice;

class TestNoticeDao {
	private INoticeDao noticeDao = new NoticeDao();

	@Test		//ok
	void testGetAll() throws SQLException {
		List<Notice> list = noticeDao.getAll();
		System.out.println(list);
	}

	@Test			//ok
	void testGetAllWithCriteria() throws SQLException {
//		List<Notice> list = noticeDao.getAllWithCriteria(new NoticeCriteria("放假通知", null));
		List<Notice> list = noticeDao.getAllWithCriteria(new NoticeCriteria("放假", true));
		System.out.println(list);
	}

	@Test			//ok
	void testAddNotice() throws SQLException {
//		noticeDao.addNotice(new Notice("放假通知", "关于过年放假", "一共放假10天", "张大帅"));
		noticeDao.addNotice(new Notice("奖励通知", "关于特别奖励", "今年张大帅表现突出，年终奖10万！", "重庆吴亦凡"));
	}

	@Test		//ok
	void testUpdateNotice() throws SQLException {
		Notice notice = new Notice(1, "放假通知", "过年放假最新更新", "紧急决定，今年放假20天", "张大帅");
		noticeDao.updateNotice(notice);
	}

	@Test		//ok
	void testDeleteNoticeById() throws SQLException {
		noticeDao.deleteNoticeById(2);
	}

	@Test		//ok
	void testGetNoticeById() throws SQLException {
//		Notice notice = noticeDao.getNoticeById(1);
		Notice notice = noticeDao.getNoticeById(2);
		System.out.println(notice);
	}

	@Test			//ok
	void testGetCountWithName() throws SQLException {
		long count = noticeDao.getCountWithName("奖励通知");
		System.out.println(count);
	}

}

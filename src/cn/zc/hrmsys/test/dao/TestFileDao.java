package cn.zc.hrmsys.test.dao;

import static org.junit.jupiter.api.Assertions.*;

import java.sql.SQLException;
import java.util.List;

import org.junit.jupiter.api.Test;

import cn.zc.hrmsys.dao.file.dao.FileDao;
import cn.zc.hrmsys.dao.file.dao.IFileDao;
import cn.zc.hrmsys.pojo.entity.FileBean;

class TestFileDao {

	IFileDao fileDao = new FileDao();
	@Test			//ok
	void testGetAll() throws SQLException {
		List<FileBean> list = fileDao.getAll();
		System.out.println(list);
	}

	@Test			//ok
	void testGetAllWithFileName() throws SQLException {
		List<FileBean> list = fileDao.getAllWithFileName("指");
		System.out.println(list);
	}

	@Test		//ok
	void testUpdateFile() throws SQLException {
		fileDao.updateFile(new FileBean(3, "一阳指", "欲练此功,先自插100次", "张无忌"));
	}

	@Test		//ok
	void testAddFile() throws SQLException {
//		fileDao.addFile(new File("九银真经", "d:\\files", "欲练此经，必先自宫", "老gay"));
		fileDao.addFile(new FileBean("九阳圣功", "d:\\files\\miji", "欲练此功，必选采银999次", "小gay"));
	}

	@Test		//ok
	void testDeleteFileById() throws SQLException {
		fileDao.deleteFileById(3);
	}

	@Test		//ok
	void testGetFileById() throws SQLException {
		FileBean file = fileDao.getFileById(3);
		System.out.println(file);
	}

}

package cn.zc.hrmsys.service.file.service;

import java.sql.SQLException;
import java.util.List;

import cn.zc.hrmsys.dao.file.dao.IFileDao;
import cn.zc.hrmsys.factory.dao.factory.DaoFactory;
import cn.zc.hrmsys.pojo.entity.File;

public class FileService implements IFileService {

	IFileDao fileDao = (IFileDao) DaoFactory.newInstance().getDaoEntity("FileDao");
	
	/**
	 * 
	 * @MethodName: getAll
	 * @Description: TODO (获取所有文件记录)
	 * @see cn.zc.hrmsys.service.file.service.IFileService#getAll()
	 * @Author：zc-cris
	 * @Create Date：2018年2月1日下午7:01:45
	 */
	@Override
	public List<File> getAll() throws SQLException {
		return fileDao.getAll();
	}

	/**
	 * 
	 * @MethodName: delete
	 * @Description: TODO (注销一条文件记录)
	 * @see cn.zc.hrmsys.service.file.service.IFileService#delete(java.lang.Integer)
	 * @Author：zc-cris
	 * @Create Date：2018年2月1日下午7:01:58
	 */
	@Override
	public void delete(Integer id) throws SQLException {
		fileDao.deleteFileById(id);
	}

	/**
	 * 
	 * @MethodName: update
	 * @Description: TODO (更新一条文件记录)
	 * @see cn.zc.hrmsys.service.file.service.IFileService#update(cn.zc.hrmsys.pojo.entity.File)
	 * @Author：zc-cris
	 * @Create Date：2018年2月1日下午7:02:12
	 */
	@Override
	public void update(File file) throws SQLException {
		fileDao.updateFile(file);
	}

	/**
	 * 
	 * @MethodName: upload
	 * @Description: TODO (上传一个文件)
	 * @see cn.zc.hrmsys.service.file.service.IFileService#upload(cn.zc.hrmsys.pojo.entity.File)
	 * @Author：zc-cris
	 * @Create Date：2018年2月1日下午7:02:32
	 */
	@Override
	public void upload(File file) throws SQLException {
		fileDao.updateFile(file);
	}

	/**
	 * 
	 * @MethodName: downLoad
	 * @Description: TODO (下载文件)
	 * @see cn.zc.hrmsys.service.file.service.IFileService#downLoad(java.lang.Integer)
	 * @Author：zc-cris
	 * @Create Date：2018年2月1日下午7:02:45
	 */
	@Override			//先不写
	public void downLoad(Integer id) {
		
	}

}

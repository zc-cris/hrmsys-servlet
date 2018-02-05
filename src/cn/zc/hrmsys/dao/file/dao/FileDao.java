package cn.zc.hrmsys.dao.file.dao;

import java.sql.SQLException;
import java.util.Date;
import java.util.List;

import cn.zc.hrmsys.dao.base.dao.BaseDao;
import cn.zc.hrmsys.pojo.entity.FileBean;

public class FileDao extends BaseDao<FileBean> implements IFileDao{

	/**
	 * 
	 * @MethodName: getAll
	 * @Description: TODO (获取所有文件)
	 * @see cn.zc.hrmsys.dao.file.dao.IFileDao#getAll()
	 * @Author：zc-cris
	 * @Create Date：2018年1月31日下午8:13:58
	 */
	@Override
	public List<FileBean> getAll() throws SQLException {
		String sql = "select fileId,fileName,fileDesc,uploadTime,userName from tb_file";
		return queryList(sql);
	}

	/**
	 * 
	 * @MethodName: getAllWithFileName
	 * @Description: TODO (模糊查询文件记录)
	 * @see cn.zc.hrmsys.dao.file.dao.IFileDao#getAllWithFileName(java.lang.String)
	 * @Author：zc-cris
	 * @Create Date：2018年1月31日下午8:16:25
	 */
	@Override
	public List<FileBean> getAllWithFileName(String name) throws SQLException {
		String sql = "select fileId,fileName,fileDesc,uploadTime,userName from tb_file where fileName like ?";
		return queryList(sql, "%"+name+"%");
	}

	/**
	 * 
	 * @MethodName: updateFile
	 * @Description: TODO (修改上传文件记录)
	 * @see cn.zc.hrmsys.dao.file.dao.IFileDao#updateFile(cn.zc.hrmsys.pojo.entity.FileBean)
	 * @Author：zc-cris
	 * @Create Date：2018年1月31日下午8:11:49
	 */
	@Override
	public void updateFile(FileBean file) throws SQLException {
		String sql = "update tb_file set fileName=?,fileDesc=?,lastModifyUser=?,lastModifyTime=? where fileId=?";
		update(sql,file.getFileName(), file.getFileDesc(), file.getLastModifyUser(),new Date(),file.getFileId());
	}

	/**
	 * 
	 * @MethodName: addFile
	 * @Description: TODO (新增一条文件记录)
	 * @see cn.zc.hrmsys.dao.file.dao.IFileDao#addFile(cn.zc.hrmsys.pojo.entity.FileBean)
	 * @Author：zc-cris
	 * @Create Date：2018年1月31日下午7:54:12
	 */
	@Override		
	public void addFile(FileBean file) throws SQLException {
		String sql = "insert into tb_file (fileName, fileDesc,filePath,userName,uploadTime) values (?,?,?,?,?)";
		update(sql, file.getFileName(), file.getFileDesc(), file.getFilePath(),file.getUserName(),
				new Date());
	}

	/**
	 * 
	 * @MethodName: deleteFileById
	 * @Description: TODO (注销一个文件记录)
	 * @see cn.zc.hrmsys.dao.file.dao.IFileDao#deleteFileById(java.lang.Integer)
	 * @Author：zc-cris
	 * @Create Date：2018年1月31日下午7:56:49
	 */
	@Override
	public void deleteFileById(Integer id) throws SQLException {
		String sql = "delete from tb_file  where fileId = ?";
		update(sql, id);
	}

	/**
	 * 
	 * @MethodName: getFileById
	 * @Description: TODO (根据id查询一条文件记录)
	 * @see cn.zc.hrmsys.dao.file.dao.IFileDao#getFileById(java.lang.Integer)
	 * @Author：zc-cris
	 * @Create Date：2018年1月31日下午8:04:43
	 */
	@Override
	public FileBean getFileById(Integer id) throws SQLException {
		String sql = "select fileId,fileName,userName,fileDesc,uploadTime,lastModifyTime,lastModifyUser,fileState from tb_file where fileId = ?";
		return queryOne(sql, id);
	}

	@Override
	public FileBean getFilePathById(Integer fileId) throws SQLException {
		String sql = "select filePath from tb_file where fileId = ?";
		return queryOne(sql, fileId);
	}

}

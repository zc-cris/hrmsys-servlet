package cn.zc.hrmsys.dao.emp.dao;

import java.sql.Date;
import java.sql.SQLException;
import java.util.List;

import cn.zc.hrmsys.dao.base.dao.BaseDao;
import cn.zc.hrmsys.pojo.criteria.EmpCriteria;
import cn.zc.hrmsys.pojo.entity.Emp;
import cn.zc.hrmsys.pojo.entity.User;

/*		private Integer empId;
 * 		private String empName;
		private Boolean empGender;
		private String empTel;
		private String empEmail;
		private Integer empEdu;
		private Integer empIdCard;
		private Date empRegDate;
		private String empAddress;
		private Integer jobId;
		private Integer deptId;
 */
public class EmpDao extends BaseDao<Emp> implements IEmpDao {

	/**
	 * 
	 * @MethodName: getWithUserCriteria
	 * @Description: TODO (组合查询员工)	
	 * @see cn.zc.hrmsys.dao.emp.dao.IEmpDao#getWithUserCriteria(cn.zc.hrmsys.pojo.criteria.EmpCriteria)
	 * @Author：zc-cris
	 * @Create Date：2018年2月1日上午12:03:49
	 */
	@Override
	public List<Emp> getWithUserCriteria(EmpCriteria empCriteria) throws SQLException {
		StringBuffer sql = new StringBuffer("select empId,empName,empGender,empIdCard,empTel,empEmail,empEdu,deptId,jobId,empAddress,empRegDate from tb_emp where empName like ? and empGender = ?");
		if(empCriteria.getDeptId() != null && empCriteria.getJobId() != null) {
			//主要根据部门id和职位id模糊查询员工记录
			return getWithJIdAndDId(empCriteria, sql);
		}
		if(empCriteria.getJobId() != null && empCriteria.getDeptId() == null) {
			//主要根据职位id模糊查询于员工记录
			return getWithJId(empCriteria, sql);
		}
		if(empCriteria.getJobId() == null && empCriteria.getDeptId() != null) {
			//主要根据部门id模糊查询员工
			return getWithDId(empCriteria, sql);
		}
			//根据员工名字和性别模糊查询员工
		return queryList(sql.toString(), empCriteria.getEmpName(),empCriteria.getEmpGender());
	}

	/**
	 * 
	 * @MethodName: getWithDId
	 * @Description: TODO (根据部门id查询员工数据)
	 * @param empCriteria
	 * @param sql
	 * @return
	 * @throws SQLException
	 * @Return Type: List<Emp>
	 * @Author: zc-cris
	 * @Create Date：2018年2月1日上午9:09:16
	 * @since
	 * @throws
	 */
	public List<Emp> getWithDId(EmpCriteria empCriteria, StringBuffer sql) throws SQLException {
		sql.append(" and deptId = ?");
		return queryList(sql.toString(), empCriteria.getEmpName(),empCriteria.getEmpGender(),empCriteria.getDeptId());
	}

	/**
	 * 
	 * @MethodName: getWithJId
	 * @Description: TODO (根据jobid查询员工)
	 * @param empCriteria
	 * @param sql
	 * @return
	 * @throws SQLException
	 * @Return Type: List<Emp>
	 * @Author: zc-cris
	 * @Create Date：2018年2月1日上午9:08:45
	 * @since
	 * @throws
	 */
	public List<Emp> getWithJId(EmpCriteria empCriteria, StringBuffer sql) throws SQLException {
		sql.append(" and jobId = ?");
		return queryList(sql.toString(), empCriteria.getEmpName(),empCriteria.getEmpGender(),empCriteria.getJobId());
	}

	/**
	 * 
	 * @MethodName: getWithJIdAndDId
	 * @Description: TODO (根据jobid和deptid查询员工)
	 * @param empCriteria
	 * @param sql
	 * @return
	 * @throws SQLException
	 * @Return Type: List<Emp>
	 * @Author: zc-cris
	 * @Create Date：2018年2月1日上午9:08:06
	 * @since
	 * @throws
	 */
	public List<Emp> getWithJIdAndDId(EmpCriteria empCriteria, StringBuffer sql) throws SQLException {
		sql.append(" and deptId = ? and jobId = ?");
		return queryList(sql.toString(), empCriteria.getEmpName(),empCriteria.getEmpGender(),empCriteria.getDeptId(),empCriteria.getJobId());
	}

	/**
	 * 
	 * @MethodName: getWithIdCard
	 * @Description: TODO (根据身份证号码查询员工)
	 * @see cn.zc.hrmsys.dao.emp.dao.IEmpDao#getWithIdCard(java.lang.Integer)
	 * @Author：zc-cris
	 * @Create Date：2018年1月31日下午11:32:15
	 */
	@Override
	public Emp getWithIdCard(Integer id) throws SQLException {
		String sql = "select empId,empName,empGender,empTel,empEmail,empEdu,deptId,empAddress,empRegDate from tb_emp where empIdCard=?";
		return queryOne(sql, id);
	}

	/**
	 * 
	 * @MethodName: deleteEmpById
	 * @Description: TODO (删除一条员工记录)
	 * @see cn.zc.hrmsys.dao.emp.dao.IEmpDao#deleteEmpById(java.lang.Integer)
	 * @Author：zc-cris
	 * @Create Date：2018年1月31日下午11:06:33
	 */
	@Override
	public void deleteEmpById(Integer id) throws SQLException {
		String sql = "delete from tb_emp where empId=?";
		update(sql, id);
	}

	/**
	 * 
	 * @MethodName: addEmp
	 * @Description: TODO (新增一个员工记录)
	 * @see cn.zc.hrmsys.dao.emp.dao.IEmpDao#addEmp(cn.zc.hrmsys.pojo.entity.Emp)
	 * @Author：zc-cris
	 * @Create Date：2018年1月31日下午8:40:54
	 */
	@Override
	public void addEmp(Emp emp) throws SQLException {
		String sql = "insert into tb_emp (empName, empGender, empTel,empEmail,empEdu,empIdCard,empRegDate,empAddress,jobId,deptId) values (?,?,?,?,?,?,?,?,?,?)";
		update(sql, emp.getEmpName(), emp.getEmpGender(), emp.getEmpTel(), emp.getEmpEmail(), emp.getEmpEdu(),
				emp.getEmpIdCard(), new Date(System.currentTimeMillis()), emp.getEmpAddress(), emp.getJobId(),
				emp.getDeptId());
	}

	/**
	 * 
	 * @MethodName: updateEmp
	 * @Description: TODO (修改员工信息)
	 * @see cn.zc.hrmsys.dao.emp.dao.IEmpDao#updateEmp(cn.zc.hrmsys.pojo.entity.Emp)
	 * @Author：zc-cris
	 * @Create Date：2018年1月31日下午8:54:36
	 */
	@Override
	public void updateEmp(Emp emp) throws SQLException {
		String sql = "update tb_emp set empTel=?,empEmail=?,empEdu=?,empAddress=?,jobId=?,deptId=? where empId=?";
		update(sql, emp.getEmpTel(), emp.getEmpEmail(), emp.getEmpEdu(), emp.getEmpAddress(), emp.getJobId(),
				emp.getDeptId(), emp.getEmpId());
	}

	/**
	 * 
	 * @MethodName: getById
	 * @Description: TODO (根据id查询员工)
	 * @see cn.zc.hrmsys.dao.emp.dao.IEmpDao#getById(java.lang.Integer)
	 * @Author：zc-cris
	 * @Create Date：2018年1月31日下午11:03:29
	 */
	@Override
	public Emp getById(Integer id) throws SQLException {
		String sql = "select empId, empName, empGender, empTel,empEmail,empEdu,empIdCard,empRegDate,empAddress,jobId,deptId from tb_emp where empId=?";
		return queryOne(sql, id);
	}

}

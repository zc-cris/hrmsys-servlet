package cn.zc.hrmsys.servlet;

import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.sql.SQLException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import cn.zc.hrmsys.MyException.DeptException;
import cn.zc.hrmsys.MyException.UserException;
import cn.zc.hrmsys.factory.service.factory.ServiceFactory;
import cn.zc.hrmsys.pojo.criteria.DeptCriteria;
import cn.zc.hrmsys.pojo.entity.Dept;
import cn.zc.hrmsys.pojo.entity.User;
import cn.zc.hrmsys.service.dept.service.IDeptService;

@WebServlet("*.dept")
public class DeptServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private IDeptService DeptService = (IDeptService) ServiceFactory.newInstance().getServiceEntity("DeptService");

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		this.doPost(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// 1.获取serlvetPath：/add.do或者 /update.do
		String servletPath = request.getServletPath();
		// 2.对servletPath进行处理，得到方法名：add或者是update
		String methodName = servletPath.substring(1);
		methodName = methodName.substring(0, methodName.indexOf("."));
		System.out.println(methodName);
		// 3.通过反射调用该servlet里的方法
		try {
			Method method = getClass().getDeclaredMethod(methodName, HttpServletRequest.class,
					HttpServletResponse.class);
			method.invoke(this, request, response);
		} catch (NoSuchMethodException | SecurityException | IllegalAccessException | IllegalArgumentException
				| InvocationTargetException e) {
			e.printStackTrace();
			response.sendRedirect("error.jsp");
		}
	}

	@SuppressWarnings("unused")
	private void query(HttpServletRequest request, HttpServletResponse response) {
		try {
			String deptState = request.getParameter("deptState");
			Integer value = Integer.valueOf(deptState);

			// 前台必须让用户选择有效还是无效
			String deptName = request.getParameter("deptName");
			System.out.println(deptName);
			DeptCriteria criteria = new DeptCriteria(deptName, value == 1);
			List<Dept> depts = DeptService.getAllWithCriteria(criteria);

			// 页面回显
			request.setAttribute("selectStatus", deptState);
			request.setAttribute("dName", deptName);

			if (depts != null && depts.size() > 0) {
				request.setAttribute("depts", depts);
				request.setAttribute("flag", "1");
			} else {
				request.setAttribute("flag", "2");
			}
			getJsp("/jsp/dept/dept.jsp", request, response);
		} catch (SQLException | ServletException | IOException e) {
			e.printStackTrace();
		}
	}
	
	@SuppressWarnings("unused")
	private void queryAll(HttpServletRequest request, HttpServletResponse response) {
		List<Dept> depts;
		try {
			depts = DeptService.getAll();
			request.setAttribute("depts", depts);
			getJsp("/jsp/dept/dept.jsp", request, response);
		} catch (SQLException | ServletException | IOException e) {
			e.printStackTrace();
		}
	}

	/**
	 * 
	 * @MethodName: delete @Description: TODO (根据id删除用户) @param request @param
	 *              response @Return Type: void @Author: zc-cris @Create
	 *              Date：2018年2月2日上午8:49:13 @since @throws
	 */
	@SuppressWarnings("unused")
	private void delete(HttpServletRequest request, HttpServletResponse response) {
		String value = request.getParameter("checkedIdArray");
		if (null != value && !("".equals(value))) {
			String[] values = value.split(",");
			try {
				if (values.length == 1) {
					DeptService.deleteById(Integer.parseInt(values[0]));
				} else {
					DeptService.deleteByIds(values);
				}
				getJsp("/jsp/dept/dept.jsp", request, response);
			} catch (NumberFormatException | SQLException | ServletException | IOException e) {
				e.printStackTrace();
			}
		}
	}

	/**
	 * 
	 * @MethodName: add
	 * @Description: TODO (带验证的添加部门)
	 * @param request
	 * @param response
	 * @Return Type: void
	 * @Author: zc-cris
	 * @Create Date：2018年2月3日上午11:24:00
	 * @since
	 * @throws
	 */
	@SuppressWarnings("unused")
	private void add(HttpServletRequest request, HttpServletResponse response) {
		String deptName = request.getParameter("deptName");
		String deptDesc = request.getParameter("deptDesc");
		Dept dept = new Dept(deptName, deptDesc);
		request.setAttribute("flag", "1");			//页面显示提示信息
		try {
			if (deptName == null || deptName.trim().equals("")) {
				request.setAttribute("message", "部门名字不能为空");
				getJsp("/jsp/dept/showAddDept.jsp", request, response);
				return;
			}
			if(deptDesc == null || deptDesc.trim().equals("")) {
				request.setAttribute("message", "部门描述不能为空");
				getJsp("/jsp/dept/showAddDept.jsp", request, response);
				return;
			}
			DeptService.add(dept);
			request.setAttribute("message", "添加成功,点击确定后跳转至首页");
			//给页面设置自动跳转
			response.setHeader("Refresh", "0;URL="+request.getContextPath()+"/jsp/dept/dept.jsp");
			getJsp("/jsp/dept/showAddDept.jsp", request, response);
		} catch (ServletException | IOException | SQLException | DeptException e) {
			checkException("/jsp/dept/showAddDept.jsp",request, response, e);
			e.printStackTrace();
		}
	}
	
	/**
	 * 
	 * @MethodName: get
	 * @Description: TODO (先根据id查询到部门数据，再进行更新数据操作)
	 * @param request
	 * @param response
	 * @Return Type: void
	 * @Author: zc-cris
	 * @Create Date：2018年2月2日下午8:04:02
	 * @since
	 * @throws
	 */
	@SuppressWarnings("unused")
	private void get(HttpServletRequest request, HttpServletResponse response) {
		String deptId = request.getParameter("deptId");
		System.out.println(deptId);
		try {
			if(deptId == null || "".equals(deptId.trim())) {
				getJsp("/jsp/dept/dept.jsp", request, response);
				return;
			}
			Dept dept = DeptService.getDeptById(Integer.valueOf(deptId));
			if(dept != null) {
				request.setAttribute("dept", dept);
				getJsp("/jsp/dept/showUpdateDept.jsp", request, response);
			}else {
				getJsp("/jsp/dept/dept.jsp", request, response);
			}
		} catch (ServletException | IOException | SQLException e) {
			try {
				getJsp("/jsp/dept/dept.jsp", request, response);
			} catch (ServletException | IOException e1) {
				e1.printStackTrace();
			}
		} 
	}
	
	/**
	 * 
	 * @MethodName: update
	 * @Description: TODO (更新用户数据)
	 * @param request
	 * @param response
	 * @Return Type: void
	 * @Author: zc-cris
	 * @Create Date：2018年2月3日上午11:24:34
	 * @since
	 * @throws
	 */
	@SuppressWarnings("unused")
	private void update(HttpServletRequest request, HttpServletResponse response) {
		String deptName = request.getParameter("deptName");
		String oldName = request.getParameter("oldName");
		String deptId = request.getParameter("deptId");
		String deptState = request.getParameter("deptState");
		String oldState = request.getParameter("oldState");
		String oldDesc = request.getParameter("oldDesc");
		String deptDesc = request.getParameter("deptDesc");
		
		Dept oldDept = new Dept(oldName, oldDesc,Integer.valueOf(deptId),oldState.equals("true"));
		Dept newDept = new Dept(deptName, deptDesc,Integer.valueOf(deptId),Integer.parseInt(deptState)==1);
		
		request.setAttribute("flag", "1");			//页面显示提示信息
		try {
			//service层对部门更新后的资料和原始部门资料进行比较
			DeptService.update(newDept,oldDept);
			request.setAttribute("message", "更新成功,点击确定后跳转至首页");
			//给页面设置自动跳转
			response.setHeader("Refresh", "1;URL="+request.getContextPath()+"/jsp/dept/dept.jsp");
			getJsp("/jsp/dept/showUpdateDept.jsp", request, response);
			//后台一定要抛出UserException自定义异常，这边才能抓取
		} catch (ServletException | IOException | SQLException | DeptException e) {
			request.setAttribute("dept", oldDept);
			checkException("/jsp/dept/showUpdateDept.jsp",request, response, e);
			e.printStackTrace();
		}
	}

	
	/**
	 * 
	 * @MethodName: checkException
	 * @Description: TODO (如果是自定义的异常信息，就跳转到添加页面并提示用户)
	 * @param request
	 * @param response
	 * @param e
	 * @Return Type: void
	 * @Author: zc-cris
	 * @Create Date：2018年2月2日下午3:14:18
	 * @since
	 * @throws
	 */
	public void checkException(String path, HttpServletRequest request, HttpServletResponse response, Exception e) {
		if(e instanceof DeptException) {
			request.setAttribute("message", e.getMessage());
			try {
				getJsp(path, request, response);
			} catch (ServletException | IOException e1) {
				e1.printStackTrace();
			}
		}
	}
	/**
	 * 
	 * @MethodName: getJsp @Description: TODO (请求转发) @param request @param
	 *              response @throws ServletException @throws IOException @Return
	 *              Type: void @Author: zc-cris @Create
	 *              Date：2018年2月1日下午8:38:56 @since @throws
	 */
	public void getJsp(String path, HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		request.getRequestDispatcher(path).forward(request, response);
	}
}

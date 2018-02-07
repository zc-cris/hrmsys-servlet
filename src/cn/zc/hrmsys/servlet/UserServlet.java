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

import cn.zc.hrmsys.MyException.UserException;
import cn.zc.hrmsys.factory.service.factory.ServiceFactory;
import cn.zc.hrmsys.pojo.criteria.UserCriteria;
import cn.zc.hrmsys.pojo.entity.User;
import cn.zc.hrmsys.service.user.service.IUserService;
import cn.zc.hrmsys.util.ObjectUtils;

@WebServlet("*.user")
public class UserServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private IUserService userService = (IUserService) ServiceFactory.newInstance().getServiceEntity("UserService");

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

	/**
	 * @throws IOException 
	 * @throws SQLException 
	 * 
	 * @MethodName: nameInvalidate
	 * @Description: TODO (通过jquery来验证用户名)
	 * @Return Type: void
	 * @Author: zc-cris
	 * @Create Date：2018年2月6日下午7:50:32
	 * @since
	 * @throws
	 */
	@SuppressWarnings("unused")
	private void nameInvalidate(HttpServletRequest request, HttpServletResponse response) throws SQLException, IOException {
		String name = request.getParameter("name");
		int count = userService.getCountByName(name);
		String str = null;
		if(count > 0) {
			str = "<font color='red'>用户名已经存在了！</font>";
		}else {
			str = "<font color='green'>用户名可以使用</font>";
		}
		response.setCharacterEncoding("utf-8");
		response.setContentType("text/html;charset=utf-8");
		response.getWriter().write(str);
	}
	/**
	 * 
	 * @MethodName: login
	 * @Description: TODO (登录)
	 * @param request
	 * @param response
	 * @Return Type: void
	 * @Author: zc-cris
	 * @Create Date：2018年2月3日上午11:22:09
	 * @since
	 * @throws
	 */
	@SuppressWarnings("unused")
	private void login(HttpServletRequest request, HttpServletResponse response) {
		User user;
		try {
			user = ObjectUtils.getObject(request, User.class);
			user = userService.getUser(user);
			if (user != null) {
				request.getSession().setAttribute("user", user);
				getJsp("/jsp/main.jsp", request, response);
			} else {
				request.setAttribute("message", "no");
				getJsp("/loginForm.jsp", request, response);
			}
		} catch (InstantiationException | IllegalAccessException | InvocationTargetException | SQLException
				| ServletException | IOException e) {
			e.printStackTrace();
		}
	}
	@SuppressWarnings("unused")
	private void logout(HttpServletRequest request, HttpServletResponse response) throws IOException {
		request.getSession().removeAttribute("user");
		response.sendRedirect(request.getContextPath()+"/loginForm.jsp");
	}
	
	/**
	 * 
	 * @MethodName: query
	 * @Description: TODO (模糊查询)
	 * @param request
	 * @param response
	 * @Return Type: void
	 * @Author: zc-cris
	 * @Create Date：2018年2月3日上午11:21:56
	 * @since
	 * @throws
	 */
	@SuppressWarnings("unused")
	private void query(HttpServletRequest request, HttpServletResponse response) {
		try {
			String userState = request.getParameter("userState");
			Integer value = Integer.valueOf(userState);

			// 前台必须让用户选择有效还是无效
			String userName = request.getParameter("userName");
			UserCriteria criteria = new UserCriteria(userName, value == 1);
			List<User> users = userService.getAllWithCriteria(criteria);

			// 页面回显
			request.setAttribute("selectStatus", userState);
			request.setAttribute("uName", userName);

			if (users != null && users.size() > 0) {
				request.setAttribute("users", users);
				request.setAttribute("flag", "1");
			} else {
				request.setAttribute("flag", "2");
			}

			getJsp("/jsp/user/user.jsp", request, response);
		} catch (SQLException | ServletException | IOException e) {
			e.printStackTrace();
		}
	}

	/**
	 * 
	 * @MethodName: delete @Description: TODO (根据一个或者多个id删除用户) @param request @param
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
					userService.deleteById(Integer.parseInt(values[0]));
				} else {
					userService.deleteByIds(values);
				}
				getJsp("/jsp/user/user.jsp", request, response);
			} catch (NumberFormatException | SQLException | ServletException | IOException e) {
				e.printStackTrace();
			}
		}

	}

	/**
	 * 
	 * @MethodName: queryAll
	 * @Description: TODO (查询所有有效用户)
	 * @param request
	 * @param response
	 * @Return Type: void
	 * @Author: zc-cris
	 * @Create Date：2018年2月3日上午11:23:38
	 * @since
	 * @throws
	 */
	@SuppressWarnings("unused")
	private void queryAll(HttpServletRequest request, HttpServletResponse response) {
		List<User> users;
		try {
			users = userService.getAll();
			request.setAttribute("users", users);
			getJsp("/jsp/user/user.jsp", request, response);
		} catch (SQLException | ServletException | IOException e) {
			e.printStackTrace();
		}
	}

	/**
	 * 
	 * @MethodName: add
	 * @Description: TODO (带验证的添加用户)
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
		String userName = request.getParameter("userName");
		String userPwd = request.getParameter("userPwd");
		User user = new User(userName, userPwd);
		request.setAttribute("flag", "1");			//页面显示提示信息
		try {
			if (userName == null || userName.trim().equals("")) {
				request.setAttribute("message", "员工姓名不能为空");
				getJsp("/jsp/user/showAddUser.jsp", request, response);
				return;
			}
			if(userPwd == null || userPwd.trim().equals("")) {
				request.setAttribute("message", "员工密码不能为空");
				getJsp("/jsp/user/showAddUser.jsp", request, response);
				return;
			}
			userService.add(user);
			request.setAttribute("message", "添加成功,点击确定后跳转至首页");
			//给页面设置自动跳转
			response.setHeader("Refresh", "0;URL="+request.getContextPath()+"/jsp/user/user.jsp");
			getJsp("/jsp/user/showAddUser.jsp", request, response);
		} catch (ServletException | IOException | SQLException | UserException e) {
			checkException("/jsp/user/showAddUser.jsp",request, response, e);
			e.printStackTrace();
		}
	}
	
	/**
	 * 
	 * @MethodName: get
	 * @Description: TODO (先根据id查询到用户数据，再进行更新数据操作)
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
		String userId = request.getParameter("userId");
		try {
			if(userId == null || "".equals(userId.trim())) {
				getJsp("/jsp/user/user.jsp", request, response);
				return;
			}
			User user = userService.getUserById(Integer.valueOf(userId));
			if(user != null) {
				request.setAttribute("user", user);
				getJsp("/jsp/user/showUpdateUser.jsp", request, response);
			}else {
				getJsp("/jsp/user/user.jsp", request, response);
			}
		} catch (ServletException | IOException | SQLException e) {
			try {
				getJsp("/jsp/user/user.jsp", request, response);
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
		String userId = request.getParameter("userId");
		String userName = request.getParameter("userName");
		String oldName = request.getParameter("oldName");
		String userPwd = request.getParameter("userPwd");
		String oldPwd = request.getParameter("oldPwd");
		String userState = request.getParameter("userState");
		String oldState = request.getParameter("oldState");
		User oldUser = new User(oldName, oldPwd,Integer.valueOf(userId),oldState.equals("true"));
		User newUser = new User(userName, userPwd,Integer.valueOf(userId),Integer.parseInt(userState) == 1 );
		request.setAttribute("flag", "1");			//页面显示提示信息
		try {
			//添加的时候不允许用户名和密码为空，成功取出来的话，自然都是有值的，跳转到showUpdate.jsp也肯定有值
			//更新跳转回后台也应该是有值的
//			if (userName == null || userName.equals("")) {
//				request.setAttribute("message", "员工姓名不能为空");
//				getJsp("/jsp/user/showUpdateUser.jsp", request, response);
//				return;
//			}
//			if(userPwd == null || userPwd.equals("")) {
//				request.setAttribute("message", "员工密码不能为空");
//				getJsp("/jsp/user/showUpdateUser.jsp", request, response);
//				return;
//			}
			//service层对用户更新后的用户资料和原始用户资料进行比较
			userService.update(newUser,oldUser);
			request.setAttribute("message", "更新成功,点击确定后跳转至首页");
			//给页面设置自动跳转
			response.setHeader("Refresh", "0;URL="+request.getContextPath()+"/jsp/user/user.jsp");
			getJsp("/jsp/user/showUpdateUser.jsp", request, response);
			//后台一定要抛出UserException自定义异常，这边才能抓取
		} catch (ServletException | IOException | SQLException | UserException e) {
			//页面回显原始数据
			request.setAttribute("user", oldUser);
			checkException("/jsp/user/showUpdateUser.jsp",request, response, e);
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
		if(e instanceof UserException) {
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

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

import cn.zc.hrmsys.factory.service.factory.ServiceFactory;
import cn.zc.hrmsys.pojo.entity.Job;
import cn.zc.hrmsys.service.dept.service.IDeptService;
import cn.zc.hrmsys.service.job.service.IJobService;
import cn.zc.hrmsys.service.user.service.IUserService;

@WebServlet("*.job")
public class JobServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private IJobService jobService = (IJobService) ServiceFactory.newInstance().getServiceEntity("JobService");

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		this.doPost(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
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
	private void update(HttpServletRequest request, HttpServletResponse response) throws SQLException, ServletException, IOException {
		Job job = new Job(request.getParameter("jobName"), request.getParameter("jobDesc"));
		job.setJobId(Integer.parseInt(request.getParameter("jobId")));
		jobService.update(job);
		request.setAttribute("flag", "1");
		request.setAttribute("message", "修改成功，点击确定返回首页");
		//给页面设置自动跳转
		response.setHeader("Refresh", "0;URL="+request.getContextPath()+"/jsp/job/job.jsp");
		getJsp("/jsp/job/showUpdateJob.jsp", request, response);
	}
	@SuppressWarnings("unused")
	private void get(HttpServletRequest request, HttpServletResponse response) throws NumberFormatException, SQLException, ServletException, IOException {
		Job job = jobService.getById(Integer.parseInt(request.getParameter("jobId")));
		request.setAttribute("job", job);
		getJsp("/jsp/job/showUpdateJob.jsp", request, response);
	}
	@SuppressWarnings("unused")
	private void add(HttpServletRequest request, HttpServletResponse response) throws SQLException, ServletException, IOException {
		jobService.add(new Job(request.getParameter("jobName"), request.getParameter("jobDesc")));
		request.setAttribute("flag", "1");
		request.setAttribute("message", "添加成功,点击确定后跳转至首页");
		//给页面设置自动跳转
		response.setHeader("Refresh", "0;URL="+request.getContextPath()+"/jsp/job/job.jsp");
		getJsp("/jsp/job/showAddJob.jsp", request, response);
	}
	@SuppressWarnings("unused")
	private void delete(HttpServletRequest request, HttpServletResponse response) throws NumberFormatException, SQLException, ServletException, IOException {
		String jobId = request.getParameter("jobId");
		jobService.delete(Integer.parseInt(jobId));
		request.setAttribute("flag", "1");
		request.setAttribute("message", "删除成功");
		getJsp("/jsp/job/job.jsp", request, response);
	}
	@SuppressWarnings("unused")
	private void query(HttpServletRequest request, HttpServletResponse response) throws SQLException, ServletException, IOException {
		String jobName = request.getParameter("jobName");
		List<Job> jobs = jobService.getAll(jobName);
		request.setAttribute("jobs", jobs);
		getJsp("/jsp/job/job.jsp", request, response);
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

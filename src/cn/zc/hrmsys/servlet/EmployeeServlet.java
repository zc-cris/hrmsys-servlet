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

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import cn.zc.hrmsys.factory.service.factory.ServiceFactory;
import cn.zc.hrmsys.pojo.criteria.EmpCriteria;
import cn.zc.hrmsys.pojo.entity.Dept;
import cn.zc.hrmsys.pojo.entity.Emp;
import cn.zc.hrmsys.pojo.entity.Job;
import cn.zc.hrmsys.service.dept.service.IDeptService;
import cn.zc.hrmsys.service.emp.service.IEmpService;
import cn.zc.hrmsys.service.job.service.IJobService;
import jdk.nashorn.internal.parser.JSONParser;

@WebServlet("*.emp")
public class EmployeeServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private IDeptService DeptService = (IDeptService) ServiceFactory.newInstance().getServiceEntity("DeptService");
	private IEmpService empService = (IEmpService) ServiceFactory.newInstance().getServiceEntity("EmpService");
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
	private void queryEmp(HttpServletRequest request, HttpServletResponse response) throws NumberFormatException, SQLException, ServletException, IOException {
		String deptId = request.getParameter("job_id");
		String jobId = request.getParameter("dept_id");
		String name = request.getParameter("name");
		String sex = request.getParameter("sex");
		List<Emp> emps = empService.getAllWithCriteria(
				new EmpCriteria(
				"".equals(name)==true?null:name,Integer.valueOf(deptId)==0?null:Integer.valueOf(deptId),
				Integer.valueOf(jobId)==0?null:Integer.valueOf(jobId),
				Integer.parseInt(sex)==1));
		
		request.setAttribute("emps", emps);
		queryDept(request, response);
	}
	
	@SuppressWarnings("unused")
	private void queryDept(HttpServletRequest request, HttpServletResponse response) throws SQLException, ServletException, IOException {
		List<Dept> depts = DeptService.getAll();
		request.setAttribute("depts", depts);
		getJsp("/jsp/employee/employee.jsp", request, response);
	}
	@SuppressWarnings("unused")
	private void getJob(HttpServletRequest request, HttpServletResponse response) throws NumberFormatException, SQLException, JSONException, IOException {
		String deptId = request.getParameter("deptVal");
		System.out.println(deptId);
		List<Job> jobs = jobService.getByDeptId(Integer.parseInt(deptId));
		//对象转换为JSONObject对象，然后再把JSONObject对象放进JSONArray数组里面
		JSONArray array = new JSONArray();
		for(Job job : jobs) {
			JSONObject obj = new JSONObject();
			obj.put("jobId", job.getJobId());
			obj.put("jobName", job.getJobName());
			array.put(obj);
		}
		response.setCharacterEncoding("utf-8");
		response.getWriter().write(array.toString());
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

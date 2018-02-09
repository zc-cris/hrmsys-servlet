package cn.zc.hrmsys.servlet;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.UnsupportedEncodingException;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;
import java.util.zip.ZipOutputStream;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.Part;

import org.apache.commons.beanutils.BeanUtils;
import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.FileUploadException;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;

import cn.zc.hrmsys.MyException.UploadException;
import cn.zc.hrmsys.factory.service.factory.ServiceFactory;
import cn.zc.hrmsys.pojo.entity.FileBean;
import cn.zc.hrmsys.pojo.entity.User;
import cn.zc.hrmsys.service.file.service.IFileService;
import cn.zc.hrmsys.util.DeleteFileUtils;
import cn.zc.hrmsys.util.UploadParamsUtils;
import cn.zc.hrmsys.util.ZipUtils;

@WebServlet("*.file")
public class FileServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	private IFileService fileService = (IFileService) ServiceFactory.newInstance().getServiceEntity("FileService");
	private String type = UploadParamsUtils.newInstance().getKey("type"); // 文件类型
	private String uploadFilePath = UploadParamsUtils.newInstance().getKey("upload.file.path"); // 文件的上传根路径

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
	private void update(HttpServletRequest request, HttpServletResponse response) throws SQLException {
		
		FileBean fileBean = new FileBean(
				Integer.parseInt(request.getParameter("id")),
				request.getParameter("name"),
				request.getParameter("desc"), 
				((User)request.getSession().getAttribute("user")).getUserName());
		fileService.update(fileBean);
		request.setAttribute("message", "修改成功,点击确定后跳转至首页");
		request.setAttribute("flag", "1");
		// 给页面设置自动跳转
		response.setHeader("Refresh", "0;URL=" + request.getContextPath() + "/jsp/file/file.jsp");
		getJsp("/jsp/file/showUpdateFile.jsp", request, response);
	}

	@SuppressWarnings("unused")
	private void get(HttpServletRequest request, HttpServletResponse response)
			throws NumberFormatException, SQLException {
		FileBean file = fileService.getFileById(Integer.valueOf(request.getParameter("fileId")));
		request.setAttribute("file", file);
//		System.out.println(file);
		getJsp("/jsp/file/showUpdateFile.jsp", request, response);
	}

	/**
	 * 
	 * @MethodName: download @Description: TODO (借助工具实现压缩包形式下载单文件) @param
	 * request @param response @throws IOException @Return Type: void @Author:
	 * zc-cris @Create Date：2018年2月5日下午9:32:05 @since @throws
	 */
	@SuppressWarnings("unused")
	private void download(HttpServletRequest request, HttpServletResponse response) throws IOException {
		String zipName = "file.zip";
		// String[] filePaths = request.getParameterValues("downloadFile");
		String fileId = request.getParameter("fileId");

		response.setHeader("Content-Disposition", "attachment; filename=" + zipName);
		response.setContentType("APPLICATION/OCTET-STREAM");
		ZipOutputStream out = new ZipOutputStream(response.getOutputStream());
		try {
			String filePath = fileService.getFilePathById(Integer.valueOf(fileId)).getFilePath();
			ZipUtils.doCompress(filePath, out);
			response.flushBuffer();
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			out.close();
		}

	}

	@SuppressWarnings("unused")
	private void delete(HttpServletRequest request, HttpServletResponse response)
			throws NumberFormatException, SQLException {
		fileService.delete(Integer.parseInt(request.getParameter("fileId")));
		request.setAttribute("flag", "1");
		request.setAttribute("message", "删除成功");
		getJsp("/jsp/file/file.jsp", request, response);
	}

	@SuppressWarnings("unused")
	private void query(HttpServletRequest request, HttpServletResponse response) throws SQLException {
		String fileName = request.getParameter("fileName");
		if (fileName == null || "".equals(fileName.trim())) {
			List<FileBean> files = fileService.getAll();

			request.setAttribute("files", files);
			getJsp("/jsp/file/file.jsp", request, response);
		} else {
			List<FileBean> files = fileService.getAllWithName(fileName);
			if (files == null || files.size() == 0) {
				request.setAttribute("flag", "1");
				request.setAttribute("message", "没有找到该文件名的记录");
			}
			request.setAttribute("files", files);
			getJsp("/jsp/file/file.jsp", request, response);
		}
	}

	/**
	 * @throws InvocationTargetException @throws IllegalAccessException
	 * 
	 * @MethodName: upload @Description: TODO (文件上传) @param request @param
	 * response @throws UnsupportedEncodingException @Return Type: void @Author:
	 * zc-cris @Create Date：2018年2月3日下午5:10:20 @since @throws
	 */
	@SuppressWarnings("unused")
	private void upload(HttpServletRequest request, HttpServletResponse response)
			throws UnsupportedEncodingException, IllegalAccessException, InvocationTargetException {
		String path = ""; // 上传成功或者失败需要转发到的路径
		// 1.获取到ServletFileUpload对象
		request.setCharacterEncoding("utf-8");
		request.setAttribute("flag", "1");
		ServletFileUpload upload = getServletFileUpload();
		try {
			// 2.解析request对象得到FileItem对象的集合，并且创建一个FileBean对象的集合
			List<FileItem> items = upload.parseRequest(request);
			FileBean fileBean = new FileBean();

			// 2.1校验每个文件的大小和总文件的大小是否合乎标准(在解析时，组件已经帮我们校验了，我们只需要得到异常
			// 的结果并且通知用户即可);

			// 3.检查文件的扩展名是否合乎规范并且把每一个FileItem对象设置到一个FileBean对象中
			check(items, fileBean, request);

			// 如果当前request域中没有message提示信息属性的值
			// 4.进行文件的上传操作
			beginUpload(fileBean);

			// 5.将文件信息保存到数据库
			save(fileBean, request);

			// 6.清空临时文件夹里的缓存
			DeleteFileUtils.delete(new File(UploadParamsUtils.newInstance().getKey("temp.file.path")));
			// 7.成功后页面跳转
			path = "/jsp/file/showAddFile.jsp";
			request.setAttribute("message", "上传成功,点击确定后跳转至首页");
			// 给页面设置自动跳转
			response.setHeader("Refresh", "0;URL=" + request.getContextPath() + "/jsp/file/file.jsp");
			getJsp(path, request, response);
		} catch (Exception e) {
			if (e instanceof UploadException) {
				path = "/jsp/file/showAddFile.jsp";
				request.setAttribute("message", e.getMessage());
				getJsp(path, request, response);
			}
			e.printStackTrace();
		}
	}

	/**
	 * @throws Exception @throws Exception
	 * 
	 * @MethodName: BeginUpload @Description: TODO (将文件上传到硬盘) @param
	 * fileBeans @Return Type: void @Author: zc-cris @Create
	 * Date：2018年2月3日下午5:07:45 @since @throws
	 */
	private void beginUpload(FileBean fileBean) throws Exception {
		fileBean.getFileItem().write(new File(fileBean.getFilePath()));
		// InputStream in = null;
		// OutputStream out = null;
		// // 将文件从item读出来然后写到硬盘上
		// in = fileBean.getFileItem().getInputStream();
		// byte[] buffer = new byte[1024];
		// int len = 0;
		// out = new FileOutputStream(fileBean.getFilePath());
		// while ((len = in.read(buffer)) != -1) {
		// out.write(buffer, 0, len);
		// }
		// out.close();
		// in.close();
	}

	/**
	 * @throws SQLException
	 * 
	 * @MethodName: save @Description: TODO (将文件信息存进数据库) @param fileBeans @Return
	 * Type: void @Author: zc-cris @Create Date：2018年2月3日下午5:08:15 @since @throws
	 */
	private void save(FileBean fileBean, HttpServletRequest request) throws SQLException {
		fileBean.setUserName(((User) request.getSession().getAttribute("user")).getUserName());
		fileService.upload(fileBean);
	}

	/**
	 * @throws InvocationTargetException @throws IllegalAccessException
	 * 
	 * @MethodName: check @Description: TODO (检查上传文件是否符合格式要求) @param items @param
	 * fileBeans @param request @throws UnsupportedEncodingException @Return Type:
	 * void @Author: zc-cris @Create Date：2018年2月3日下午5:08:32 @since @throws
	 */
	private void check(List<FileItem> items, FileBean fileBean, HttpServletRequest request)
			throws UnsupportedEncodingException, IllegalAccessException, InvocationTargetException {
		String name = null;
		String subName = null;
		List<String> typeList = Arrays.asList(type.split(","));
		for (FileItem item : items) {
			// 如果是普通的文本域
			if (item.isFormField()) {
				isFormField(fileBean, item);
			} else { // 如果是文件域
				isFileField(fileBean, typeList, item);
			}
		}
	}

	// 首字母小写转大写
	public String upperCase(String str) {
		char[] ch = str.toCharArray();
		if (ch[0] >= 'a' && ch[0] <= 'z') {
			ch[0] = (char) (ch[0] - 32);
		}
		return new String(ch);
	}

	/**
	 * 
	 * @MethodName: isFileField @Description: TODO (如果是文件域进行的操作) @param
	 * fileBeans @param typeList @param item @Return Type: void @Author:
	 * zc-cris @Create Date：2018年2月3日下午5:09:24 @since @throws
	 */
	private void isFileField(FileBean fileBean, List<String> typeList, FileItem item) {
		String name;
		String subName;
		name = item.getName(); // 上传文件的文件名字(2.简单MVC流程.pptx)
		if (name == null || name == "") {
			throw new UploadException("不能上传空文件！！！");
		}
		subName = name.substring(name.lastIndexOf("."));
		// 如果上传的文件扩展名不符合要求的话，直接返回到上传页面并提醒用户
		if (!(typeList.contains(subName))) {
			throw new UploadException("您上传的名为" + name + "的文件的扩展名不正确，必须为" + type + "类型！！！");
		} else {
			// 注意：存储在数据库的文件名就是上传文件的名字，但是存储在硬盘上的文件路径需要使用uuid唯一区分，可以防止同名文件覆盖
			fileBean.setFileName(name);
			fileBean.setFilePath(uploadFilePath + UUID.randomUUID().toString().replaceAll("-", "") + subName);
			fileBean.setFileItem(item);
		}
	}

	/**
	 * @throws InvocationTargetException @throws IllegalAccessException
	 * 
	 * @MethodName: isFormField @Description: TODO (如果是文本域进行的操作) @param
	 * formFields @param item @throws UnsupportedEncodingException @Return Type:
	 * void @Author: zc-cris @Create Date：2018年2月3日下午5:09:44 @since @throws
	 */
	private void isFormField(FileBean fileBean, FileItem item)
			throws UnsupportedEncodingException, IllegalAccessException, InvocationTargetException {
		// System.out.println(item.getString());
		if (item.getString() == null || item.getString().trim().equals("")) {
			throw new UploadException("文件描述或者标题不能为空！！！");
		}
		// 防止getString获取到的中文在数据库中乱码，所以需要在这里指定编码格式
		// System.out.println(item.getFieldName()+"======"+item.getString("utf-8"));

		BeanUtils.copyProperty(fileBean, item.getFieldName(), item.getString("UTF-8"));
	}

	/**
	 * 
	 * @MethodName: getServletFileUpload @Description: TODO
	 * (获取到ServeltFileUpload对象) @return @Return Type: ServletFileUpload @Author:
	 * zc-cris @Create Date：2018年2月3日下午4:20:30 @since @throws
	 */
	private ServletFileUpload getServletFileUpload() {
		UploadParamsUtils newInstance = UploadParamsUtils.newInstance();
		String perFileSize = newInstance.getKey("per.file.size");
		// String totalFileSize = newInstance.getKey("total.file.size");
		String tempSize = newInstance.getKey("temp.size");
		String tempFilepath = newInstance.getKey("temp.file.path");

		// System.out.println(type+"----"+fileSize+"-----"+totalFileSize);

		DiskFileItemFactory factory = new DiskFileItemFactory();
		// 设置文件读取到内存中的最大字节数
		factory.setSizeThreshold(Integer.parseInt(tempSize));
		// 设置临时的文件夹（如果文件的内容在读取中大于上面设置的最大字节数，就写入临时的文件夹）
		File repository = new File(tempFilepath);
		factory.setRepository(repository);

		// 3. create a new file upload handler
		ServletFileUpload upload = new ServletFileUpload(factory);

		// 4. set overall request size constraint
		// 设置上传的文件单个最大大小
		upload.setFileSizeMax(Integer.parseInt(perFileSize));
		// 设置上传的总文件的最大大小
		// upload.setSizeMax(Integer.parseInt(totalFileSize));
		return upload;
	}

	/**
	 * 
	 * @MethodName: getJsp @Description: TODO (请求转发) @param request @param
	 *              response @throws ServletException @throws IOException @Return
	 *              Type: void @Author: zc-cris @Create
	 *              Date：2018年2月1日下午8:38:56 @since @throws
	 */
	public void getJsp(String path, HttpServletRequest request, HttpServletResponse response) {
		try {
			request.getRequestDispatcher(path).forward(request, response);
		} catch (ServletException | IOException e) {
			e.printStackTrace();
		}
	}

}

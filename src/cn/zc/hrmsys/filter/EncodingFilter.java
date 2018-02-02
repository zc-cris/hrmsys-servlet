package cn.zc.hrmsys.filter;

import java.io.IOException;

import javax.servlet.DispatcherType;
import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebFilter(dispatcherTypes = {
		DispatcherType.REQUEST, 
		DispatcherType.FORWARD, 
		DispatcherType.INCLUDE, 
		DispatcherType.ERROR
}
			, urlPatterns = { "/*" })
public class EncodingFilter extends HttpFilter {

	//通过初始化的方式来获取web应用的全局参数并赋值给一个私有变量encoding
		//好处就是只需要调用一次init方法，就可以在EncodingFilter这个单例中一直使用encoding变量
	private String encoding;
	@Override
	public void init() throws ServletException {
		encoding = getFilterConfig().getServletContext().getInitParameter("encoding");
	}
	
	/**
	 * @Field Name：serialVersionUID
	 * @Description：TODO (用一句话描述这个变量表示什么) 
	 * @Create Date：2018年2月1日下午7:44:49
	 */
	
	private static final long serialVersionUID = 1L;
	@Override
	protected void doFilter(HttpServletRequest request, HttpServletResponse response, FilterChain chain)
			throws IOException, ServletException {
		request.setCharacterEncoding(encoding);
		response.setCharacterEncoding(encoding);
		response.setContentType("text/html;charset=utf-8");
		chain.doFilter(request, response);
	}
}

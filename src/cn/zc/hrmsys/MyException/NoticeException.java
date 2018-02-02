package cn.zc.hrmsys.MyException;

public class NoticeException extends RuntimeException {

	
	/**
	 * @Field Name：serialVersionUID
	 * @Description：TODO (用一句话描述这个变量表示什么) 
	 * @Create Date：2018年2月1日下午4:02:25
	 */
	
	private static final long serialVersionUID = 1L;

	public NoticeException(String message) {
		super(message);
	}
}

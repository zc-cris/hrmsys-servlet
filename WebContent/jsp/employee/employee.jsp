<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="f" %>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
<head>
	<title>人事管理系统 ——员工管理</title>
	<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
	<meta http-equiv="pragma" content="no-cache" />
	<meta http-equiv="cache-control" content="no-cache" />
	<meta http-equiv="expires" content="0" />    
	<meta http-equiv="keywords" content="keyword1,keyword2,keyword3" />
	<meta http-equiv="description" content="This is my page" />
	<link href="${pageContext.request.contextPath}/css/css.css" type="text/css" rel="stylesheet" />
	<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/js/ligerUI/skins/Aqua/css/ligerui-dialog.css"/>
	<link href="${pageContext.request.contextPath}/js/ligerUI/skins/ligerui-icons.css" rel="stylesheet" type="text/css" />
	<script type="text/javascript" src="${pageContext.request.contextPath}/js/jquery-1.11.0.js"></script>
    <script type="text/javascript" src="${pageContext.request.contextPath}/js/jquery-migrate-1.2.1.js"></script>
	<script src="${pageContext.request.contextPath}/js/ligerUI/js/core/base.js" type="text/javascript"></script>
	<script src="${pageContext.request.contextPath}/js/ligerUI/js/plugins/ligerDrag.js" type="text/javascript"></script> 
	<script src="${pageContext.request.contextPath}/js/ligerUI/js/plugins/ligerDialog.js" type="text/javascript"></script>
	<script src="${pageContext.request.contextPath}/js/ligerUI/js/plugins/ligerResizable.jss" type="text/javascript"></script>
	<link href="${pageContext.request.contextPath}/css/pager.css" type="text/css" rel="stylesheet" />

	<script type="text/javascript">
	
	/* 	$("#province").change(function() {
    		//清空城市下拉框中除第一项以外的数据(使用jquery的remove方法)
    		$("#city option:gt(0)").remove();
			var $option = $("#province option:selected");
			var province = $option.text();
			if("选择省份"!=province){
				$.ajax({
					type: "POST",
					url: "${pageContext.request.contextPath}/struts2/ProviceAndCity_findCity?time="+new Date().getTime(),
					data: {"province":province},
					success: function(backData,textStatus,ajax) {
						//alert(backData=null?"ha":"xi");
						//alert(ajax.responseText);		通过核心对象打印出服务器传来的json文本字符串（具体的值）
						//解析json文本
						var array = backData.listCity;		//backData是json对象,array是数组（js对象）
						for(var i =0;i<array.length;i++){
							var city = array[i];
							var $city = $("<option>"+city+"</option>");
							$("#city").append($city);
						
					}
						/*
						var $city = $(array);		//把js数组对象变成jquery数组对象，然后进行迭代
						$city.each(function() {
							alert(this);		//this表示数组里的每个城市
						});
						
					}
				});
			}
		}); */
	       $(function(){
	    	   /** 获取上一次选中的部门数据 */
	    	   var boxs  = $("input[type='checkbox'][id^='box_']");
	    	   
	    	   
		    	$("#dept_id").change(function() {
		    		//清空职位下拉框中除第一项以外的数据(使用jquery的remove方法)
		    		$("#job_id option:gt(0)").remove();
		    		var $option = $("#dept_id option:selected");
		    		var deptVal = $option.val();
		    		if(deptVal != '0'){		//如果不是选择的第一项就触发ajax事件
		    			$.ajax({
		    				type: "POST",
		    				url: "${pageContext.request.contextPath}/getJob.emp",
		    				data: {"deptVal":deptVal},
		    				success: function(backData,textStatus,ajax) {
		    					//alert(backData);
		    					//alert(ajax.responseText);		//通过核心对象打印出服务器传来的json文本字符串（具体的值）
		    					//解析json格式的字符串文本
		    					var array = eval("("+backData+")");
		    					for(var i =0;i<array.length;i++){
		    						var jobName = array[i].jobName;
		    						var jobId = array[i].jobId;
		    						var $job = $("<option value='"+jobId+"' >"+jobName+"</option>");
		    						$("#job_id").append($job);
		    				} 
		    				}
		    			});
		    		}
				})
	    	   
	    	   /** 给数据行绑定鼠标覆盖以及鼠标移开事件  */
		    	$("tr[id^='data_']").hover(function(){
		    		$(this).css("backgroundColor","#eeccff");
		    	},function(){
		    		$(this).css("backgroundColor","#ffffff");
		    	})
		    	
		    	
	    	   /** 删除员工绑定点击事件 */
	    	   $("#delete").click(function(){
	    		   /** 获取到用户选中的复选框  */
	    		   var checkedBoxs = boxs.filter(":checked");
	    		   if(checkedBoxs.length < 1){
	    			   $.ligerDialog.error("请选择一个需要删除的员工！");
	    		   }else{
	    			   /** 得到用户选中的所有的需要删除的ids */
	    			   var ids = checkedBoxs.map(function(){
	    				   return this.value;
	    			   })
	    			   
	    			   $.ligerDialog.confirm("确认要删除吗?","删除员工",function(r){
	    				   if(r){
	    					   // alert("删除："+ids.get());
	    					   // 发送请求
	    					   window.location = "${pageContext.request.contextPath}/employee/removeEmployee?ids=" + ids.get();
	    				   }
	    			   });
	    		   }
	    	   })
	       })
	</script>
</head>
<body>
	<!-- 导航 -->
	<table width="100%" border="0" cellpadding="0" cellspacing="0">
	  <tr><td height="10"></td></tr>
	  <tr>
	    <td width="15" height="32"><img src="${pageContext.request.contextPath}/images/main_locleft.gif" width="15" height="32"></td>
		<td class="main_locbg font2"><img src="${pageContext.request.contextPath}/images/pointer.gif">&nbsp;&nbsp;&nbsp;当前位置：员工管理 &gt; 员工查询</td>
		<td width="15" height="32"><img src="${pageContext.request.contextPath}/images/main_locright.gif" width="15" height="32"></td>
	  </tr>
	</table>
	
	<table width="100%" height="90%" border="0" cellpadding="5" cellspacing="0" class="main_tabbor">
	  <!-- 查询区  -->
	  <tr valign="top">
	    <td height="30">
		  <table width="100%" border="0" cellpadding="0" cellspacing="10" class="main_tab">
		    <tr>
			  <td class="fftd">
			  	<form name="empform" method="post" id="empform" action="${pageContext.request.contextPath}/queryEmp.emp">
				    <table width="100%" border="0" cellpadding="0" cellspacing="0">
					  <tr>
					    <td class="font3">
					    	职位：
							    <select name="job_id" style="width:143px;" id="job_id">
					    			<option value="0">--请选择职位--</option>
					    		</select>
					    	姓名：<input type="text" name="name">
					    	身份证号码：<input type="text" name="empIdCard" maxlength="18">
					    </td>
					  </tr>
					  <tr>
					    <td class="font3">
					    	性别：
					    		<select name="sex" style="width:143px;">
					    			<option value="1">--请选择性别--</option>
					    			<option value="1">男</option>
					    			<option value="2">女</option>
					    		</select>
					    	所属部门：<select  name="dept_id" style="width:100px;" id="dept_id">
								   <option value="0">--部门选择--</option>
								   <c:forEach items="${requestScope.depts }" var="dept">
					    				<option value="${dept.deptId }">${dept.deptName }</option>
					    			</c:forEach>
							</select>&nbsp;
					    	<input type="submit" value="搜索"/>
					    	<input id="delete" type="button" value="删除"/>
					    </td>
					  </tr>
					</table>
				</form>
			  </td>
			</tr>
		  </table>
		</td>
	  </tr>
	  
	  <!-- 数据展示区 -->
	  <tr valign="top">
	    <td height="20">
		  <table width="100%" border="1" cellpadding="5" cellspacing="0" style="border:#c2c6cc 1px solid; border-collapse:collapse;">
		    <tr class="main_trbg_tit" align="center">
			  <td><input type="checkbox" name="checkAll" id="checkAll"></td>
			  <td>姓名</td>
			  <td>性别</td>
			  <td>手机号码</td>
			  <td>邮箱</td>
			  <td>职位</td>
			  <td>学历</td>
			  <td>身份证号码</td>
			  <td>部门</td>
			  <td>联系地址</td>
			  <td>建档日期</td>
			  <td align="center">操作</td>
			</tr>
			<c:forEach items="${requestScope.emps}" var="emp" varStatus="stat">
				<tr id="data_${stat.index}" class="main_trbg" align="center">
					<td><input type="checkbox" id="box_${stat.index}" value="${emp.empId}"></td>
					 <td>${emp.empName }</td>
					  <td>
					        <c:choose>
					        	<c:when test="${emp.empGender == true }">男</c:when>
					        	<c:otherwise>女</c:otherwise>
					        </c:choose>
					  </td>
					  <td>${emp.empTel }</td>
					  <td>${emp.empEmail }</td>
					  <td>${emp.jobId }</td>
					  <td>${emp.empEdu }</td>
					  <td>${emp.empIdCard }</td>
					  <td>${emp.deptId }</td>
					  <td>${emp.empAddress }</td>
					  <td>
					  	<f:formatDate value="${emp.empRegDate}" 
								type="date" dateStyle="long"/>
					  </td>
					  <td align="center" width="40px;"><a href="${pageContext.request.contextPath}/get.emp?empId=${emp.empId}">
							<img title="修改" src="${pageContext.request.contextPath}/images/update.gif"/></a>
					  </td>
				</tr>
			</c:forEach>
		  </table>
		</td>
	  </tr>
	  <!-- 分页标签 -->
	  <tr valign="top"><td align="center" class="font3">
	  	 <fkjava:pager
	  	        pageIndex="${requestScope.pageModel.pageIndex}" 
	  	        pageSize="${requestScope.pageModel.pageSize}" 
	  	        recordCount="${requestScope.pageModel.recordCount}" 
	  	        style="digg"
	  	        submitUrl="${pageContext.request.contextPath}/employee/selectEmployee?pageIndex={0}"/>
	  </td></tr>
	</table>
	<div style="height:10px;"></div>
</body>
</html>
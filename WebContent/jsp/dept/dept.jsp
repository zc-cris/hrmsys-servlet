<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="f"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
<head>
<title>人事管理系统 ——部门管理</title>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<meta http-equiv="pragma" content="no-cache" />
<meta http-equiv="cache-control" content="no-cache" />
<meta http-equiv="expires" content="0" />
<meta http-equiv="keywords" content="keyword1,keyword2,keyword3" />
<meta http-equiv="description" content="This is my page" />
<link href="${pageContext.request.contextPath }/css/css.css"
	type="text/css" rel="stylesheet" />
<link rel="stylesheet" type="text/css"
	href="${pageContext.request.contextPath }/js/ligerUI/skins/Aqua/css/ligerui-dialog.css" />
<link
	href="${pageContext.request.contextPath }/js/ligerUI/skins/ligerui-icons.css"
	rel="stylesheet" type="text/css" />
<script type="text/javascript"
	src="${pageContext.request.contextPath }/js/jquery-1.11.0.js"></script>
<script type="text/javascript"
	src="${pageContext.request.contextPath }/js/jquery-migrate-1.2.1.js"></script>
<script
	src="${pageContext.request.contextPath }/js/ligerUI/js/core/base.js"
	type="text/javascript"></script>
<script
	src="${pageContext.request.contextPath }/js/ligerUI/js/plugins/ligerDrag.js"
	type="text/javascript"></script>
<script
	src="${pageContext.request.contextPath }/js/ligerUI/js/plugins/ligerDialog.js"
	type="text/javascript"></script>
<script
	src="${pageContext.request.contextPath }/js/ligerUI/js/plugins/ligerResizable.jss"
	type="text/javascript"></script>
<link href="${pageContext.request.contextPath }/css/pager.css"
	type="text/css" rel="stylesheet" />
<script type="text/javascript">
	$(function() {
		//获取上一次选中的部门数据 
		var boxs = $("input[type='checkbox'][id^='box_']");

		// 给数据行绑定鼠标覆盖以及鼠标移开事件  
		$("tr[id^='data_']").hover(function() {
			$(this).css("backgroundColor", "#eeccff");
		}, function() {
			$(this).css("backgroundColor", "#ffffff");
		})

		// 删除员工绑定点击事件 
		$("#delete")
				.click(
						function() {
							//获取到用户选中的复选框  
							var checkedBoxs = boxs.filter(":checked");
							if (checkedBoxs.length < 1) {
								$.ligerDialog.error("请选择需要删除的部门！");
							} else {
								//得到用户选中的所有的需要删除的ids 
								var ids = checkedBoxs.map(function() {
									return this.value;
								})
								///////创建一个数组，将每个id传递进去，放进隐藏域中
								var checkedIdArray = new Array();
								for (var a = 0; a < checkedBoxs.length; a++) {
									checkedIdArray.push(checkedBoxs[a].value);
								}
								alert(checkedIdArray.length); //打印用户要注销的所有用户的id的个数
								$("#checkedIdArray").val(checkedIdArray);

								$.ligerDialog
										.confirm(
												"确认要删除吗?",
												"删除部门",
												function(r) {
													if (r) {
														// alert("删除："+ids.get());
														// 发送请求
														$("#form1").submit();
													}
												});
							}
						})

		$("#queryAll")
				.click(
						function() {
							window.location = "${pageContext.request.contextPath}/queryAll.dept";
						})

		//全选，全不选
		$("#checkAll").click(function() {
			if ($(":checkbox").attr("checked") != "checked") {
				$(":checkbox").attr("checked", "checked");
			} else {
				$(":checkbox").removeAttr("checked");
			}
		})
	})
</script>
</head>
<body>
	<!-- 如果没有查询到用户，就进行弹出提示 -->
	<c:if test="${requestScope.flag eq '2'}">
		<script type="text/javascript">
			alert("没有要查询的部门信息");
		</script>
	</c:if>
	<!-- 导航 -->
	<table width="100%" border="0" cellpadding="0" cellspacing="0">
		<tr>
			<td height="10"></td>
		</tr>
		<tr>
			<td width="15" height="32"><img
				src="${pageContext.request.contextPath }/images/main_locleft.gif"
				width="15" height="32"></td>
			<td class="main_locbg font2"><img
				src="${pageContext.request.contextPath }/images/pointer.gif">&nbsp;&nbsp;&nbsp;当前位置：部门管理
				&gt; 部门查询</td>
			<td width="15" height="32"><img
				src="${pageContext.request.contextPath }/images/main_locright.gif"
				width="15" height="32"></td>
		</tr>
	</table>

	<table width="100%" height="90%" border="0" cellpadding="5"
		cellspacing="0" class="main_tabbor">
		<!-- 查询区  -->
		<tr valign="top">
			<td height="30">
				<table width="100%" border="0" cellpadding="0" cellspacing="10"
					class="main_tab">
					<tr>
						<td class="fftd">
							<form name="deptform" method="post" id="deptform"
								action="${pageContext.request.contextPath }/query.dept">
								<table width="100%" border="0" cellpadding="0" cellspacing="0">
									<tr>
										<td class="font3">部门名称：<input type="text" name="deptName"
											value="${dName }"> 部门状态：<select name="deptState"
											style="width: 60px" value="${selectStatus }">
												<option value="1"
													<c:if test="${selectStatus eq '1'}">selected</c:if>>有效</option>
												<option value="2"
													<c:if test="${selectStatus eq '2'}">selected</c:if>>失效</option>
										</select>&nbsp; <input id="query" type="submit" value="搜索" />&nbsp; <input
											id="delete" type="button" value="解散" />&nbsp; <input
											type="button" value="查询全部有效部门" id="queryAll" />
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
			<form action="${pageContext.request.contextPath}/delete.dept"
					id="form1" method="post">
				<table width="100%" border="1" cellpadding="5" cellspacing="0"
					style="border: #c2c6cc 1px solid; border-collapse: collapse;">
					<input type="hidden" name="checkedIdArray" value=""
						id="checkedIdArray">
					<tr class="main_trbg_tit" align="center">
						<td style="width: 30px"><input type="button" name="checkAll"
							id="checkAll" value="全选" style="width: 40px"></td>
						<td>部门名称</td>
						<td>详细信息</td>
						<td>状态</td>
						<td align="center">操作</td>
					</tr>
					<c:forEach items="${requestScope.depts}" var="dept"
						varStatus="stat">

						<tr id="data_${stat.index}" align="center" class="main_trbg"
							onMouseOver="move(this);" onMouseOut="out(this);">
							<td><input type="checkbox" id="box_${stat.index}"
								value="${dept.deptId}"></td>
							<td>${dept.deptName }</td>
							<td>${dept.deptDesc }</td>
							<td><c:choose>
									<c:when test="${dept.deptState == true }">有效部门</c:when>
									<c:otherwise>无效部门</c:otherwise>
								</c:choose></td>
							<td align="center" width="40px;"><a
								href="${pageContext.request.contextPath }/get.dept?deptId=${dept.deptId}">
									<img title="修改"
									src="${pageContext.request.contextPath }/images/update.gif" />
							</a></td>
						</tr>
					</c:forEach>
				</table>
				</form>
			</td>
		</tr>
		<!-- 分页标签 -->
		<tr valign="top">
			<td align="center" class="font3"><fkjava:pager
					pageIndex="${requestScope.pageModel.pageIndex}"
					pageSize="${requestScope.pageModel.pageSize}"
					recordCount="${requestScope.pageModel.recordCount}" style="digg"
					submitUrl="${pageContext.request.contextPath }/dept/selectDept?pageIndex={0}" />
			</td>
		</tr>
	</table>
	<div style="height: 10px;"></div>
</body>
</html>
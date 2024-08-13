<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<html>
<head>
	<meta charset="utf-8">
	<title>书绘人生官网</title>

	<link href="../style/css1.css" rel="stylesheet" type="text/css"/>
	<link href="../style/shouye.css" rel="stylesheet" type="text/css"/>
	<link rel="stylesheet" href="${pageContext.request.contextPath}/css/bootstrap.css">
	<link rel="stylesheet" href="${pageContext.request.contextPath}/css/AdminLTE.css">
	<link rel="stylesheet" href="${pageContext.request.contextPath}/css/_all-skins.min.css">
	<script src="${pageContext.request.contextPath}/js/jquery.min.js"></script>
	<script src="${pageContext.request.contextPath}/js/bootstrap.js"></script>
	<script type="text/javascript">
		function SetIFrameHeight() {
			var iframeid = document.getElementById("iframe");
			if (document.getElementById) {
				/*设置 内容展示区的高度等于页面可视区的高度*/
				iframeid.height = document.documentElement.clientHeight;
			}
		}
	</script>
</head>


<body>

		<div class="hezi1">
			<span class="span1">书绘人生-图书管理系统</span>
        	<span class="span1">www.shrs.org</span>
   	 	</div>
		<nav class="navbar navbar-static-top">
			<div class="navbar-custom-menu">
				<ul class="nav navbar-nav">
					<li class="dropdown user user-menu">
						<a href="${pageContext.request.contextPath}/logout">
							<span class="hidden-xs">注销</span>
						</a>
					</li>
					<li class="dropdown user user-menu">
						<a>
							<img src="${pageContext.request.contextPath}/img/user.jpg" class="user-image"
								 alt="User Image">
							<span class="hidden-xs">${USER_SESSION.name}</span>
						</a>
					</li>

				</ul>
			</div>
		</nav>
		<div class="fen1">
			<div class="p1">书绘人生图书借阅</div>
			<div class="p2"> Using Book To Describe Life </div>
		</div>
        <div class="daohang">
       		<a class="link1 a1" href="${pageContext.request.contextPath}/book/search" target="iframe"><span>图书列表</span></a>
			<a class="link1 a1"  href="${pageContext.request.contextPath}/book/searchBorrowed" target="iframe" ><span>当前借阅图书</span></a>
			<a class="link1 a1" href="${pageContext.request.contextPath}/record/searchRecords" target="iframe"><span>借阅记录</span></a>
			<a class="link1 a1" href="${pageContext.request.contextPath}/book/searchNoborrowBook" target="iframe"><span>图书管理</span></a>
			<a class="link1 a1" href="${pageContext.request.contextPath}/usermanage" target="iframe"><span>用户管理</span></a>
			<a class="link1 a1" href="${pageContext.request.contextPath}/book/bookUpload" target="iframe"><span>书目下载</span></a>
<%--			<ul>--%>
<%--				<li class="link1 a1"><a  href="${pageContext.request.contextPath}/book/selectNewbooks" target="iframe"><span>首页</span></a></li>--%>
<%--				<li class="link1 a1"><a  href="${pageContext.request.contextPath}/book/search" target="iframe" ><span>图书借阅</span></a></li>--%>
<%--				<li class="link1 a1"><a href="${pageContext.request.contextPath}/record/searchRecords" target="iframe"><span>借阅记录</span></a></li>--%>
<%--				<li class="link1 a1"><a  href="${pageContext.request.contextPath}/book/searchNoborrowBook" target="iframe"><span>图书管理</span></a></li>--%>
<%--				<li class="link1 a1"><a href="${pageContext.request.contextPath}/usermanage" target="iframe"><span>车辆管理</span></a></li>--%>
<%--				<li class="link1 a1"><a  href="${pageContext.request.contextPath}/book/bookUpload" target="iframe"><span>图书上传</span></a></li>--%>
<%--			</ul>--%>
        </div>
		<div >
			<iframe width="100%" id="iframe" name="iframe" onload="SetIFrameHeight()"
					frameborder="0" src="${pageContext.request.contextPath}/book/search">

			</iframe>
		</div>


</body>
</html>
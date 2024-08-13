<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<html>
<head>
    <meta charset="utf-8">
    <title>用户管理</title>
    <link rel="stylesheet" href="${pageContext.request.contextPath}/css/bootstrap.css">
    <link rel="stylesheet" href="${pageContext.request.contextPath}/css/AdminLTE.css">
    <link rel="stylesheet" href="${pageContext.request.contextPath}/css/pagination.css">
    <script src="${pageContext.request.contextPath}/js/jquery.min.js"></script>
    <script src="${pageContext.request.contextPath}/js/bootstrap.js"></script>
    <script src="${pageContext.request.contextPath}/js/pagination.js"></script>
    <script src="${pageContext.request.contextPath}/js/my.js"></script>
</head>
<body class="hold-transition skin-red sidebar-mini">
<!--数据展示头部-->
<div class="box-header with-border">
    <h3 class="box-title">用户管理</h3>
</div>
<!--数据展示头部-->
<!--数据展示内容区-->
<div class="box-body">
    <c:if test="${USER_SESSION.role =='ADMIN'}">
        <div class="pull-left">
            <div class="form-group form-inline">
                <div class="btn-group">
                    <button type="button" class="btn btn-default" title="添加车辆" data-toggle="modal"
                            data-target="#adduserr" onclick="resetStyll()"> 添加用户
                    </button>
                </div>
            </div>
        </div>
    </c:if>
    <!-- 数据表格 -->
    <table id="dataList" class="table table-bordered table-striped table-hover dataTable text-center">
        <thead>
        <tr>
            <th class="sorting_asc">用户姓名</th>
            <th class="sorting">用户名称</th>
            <th class="sorting">用户权限</th>
            <th class="sorting">用户状态</th>
            <th class="text-center">操作</th>
        </tr>
        </thead>
        <tbody>
        <c:forEach items="${pageResult.rows}" var="user">
            <tr>
                <td> ${user.name}</td>
                <td>${user.email}</td>
                <td>${user.role}</td>
                <td>${user.status}</td>
                <td class="text-center">

                <button type="button" class="btn bg-olive btn-xs" data-toggle="modal" data-target="#borrowModal"
                        onclick="deleteuser(${user.id})"> 删除
                </button>
                <c:if test="${USER_SESSION.role =='ADMIN'}">
                    <button type="button" class="btn bg-olive btn-xs" data-toggle="modal"
                            data-target="#adduserr" onclick=" findUserByeid(${user.id},'edit')"> 编辑
                    </button>
                </c:if>

                </td>
            </tr>
        </c:forEach>
        </tbody>
    </table>
    <!-- 数据表格 /-->
    <%--分页插件--%>
    <div id="pagination" class="pagination"></div>

</div>
<%--<jsp:include page="/web/Insert_modal.jsp"></jsp:include>--%>


<!-- 添加和编辑图书的模态窗口 -->
<div class="modal fade" id="adduserr" tabindex="-1" role="dialog" aria-labelledby="myModalLabel"
     aria-hidden="true">
    <div class="modal-dialog">
        <div class="modal-content">
            <div class="modal-header">
                <h3 id="myModalLabel">用户信息</h3>
            </div>
            <div class="modal-body">
                <form id="adduser">
                    <span><input type="hidden" id="uid" name="id"></span>
                    <table id="addOrEditTab" class="table table-bordered table-striped" width="800px">
                        <%--图书的id,不展示在页面--%>
                        <tr>
                            <td>用户名称</td>
                            <td><input class="form-control" placeholder="用户名称" name="name" id="uname"></td>
                            <td>用户权限</td>
                            <td><input class="form-control" placeholder="用户权限" name="role" id="urole"></td>
                        </tr>
                        <tr>
                            <td>用户密码</td>
                            <td><input class="form-control" placeholder="用户密码" name="password" id="pw"></td>
                            <td>账户名称</td>
                            <td><input class="form-control" placeholder="账户名称" name="email" id="uemail"></td>
                        </tr>
                            <tr>
                                <td>用户状态</td>
                                <td>
                                    <select class="form-control" name="status" id="ustatus">
                                        <option value="0">离线</option>
                                        <option value="1">在线</option>
                                    </select>
                                </td>
                            </tr>
                    </table>
                </form>
            </div>
            <div class="modal-footer">
                <button class="btn btn-success" data-dismiss="modal" aria-hidden="true" id="aoe" disabled onclick="addUser()">保存
                </button>
                <button class="btn btn-default" data-dismiss="modal" aria-hidden="true">关闭</button>
            </div>
        </div>
    </div>
</div>
<!-- 数据展示内容区/ -->
<%--引入存放模态窗口的页面--%>
<!-- 数据表格 /-->
<!-- 数据展示内容区/ -->
<%--引入存放模态窗口的页面--%>
</body>
<script>
    /*分页插件展示的总页数*/
    pageargs.total = Math.ceil(${pageResult.total}/pageargs.pagesize)
    /*分页插件当前的页码*/
    pageargs.cur = ${pageNum}
        /*分页插件页码变化时将跳转到的服务器端的路径*/
        pageargs.gourl = "${gourl}"
    /*保存搜索框中的搜索条件，页码变化时携带之前的搜索条件*/
    bookVO.name = "${search.name}"
    bookVO.author = "${search.author}"
    bookVO.press = "${search.press}"
    /*分页效果*/
    pagination(pageargs);
</script>
</html>

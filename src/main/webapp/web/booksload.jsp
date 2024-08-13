<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>图书上传与下载</title>
    <link href="../style/css1.css" rel="stylesheet" type="text/css"/>
    <script src="${pageContext.request.contextPath}/js/jquery.min.js" type="text/javascript"></script>
</head>

<body class="total">
<div >
    <table border-collapse="1">
        <tr>
            <td class="upload" >文件上传</td>
            <td class="upload">下载列表</td>
        </tr>
        <tr>
            <td height="100">
                <form action="${pageContext.request.contextPath}/booksUpload" method="post" enctype="multipart/form-data">
                    <input type="file" name="files" multiple="multiple"><br/>
                    <input type="reset" value="清空"/>
                    <input type="submit" value="提交"/>
                </form>
            </td>
            <td id="files"></td>
        </tr>
    </table>

</div>

</body>
<script>
    $(document).ready(function (){
        var url="${pageContext.request.contextPath}/getbooksName";
        $.get(url,function (files) {
            var files = eval('('+files+')');
            for(var i =0;i<files.length; i++){
                $("#files").append("<li><a href=${pageContext.request.contextPath}"+"\\"+"download?filename="+files[i].name+">"+files[i].name+"</a></li>")
            }

        })
    })
</script>
</html>

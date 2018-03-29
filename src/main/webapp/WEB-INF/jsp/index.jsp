<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8"%>
<%@taglib prefix="shiro" uri="http://shiro.apache.org/tags" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
 <script  type="text/javascript" src="../js/jquery-3.3.1.js"></script>  
 <script  type="text/javascript" src="../js/jquery-3.3.1.min.js"></script>
<title>首页</title>
<script>
function queryUser(){
   $.ajax({
    url:"${pageContext.request.contextPath}/index/selectUser",
    type:"post",
    dataType:"json",
    success:function(data){
    	/* var jsonData = JSON.stringify(data);
    	alert(jsonData) */
    	var userName = data.userName;
    	var realName = data.realName;
    	$("#userName").val(userName);
    	$("#realName").val(realName);
    }
  });
 }
</script>
</head>
<body>
   <div>
      <table>
         <tr>
            <th>用户名:</th>
            <td><input id="userName" name="userName" value="" type="text"/></td>
            <th>真实姓名:</th>
            <td><input id="realName" name="realName" value="" type="text"/></td>
         </tr>
         <tr>
           <shiro:hasRole name="admin">
           <td><input id="queryUser" name="queryName" type="button" value="查询" onClick="queryUser()"/></td>
           </shiro:hasRole>
         </tr>
      </table>
   </div>
</body>
</html>
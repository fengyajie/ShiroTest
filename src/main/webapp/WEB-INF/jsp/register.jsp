<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
<title>注册</title>
<script  type="text/javascript" src="../js/jquery-3.3.1.js"></script>  
 <script  type="text/javascript" src="../js/jquery-3.3.1.min.js"></script>
</head>
<body>
 <form method="post" id="userAdd">
  <table>
     <tr>
       <th>用户名:</th>
       <td><input id="userName" name="userName" type="text"/></td>
     </tr>
     <tr>
       <th>真实姓名:</th>
       <td><input id="realName" name="realName" type="text"/></td>
     </tr>
     <tr>
       <th>密码:</th>
       <td><input id="password" name="password" type="password" /></td>
     </tr>
     <tr>
      <th>再次输入密码:</th>
      <td><input id="repassword" name="repassword" type="password"/></td>
     </tr>
     <tr>
       <td>
       <a href="javascript:userAdd()">确定</a>
       </td>
     </tr>
  </table>
 </form>
 <script>
	 function userAdd(){
		  var password = $("#password").val();
		  var repassword = $("#repassword").val();
		  var userName = $("#userName").val();
		  if(userName == null || userName == ''){
			  alert("请输入用户名");
			  return;
		  }
		  if(password == null || password== ""){
			  alert("请输入密码");
			  return;
		  }
		  
		  if(repassword == null || repassword == ''){
			  alert("请输入确认密码");
			  return;
		  }
		  
		  if(password != repassword){
			  alert("密码不一致");
			  return;
		  }
	    $.ajax({
	      url:"${pageContext.request.contextPath}/form/userAdd",
	      type:"post",
	      data:$("#userAdd").serialize(),
	      success:function(data){
	        console.log(data);
	      }
	    });
	  } 
  
</script>
</body>
</html>
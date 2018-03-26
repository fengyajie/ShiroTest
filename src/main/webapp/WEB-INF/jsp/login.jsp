<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<title>登陆</title>
 <script  type="text/javascript" src="../TestProject/js/jquery-3.3.1.js"></script>  
 <script  type="text/javascript" src="../TestProject/js/jquery-3.3.1.min.js"></script>
 
</head>
<body>
<div>
  <div>
    <div class="error">${error}</div>
  </div>
  <form id="login" method="post">
    <table>
       <tr>
          <th>用户名：</th>
          <td><input id="userName" name="userName" type="text" /></td>
       </tr>
       <tr>
         <th>密码:</th>
         <td><input id="password" name="password" type="password" /></td>
       </tr>
       <tr>
         <!-- <td><a href="javascript:login()">登陆</a></td> -->
        <td><input id="submit" type="submit" value="登录"/></td>
       </tr>
    </table>
  </form>
  <script>
   /* function login(){
     var userName = $("#userName").val();
     var password = $("#password").val();
     if(userName == null || userName ==''){
    	 alert("请输入用户名");
    	 return;
     }
     if(password == null || password == ''){
    	 alert("请输入密码");
    	 return;
     }
     
     $.ajax({
	      url:"${pageContext.request.contextPath}/loginLogin",
	      type:"post",
	      data:$("#login").serialize(),
	      success:function(data){
	        console.log(data);
	      }
	    });
  } */
  </script>
  </div>
</body>
</html>
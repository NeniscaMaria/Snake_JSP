<%@ page import="mvcIntelliJIdea.model.DBManager" %>
<%@ page import="mvcIntelliJIdea.model.User" %>
<!DOCTYPE html>

<html>
<head>
    <meta charset="UTF-8">
    <link href="snake.css" rel="stylesheet" type="text/css">
    <title>Snake</title>
</head>
<body class="login">
<%
    if(session.getAttribute("username")!=null){
        DBManager dbManager = new DBManager();
        dbManager.logTimeEnd((User)session.getAttribute("username"));
        session.invalidate();
    }
%>
<div>
<form action="LoginController" method="post">
    Username <br>
    <div class="input">
    <input type="text" name="username"> <BR></div>
    Password <br>
    <div class="input">
    <input type="password" name="password"> <BR></div>
    <button class = 'login' type="submit" value="Login">Login</button>
</form>
</div>
</body></html>
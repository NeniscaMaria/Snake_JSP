<%@ page import="mvcIntelliJIdea.model.DBManager" %>
<%@ page import="mvcIntelliJIdea.model.User" %>
<!DOCTYPE html>

<html>
<head>
    <meta charset="UTF-8">
    <link href="login.css" rel="stylesheet" type="text/css">
    <title>Snake</title>
</head>
<body>
<%
    if(session.getAttribute("username")!=null){
        DBManager dbManager = new DBManager();
        dbManager.logTimeEnd((User)session.getAttribute("username"));
        session.invalidate();
    }
%>
<form action="LoginController" method="post">
    Username <br><input type="text" name="username"> <BR>
    Password <br> <input type="password" name="password"> <BR>
    <input class = 'login' type="submit" value="Login"/>
</form>
</body></html>
<%@ page import="mvcIntelliJIdea.model.User" %>
<%@ page import="mvcIntelliJIdea.model.DBManager" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <link href="snake.css" rel="stylesheet" type="text/css">
    <link href='http://fonts.googleapis.com/css?family=Cabin+Sketch:bold' rel='stylesheet' type='text/css'>
    <title>Snake</title>
    <script src="ajax-utils.js"></script>
    <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.4.1/jquery.min.js"></script>
    <style type="text/css">
        canvas {
            background:#9c9;
            -webkit-box-shadow:0 0 20px #000;
            -moz-box-shadow: 0 0 20px #000;
            box-shadow:0 0 20px #000;
        }
        h1 { font-family: 'Cabin Sketch', arial, serif; font-size:50px;
            text-indent: -100px;margin-bottom:20px;margin-top:30px;margin-left:1.5em}
    </style>
<script src="snakeLogic.js"></script>
</head>

<body class='snake' onload="init()">
<%
     User user = (User) request.getSession().getAttribute("username");
     if (user==null || user.getUsername().equals("")) {
         out.println("You have unauthorized access.");
         return;
     }
%>
<form class = 'logout' action="LogoutController" method="get">
    <button type="submit" value="Log out">Log out</button>
</form>
<h1> Welcome to snake!</h1>

</body>
</html>
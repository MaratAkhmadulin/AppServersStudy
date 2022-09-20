<%@page pageEncoding="UTF-8" contentType="text/html; charset=UTF-8"%>
<!DOCTYPE html>
<html lang="ru">
<head><title> User Welcome Page </title></head>
<body>
<p><%= request.getAttribute("welcomeText") %>, <%= request.getParameter("UserName") %></p>
</body>
</html>
<%@page pageEncoding="UTF-8" contentType="text/html; charset=UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<title>ExceptionPage</title>
</head>
<body>
<p>Ошибка при попытке аутентификации, куки = <%= request.getAttribute("jakarta.servlet.error.exception") %></p>
</body>
</html>
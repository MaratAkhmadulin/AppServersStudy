<%@page pageEncoding="UTF-8" contentType="text/html; charset=UTF-8"%>
<!DOCTYPE html>
<html lang="ru">
<head><title> Calculator Error Page </title></head>
<body>
<p>Ошибка! Значение <%= request.getParameter("Value") %> не является числом!</p>
</body>
</html>
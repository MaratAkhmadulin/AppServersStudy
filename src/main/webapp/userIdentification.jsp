<!DOCTYPE html>
<html>
<head><title> User Identification </title></head>
<body>
<p><%= request.getAttribute("inputUserNameText") %></p>
<form method="post" action="userwelcomepage">
    <label>UserName:
        <input type="text" name="UserName"><br />
    </label>
    <button type="submit">Entry</button>
</form>
</body>
</html>
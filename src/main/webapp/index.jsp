<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<!DOCTYPE html>
<html>
<head>
    <title>Qrup58</title>
    <link rel="stylesheet"type="text/css" href="css/main.css">
</head>
<body>
<form action="cs?action=register" method="post">
    <label>Student name :</label><input type="text" name="name" placeholder="Student name"><br>
    <label> Password: </label><input type="password" name="password" placeholder="password"><br>
    <label>Date of birth:</label><input type="date" name="dob"><br>
    <label>E-mail:</label><input type="email" name="email"><br>
    <label>Country:</label><select name="country">
    <option value="Azerbaijan">Azerbaijan</option>
    <option value="Turkey">Turkey</option>
    <option value="England">England</option>
    <option value="Italy">Italy</option>

    </select>
    <br>
    <br>
    <input type="submit" value="Send"/>&nbsp; &nbsp;&nbsp;&nbsp;<input type="reset" value="Clear">


</form>


</body>
</html>
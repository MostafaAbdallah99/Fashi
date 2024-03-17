<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<html>
<head>
    <title>Reset Password Request</title>
</head>
<body>
<h2>Reset Password</h2>
<form action="reset-password-request" method="post">    <div>
        <label for="email">Email address *</label>
        <input type="text" id="email" name="email" required>
    </div>
    <button type="submit">Request Password Reset</button>
</form>
</body>
</html>
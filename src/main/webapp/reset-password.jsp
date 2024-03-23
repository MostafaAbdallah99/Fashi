<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<html>
<head>
    <title>Reset Password</title>
    <link rel="stylesheet" href="/css/style.css" type="text/css">
</head>
<body>
<h2>Reset Password</h2>
<form action="reset-password" method="post">
    <input type="hidden" id="token" name="token" value="${token}">
    <div>
        <label for="newPassword">New Password *</label>
        <input type="password" id="newPassword" name="newPassword" required>
    </div>
    <button type="submit">Reset Password</button>
</form>
</body>
</html>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<!DOCTYPE html>

<html>
    <head>
        <title>Welcome to JSP</title>
        <link href="/static/css/style.css" rel="stylesheet">

    </head>
    <body>
    	<h1>Welcome to JSP</h1>
    	<form action="auth" method="post">
    		<p>Username: <input type='text' name='username'/></p>
    		<p>Password: <input type="password" name="password"/></p>
    		<div><button>Login</button></div>
    	</form>
    </body>
</html>
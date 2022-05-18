<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core"  prefix="c"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<link rel="shortcut icon" href="/resources/favicon.ico" type="image/x-icon">
<link rel="icon" href="/resources/favicon.ico" type="image/x-icon">
<title>2022. 4. 15.오후 12:09:49</title>
</head>
<body>
	<h1>Get Page</h1>
	<table border="1" width="800">
		<tr>
			<th>bno</th>
			<td>${board.bno}</td>
			<th>writer</th>
			<td>${board.writer}</td>
		</tr>
		
		<tr>
			<th>title</th>
			<td colspan="3">${board.title}</td>
		</tr>
		<tr>
			<td colspan="4">${board.content}</td>
		</tr>

	</table>
</body>
</html>
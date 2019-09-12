<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
    
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Insert title here</title>
<link href="<c:url value="/resources/css/rabo.css" />" rel="stylesheet">
</head>
<body>
	
	<a href="/">Home</a>
	
	<h2>Rabo GitHub Repository List!</h2>
	
	<table><tr><th>Name</th><th>Description</th></tr>
	
	<c:forEach var="repo" items="${repoList}">
		<tr>
			<td><a href="">${repo.name}</a></td>
			<td>${repo.description}</td>
		</tr>		
	</c:forEach>
	</table>
	
</body>
</html>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="sec"
	uri="http://www.springframework.org/security/tags"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>

<%@page session="true"%>
<!DOCTYPE html>
<html>
<head>
<link rel="stylesheet" href="<c:url value="/resources/css/printusers.css"/>">
<meta charset="ISO-8859-1">
<title>View Users</title>
</head>
<body>
	
	<c:set var="username" value="${username}" scope="request" />
	<jsp:include page="include/header.jsp" />
	

	<div class="mainDiv">
		<c:choose>
			<c:when test="${empty usersList}">
				<div class="wrapper">
				<p style="color:white;">
					No Users Found
				</p>
				</div>
			</c:when>
			<c:otherwise>
			<c:if test="${deletedvar == 1}">
				<div id="DeletedSuc">User Deleted Successfully</div>
			</c:if>
			
			<form:form action="edituser" method="post"
						modelAttribute="edituser" >
				<c:forEach var="tempUser" items="${usersList}">
					<div class="wrapper">
						<p>${tempUser.id}.  &emsp;${tempUser.lastName} &ensp;-&ensp; ${tempUser.firstName} &ensp;-&ensp; ${tempUser.afm} &ensp;-&ensp; ${tempUser.email} &ensp;-&ensp; ${tempUser.authority} &ensp;-&ensp; ${tempUser.enabled}</p>


						<button class="rejected" name="DeleteUser" value="${tempUser.id}">Delete</button>
						<button class="openB" name="EditUser" value="${tempUser.username}">Edit</button>
						
					</div>
				</c:forEach>
				</form:form>
			</c:otherwise>
		</c:choose>
	</div>





	<jsp:include page="include/footer.jsp" />

</body>
</html>
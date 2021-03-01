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
<link rel="stylesheet" href="<c:url value="/resources/css/style.css"/>">
<meta charset="ISO-8859-1">
<title>View Applications</title>
</head>
<body>
	
	<c:set var="username" value="${username}" scope="request" />
	<jsp:include page="include/header.jsp" />
	

	<div class="mainDiv">
		<c:choose>
			<c:when test="${empty apps}">
				<div class="wrapper">
				<p style="color:white;">
					No Applications Found
				</p>
				</div>
			</c:when>
			<c:otherwise>
			<form:form action="openapp" method="post"
						modelAttribute="openapp" >
				<c:forEach var="tempApp" items="${apps}">
					<div class="wrapper">
						<p>${tempApp.id}.  ${tempApp.reason}:  ${tempApp.start_date}</p>

						<c:if test="${user.authority == 'ROLE_ADMIN' || tempApp.employee_id.username == username}">
						<button class="deleteB" name="DeleteApp" value="${tempApp.id}">Delete</button>
						</c:if>
						
						<button class="openB" name="OpenApp" value="${tempApp.id}">Open</button>
						<c:if test="${tempApp.status == 'Processing'}">
										<button class="pross">${tempApp.status}</button></c:if>
										<c:if test="${tempApp.status == 'Rejected'}">
										<button class="rejected">${tempApp.status}</button></c:if>
										<c:if test="${tempApp.status == 'Accepted'}"><c:if test="${tempApp.accepted == 'Yes'}"><button class="accepted">${tempApp.status}</button></c:if>
										<c:if test="${tempApp.accepted == 'No'}"><button class="pross">Processing</button></c:if></c:if>
					</div>
				</c:forEach>
				</form:form>
			</c:otherwise>
		</c:choose>
	</div>





	<jsp:include page="include/footer.jsp" />

</body>
</html>
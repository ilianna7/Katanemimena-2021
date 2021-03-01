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
<link rel="stylesheet" href="<c:url value="/resources/css/mycss.css"/>">
<meta charset="ISO-8859-1">
<title>User</title>
</head>
<body>

	<c:set var="username" value="${username}" scope="request" />
<jsp:include page="include/header.jsp" />
<div class="mainDiv">
		<h3><b>User:</b> <c:out value="${user.id} ${msg}" /></h3>
		<div class="wrapper">
		<form:form action="edituser" method="post"
						modelAttribute="edituser" >
		<div class="outter">
				<br/>
				<p><b>Username:   </b> ${user.username}</p>
				<br/>
				<p><b>Last Name:   </b> ${user.lastName}</p>
				<br/>
				<p><b>Fist Name:   </b> ${user.firstName}</p>
				<br/>
				<p><b>AFM:   </b> ${user.afm}</p>
				<br/>
				<p><b>Email:   </b> ${user.email}</p>
				<br/>
				<p><b>Role:   </b> ${user.authority}</p>
				<br/>
				<p><b>Enabled:   </b> ${user.enabled}</p>
				<br/>

</div>
		</form:form>
		</div>
</div>



<jsp:include page="include/footer.jsp" />

</body>
</html>
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
<title>New Application</title>
</head>
<body>

<c:set var="username" value="${username}" scope="request" />
<jsp:include page="include/header.jsp" />

<div class="mainDiv">
		<h3>New Application</h3>
		<div class="wrapper">
		<div class="outter">
<c:if test="${catch_error == 1}">
							<div id="errorDiv">Something went wrong!</div>
						</c:if>
				<br/>
				<label for="uname"><b>Last Name:</b></label>
				<input type="text" placeholder="${user.lastName}" name="uname" readonly="readonly">
				<br/>
				<label for="uname"><b>Fist Name: </b></label>
				<input type="text" placeholder="${user.firstName}" name="uname" readonly="readonly">
				<br/>
				<label for="uname"><b>AFM: </b></label>
				<input type="text" placeholder="${user.afm}" name="uname" readonly="readonly">
				<br/>
				
				<form:form action="newapp" method="post" modelAttribute="newapp" >
					<label for="uname"><b>Start Date:</b></label>
					<form:input path="start_date" required="required"/>
					<br/>
					<label for="uname"><b>End Date:</b></label>
					<form:input path="end_date" required="required"/>
					<br/>
					<label for="uname"><b>Reason: </b></label>
					<form:select  path="reason" items="${reasonList}" />
					
				<button class="openB" type="submit">Save</button>

				</form:form>
			
			
	
		</div>
		</div>
		
		
	</div>




<jsp:include page="include/footer.jsp" />

</body>
</html>
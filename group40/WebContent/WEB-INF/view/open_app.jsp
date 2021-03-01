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
<title>Application</title>
</head>
<body>

	<c:set var="username" value="${user.username}" scope="request" />
<jsp:include page="include/header.jsp" />

<div class="mainDiv">
		<h3><b>Application:</b> <c:out value="${app.id} ${msg}" /></h3>
		<div class="wrapper">
		<form:form action="editapp" method="post"
						modelAttribute="editapp" >
		<div class="outter">

				<br/>
				<p><b>Last Name:   </b> ${app.employee_id.lastName}</p>
				<br/>
				<p><b>Fist Name:   </b> ${app.employee_id.firstName}</p>
				<br/>
				<p><b>AFM:   </b> ${app.employee_id.afm}</p>
				<br/>
				<p><b>Start Date:   </b> ${app.start_date}</p>
				<br/>
				<p><b>End Date:   </b> ${app.end_date}</p>
				<br/>
				<p><b>Reason:   </b> ${app.reason}</p>
				<br/>
				<p><b>Status:   </b> ${app.status}</p>
				
				<c:if test="${app.employee_id.username == username }">
				<c:if test="${app.status == 'Processing' }">
				<button class="openB" type="submit" value="${app.id}" name="EditApp">Edit</button></c:if>
				<c:if test="${user.authority == 'ROLE_SUPER' }">
						<c:if test="${app.status == 'Processing' }">
						<button class="accepted" type="submit" value="${app.id}" name="AcceptApp">Accept</button>
						<button class="rejected" type="submit" value="${app.id}" name="RejectApp">Reject</button>
						</c:if></c:if>
					<c:if test="${user.authority == 'ROLE_TM' }">
						<c:if test="${app.accepted == 'No' }">
						<button class="accepted" type="submit" value="${app.id}" name="AcceptApp">Accept</button>
						<button class="rejected" type="submit" value="${app.id}" name="RejectApp">Reject</button>
						</c:if></c:if>
					<c:if test="${user.authority == 'ROLE_ADMIN' }">
						<c:if test="${app.accepted == 'No' }">
						<button class="accepted" type="submit" value="${app.id}" name="AcceptApp">Accept</button>
						<button class="rejected" type="submit" value="${app.id}" name="RejectApp">Reject</button>
						</c:if>
				</c:if>
				</c:if>
				
				<c:if test="${app.employee_id.username != username }">
					
					<c:if test="${user.authority == 'ROLE_SUPER' }">
						<c:if test="${app.status == 'Processing' }">
						<button class="accepted" type="submit" value="${app.id}" name="AcceptApp">Accept</button>
						<button class="rejected" type="submit" value="${app.id}" name="RejectApp">Reject</button>
						</c:if></c:if>
					<c:if test="${user.authority == 'ROLE_TM' }">
						<c:if test="${app.accepted == 'No' }">
						<button class="accepted" type="submit" value="${app.id}" name="AcceptApp">Accept</button>
						<button class="rejected" type="submit" value="${app.id}" name="RejectApp">Reject</button>
						</c:if></c:if>
					<c:if test="${user.authority == 'ROLE_ADMIN' }">
						<c:if test="${app.accepted == 'No' }">
						<button class="accepted" type="submit" value="${app.id}" name="AcceptApp">Accept</button>
						<button class="rejected" type="submit" value="${app.id}" name="RejectApp">Reject</button>
						</c:if>
				</c:if></c:if>
		</div>
		</form:form>
		</div>
</div>


<jsp:include page="include/footer.jsp" />

</body>
</html>
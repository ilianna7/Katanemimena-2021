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
<title>New User</title>
</head>
<body>

	<c:set var="username" value="${username}" scope="request" />
<jsp:include page="include/header.jsp" />


<div class="mainDiv">
		<h3>New User</h3>
		<div class="wrapper">
		<div class="outter">
<c:if test="${catch_error == 1}">
							<div id="errorDiv">Username, AFM or Email already exist!</div>
						</c:if>
<c:if test="${catch_error == 2}">
							<div id="errorDiv">AFM has to be 9 digits.</div>
						</c:if>
				<form:form action="newuser" method="post" modelAttribute="newuser" >
				<br/>
				<label for="uname"><b>Username:</b></label>
				<form:input path="username" required="required"/>
				<br/>
				<label for="uname"><b>Password:</b></label>
				<form:password path="password" required="required"/>
				<br/>
				<label for="uname"><b>Last Name:</b></label>
				<form:input path="lastName" required="required"/>
				<br/>
				<label for="uname"><b>Fist Name: </b></label>
				<form:input path="firstName" required="required"/>
				<br/>
				<label for="uname"><b>AFM: </b></label>
				<form:input path="afm" required="required" oninput="this.value = this.value.replace(/[^0-9]/g, '');"/>
				<br/>
				<label for="uname"><b>Email:</b></label>
				<form:input path="email" required="required"/>
				<br/>
				
				
				
				<label for="uname"><b>Role:</b></label>
				
				<form:select  path="authority">
								<c:forEach items="${roleList}" var="role">
									<option value="${role}"><c:if
											test="${role == 'ROLE_ADMIN'}">
											<c:out value="Admin" />
										</c:if><c:if test="${role == 'ROLE_SUPER'}">
											<c:out value="Secretery" />
										</c:if>
											<c:if test="${role == 'ROLE_EMPLOYEE'}">
											<c:out value="Student" />
										</c:if>
										<c:if test="${role == 'ROLE_TM'}">
											<c:out value="MGA" />
										</c:if></option>
								</c:forEach>
							</form:select>
				
				
				
				
				<br/>
				<label for="uname"><b>Enebled:</b></label>
				<form:select path="enabled">
								<c:forEach items="${enabledList}" var="enabled">
									<option value="${enabled}"><c:if
											test="${enabled == 1}">
											<c:out value="Enabled" />
										</c:if><c:if test="${enabled == 0}">
											<c:out value="Disabled" />
										</c:if></option>
								</c:forEach>
							</form:select>
				


			
			
	
				<button class="openB" type="submit">Save</button>
		</form:form>
		</div>

		</div>
		
		
	</div>
	



<jsp:include page="include/footer.jsp" />

</body>
</html>
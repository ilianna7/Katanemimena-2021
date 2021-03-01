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
<link rel="stylesheet" href="<c:url value="/resources/css/logincss.css"/>">
<meta charset="ISO-8859-1">
<title>Sing In</title>
</head>
<body>
	<div class="headerDiv">
		<h1 id="headerH">Personel Leaves</h1>
		<p id="headerP">Katanemimena Systimata 2020-2021</p>

		<div id="mainMenu">
			<ul>
				<li><a href="<c:url value='/login' />">Sing In</a></li>
			</ul>
		</div>
	</div>

	<div class="mainDiv">


		<div id="rightDiv">
			<div class="container">
					<c:url value="/login" var="loginUrl" />
				<form id="login" name='loginForm' action="${loginUrl}" method="post">
					<c:if test="${param.error != null}">
						<div id="errorDiv">Invalid username or password.</div>
					</c:if>
						<label for="uname"><b>Username</b></label> <input type="text"
								placeholder="Enter Username" name="username" required> <label
								for="psw"><b>Password</b></label> <input type="password"
								placeholder="Enter Password" name="password" required> <input
								type="hidden" name="${_csrf.parameterName}"
								value="${_csrf.token}" />
					<button type="submit">Login</button>
				</form>
				<a id="forgot" href="<c:url value='/forgotpassword' />">Forgot
					My Password?</a>
			</div>
		</div>
		<div id="leftDiv">
			<h2>Sing In</h2>

			<p class="temp">Please enter your crendentials to use this
				application.</p>

			<a href="#">Contact Us</a>


		</div>

	</div>

	<div id="secondDiv" class="mainDiv">

		<p>This application is devolepoed for the class of Katanemimena
			Systimata, year 2020-2021. It represents a system for a company that
			manages its personel leaves. The employees will apply for a leave
			(sick, regular, special purpose, such as student, agricultural, etc.
			as well as strikes) and their boss will validate them. The payroll
			department will also review the permits to update payroll if needed.</p>



	</div>

	<jsp:include page="include/footer.jsp" />
</body>
</html>
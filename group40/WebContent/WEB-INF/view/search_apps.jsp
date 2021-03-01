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
<link rel="stylesheet" href="<c:url value="/resources/css/search.css"/>">
<meta charset="ISO-8859-1">
<title>Search</title>
</head>
<body>

	<c:set var="username" value="${username}" scope="request" />
<jsp:include page="include/header.jsp" />

<div class="mainDiv">
		<h3>Search <c:out value="${type}"/></h3>
		<div class="wrapper">
		<form:form action="searchapps" method="post" modelAttribute="searchapps">
		<div class="outter">

				<br/>
				<label for="uname"><b>Search By:</b></label>

				<form:select path="sBy" class="forminputcss">
								<c:forEach items="${sByList}" var="Temp">
									<option value="${Temp}">
											<c:if test="${Temp == 'id'}"><c:out value="ID" /></c:if>
											<c:if test="${Temp == 'username'}"><c:out value="Username" /></c:if>
											<c:if test="${Temp == 'reason'}"><c:out value="Reason" /></c:if>
										</option>
								</c:forEach>
							</form:select>

				<form:input path="sKeyword" required="required"/>
				
				<button class="openB" type="submit">Search</button>

		</div>
		</form:form>
		</div>
		
		
	</div>




<jsp:include page="include/footer.jsp" />

</body>
</html>
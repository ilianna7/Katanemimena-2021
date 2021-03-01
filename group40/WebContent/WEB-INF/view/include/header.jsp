<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="sec"
	uri="http://www.springframework.org/security/tags"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>

<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>

<%@page session="true"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Insert title here</title>
</head>
<body>
<div class="headerDiv">
		<h1 id="headerH"> Personel Leaves</h1>
		<p id="headerP">Katanemimena Systimata 2020-2021</p>
		
		<div id="mainMenu">
		<ul>
		<div class="dropdown">
					<button class="dropbtn">
						My Applications 
					</button>
					<div class="dropdown-content">
						<a href="<c:url value='/newapp' />">New Application</a> 
						<a href="<c:url value='/viewapps' />">View Applications</a> 
					</div>
					</div>
					<sec:authorize access="hasRole('SUPER')">
					<div class="dropdown">
					<button class="dropbtn">
						Pending Applications 
					</button>
					<div class="dropdown-content">
						<a href="<c:url value='/pending' />">All Applications</a> 
						<a href="<c:url value='/viewapps' />">Search Applications</a> 
					</div>
					</div>
					</sec:authorize>
					
					<sec:authorize access="hasRole('TM')">
					<div class="dropdown">
					<button class="dropbtn">
						Pending Applications 
					</button>
					<div class="dropdown-content">
						<a href="<c:url value='/pending' />">All Applications</a> 
						<a href="<c:url value='/viewapps' />">Search Applications</a> 
					</div>
					</div>
					</sec:authorize>
					
					<sec:authorize access="hasRole('ADMIN')">
					<div class="dropdown">
					<button class="dropbtn">
						Pending Applications 
					</button>
					<div class="dropdown-content">
						<a href="<c:url value='/pending' />">All Applications</a> 
						<a href="<c:url value='/searchmyapps' />">Search Applications</a> 
					</div>
					</div>
						<div class="dropdown">
					<button class="dropbtn">
						Users
					</button>
					<div class="dropdown-content">
						<a href="<c:url value='/admin/newuser' />">New User</a> 
						<a href="<c:url value='/admin/search' />">Edit User</a> 
						<a href="<c:url value='/admin/printusers' />">Print Users</a> 
					</div>
					</div>
					<div class="dropdown">
					<button class="dropbtn">
						Applications
					</button>
					<div class="dropdown-content">
						<a href="<c:url value='/admin/adminnewapp' />">Create Application</a> 
						<a href="<c:url value='/admin/searchapps' />">Search Applications</a> 
						<a href="<c:url value='/admin/viewall' />">Print Applications</a> 
					</div>
					</div>
					</sec:authorize>
					<div class="dropdown" id="leftdrop">
								
		<button class="dropbtn">
						${username}
					</button>
					<div class="dropdown-content">
						<a href="<c:url value='/editprofile' />">Edit Profile</a> 
						<a href="<c:url value='/logout' />">Sing Out</a> 
					</div>
					</div>
		</ul>
		</div>
	</div>

</body>
</html>
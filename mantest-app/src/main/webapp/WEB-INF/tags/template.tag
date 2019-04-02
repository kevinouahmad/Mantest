<?xml version="1.0" encoding="UTF-8" ?>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<!DOCTYPE html>
<jsp:directive.attribute name="head" required="false" fragment="true" />
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title>Manual Tests Manager</title>

<c:url value="/" var="baseUrl" />
<c:set value="${fn:length(baseUrl)}" var="baseUrlLen" />
<c:set var="baseUrl" value="${fn:substring(baseUrl, 0, baseUrlLen - 1)}" />

<link rel="shortcut icon" type="image/x-icon" href="${baseUrl}/resources/pics/icon.ico"/>
<link rel="stylesheet" type="text/css" media="all"
	href="${baseUrl}/resources/bootstrap/css/bootstrap.css" />
<link rel="stylesheet" type="text/css" media="all"
	href="${baseUrl}/resources/bootstrap/css/bootstrap-responsive.css" />
<link rel="stylesheet" type="text/css" media="all"
	href="${baseUrl}/resources/template.css" />
<link rel="stylesheet" type="text/css" media="all"
	href="${baseUrl}/resources/TestBooks.css" />
<link rel="stylesheet" type="text/css" media="all"
	href="${baseUrl}/resources/Tests.css" />
<link rel="stylesheet" type="text/css" media="all"
	href="${baseUrl}/resources/Step.css" />
<link rel="stylesheet" type="text/css" media="all"
	href="${baseUrl}/resources/run.css" />
<link rel="stylesheet" type="text/css" media="all"
	href="${baseUrl}/resources/reports.css" />
<link rel="stylesheet" type="text/css" media="all"
	href="${baseUrl}/resources/signin.css" />
<link rel="stylesheet"
	href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/4.7.0/css/font-awesome.min.css">
<jsp:invoke fragment="head" />
</head>
<body>
	<div class="nav">
		<div class="nav-container">
			<a href="/mantest-app/"><img class="logo"
				src="${baseUrl}/resources/pics/logo.png" alt="Club Piquette"></a>

			<div class="nav-menu">
				<c:if test="${ isAnyConnected==false }">
					<a href="signin">Sign in</a>
				</c:if>
				<c:if test="${ isAnyConnected == true }">
					<a href="disconnect"> Hello ${ user.fName }</a>
				</c:if>
			</div>
		</div>
	</div>
	<div id="backLayer">
		<div id="main">
			<jsp:doBody />
		</div>
	</div>
	<%@ include file="Footer.html"%>
</body>
</html>
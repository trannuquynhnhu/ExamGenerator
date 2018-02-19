<%@ page contentType="text/html;charset=UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="tiles" uri="http://tiles.apache.org/tags-tiles"%>
<%@ taglib prefix="security" uri="http://www.springframework.org/security/tags" %>

<!DOCTYPE html>
<html lang="en">
<head>
	<meta charset="utf-8">
	<meta http-equiv="X-UA-Compatible" content="IE=edge">
	<meta name="viewport" content="width=device-width, initial-scale=1.0">
	<c:set var="title"><tiles:getAsString name="title" /></c:set>			
	<title><spring:message code="${title}"/></title>
	<link href='<spring:url value="/resource/css/bootstrap.min.css" />'	rel="stylesheet">
	<link href='<spring:url value="/resource/css/site.css" />'	rel="stylesheet">	
	<script type="text/javascript" src="http://code.jquery.com/jquery-1.10.1.min.js"></script>
	<script type="text/javascript" src="<spring:url value="/resource/js/question.js" />"></script>
	
	
</head>

<body>
	<div class="container">
		<div>	
			<ul class="nav nav-pills pull-right">
				<tiles:insertAttribute name="navigation" />
			</ul>	
			<h2>Test Online</h2>	
			<security:authorize access="isAuthenticated()">		
				<div>
					<spring:message code="welcome.message"/>
	   				<security:authentication property="principal.username" />!
	   				<a href="<c:url value='/logout'/>"><spring:message code="welcome.Logout"/> |</a>				
					<span><a href="?language=en" ><spring:message code="welcome.English"/></a> | <a href="?language=vi" ><spring:message code="welcome.Vietnamese"/></a></span>

				</div>
			</security:authorize>	
		</div>
		<div id="breadcrumb">
			<c:set var="heading"><tiles:getAsString name="heading" /></c:set>			
				<spring:message code="${heading}"/> -
			    <c:set var="tagline"><tiles:getAsString name="tagline" /></c:set>			
			<spring:message code="${tagline}"/>
		</div>

		<div class="row">
			<tiles:insertAttribute name="body" />
		</div>

		<div class="footer">
			<tiles:insertAttribute name="footer" />
		</div>

	</div>
</body>
</html>

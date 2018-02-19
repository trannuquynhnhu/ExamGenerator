<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="security" uri="http://www.springframework.org/security/tags" %>

<li><a href="<spring:url value="/welcome" />"><spring:message code="navigation.Home"/></a></li>
<li><a href="<spring:url value="/users/" />"><spring:message code="navigation.User"/></a></li>
<li><a href="<spring:url value="/users/addUser" />"><spring:message code="navigation.AddUser"/></a></li>
<li><a href="<spring:url value="/questions/" />"><spring:message code="navigation.Questions"/></a></li>
<security:authorize access="hasRole('ROLE_PROFESSOR')">
<li><a href="<spring:url value="/questions/add" />"><spring:message code="navigation.AddQuestion"/></a></li>
</security:authorize>
<li><a href="<spring:url value="/exam" />"><spring:message code="navigation.Exams"/></a></li>
<security:authorize access="hasRole('ROLE_PROFESSOR')">
<li><a href="<spring:url value="/exam/addExam" />"><spring:message code="navigation.AddExam"/></a></li>
</security:authorize>
<li><a href="<spring:url value="/assessments/" />"><spring:message code="navigation.Assessments"/></a></li>


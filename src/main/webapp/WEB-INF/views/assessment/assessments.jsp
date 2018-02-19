<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
 
<body>
	<c:if test="${assessments.size() > 0}">
 	<section class="container">
		<div class="row">			
			<span class="col-md-3"><spring:message code="Assessment.ExamId"/></span>
			<span class="col-md-3"><spring:message code="Assessment.Subject"/></span>
			<span class="col-md-3"><spring:message code="Assessment.StartTime"/></span>
			<span class="col-md-3"><spring:message code="Assessment.EndTime"/></span>	
		</div>			
		<c:forEach items="${assessments}" var="assessment">
			<div class="row">
				<span class="col-md-3"><a href="<spring:url value="/assessments/detail?id=${assessment.id}"/>"> 
					${assessment.exam.examId} </a></span>
				<span class="col-md-3">${assessment.exam.subject.name}</span>
				<span class="col-md-3">${assessment.startTime}</span>
				<span class="col-md-3">${assessment.endTime}</span>
			</div>
		</c:forEach>
	</section>
	</c:if>
</body>
</html>


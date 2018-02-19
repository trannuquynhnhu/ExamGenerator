<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"  %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
	
<html>
 <link rel="stylesheet"	href="//netdna.bootstrapcdn.com/bootstrap/3.0.0/css/bootstrap.min.css">
 	<script type="text/javascript" src="http://code.jquery.com/jquery-1.10.1.min.js"></script>
	<script type="text/javascript" src="<spring:url value="/resource/js/cart.js"/>"></script>
	
 <body>
 ${exam.examId} <br>
 ${exam.subject.name}<br>
 <c:forEach items="${exam.questions}" var="question">
${question.questionNumber} ${question.question.description} ${question.gradePoint}<br>
</c:forEach>
<div id="update">
<form id="modify" action="examStatus">
<input type="submit" class="btn btn-primary" value="Modify"/>
</form>
</div>
		
 	
</body>
</html>

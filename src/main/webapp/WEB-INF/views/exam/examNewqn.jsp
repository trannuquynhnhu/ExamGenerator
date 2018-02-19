<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"  %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
	
<html>
 <link rel="stylesheet"	href="//netdna.bootstrapcdn.com/bootstrap/3.0.0/css/bootstrap.min.css">
 	<script type="text/javascript" src="http://code.jquery.com/jquery-1.10.1.min.js"></script>
	<script type="text/javascript" src="<spring:url value="/resource/js/addqn.js"/>"></script>
	
 <body>
 <div>
 Exam ID : ${exam.examId} <br>
 Subject : ${exam.subject}<br>
 </div>


 <div id="new" style="display:none">	
 	<section class="container" >
 	<form:form modelAttribute="examQuestion" id="examQuestion" action="addExamQuestion" method='POST'>
		<form:errors path="*"/>
		<form:hidden value="${newquestion.questionId}" path="question.questionId"/>${newquestion.description}<br>
		
	    <div class="form-group">
					<label class="control-label col-sm-4" for="gradePoint"><spring:message code="examquestion.gradepoint"/></label>
					<div class="col-sm-6">
						<form:input path="gradePoint"/>
					</div>
		</div>
		
						<form:hidden path="questionNumber" value="1"/>
				
		<div class="col-lg-offset-2 col-sm-10">
						<input type="submit" id="btnAdd" class="btn btn-primary" value ="Add"/>
		</div>
		
	</form:form>
	
	</section>
</div>	
</body>
</html>
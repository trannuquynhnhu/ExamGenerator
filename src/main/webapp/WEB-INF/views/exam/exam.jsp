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
 Subject : ${exam.subject.name}<br>
 </div>
 <c:choose>
 <c:when test="${newquestion!=null}">
 <div id="new">	
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
</c:when>
<c:otherwise>
<div id=questions>
<c:forEach items="${exam.questions}" var="question">
${question.questionNumber} . ${question.question.description}<br>
</c:forEach>
</div>
<div id="errors" style="display:none"></div>
 <div id="existing" style="display:none">	
 	<section class="container" >
 	<c:choose>	
	<c:when test="${questions!=null && fn:length(questions)>0}">
		<form:form modelAttribute="examQuestion" id="existingForm" action="addExamQuestion" method='POST'>
		<c:forEach items="${questions}" var="question">
		<form:radiobutton value="${question.questionId}" path="question.questionId"/>${question.description}<br>
		</c:forEach>
	    <div class="form-group">
					<label for="gradePoint"><spring:message code="examquestion.gradepoint"/></label>
					<div class="col-sm-6">
						<form:input path="gradePoint"/>
					</div>
		</div>		
						<form:hidden path="questionNumber" value="1"/>
				
		<div class="col-lg-offset-2 col-sm-10">
						<input type="submit" id="btnAdd" class="btn btn-primary" value ="Add"/>
		</div>
		
	</form:form>
	</c:when>
	<c:otherwise>
	No Questions to choose from<br>
	</c:otherwise>
	</c:choose>
	</section>
</div>	
<div id="buttons">
<a href="../questions/addToExam?examid=${exam.examId}&subjectid=${exam.subject.id}"><input type="button" class="btn btn-primary" id="addNew"  value="Add New Question"></a>
<input type="button" class="btn btn-primary" id="addExisting" onclick="getExistingQuestions();" value="Add Existing Question"/>
</div>
<div id="new" style="display:none">	
</div>
</c:otherwise>
</c:choose>	
</body>
</html>

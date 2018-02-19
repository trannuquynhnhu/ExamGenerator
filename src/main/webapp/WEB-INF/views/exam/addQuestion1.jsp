<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"  %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>

<html>
 <link rel="stylesheet"	href="//netdna.bootstrapcdn.com/bootstrap/3.0.0/css/bootstrap.min.css">
 <script type="text/javascript" src="<spring:url value="/resource/js/addqn.js"/>"></script>
 <body>
 ExamID : ${exam.examId} <br>
 Subject : ${exam.subject}<br>
 <div id="questions">
 <c:forEach items="${exam.questions}" var="question">
 ${question.text}<br>
 </c:forEach>
 </div>
 	<section class="container">
 	<div id="errors"></div>
		<form:form id="qnForm" class="form-horizontal" action="/exam/save" modelAttribute="question">
			<fieldset>
				<legend>Add new question</legend>
				<div class="form-group">
					<label class="control-label col-sm-4" for="questionId"><spring:message code="question.questionId"/></label>
					<div class="col-sm-6">
						<form:input id="questionId" path ="questionId" type="text" class="form:input-large"/>
					</div>
				</div>

				<div class="form-group">
					<label class="control-label col-sm-4" for="sortOrder"><spring:message code="question.sortOrder"/></label>
					<div class="col-sm-6">
						<div class="form:input-prepend">
							<form:input id="sortOrder" path="sortOrder" type="text" class="form:input-large"/>
						</div>
					</div>
				</div>

				<div class="form-group">
					<label class="control-label col-sm-4" for="text"><spring:message code="question.text"/></label>
					<div class="col-sm-6">
						<form:textarea id="text" path="text" rows = "2"/>
					</div>
				</div>
				
				<div class="form-group">
					<label class="control-label col-sm-4" for="type"><spring:message code="question.type"/></label>
					<div class="col-sm-6">
						<form:select id="question.type" path="question.type" class="form:input-large">
							<option value="" label="--Select a Type--"/>
							<c:forEach items="${questionTypes}" var="qntype">
							<option value="${qntype}">${qntype}</option>
							</c:forEach>							
						</form:select>
					</div>
				</div>		
				
				<div class="form-group">
					<div class="col-lg-offset-2 col-sm-10">
						<button id="btnAdd" class="btn btn-primary" value ="Add" onclick="addQuestion();"></button>
					</div>
					<div class="col-lg-offset-2 col-sm-10">
						<input type="submit" id="btnAdd" class="btn btn-primary" value ="Save Exam"/>
					</div>
				</div>
				
			</fieldset>
		</form:form>
	</section>
</body>
</html>

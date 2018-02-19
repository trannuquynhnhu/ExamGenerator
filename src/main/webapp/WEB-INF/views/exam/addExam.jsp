<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"  %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>

<html>
<body>
 	<section class="container">
		<form:form  modelAttribute="exam" class="form-horizontal" method="POST" action="addExam">
			<fieldset>				
				<div class="form-group">
					<label class="control-label col-sm-4" for="examId"><spring:message code="exam.examId"/></label>
					<div class="col-sm-6">
						<form:input id="examId" path="examId" type="text" class="form:input-large"/>
						<form:errors path="examId" cssClass="text-danger"/>
					</div>
				</div>
				
				<div class="form-group">
					<label class="control-label col-sm-4" for="subject.id"><spring:message code="exam.subject"/></label>
					<div class="col-sm-6">												
						<form:select id="subject" path="subject.id" class="width200">
							<form:option value="" label="---Select a subject---"/>
							<form:options items="${subjects}" itemLabel="name" itemValue="id"/>
						</form:select>	
						<form:errors path="subject.id" cssClass="text-danger"/>					
					</div>
				</div>
				<div class="form-group">
					<div class="col-lg-offset-2 col-sm-10">
						<input type="submit" id="btnAdd" class="btn btn-primary" value ="Add Exam"/>
					</div>
				</div>
				
			</fieldset>
		</form:form>
	</section>
</body>
</html>

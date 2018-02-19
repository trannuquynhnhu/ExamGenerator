<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"  %>

<section class="container">
	<form:form modelAttribute="assessment" class="form-horizontal">
		<input type="hidden" name="_flowExecutionKey" value="${flowExecutionKey}"/>
		<p>Exam: <c:out value="${assessment.exam.examId}"/></p>
		<hr>
		<div class="row">${assessment.questionNumber}. <c:out value="${assessment.question.description}"/></div>
		<c:if test="${assessment.question.imagePath ne ''}">
			<img src="<c:url value ="/resource/images/${assessment.question.questionId}.png"/>" alt="This is the question image" height="150" width="150"/>
		</c:if>
		<c:choose>
			<c:when test="${assessment.question.type eq 'FreeText'}">
				<div class="row">
					<form:textarea id="answerdescription" path="answer.description" rows = "5" cols="100"/>
				</div>
			</c:when>
			<c:otherwise>	
				<ol class="choices">
				<c:forEach items="${assessment.question.choices}" var="choice" varStatus="loop">
					<li>
						<c:choose>
							<c:when test="${assessment.question.type eq 'SingleChoice'}">
								<form:checkbox id="choice" path="answer.choices[${loop.index}].selected" />
							</c:when>
							<c:when test="${assessment.question.type eq 'MultipleChoices'}">
								<form:checkbox id="choice" path="answer.choices[${loop.index}].selected" />
							</c:when>
						</c:choose>
						${choice.description}
					</li>
				</c:forEach>
				</ol>
			</c:otherwise>
		</c:choose>	
		<div class="buttons">	
			<c:if test="${assessment.hasPreviousAnswer}">
				<button  class="btn" id="previous" name="_eventId_previous">Previous</button>
			</c:if>	
			<c:choose>
				<c:when test="${assessment.hasNextAnswer}">
					<button class="btn" id="next" name="_eventId_next">Next</button>
				</c:when>
				<c:otherwise>
					<button class="btn" id="finish" name="_eventId_finish">Finish</button>
				</c:otherwise>
			</c:choose>
			<button class="btn" id="cancel" name="_eventId_cancel">Cancel</button>
		</div>
	</form:form>
</section>	



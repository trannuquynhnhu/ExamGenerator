<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"  %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
		

<section class="container" >

	<form:form  modelAttribute="question" class="form-horizontal" method="POST" action="add">
		<fieldset>
			<legend>Edit a question</legend>			
			
			<div >
				<label class="labelDecor" for="questionId"><spring:message code="question.questionId"/></label>
				<div class="divDecor">
					${question.questionId}					
				</div>
			</div>
			
			<div >
				<label class="labelDecor" for="subject"><spring:message code="question.subject"/></label>
				<div class="divDecor">
					${question.subject.name}										
				</div>
			</div>
						
			<div>
				<label class="labelDecor" for="level"><spring:message code="question.level"/></label>
				<div class="divDecor">
					${question.level}					
				</div>
			</div>
			
			<div >
				<label class="labelDecor" for="type"><spring:message code="question.type"/></label>
				<div class="divDecor">
					${question.type}					
				</div>
			</div>		

			<div >
				<label class="labelDecor" for="description"><spring:message code="question.description"/></label>
				<div class="divDecor">
					${question.description}					
				</div>
			</div>
			
			<div class="imageDecor">						
				<c:if test="${question.imagePath ne null}">
					<img src="<c:url value ="/resource/images/${question.questionId}.png"/>" alt="This is the question image" height="150" width="150"/>
				</c:if>
			</div>						
					
									
			<div id="divQuestionChoice" >			
				<c:forEach items="${question.choices}" var="choice" varStatus="loop">
					<div id="0">
						<label class="labelDecor" for="questionChoice"><spring:message code="question.questionChoice"/></label>
						<div class="divDecor">
							${choice.description}							
							<form:checkbox id="isCorrect" path="choices[${loop.index}].isCorrect" disabled="true"/>					
							
						</div>
					</div>
				</c:forEach>									
									
			</div>		
								
										
		</fieldset>
	</form:form>
</section>


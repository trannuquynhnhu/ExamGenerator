<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>


<section class="container">
	<div class="row">
		<c:forEach items="${questions}" var="question">
			<div class="col-sm-6 col-md-3" style="padding-bottom: 15px">
				<div class="thumbnail">					
					<div class="caption">
						<h3>${question.questionId}</h3>
						<p>${question.description}</p>
						<!-- <p><spring:message code="QuestionType.${question.type}" /></p>  -->
						
						<c:if test="${question.imagePath ne null}">
							<img src="<c:url value ="/resource/images/${question.questionId}.png"/>" alt="This is the question image" height="150" width="150"/>
						</c:if>					
												
						<ol class="choices">
							<c:forEach items="${question.choices}" var="choice">
								<li>
									<c:choose>
										<c:when test="${question.type eq 'SingleChoice'}"><input type="radio"/></c:when>
										<c:when test="${question.type eq 'MultipleChoices'}"><input type="checkbox"/></c:when>
									</c:choose>
								${choice.description}
								</li>
							</c:forEach>
						</ol>					
						<p>
							<a
								href=" <spring:url value="/questions/detail?id=${question.id}" /> "
								class="btn btn-primary"> <span
								class="glyphicon-info-sign glyphicon" /></span> <spring:message code="button.Details"/>
							</a>
						</p>

					</div>

				</div>
			</div>
		</c:forEach>
	</div>
</section>



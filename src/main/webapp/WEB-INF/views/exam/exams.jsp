<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>

<html>
 <link rel="stylesheet"
	href="//netdna.bootstrapcdn.com/bootstrap/3.0.0/css/bootstrap.min.css">
 
<body>
 	<section class="container">
		<div class="row">
			<c:forEach items="${exams}" var="exam">
				<div class="col-sm-6 col-md-3" style="padding-bottom: 15px">
					<div class="thumbnail">					
						<div class="caption">
							<h3>${exam.examId}</h3>
							<p>${exam.subject.name}</p>
							
							<p>
								<a
									href=" <spring:url value="/exam/detail?examId=${exam.examId}" /> "
									class="btn btn-primary"> <span
									class="glyphicon-info-sign glyphicon" /></span> Details
								</a>
							</p>
							<p>
								<a
									href=" <spring:url value="/assessments/start?examId=${exam.id}" /> "
									class="btn btn-primary"> <span
									class="glyphicon-info-sign glyphicon" /></span> Take Exam
								</a>
							</p>

						</div>
					</div>
				</div>
			</c:forEach>
		</div>
	</section>
</body>
</html>

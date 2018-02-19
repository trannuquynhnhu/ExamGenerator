<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>

<html>
 <link rel="stylesheet"
	href="//netdna.bootstrapcdn.com/bootstrap/3.0.0/css/bootstrap.min.css">
 
<body>
 	<section class="container">
		<div class="row">
			<c:forEach items="${users}" var="user">
				<div class="col-sm-6 col-md-3" style="padding-bottom: 15px">
					<div class="thumbnail">					
						<div class="caption">
							<h3>${user.id}</h3>
							<h4>First Name - ${user.firstName}</h4>
							<h4>Last Name -  ${user.lastName}</h4>
							<p>
								<a
									href=" <spring:url value="/users/detail?id=${user.id}" /> "
									class="btn btn-primary"> <span
									class="glyphicon-info-sign glyphicon" /></span> Details
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

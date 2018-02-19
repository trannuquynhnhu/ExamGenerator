<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"  %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>

<html>
<body>
 	<section class="container">
 		<div class="form-group">		
			First Name: ${user.firstName}
		</div>
		<div class="form-group">		
			Last Name: ${user.lastName}
		</div>
		<div class="form-group">		
			User Name:${user.userCredentials.username}
		</div>
		<div class="form-group">		
			Role :${user.userCredentials.authority[0].authority}
		</div>
	</section>
</body>
</html>

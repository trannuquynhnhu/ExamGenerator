<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"  %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>

<html>
<body>
 	<section class="container">
		<form:form  modelAttribute="user" class="form-horizontal" method="POST" action="addUser">
			<fieldset>
				<legend>Add new User</legend>
				<div class="form-group">
					<label class="control-label col-lg-2" for="firstName">First Name</label>
					<div class="col-lg-10">
						<form:input id="firstName" path="firstName" type="text" class="form:input-large"/>
						<form:errors path="firstName" cssClass="text-danger"/>
					</div>
				</div>

				<div class="form-group">
					<label class="control-label col-lg-2" for="lastName">Last Name</label>
					<div class="col-lg-10">
						<form:input id="lastName" path="lastName" type="text" class="form:input-large"/>
						<form:errors path="lastName" cssClass="text-danger"/>
					</div>
				</div>
				
				<div class="form-group">
					<label class="control-label col-lg-2" for="userName">User Name</label>
					<div class="col-lg-10">
						<div class="form:input-prepend">
							<form:input id="username" path="userCredentials.username" type="text" class="form:input-large"/>
							<form:errors path="userCredentials.username" cssClass="text-danger"/>
						</div>
					</div>
				</div>
				
				<div class="form-group">
					<label class="control-label col-lg-2" for="password">Password</label>
					<div class="col-lg-10">
						<div class="form:input-prepend">
							<form:password id="password" path="userCredentials.password"  class="form:input-large"/>
							<form:errors path="userCredentials.password" cssClass="text-danger"/>
						</div>
					</div>
				</div>
	
				<div class="form-group">
					<label class="control-label col-lg-2" for="verifyPassword">Verify Password</label>
					<div class="col-lg-10">
						<div class="form:input-prepend">
							<form:password  path="userCredentials.verifyPassword"  class="form:input-large"/>
							<form:errors path="userCredentials.verifyPassword" cssClass="text-danger"/>
						</div>
					</div>
				</div>

					<div class="form-group">
					<label class="control-label col-lg-2" for="authority">Role</label>
					<div class="col-lg-10">
						<div class="form:input-prepend">
							<form:select  path="userCredentials.authority[0].authority">
							<form:option value="ROLE_ADMIN"></form:option>
							<form:option value="ROLE_PROFESSOR"></form:option>
							<form:option value="ROLE_STUDENT"></form:option>
							</form:select>
							<form:errors path="userCredentials.authority[0].authority" cssClass="text-danger"/>
						</div>
					</div>
				</div>
 
 				<form:hidden path="userCredentials.enabled" value="TRUE"  />
				<div class="form-group">
					<div class="col-lg-offset-2 col-sm-10">
						<input type="submit" id="btnAdd" class="btn btn-primary" value ="Add User"/>
					</div>
				</div>
				
			</fieldset>
		</form:form>
	</section>
</body>
</html>

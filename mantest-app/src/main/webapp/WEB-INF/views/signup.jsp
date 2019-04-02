<%@taglib prefix="tags" tagdir="/WEB-INF/tags"%>
<%@taglib uri="http://www.springframework.org/tags" prefix="spring"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<tags:template>
	<jsp:attribute name="head">
  	</jsp:attribute>
	<jsp:body>
		<div class="container">
			<div class="heading">
				<h1> Sign up </h1>
			</div>
			<form id="formConnect" action='<spring:url value="/createUser"/>' method="POST">
				<label for="fname"><b> First Name </b></label>
			    <input type="text" placeholder="Enter first name" name="fname" required>
			    
			    <label for="lname"><b> Last Name </b></label>
			    <input type="text" placeholder="Enter last name" name="lname" required>
			
				<label for="mail"><b> E-mail </b></label>
			    <input type="text" placeholder="Enter E-mail" name="mail" required>
			
			    <label for="password"><b>Password</b></label>
			    <input type="password" placeholder="Enter Password" name="password" required>
			
			    <button type="submit">Sign Up</button>
			    <span class="noAcc"> <a class="noAcc" href="signin"> already have an account? </a> </span>
			</form>
		</div>
	</jsp:body>
</tags:template>
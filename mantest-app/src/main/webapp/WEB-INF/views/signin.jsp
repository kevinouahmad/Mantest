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
				<h1> Sign in </h1>
			</div>
			<img class="avatar" src="resources/pics/avatar.png" alt="avatar"/>
			<form id="formConnect" action='<spring:url value="/connect"/>' method="POST">
			    <label for="mail"><b>E-mail</b></label>
			    <input type="text" placeholder="Enter e-mail" name="mail" required>
			
			    <label for="password"><b>Password</b></label>
			    <input type="password" placeholder="Enter Password" name="password" required>
			
			    <button type="submit">Login</button>
			    <span class="noAcc"> <a class="noAcc" href="signup"> not have an account yet? </a> </span>
			</form>
		</div>
	</jsp:body>
</tags:template>
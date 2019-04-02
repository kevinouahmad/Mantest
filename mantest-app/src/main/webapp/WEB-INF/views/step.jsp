<%@taglib prefix="tags" tagdir="/WEB-INF/tags"%>
<%@taglib uri="http://www.springframework.org/tags" prefix="spring"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<tags:template>
	<jsp:attribute name="head">  
		<script type="text/javascript">
			// inline JavaScript here
		</script>
  	</jsp:attribute>
	<jsp:body>
	<div class="container">
			<div class="heading">
				<c:if test="${isSub==true}">
					<a href='<spring:url value="/singleTest?testBookId=${testBook.id}&testId=${parentTest.id}&subtestId=${test.id}"/>'>
						<img title="back" class="icon backLink" src="resources/pics/arrowLeft.png" alt="back to ${ test.name }">
					</a>
				</c:if>
				<c:if test="${isSub==false}">
					<a href='<spring:url value="/singleTest?testBookId=${testBook.id}&testId=${test.id}&subtestId=0"/>'>
						<img title="back" class="icon backLink" src="resources/pics/arrowLeft.png" alt="back to ${ test.name }">
					</a>
				</c:if>
				
				<h1> ${ step.name } </h1>
			</div>
			
			<div class="list">
				<div>
					<form class="editStep" id="formCreate" action='<spring:url value="/editStep"/>' method='POST'>
						<input type="hidden" value=${ testBook.id } name="testBookId">
						<c:if test="${isSub==true}">
							<input type="hidden" value=${ parentTest.id } name="testId">
							<input type="hidden" value=${ test.id } name="subtestId">
						</c:if>
						<c:if test="${isSub==false}">
							<input type="hidden" value=${ test.id} name="testId">
							<input type="hidden" value="0" name="subtestId">
						</c:if>
						
						<input type="hidden" value = ${ step.id } name="stepId">
						<textarea id="stepDescription" name="stepDescription" rows=20>${ step.description }</textarea>
						<button type="submit" form="formCreate" value="Submit" style="background-color:transparent; border:none;">
							Edit
						</button>
					</form>
				</div>
			</div>
			<script>
				document.getElementById("stepDescription").focus();
			</script>
			
		</div>
	</jsp:body>
</tags:template>
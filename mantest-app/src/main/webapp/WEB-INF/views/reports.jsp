<%@taglib prefix="tags" tagdir="/WEB-INF/tags"%>
<%@taglib uri="http://www.springframework.org/tags" prefix="spring"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<tags:template>
	<jsp:attribute name="head">  
		<script type="text/javascript">
		</script>
  	</jsp:attribute>
	<jsp:body>
	<div class="container">
			<div class="heading">
				<a href='<spring:url value="/testBook?id=${ testBook.id }"/>'>
					<img title="back" class="icon backLink" src="resources/pics/arrowLeft.png" alt="back to tests books">
				</a>
				<h1> 
					Reports for <span style="font-style:italic;"> 
									 ${ testBook.name } : 
									 <c:if test="${isSub==true}">
									 	${parentTest.name} -
									 </c:if>
									 ${ test.name } 
								</span> 
				</h1>
			</div>
			
			<div class="list">
				<div class="lineTable tableHeader">
					<span class="tableCol colHeader"> date </span>
					<span class="tableCol colHeader"> tester </span>
					<span class="tableCol colHeader"> step that failed </span>
					<span class="tableCol colHeader"> observation left </span>
					<span class="tableCol colHeader"> time spent for the test </span>		
				</div>
				<c:forEach items="${reports}" var="report">
					<div class="lineTable">
						<span class="tableCol"> ${report.date} </span>
						<span class="tableCol"> ${report.fname} ${report.lname} </span>
						<span class="tableCol"> ${report.problem.name} </span>
						<span class="tableCol"> ${report.observation} </span>
						<span class="tableCol"> ${report.timeOccured} ms </span>
					</div>
				</c:forEach>
			</div>
			
		</div>
	</jsp:body>
</tags:template>
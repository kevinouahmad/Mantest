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
				<a href='<spring:url value="/testBook?id=${ testBook.id }"/>'>
					<img title="back" class="icon backLink" src="resources/pics/arrowLeft.png" alt="back to tests books">
				</a>
				<h1> Steps for <span style="font-style:italic;">  ${ test.name } </span> </h1>
			</div>
			
			<div class="list">
				<c:forEach items="${ test.steps }" var="step">
					<c:if test="${isSub==false}">
						<div class="line">
							<a href="step?testBookId=${testBook.id}&testId=${test.id}&subtestId=0&stepId=${step.id}" id='stepName_${step.id}' class="name"> 
								${ step.name }
							</a>
							
							<img title="rename step" id="but_${step.id}" class="icon" src="resources/pics/edit.png" alt="rename step"/>
							
							<a href="deleteStep?testBookId=${testBook.id}&testId=${test.id}&subtestId=0&stepId=${step.id}">
								<img title="delete step" class="icon" src="resources/pics/delete.png" alt="delete step"/>
							</a>
							
						</div>
					</c:if>
					<c:if test="${isSub==true}">
						<div class="line">
							<a href="step?testBookId=${testBook.id}&testId=${parentTest.id}&subtestId=${test.id}&stepId=${step.id}" id='stepName_${step.id}' class="name"> 
								 ${ step.name }
							</a>
							
							<img title="rename step" id="but_${step.id}" class="icon" src="resources/pics/edit.png" alt="rename step"/>
				
							<a href="deleteStep?testBookId=${testBook.id}&testId=${parentTest.id}&subtestId=${test.id}&stepId=${step.id}">
								<img title="delete step" class="icon" src="resources/pics/delete.png" alt="delete step"/>
							</a>
							
						</div>
					</c:if>
					<script>
						document.getElementById("but_${step.id}").addEventListener('click', function() {
							document.getElementById("but_${step.id}").src="resources/pics/save.png";
							document.getElementById('stepName_${step.id}').contentEditable = "true";
							document.getElementById('stepName_${step.id}').style.cursor = "text";
							document.getElementById('stepName_${step.id}').style.backgroundImage = "linear-gradient(45deg, #181818 30%, #672671 100%)";
							document.getElementById('stepName_${step.id}').style.border = "1px white solid";
							document.getElementById('stepName_${step.id}').style.borderRadius = "5px";
							document.getElementById("but_${step.id}").addEventListener('click', function() {
							
								var form = document.createElement("form");
								    
								var element1 = document.createElement("input"); 
								var element2 = document.createElement("input");
								var element3 = document.createElement("input");
								var element4 = document.createElement("input");
								var element5 = document.createElement("input");
		
								form.method = "POST";
								form.action = "renameStep";   
		
								element1.value= ${testBook.id};
							    element1.name="testBookId";
							    
							    <c:if test="${isSub==true}">
								    element2.value=${parentTest.id};
								    element2.name="testId";
								    
								    element3.value=${test.id};
								    element3.name="subtestId";
							    </c:if>
							    
							    <c:if test="${isSub==false}">
								    element2.value=${test.id};
								    element2.name="testId";
								    
								    element3.value="0";
								    element3.name="subtestId";
							    </c:if>
							    
							    element4.value=${step.id};
							    element4.name="stepId";
							    
							    element5.value=document.getElementById('stepName_${step.id}').innerText;
							    element5.name="stepName";
							    
							    form.appendChild(element1);  
							    form.appendChild(element2); 
							    form.appendChild(element3); 
							    form.appendChild(element4); 
							    form.appendChild(element5); 

							    form.style.display="none";
							    
							    document.body.appendChild(form);

							    form.submit();
							});
						});
					</script>
				</c:forEach>
				<div class="lineNew">
					<form id="formCreate" action='<spring:url value="/createStep"/>' method="POST">
						<input type="hidden" value=${ testBook.id } name="testBookId">
						<c:if test="${isSub == true}">
							<input type="hidden" value=${ parentTest.id } name="testId">
							<input type="hidden" value=${ test.id } name="subtestId">
						</c:if>
						<c:if test="${isSub == false}">
							<input type="hidden" value=${ test.id } name="testId">
							<input type="hidden" value="0" name="subtestId">
						</c:if>
						<input id="inputNew" type="text" name="stepName" class="form-control"
							autocomplete="off" placeholder="add a step..">
						<button type="submit" form="formCreate" value="Submit" style="background-color:transparent; border:none;">
							<img style="border-radius:5px;"class="icon" src="resources/pics/add.png" alt="add"/>
						</button>
					</form>
				</div>
			</div>
			
		</div>
		<script>
			document.getElementById("inputNew").focus();
		</script>
	</jsp:body>
</tags:template>
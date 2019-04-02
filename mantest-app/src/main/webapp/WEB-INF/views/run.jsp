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
		<div id="runStep">
			<h1 class="heading"> ${ currentStep.name } </h1>
			<div id="runStepContainer">
				<div id="runStepPath">
					<c:set var="passed" scope="session" value="false" />
					<c:forEach items="${ steps }" var="step">
						<c:if test="${step==currentStep}">
							<span style="color: #5555f4">
								&ensp; ${step.name}
								<c:remove var="passed" />
								<c:set var="passed" scope="session" value="true" />
							</span>
						</c:if>
						<c:if test="${step!=currentStep}">
							<c:if test="${passed==true}">
								<span>
									${step.name}
								</span>
							</c:if>
							<c:if test="${passed!=true}">
								<span style="color:green">
									${step.name}
								</span>
							</c:if>
						</c:if>
					</c:forEach>
				</div>
				
				<div id="runStepDescription">
					<p>
						${ currentStep.description }
					</p>
					<c:if test="${isSub==false}">
						<div id="successFailure">
								<button id="testFailedButton" type="button" class="btn-danger">
									failed
								</button>
							<c:if test="${isLastStep==false}">
								<a href="runTest?testBookId=${testBook.id}&testId=${test.id}&subtestId=0&stepIndex=${stepIndex + 1}&reportId=${currentReport.id}">
									<button type="button" class="btn-success">
										successed
									</button>
								</a>
							</c:if>
							<c:if test="${isLastStep==true}">
								<c:if test="${isAnyConnected==true}">
									<a href="testExecutionEndedWell?testBookId=${testBook.id}&testId=${test.id}&subtestId=0&reportId=${currentReport.id}&fname=${user.fName}&lname=${user.lName}">
										<button type="button" class="btn-success">
											successed
										</button>
									</a>
								</c:if>
								<c:if test="${isAnyConnected==false}">
									<a href="testExecutionEndedWell?testBookId=${testBook.id}&testId=${test.id}&subtestId=0&reportId=${currentReport.id}">
										<button type="button" class="btn-success">
											successed
										</button>
									</a>
								</c:if>
							</c:if>
						</div>
					</c:if>
					
					<c:if test="${isSub==true}">
						<div id="successFailure">
								<button id="testFailedButton" type="button" class="btn-danger">
									failed
								</button>
							<c:if test="${isLastStep==false}">
								<a href="runTest?testBookId=${testBook.id}&testId=${parentTest.id}&subtestId=${test.id}&stepIndex=${stepIndex + 1}&reportId=${currentReport.id}">
									<button type="button" class="btn-success">
										successed
									</button>
								</a>
							</c:if>
							<c:if test="${isLastStep==true}">
								<c:if test="${isAnyConnected==true}">
									<a href="testExecutionEndedWell?testBookId=${testBook.id}&testId=${parentTest.getId()}&subtestId=${test.id}&reportId=${currentReport.id}&fname=${user.fName}&lname=${user.lName}">
										<button type="button" class="btn-success">
											successed
										</button>
									</a>
								</c:if>
								<c:if test="${isAnyConnected==false}">
									<a href="testExecutionEndedWell?testBookId=${testBook.id}&testId=${parentTest.id}&subtestId=${test.id}&reportId=${currentReport.id}">
										<button type="button" class="btn-success">
											successed
										</button>
									</a>
								</c:if>
							</c:if>
						</div>
					</c:if>
					
				</div>
			</div>
		</div>
		
		<script>
			failedButton = document.getElementById("testFailedButton");
			failedButton.addEventListener('click', function() {
				var	form = document.createElement("form");
				form.method = "GET";
				form.action = "reportFilled";
				
				var element1 = document.createElement("input"); 
			    var element2 = document.createElement("input");
			    var element3 = document.createElement("input"); 
			    var element4 = document.createElement("input");
			    var element5 = document.createElement("input");
			  
			    <c:if test="${isAnyConnected==true}">
					var element = document.createElement("input");
					var element0 = document.createElement("input"); 
			    	element.value="${user.fName}";
			    	element.name="fname";
			    	element.type="hidden";
			    	
			    	element0.value="${user.lName}";
			    	element0.name="lname";
			    	element0.type="hidden";
			    	form.appendChild(element);  
			    	form.appendChild(element0);  
			    </c:if>
			    
				element1.value=${testBook.id};
			    element1.name="testBookId";
			    element1.type="hidden";
			    
			    <c:if test="${isSub==false}">
				    element2.value= ${test.id};
				    element3.value= "0";
			    </c:if>
			    
			    <c:if test="${isSub==true}">
				    element2.value= ${parentTest.id};
				    element3.value= ${test.id};
			    </c:if>	
			    
			    element2.name="testId";
			    element2.type="hidden";
			    
			    element3.name="subtestId";
			    element3.type="hidden";
			    
			    element4.value= ${currentReport.id};
			    element4.name="reportId";
			    element4.type="hidden";
			    
			    element5.value=${currentStep.id};
			    element5.name="stepProblemId";
			    element5.type="hidden";
			    
			    var element6 = document.createElement("input");
			    element6.name="observation";
			    element6.placeholder="Problem you encountered..";
			    element6.style.backgroundColor="#585858";
			    element6.style.color="#ebebeb";
			    element6.style.margin="2px";
			    element6.style.borderRadius="5px";
			    element6.style.paddingLeft="2px";
			    element6.style.minHeight="25px";
			    element6.style.width="44%";
			    
			    var element7 = document.createElement("input");
			    element7.type="submit";
			    element7.value = "Submit";
			    element7.style.backgroundColor="red";
			    element7.style.color="#ebebeb";
			    element7.style.margin="2px";
			    element7.style.borderRadius="5px";
			    element7.style.paddingLeft="2px";
			    element7.style.minHeight="32px";
			    element7.style.width="44%";
			    
			    form.appendChild(element1);  
			    form.appendChild(element2);  
			    form.appendChild(element3);  
			    form.appendChild(element4); 
			    form.appendChild(element5);
			    form.appendChild(element6);
			    form.appendChild(element7);
			    document.getElementById("successFailure").parentNode.removeChild(document.getElementById("successFailure"));
			    form.id= "successFailure";
			    document.getElementById("runStepDescription").appendChild(form);
			});
		</script>
	</jsp:body>
</tags:template>
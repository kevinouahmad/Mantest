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
				<a href='<spring:url value="/"/>'>
					<img title="back" class="icon backLink" src="resources/pics/arrowLeft.png" alt="back to tests books">
				</a>
				<h1> Tests for <span style="font-style:italic;">  ${ testBook.name } </span> </h1>
			</div>
			
			<div class="list">
				<c:forEach items="${ tests }" var="test">
					<c:if test = "${ test.nature==1 }" >
						<div class="lineSuite">
							<div class="headerSuite">
								<div id="triangle_${test.id}" class="smallIcon triangleRight"> </div>
								
								<span id="testName_${test.id}" class="name"> ${ test.name } </span>
								
								<img title="rename test" id="but_${test.id}" class="icon" src="resources/pics/edit.png" alt="rename item"/>
						
								<a href="deleteTest?testBookId=${ testBook.id }&testId=${test.id}&subtestId=0">
									<img title="delete test" class="icon" src="resources/pics/delete.png" alt="delete item"/>
								</a>
							</div>
							<div id="listSuite_${test.id}" class="hidden">
								<c:forEach items="${test.getTests()}" var="subtest">
									<div class="subtestSuite">
										<a href="singleTest?testBookId=${ testBook.id }&testId=${ test.id }&subtestId=${subtest.id}" id='subtestName_${subtest.id}' class="name"> 
											${ subtest.name }
										</a>
										
										<c:if test="${subtest.progress()==-1}">
											<img title="test progression" class="icon lr" src="resources/pics/arrowDescending.png" alt="test progression">
										</c:if>
										
										<c:if test="${subtest.progress()==0}">
											<img title="test progression" class="icon lr" src="resources/pics/arrowConstant.png" alt="test progression">
										</c:if>
										
										<c:if test="${subtest.progress()==1}">
											<img title="test progression" class="icon lr" src="resources/pics/arrowAscending.png" alt="test progression">
										</c:if>
										
										<a href="runTest?testBookId=${ testBook.id }&testId=${ test.id }&subtestId=${subtest.id}&stepIndex=0&reportId=0">
											<img title="run test" class="icon" src="resources/pics/run.png" alt="run test">
										</a>
										
										<a href="viewReports?testBookId=${ testBook.id }&testId=${ test.id }&subtestId=${subtest.id}">
											<img title="view reports for this test" class="icon lr" src="resources/pics/reports.png" alt="view reports">
										</a>
										
										<img title="rename test" id="butSub_${subtest.id}" class="icon" src="resources/pics/edit.png" alt="rename item"/>
									
										<a href="deleteTest?testBookId=${ testBook.id }&testId=${test.id}&subtestId=${subtest.id}">
											<img title="delete test" class="icon" src="resources/pics/delete.png" alt="delete item"/>
										</a>
									</div>
									<script>
										document.getElementById("butSub_${subtest.id}").addEventListener('click', function() {
											document.getElementById("butSub_${subtest.id}").src="resources/pics/save.png";
											document.getElementById('subtestName_${subtest.id}').contentEditable = "true";
											document.getElementById('subtestName_${subtest.id}').style.cursor = "text";
											document.getElementById('subtestName_${subtest.id}').style.backgroundImage = "linear-gradient(45deg, #181818 30%, #672671 100%)";
											document.getElementById('subtestName_${subtest.id}').style.border = "1px white solid";
											document.getElementById('subtestName_${subtest.id}').style.borderRadius = "5px";
											document.getElementById('butSub_${subtest.id}').addEventListener('click', function() {
											
												var form = document.createElement("form");
												    
												var element1 = document.createElement("input"); 
												var element2 = document.createElement("input"); 
												var element3 = document.createElement("input");
												var element4 = document.createElement("input");
						
												form.method = "POST";
												form.action = "renameTest";   
						
												element1.value= ${testBook.id};
											    element1.name="testBookId";
											    
											    element2.value=${test.id};
											    element2.name="testId";
											    
											    element3.value=${subtest.id};
											    element3.name="subtestId";
											    
											    element4.value=document.getElementById('subtestName_${subtest.id}').innerText;
											    element4.name="testName";
											    
											    form.appendChild(element1);  
											    form.appendChild(element2); 
											    form.appendChild(element3);
											    form.appendChild(element4); 
											    
											    form.style.display="none";
				
											    document.body.appendChild(form);
				
											    form.submit();
											});
										});
									</script>
								</c:forEach>
							</div>
							<div id="newSubtest_${test.id}" class="hidden">
								<form id="formCreateSub" action='<spring:url value="/createTest"/>' method="POST">
									<input type="hidden" value=${ testBook.id } name="testBookId">
									<input type="hidden" value=${ test.id } name="testId">
									<input id="inputSubNew_${test.id}" type="text" name="testName" class="form-control" autocomplete="off"
										placeholder="add a test to ${test.name} ..">
									<input type="hidden" value="0" name="testNature">
									<button type="submit" form="formCreateSub" value="Submit" style="background-color: transparent; border: none;">
										<img style="border-radius: 5px;" class="icon"
											src="resources/pics/add.png" alt="add" />
									</button>
								</form>
							</div>
						</div>
						<script>
							document.getElementById("testName_${test.id}").addEventListener("click", function() {
								document.getElementById("triangle_${test.id}").classList.toggle("triangleRight");
								document.getElementById("triangle_${test.id}").classList.toggle("triangleDown");
								document.getElementById("listSuite_${test.id}").classList.toggle("hidden");
								document.getElementById("listSuite_${test.id}").classList.toggle("listSuite");
								document.getElementById("newSubtest_${test.id}").classList.toggle("hidden");
								document.getElementById("newSubtest_${test.id}").classList.toggle("newSubTest");
								document.getElementById("inputSubNew_${test.id}").focus();
							})
						</script>
					</c:if>
					
					<c:if test = "${ test.nature==0 }" >
						<div class="line">	
							<a href="singleTest?testBookId=${ testBook.id }&testId=${test.id}&subtestId=0" id='testName_${test.id}' class="name"> 
								${ test.name }
							</a>
							
							<c:if test="${test.progress()==-1}">
								<img title="test progression" class="icon lr" src="resources/pics/arrowDescending.png" alt="test progression">
							</c:if>
							
							<c:if test="${test.progress()==0}">
								<img title="test progression" class="icon lr" src="resources/pics/arrowConstant.png" alt="test progression">
							</c:if>
							
							<c:if test="${test.progress()==1}">
								<img title="test progression" class="icon lr" src="resources/pics/arrowAscending.png" alt="test progression">
							</c:if>
							
							<a href="runTest?testBookId=${testBook.id}&testId=${test.id}&subtestId=0&stepIndex=0&reportId=0">
								<img title="run test" class="icon" src="resources/pics/run.png" alt="run test">
							</a>
							
							<a href="viewReports?testBookId=${testBook.id}&testId=${test.id}&subtestId=0">
								<img title="view reports for this test" class="icon lr" src="resources/pics/reports.png" alt="view reports">
							</a>
							
							<img title="rename test" id="but_${test.id}" class="icon" src="resources/pics/edit.png" alt="rename item"/>
						
							<a href="deleteTest?testBookId=${ testBook.id }&testId=${test.id}&subtestId=0">
								<img title="delete test" class="icon" src="resources/pics/delete.png" alt="delete item"/>
							</a>
						</div>
					</c:if>
					<script>
						document.getElementById("but_${test.id}").addEventListener('click', function() {
							document.getElementById("but_${test.id}").src="resources/pics/save.png";
							document.getElementById('testName_${test.id}').contentEditable = "true";
							document.getElementById('testName_${test.id}').style.cursor = "text";
							document.getElementById('testName_${test.id}').style.backgroundImage = "linear-gradient(45deg, #181818 30%, #672671 100%)";
							document.getElementById('testName_${test.id}').style.border = "1px white solid";
							document.getElementById('testName_${test.id}').style.borderRadius = "5px";
							document.getElementById('but_${test.id}').addEventListener('click', function() {
							
								var form = document.createElement("form");
								    
								var element1 = document.createElement("input"); 
								var element2 = document.createElement("input"); 
								var element3 = document.createElement("input");
								var element4 = document.createElement("input");
		
								form.method = "POST";
								form.action = "renameTest";   
		
								element1.value= ${testBook.id};
							    element1.name="testBookId";
							    
							    element2.value=${test.id};
							    element2.name="testId";
							    
							    element3.value="0";
							    element3.name="subtestId";
							    
							    element4.value=document.getElementById('testName_${test.id}').innerText;
							    element4.name="testName";
							    
							    form.appendChild(element1);  
							    form.appendChild(element2); 
							    form.appendChild(element3);
							    form.appendChild(element4); 
							    
							    form.style.display="none";

							    document.body.appendChild(form);

							    form.submit();
							});
						});
					</script>
				</c:forEach>
				<div class="lineNew">
					<form id="formCreate" action='<spring:url value="/createTest"/>' method="POST">
						<input type="hidden" value=${ testBook.id } name="testBookId">
						<input type="hidden" value="0" name="testId">
						<input id="inputNew" type="text" name="testName" class="form-control"
							autocomplete="off" placeholder="add a test to ${testBook.name}..">
						<select name="testNature">
							<option value="0">Single Test</option>
							<option value="1"> Test Suite</option>
						</select>
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
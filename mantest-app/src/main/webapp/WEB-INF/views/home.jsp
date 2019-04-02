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
				<h1> Tests Books </h1>
			</div>
			
			<div class="list">
				<c:forEach items="${ testBooks }" var="testBook">
					<div class="line">
						<a href="testBook?id=${ testBook.id }" id='testBookName_${testBook.id}' class="name"> 
							 ${ testBook.name }
						</a>
						<img id="but_${testBook.id}" class="icon" src="resources/pics/edit.png" alt="rename item"/>
						<a href="deleteTestBook?id=${ testBook.id }">
							<img class="icon" src="resources/pics/delete.png" alt="delete item"/>
						</a>
					</div>
					<script>
						document.getElementById("but_${testBook.id}").addEventListener('click', function() {
							document.getElementById("but_${testBook.id}").src="resources/pics/save.png";
							document.getElementById('testBookName_${testBook.id}').contentEditable = "true";
							document.getElementById('testBookName_${testBook.id}').style.cursor = "text";
							document.getElementById('testBookName_${testBook.id}').style.backgroundImage = "linear-gradient(45deg, #181818 30%, #672671 100%)";
							document.getElementById('testBookName_${testBook.id}').style.border = "1px white solid";
							document.getElementById('testBookName_${testBook.id}').style.borderRadius = "5px";
							document.getElementById('but_${testBook.id}').addEventListener('click', function() {
							
								var form = document.createElement("form");
								    
								var element1 = document.createElement("input"); 
								var element2 = document.createElement("input");  
		
								form.method = "POST";
								form.action = "renameTestBook";   
		
								element1.value= ${testBook.id};
							    element1.name="testBookId";
							    
							    element2.value=document.getElementById('testBookName_${testBook.id}').innerText;
							    element2.name="testBookName";
							    
							    form.appendChild(element1);  
							    form.appendChild(element2);  
							    
							    form.style.display="none";

							    document.body.appendChild(form);

							    form.submit();
							});
						});
					</script>
				</c:forEach>
				<div class="lineNew">
					<form id="formCreate" action='<spring:url value="/createTestBook"/>' method="POST">
						<input id="inputNew" type="text" name="testBookName" class="form-control"
							autocomplete="off" placeholder="add a test book..">
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
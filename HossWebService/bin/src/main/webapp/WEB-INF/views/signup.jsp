<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>

<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="sf"%>


<body>
	<div class="container">
		<h1>Sign up</h1>
	
		<sf:form action="${pageContext.request.contextPath}/signup" method="post" modelAttribute="hospital">
			<div class="form-group">
				<label for="name">hospital_id</label>
				<sf:input path="hospital_id" id="hospital_id" class="form-control" />
				<sf:errors path="hospital_id" cssStyle="color:#ff0000"/>
			</div>
			
			<div class="form-group">
				<label for="name">hospital_pw</label>
				<sf:password path="hospital_pw" id="hospital_pw" class="form-control" />
				<sf:errors path="hospital_pw" cssStyle="color:#ff0000"/>
			</div>
			
			<div class="form-group">
				<label for="name">hospital_name</label>
				<sf:input path="hospital_name" id="hospital_name" class="form-control" />
				<sf:errors path="hospital_name" cssStyle="color:#ff0000"/>
			</div>
			
			<div class="form-group">
				<label for="name">address</label>
				<sf:input path="address" id="address" class="form-control" />
				<sf:errors path="address" cssStyle="color:#ff0000"/>
			</div>
			
			<div class="form-group">
				<label for="name">department</label>
				<sf:input path="department" id="department" class="form-control" />
				<sf:errors path="department" cssStyle="color:#ff0000"/>
			</div>
			
			<div class="form-group">
				<label for="name">hours</label>
				<sf:input path="hours" id="hours" class="form-control" />
				<sf:errors path="hours" cssStyle="color:#ff0000"/>
			</div>
			
			<button type="submit" class="btn btn-primary">Submit</button>
			<a href="<c:url value="/login" />" class="btn btn-dark">Cancel</a>
		</sf:form>
		
	</div>
</body>
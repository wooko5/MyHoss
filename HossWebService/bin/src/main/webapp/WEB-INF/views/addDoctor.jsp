<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>

<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="sf"%>

<div class="container-wrapper">
	<div class="container">
		<h1>Add Doctor</h1>
		<p class="lead">Fill the below information to add a Doctor:</p>
		
		<sf:form action="${pageContext.request.contextPath}/managing/doctors/addDoctor" method="post" modelAttribute="doctor">
			<sf:hidden path="hospital_id"/>
			
			<div class="form-group">
				<label for="name">doctor_id*</label>
				<sf:input path="doctor_id" id="doctor_id" class="form-control" />
				<sf:errors path="doctor_id" cssStyle="color:#ff0000"/>
			</div>
			
			<div class="form-group">
				<label for="name">doctor_pw*</label>
				<sf:password path="doctor_pw" id="doctor_pw" class="form-control" />
				<sf:errors path="doctor_pw" cssStyle="color:#ff0000"/>
			</div>
			
			<div class="form-group">
				<label for="name">doctor_name*</label>
				<sf:input path="doctor_name" id="doctor_name" class="form-control" />
				<sf:errors path="doctor_name" cssStyle="color:#ff0000"/>
			</div>
			
			<div class="form-group">
				<label for="office_number">office_number: </label>
				<c:forEach var="office" items="${offices}">
					<sf:radiobutton path="office_number" id="office_number" value="${office.office_number}" /> ${office.office_number}
				</c:forEach>
			</div>
			
			<button type="submit" class="btn btn-primary">Submit</button>
			<a href="<c:url value="/managing/doctors" />" class="btn btn-dark">Cancel</a>
		</sf:form>
	</div>
</div>

<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>

<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="sf"%>

<div class="container-wrapper">
	<div class="container">
		<h1>Add Clinic</h1>
		<p class="lead">Fill the below information to add:</p>
		
		<sf:form action="${pageContext.request.contextPath}/clinics/addClinic/${reservation_number}" method="post" modelAttribute="clinic">
			<sf:hidden path="clinic_number"/>
			<sf:hidden path="user_id"/>
			<sf:hidden path="doctor_id"/>
			
			<div class="form-group">
				<label for="name">clinic_date*</label>
				<sf:input path="clinic_date" id="clinic_date" class="form-control" />
				<sf:errors path="clinic_date" cssStyle="color:#ff0000"/>
			</div>
			
			<div class="form-group">
				<label for="name">clinic_treatment*</label>
				<sf:input path="clinic_treatment" id="clinic_treatment" class="form-control" />
				<sf:errors path="clinic_treatment" cssStyle="color:#ff0000"/>
			</div>

			<div class="form-group">
				<label for="name">prescription_drugname*</label>
				<sf:input path="prescription_drugname" id="prescription_drugname" class="form-control" />
				<sf:errors path="prescription_drugname" cssStyle="color:#ff0000"/>
			</div>
			
			<div class="form-group">
				<label for="name">prescription_dosage*</label>
				<sf:input path="prescription_dosage" id="prescription_dosage" class="form-control" />
				<sf:errors path="prescription_dosage" cssStyle="color:#ff0000"/>
			</div>
			
			<div class="form-group">
				<label for="name">prescription_caution</label>
				<sf:input path="prescription_caution" id="prescription_caution" class="form-control" />
				<sf:errors path="prescription_caution" cssStyle="color:#ff0000"/>
			</div>
			
			<button type="submit" class="btn btn-primary">Submit</button>
			<a href="<c:url value="/clinics" />" class="btn btn-dark">Cancel</a>
		</sf:form>
	</div>
</div>

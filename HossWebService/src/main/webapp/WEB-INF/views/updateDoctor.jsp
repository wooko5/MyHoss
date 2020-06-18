<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>

<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="sf"%>

<div class="container-wrapper">
	<div class="container">
		<h1>Update Doctor</h1>
		<p class="lead">Fill the below information to update:</p>
		
		<sf:form action="${pageContext.request.contextPath}/managing/doctors/updateDoctor" method="post" modelAttribute="doctor">
			<sf:hidden path="doctor_id"/>
			<sf:hidden path="doctor_pw"/>
			<sf:hidden path="doctor_name"/>
			<sf:hidden path="hospital_id"/>
			
			<div class="form-group">
				<label for="office_number">진료실 번호: </label>
				<c:forEach var="office" items="${offices}">
					<sf:radiobutton path="office_number" id="office_number" value="${office.office_number}" /> ${office.office_number}
				</c:forEach>
				<sf:radiobutton path="office_number" id="office_number" value="" /> 없음
			</div>
			
			<button type="submit" class="btn btn-primary">제출</button>
			<a href="<c:url value="/managing/doctors" />" class="btn btn-dark">취소</a>
		</sf:form>
	</div>
</div>

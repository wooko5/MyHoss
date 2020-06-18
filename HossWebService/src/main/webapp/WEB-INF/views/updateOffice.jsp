<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>

<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="sf"%>

<div class="container-wrapper">
	<div class="container">
		<h1>Update Office</h1>
		<p class="lead">Fill the below information to update:</p>
		
		<sf:form action="${pageContext.request.contextPath}/managing/offices/updateOffice" method="post" modelAttribute="office">
			<sf:hidden path="hospital_id"/>
			<sf:hidden path="office_number"/>
			
			<div class="form-group">
				<label for="name">진료과*</label>
				<sf:input path="office_department" id="office_department" class="form-control" />
				<sf:errors path="office_department" cssStyle="color:#ff0000"/>
			</div>
			
			<div class="form-group">
				<label for="name">대기자 수</label>
				<sf:input path="waiting_number" id="waiting_number" class="form-control" />
				<sf:errors path="waiting_number" cssStyle="color:#ff0000"/>
			</div>
			
			<button type="submit" class="btn btn-primary">제출</button>
			<a href="<c:url value="/managing/offices" />" class="btn btn-dark">취소</a>
		</sf:form>
	</div>
</div>

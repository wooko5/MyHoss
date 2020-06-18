<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>

<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="sf"%>

<div class="container-wrapper">
	<div class="container">
		<h1>Update Hours</h1>
		<p class="lead">Fill the below information to update:</p>
		
		<sf:form action="${pageContext.request.contextPath}/managing/updateHours" method="post" modelAttribute="hospital">
			<sf:hidden path="hospital_id"/>
			<sf:hidden path="hospital_pw"/>
			<sf:hidden path="hospital_name"/>
			<sf:hidden path="address"/>
			<sf:hidden path="department"/>
			
			<div class="form-group">
				<label for="name">운영 시간*</label>
				<sf:input path="hours" id="hours" class="form-control" />
				<sf:errors path="hours" cssStyle="color:#ff0000"/>
			</div>
			
			<div class="form-group">
				<label for="name">자동 예약 거부 시간</label>
				<sf:input path="rejected_time" id="rejected_time" class="form-control" placeholder="MM/dd HH:mm~HH:mm" pattern="[0-9]{2}/[0-9]{2} [0-9]{2}:[0-9]{2}~[0-9]{2}:[0-9]{2}" />
				<sf:errors path="rejected_time" cssStyle="color:#ff0000"/>
			</div>
			
			<button type="submit" class="btn btn-primary">제출</button>
			<a href="<c:url value="/managing" />" class="btn btn-dark">취소</a>
		</sf:form>
	</div>
</div>

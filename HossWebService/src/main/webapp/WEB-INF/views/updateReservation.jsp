<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>

<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="sf"%>

<div class="container-wrapper">
	<div class="container">
		<h1>Update Office</h1>
		<p class="lead">Fill the below information to update:</p>
		
		<sf:form action="${pageContext.request.contextPath}/reservations/updateReservation" method="post" modelAttribute="reservation">
			<sf:hidden path="user_id"/>
			<sf:hidden path="hospital_id"/>
			<sf:hidden path="reservation_number"/>
			<sf:hidden path="reservation_time"/>
			
			<div class="form-group">
				<label for="reservation_status">예약 현황: </label>
				<sf:radiobutton path="reservation_status" id="reservation_status" value="승인" /> 승인
				<sf:radiobutton path="reservation_status" id="reservation_status" value="거부" /> 거부
			</div>
			
			<div class="form-group">
				<label for="name">대기 예상 시간</label>
				<sf:input path="reservation_estimated_time" id="reservation_estimated_time" class="form-control" placeholder="oo분"/>
				<sf:errors path="reservation_estimated_time" cssStyle="color:#ff0000"/>
			</div>
			
			<div class="form-group">
				<label for="office_number">진료실 번호: </label>
				<c:forEach var="office" items="${offices}">
					<sf:radiobutton path="office_number" id="office_number" value="${office.office_number}" /> ${office.office_number}
				</c:forEach>
			</div>
			
			<button type="submit" class="btn btn-primary">제출</button>
			<a href="<c:url value="/reservations" />" class="btn btn-dark">취소</a>
		</sf:form>
	</div>
</div>

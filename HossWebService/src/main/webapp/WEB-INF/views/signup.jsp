<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>

<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="sf"%>


<body>
	<div class="container">
		<h1>Sign up</h1>
	
		<sf:form action="${pageContext.request.contextPath}/signup" method="post" modelAttribute="hospital">
			<div class="form-group">
				<label for="name">병원 아이디</label>
				<sf:input path="hospital_id" id="hospital_id" class="form-control" />
				<sf:errors path="hospital_id" cssStyle="color:#ff0000"/>
			</div>
			
			<div class="form-group">
				<label for="name">비밀번호</label>
				<sf:password path="hospital_pw" id="hospital_pw" class="form-control" />
				<sf:errors path="hospital_pw" cssStyle="color:#ff0000"/>
			</div>
			
			<div class="form-group">
				<label for="name">병원 이름</label>
				<sf:input path="hospital_name" id="hospital_name" class="form-control" />
				<sf:errors path="hospital_name" cssStyle="color:#ff0000"/>
			</div>
			
			<div class="form-group">
				<label for="name">주소</label>
				<sf:input path="address" id="address" class="form-control" />
				<sf:errors path="address" cssStyle="color:#ff0000"/>
			</div>
			
			<div class="form-group">
				<label for="name">대표 진료과</label>
				<sf:input path="department" id="department" class="form-control" />
				<sf:errors path="department" cssStyle="color:#ff0000"/>
			</div>
			
			<div class="form-group">
				<label for="name">운영 시간</label>
				<sf:input path="hours" id="hours" class="form-control" placeholder="평일 09:00~18:00, 토요일 09:00~13:30, 점심시간 12:30~14:00, 일요일/공휴일 휴진" />
				<sf:errors path="hours" cssStyle="color:#ff0000"/>
			</div>
			
			<button type="submit" class="btn btn-primary">제출</button>
			<a href="<c:url value="/login" />" class="btn btn-dark">취소</a>
		</sf:form>
		
	</div>
</body>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<div class="container-wrapper">
	<div class="container">
		<h2>Managing Page</h2>
		<p>병원을 관리할 수 있는 페이지입니다.</p>
	</div>
	
	<div class="container">
		<h2><a href="<c:url value="/managing/offices" />">Offices</a></h2>
		<p>진료실을 조회, 수정할 수 있습니다.</p>
	</div>
	
	<div class="container">
		<h2><a href="<c:url value="/managing/doctors" />">Doctors</a></h2>
		<p>소속된 의사를 조회, 수정할 수 있습니다.</p>
	</div>
	
</div>

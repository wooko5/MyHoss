<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>


<body>
	<div class="container">
		<form class="form-signin" method="post"
			action="<c:url value="/login"/>">
			<h2 class="form-signin-heading">Login</h2>
			
			<c:if test="${not empty logoutMsg}">
				<div style="color:#0000ff"> <p> ${logoutMsg} </p> </div>
			</c:if>
			
			<c:if test="${not empty errorMsg}">
				<div style="color:#ff0000"> <p> ${errorMsg} </p> </div>
			</c:if>
			
			<p>
				<label for="username" class="sr-only">아이디</label> <input
					type="text" id="username" name="username" class="form-control"
					placeholder="Username" required autofocus>
			</p>
			<p>
				<label for="password" class="sr-only">비밀번호</label> <input
					type="password" id="password" name="password" class="form-control"
					placeholder="Password" required>
			</p>
			<input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}" />
			
			<button class="btn btn-lg btn-primary btn-block" type="submit">로그인</button>
		</form>
	</div>
	
	<div class="text-center">
		<a href="<c:url value="/signup"/>">병원 회원가입</a>
	</div>
</body>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
  
  <nav class="site-header sticky-top py-1 bg-dark">
  <div class="container d-flex flex-column flex-md-row justify-content-between">
  
	<a class="py-2 d-none d-md-inline-block" href="<c:url value="/"/>">Home</a>
	
    <sec:authorize access="hasRole('ROLE_HOS')">
	    <a class="py-2 d-none d-md-inline-block" href="<c:url value="/reservations"/>">Reservations</a>
	    <a class="py-2 d-none d-md-inline-block" href="<c:url value="/managing"/>">Managing</a>
    </sec:authorize>
    
    <sec:authorize access="hasRole('ROLE_DR')">
	    <a class="py-2 d-none d-md-inline-block" href="<c:url value="/clinics"/>">Clinics</a>
    </sec:authorize>
	
	<a class="py-2 d-none d-md-inline-block" href="javascript:document.getElementById('logout').submit()">Logout</a>

	<form id="logout"  action="<c:url value="/logout" />"method="post">
  		<input type="hidden" name="${_csrf.parameterName}"value="${_csrf.token}" />
	</form>
    
  </div>
</nav>
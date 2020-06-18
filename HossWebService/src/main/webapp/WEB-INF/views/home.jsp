<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>


<div class="position-relative overflow-hidden p-3 p-md-5 m-md-3 text-center bg-light">
  <div class="col-md-5 p-lg-5 mx-auto my-5">
    <h1 class="display-4 font-weight-normal">HOSS 서비스</h1>
    <sec:authorize access="hasRole('ROLE_HOS')">
      <p class="lead font-weight-normal">본 병원에 예약하신 HOSS 고객님들의 예약 현황을 쉽게 처리하실 수 있습니다. </p>
    </sec:authorize>
    <sec:authorize access="hasRole('ROLE_DR')">
      <p class="lead font-weight-normal">담당 HOSS 고객님들의 예약 현황을 쉽게 처리하실 수 있습니다. </p>
    </sec:authorize>
  </div>
</div>


<div class="d-md-flex flex-md-equal w-100 my-md-3 pl-md-3">
  
  <sec:authorize access="hasRole('ROLE_HOS')">
    <div class="bg-dark mr-md-3 pt-3 px-3 pt-md-5 px-md-5 text-center text-white overflow-hidden">
      <div class="my-3 py-3">
        <h2 class="display-5">Reservations</h2>
        <p class="lead">예약 승인, 예상 대기 시간을 설정할 수 있습니다.</p>
        <a class="btn btn-outline-secondary" href="<c:url value="/reservations"/>">바로가기</a>
      </div>
    </div>
  
    <div class="bg-light mr-md-3 pt-3 px-3 pt-md-5 px-md-5 text-center overflow-hidden">
      <div class="my-3 p-3">
        <h2 class="display-5">Managing</h2>
        <p class="lead">병원 관련 설정을 할 수 있습니다.</p>
        <a class="btn btn-outline-secondary" href="<c:url value="/managing"/>">바로가기</a>
      </div>
    </div>
  </sec:authorize>
  
  
  <sec:authorize access="hasRole('ROLE_DR')">
    <div class="bg-dark mr-md-3 pt-3 px-3 pt-md-5 px-md-5 text-center text-white overflow-hidden">
      <div class="my-3 py-3">
        <h2 class="display-5">Clinics</h2>
        <p class="lead">담당 예약의 진료, 처방 처리를 할 수 있습니다.</p>
        <a class="btn btn-outline-secondary" href="<c:url value="/clinics"/>">바로가기</a>
      </div>
    </div>
  </sec:authorize>
</div>
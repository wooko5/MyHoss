<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %> 

<div class="container-wrapper">
	<div class="container">
		<h2>담당 예약</h2>
		<p>담당 예약의 진료, 처방 처리를 할 수 있습니다.</p>
		<table class="table table-striped">
			<thead>
				<tr class="text-white bg-info">
					<th>고객 이름</th>
					<th>고객 전화번호</th>
					<th>예약 시간</th>
					<th> </th>
				</tr>
			</thead>
			<tbody>
				<c:forEach var="i" begin="0" end="${fn:length(reservations)}">
					<c:if test="${users[i].user_name != null}">
						<tr>
							<td>${users[i].user_name}</td>
							<td>${users[i].user_Phone}</td>
							<td>${reservations[i].reservation_time}</td>
							<td>
								<a href="<c:url value="/clinics/addClinic/${reservations[i].reservation_number}"/>">진료, 처방</a>
							</td>
						</tr>
					</c:if>
				</c:forEach>

			</tbody>
		</table>
		<br>
		<br>
		
		<h4>진료, 처방 내역</h4>
		<p>완료된 진료, 처방 내역을 확인 수정할 수 있습니다.</p>
		<table class="table table-striped">
			<thead>
				<tr class="text-white bg-info">
					<th>진료 날짜</th>
					<th>진료 내용</th>
					<th>처방 약</th>
					<th>처방 약 복용법</th>
					<th>처방 약 주의사항</th>
					<th> </th>
				</tr>
			</thead>
			<tbody>
				<c:forEach var="clinic" items="${clinics}">
					<tr>
						<td>${clinic.clinic_date}</td>
						<td>${clinic.clinic_treatment}</td>
						<td>${clinic.prescription_drugname}</td>
						<td>${clinic.prescription_dosage}</td>
						<td>${clinic.prescription_caution}</td>
						<td>
							<a href="<c:url value="/clinics/updateClinic/${clinic.clinic_number}"/>"><i class="fas fa-edit"></i></a>
						</td>
					</tr>
				</c:forEach>

			</tbody>
		</table>
	</div>
</div>

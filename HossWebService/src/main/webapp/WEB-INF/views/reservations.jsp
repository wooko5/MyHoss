<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %> 

<div class="container-wrapper">
	<div class="container">
		<h2>All Reservations</h2>
		<p>예약 승인, 예상 대기 시간을 설정할 수 있습니다.</p>
		<table class="table table-striped">
			<thead>
				<tr class="text-white bg-info">
					<th>고객 이름</th>
					<th>고객 전화번호</th>
					<th>예약 시간</th>
					<th>예약 현황</th>
					<th>대기 예상 시간</th>
					<th>진료실 번호</th>
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
							<td>${reservations[i].reservation_status}</td>
							<td>${reservations[i].reservation_estimated_time}</td>
							<td>${reservations[i].office_number}</td>
							<td>
								<a href="<c:url value="/reservations/updateReservation/${reservations[i].reservation_number}"/>"><i class="fas fa-edit"></i></a>
							</td>
						</tr>
					</c:if>
				</c:forEach>
			</tbody>
		</table>
	</div>
</div>

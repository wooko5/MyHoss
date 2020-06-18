<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<div class="container-wrapper">
	<div class="container">
		<h2>All Reservations</h2>
		<p>예약 승인, 예상 대기 시간을 설정할 수 있습니다.</p>
		<table class="table table-striped">
			<thead>
				<tr class="bg-success">
					<th>reservation_number</th>
					<th>reservation_time</th>
					<th>reservation_status</th>
					<th>reservation_estimated_time</th>
					<th>office_number</th>
					<th> </th>
				</tr>
			</thead>
			<tbody>
				<c:forEach var="reservation" items="${reservations}">
					<tr>
						<td>${reservation.reservation_number}</td>
						<td>${reservation.reservation_time}</td>
						<td>${reservation.reservation_status}</td>
						<td>${reservation.reservation_estimated_time}</td>
						<td>${reservation.office_number}</td>
						<td>
							<a href="<c:url value="/reservations/updateReservation/${reservation.reservation_number}"/>"><i class="fas fa-edit"></i></a>
						</td>
					</tr>
				</c:forEach>

			</tbody>
		</table>
	</div>
</div>

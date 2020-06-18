<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<div class="container-wrapper">
	<div class="container">
		<h2>담당 예약</h2>
		<p>담당 예약의 진료, 처방 처리를 할 수 있습니다.</p>
		<table class="table table-striped">
			<thead>
				<tr class="bg-success">
					<th>reservation_number</th>
					<th>reservation_time</th>
					<th> </th>
				</tr>
			</thead>
			<tbody>
				<c:forEach var="reservation" items="${reservations}">
					<tr>
						<td>${reservation.reservation_number}</td>
						<td>${reservation.reservation_time}</td>
						<td>
							<a href="<c:url value="/clinics/addClinic/${reservation.reservation_number}"/>">진료, 처방</a>
						</td>
					</tr>
				</c:forEach>

			</tbody>
		</table>
	</div>
</div>

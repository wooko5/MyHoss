<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<div class="container-wrapper">
	<div class="container">
		<h2>Managing Offices</h2>
		<p>진료실을 조회, 수정할 수 있습니다.</p>
		<table class="table table-striped">
			<thead>
				<tr class="text-white bg-info">
					<th>진료실 번호</th>
					<th>진료과</th>
					<th>대기자 수</th>
					<th> </th>
				</tr>
			</thead>
			<tbody>
				<c:forEach var="office" items="${offices}">
					<tr>
						<td>${office.office_number}</td>
						<td>${office.office_department}</td>
						<td>${office.waiting_number}</td>
						<td>
							<a href="<c:url value="/managing/offices/deleteOffice/${office.office_number}"/>"><i class="fas fa-trash-alt"></i></a>
							
							<a href="<c:url value="/managing/offices/updateOffice/${office.office_number}"/>"><i class="fas fa-edit"></i></a>
						</td>
					</tr>
				</c:forEach>

			</tbody>
		</table>
		
		<a href="<c:url value="/managing/offices/addOffice"/>" class="btn btn-primary"> 진료실 추가 </a>
	</div>
</div>

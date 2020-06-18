<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<div class="container-wrapper">
	<div class="container">
		<h2>Managing Doctors</h2>
		<p>소속된 의사를 조회, 수정할 수 있습니다.</p>
		<table class="table table-striped">
			<thead>
				<tr class="bg-success">
					<th>doctor_id</th>
					<th>doctor_name</th>
					<th>office_number</th>
					<th> </th>
				</tr>
			</thead>
			<tbody>
				<c:forEach var="doctor" items="${doctors}">
					<tr>
						<td>${doctor.doctor_id}</td>
						<td>${doctor.doctor_name}</td>
						<td>${doctor.office_number}</td>
						<td>
							<a href="<c:url value="/managing/doctors/deleteDoctor/${doctor.doctor_id}"/>"><i class="fas fa-trash-alt"></i></a>
							
							<a href="<c:url value="/managing/doctors/updateDoctor/${doctor.doctor_id}"/>"><i class="fas fa-edit"></i></a>
						</td>
					</tr>
				</c:forEach>

			</tbody>
		</table>
		
		<a href="<c:url value="/managing/doctors/addDoctor"/>" class="btn btn-primary"> Add Doctor </a>
	</div>
</div>

<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
    <%@ taglib uri="http://www.springframework.org/tags/form" prefix="form" %>
    <%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <title>Insurance Report</title>
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.2/dist/css/bootstrap.min.css" 
    	rel="stylesheet" 
    	integrity="sha384-T3c6CoIi6uLrA9TneNEoa7RxnatzjcDSCmG1MXxSR1GAsXEV/Dwwykc2MPK8M2HN" 
    	crossorigin="anonymous">
</head>
<body>
	<div class="container">
		<h1 class="pb-3 pt-3" class="text-center">WELCOME TO AKBAR INSURANCE CORPORATION</h1>
		<form:form action="search" modelAttribute="search" method="post">
			<table>
				<tr>
					<td>Plan Name:</td>
					<td>
						<form:select path="planName">
							<form:option value="">-Select-</form:option>
							<form:options items="${names}"/>
						</form:select>
					</td>
					
					<td>Plan Status:</td>
					<td>
						<form:select path="planStatus">
							<form:option value="">-Select-</form:option>
							<form:options items="${status}"/>
						</form:select>
					</td>
					
					<td>Gender:</td>
					<td>
						<form:select path="gender">
							<form:option value="">-Select-</form:option>
							<form:option value="Male">Male</form:option>
							<form:option value="Fe-Male">Fe-Male</form:option>
						</form:select>
					</td>
				</tr>
				<tr>
					<td>Plan Start Date</td>
					<td>
						<form:input path="planStartDate" type="Date"/>
					</td>
					
					<td>Plan End Date</td>
					<td>
						<form:input path="planEndDate" type="Date"/>
					</td>
					
				</tr>
				<tr>
					<td>
						<a href="/" class="btn btn-secondary">Reset</a>  
						<input class="btn btn-primary" type="submit" value="Search"/>
					</td>
				</tr>
			</table>
		</form:form>
		<hr/>
			<table class="table table-striped table-hover">
				<thead>
					<tr>
						<th>ID</th>
						<th>NAME</th>
						<th>GENDER</th>
						<th>PLAN NAME</th>
						<th>PLAN STATUS</th>
						<th>START DATE</th>
						<th>END DATE</th>
						<th>BENEFIT AMT</th>
					</tr>
				</thead>
				<tbody>
					<c:forEach items="${plans}" var="plan" varStatus="index">
						<tr>
							<td>${index.count} </td>
							<td>${plan.citizenName} </td>
							<td>${plan.gender} </td>
							<td>${plan.planName} </td>
							<td>${plan.planStatus} </td>
							<td>${plan.planStartDate} </td>
							<td>${plan.planEndDate} </td>
							<td>${plan.benefitAmount} </td>
							
						</tr>
					</c:forEach>
					<tr>
					<c:if test="${empty plans}">
							<td colspan="8" style="text-align:center"> No Records Found </td>
					</c:if>
					</tr>
				</tbody>
			</table>
		<hr/>
		Export : <a href="excel">Excel</a> <a href="pdf">Pdf</a>
	</div>
	 <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.2/dist/js/bootstrap.bundle.min.js" 
	 		integrity="sha384-C6RzsynM9kWDrMNeT87bh95OGNyZPhcTNXj1NW7RuBCsyN/o0jlpcV8Qyq46cDfL" 
	 		crossorigin="anonymous"></script>
</body>
</html>
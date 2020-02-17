<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>

<!DOCTYPE html>
<html>
<head>
<title>Customer List</title>
<link rel="stylesheet"
	href="https://stackpath.bootstrapcdn.com/bootstrap/4.4.1/css/bootstrap.min.css"
	integrity="sha384-Vkoo8x4CGsO3+Hhxv8T/Q5PaXtkKtu6ug5TOeNV6gBiFeWPGFN9MuhOf23Q9Ifjh"
	crossorigin="anonymous">

</head>

<body>
	<div class="container">
		<h2>CRM - Customer Relationship Manager</h2>
		<hr>
		<p>Customers in the system are listed below</p>
	</div>

	<div class="container">
		<!--  add a search box -->
		<form:form action="search" method="GET">
			<div class="form-row align-items-center">
				<div class="col-auto">
					<label class="sr-only" for="inlineFormInput">Search
						Customer:</label> <input type="text" class="form-control mb-2"
						id="inlineFormInput" name="theSearchName" placeholder="Enter name">
				</div>
				<div class="col-auto">
					<button type="submit" class="btn btn-outline-primary mb-2">Submit</button>
				</div>

			</div>
		</form:form>
	</div>




	<hr>

	<div class="container">
		<input type="button" value="Add Customer" class="btn btn-primary"
			onclick="window.location.href='showFormForAdd'; return false;" /> <br>
	</div>


	<div class="container">
		<br>
		<!-- HTML Table -->
		<table class="table table-hover">
			<thead class="thead-dark">
				<tr>
					<th>First Name</th>
					<th>Last Name</th>
					<th>Email</th>
					<th>Action</th>
				</tr>
			</thead>

			<!-- Loop and print customer -->
			<c:forEach var="customer" items="${ customers }">

				<!-- Construct an update and delete link with customer id -->
				<c:url var="updateLink" value="/customer/showFormForUpdate">
					<c:param name="customerID" value="${customer.getId()}" />
				</c:url>
				<c:url var="deleteLink" value="/customer/deleteCustomer">
					<c:param name="customerID" value="${customer.getId()}" />
				</c:url>

				<tr>
					<td>${ customer.getFirstName() }</td>
					<td>${ customer.getLastName() }</td>
					<td>${ customer.getEmail() }</td>
					<td><a href="${updateLink}">Update</a> | <a
						href="${deleteLink}"
						onclick="if (!(confirm('Are you sure you want to delete this customer?'))) return false">
							Delete</a></td>
				</tr>

			</c:forEach>

		</table>

	</div>

	<script src="https://code.jquery.com/jquery-3.4.1.slim.min.js"
		integrity="sha384-J6qa4849blE2+poT4WnyKhv5vZF5SrPo0iEjwBvKU7imGFAV0wwj1yYfoRSJoZ+n"
		crossorigin="anonymous"></script>
	<script
		src="https://cdn.jsdelivr.net/npm/popper.js@1.16.0/dist/umd/popper.min.js"
		integrity="sha384-Q6E9RHvbIyZFJoft+2mJbHaEWldlvI9IOYy5n3zV9zzTtmI3UksdQRVvoxMfooAo"
		crossorigin="anonymous"></script>
	<script
		src="https://stackpath.bootstrapcdn.com/bootstrap/4.4.1/js/bootstrap.min.js"
		integrity="sha384-wfSDF2E50Y2D1uUdj0O3uMBJnjuUD4Ih7YwaYd1iqfktj0Uod8GCExl3Og8ifwB6"
		crossorigin="anonymous"></script>

</body>

</html>
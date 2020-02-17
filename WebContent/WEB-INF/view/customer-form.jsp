<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>

<!DOCTYPE html>
<html>
<head>
<title>Add Customer</title>
<link rel="stylesheet"
	href="https://stackpath.bootstrapcdn.com/bootstrap/4.4.1/css/bootstrap.min.css"
	integrity="sha384-Vkoo8x4CGsO3+Hhxv8T/Q5PaXtkKtu6ug5TOeNV6gBiFeWPGFN9MuhOf23Q9Ifjh"
	crossorigin="anonymous">

</head>

<body>
	<div class="container">
		<h2>CRM - Customer Relationship Manager</h2>
		<hr>
	</div>


	<div class="container">
		<h3>Add/Edit New Customer</h3>
		<p>Use the form below to add or edit a customer.</p>

		<form:form action="saveCustomer" modelAttribute="customer"
			method="POST">
			
			<!-- If editing the customer we need to add a hidden form field for the customer -->
			<form:hidden path="id" />
			<div class="form-group">
				<label class="control-label">First Name:</label>
				<form:input class="form-control" path="firstName" />
			</div>
			<div class="form-group">
				<label class="control-label">Last Name:</label>
				<form:input class="form-control"  path="lastName" />
			</div>
			<div class="form-group">
				<label class="control-label">Email:</label>
				<form:input class="form-control"  path="email" />
			</div>
			<input class="btn btn-primary" type="submit" value="Submit" />

		</form:form>


	</div>
	
	<div class="container">
		<br>
    	<a href="${pageContext.request.contextPath}/customer/list">Back to List</a>
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
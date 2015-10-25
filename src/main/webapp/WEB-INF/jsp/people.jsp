<!DOCTYPE html>
<%@taglib uri="http://www.springframework.org/tags" prefix="spring"%>
<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<html>
<head>
<meta charset="utf-8">
<title>Priski</title>

<meta content="IE=edge,chrome=1" http-equiv="X-UA-Compatible">
<meta name="viewport" content="width=device-width, initial-scale=1.0">

<link href="/assets/public/css/bootstrap.min.css" rel="stylesheet">
<link href="/assets/public/css/bootstrap-responsive.min.css"
	rel="stylesheet">
<link href="/heroku.css" rel="stylesheet">
</head>

<body>
	<div class="navbar navbar-fixed-top">
		<div class="navbar-inner">
			<div class="container">
				<a href="/" class="brand">Primary Skills</a>
			</div>
		</div>
	</div>

	<div class="container">
		<div class="row">
			<div class="span8 offset2">
				<div class="page-header">
					<h1>PRISKI</h1>
				</div>
				<form:form method="post" action="add" class="form-vertical"
					enctype="multipart/form-data" modelAttribute="person">

					<form:label path="firstName">First Name</form:label>
					<form:input path="firstName" />
					<form:label path="lastName">Last Name</form:label>
					<form:input path="lastName" />
					Upload File:<input type="file" name="file" />
					<input type="submit" value="Add Person" class="btn" />
				</form:form>


				<c:if test="${!empty peopleList}">
					<h3>People</h3>
					<table class="table table-bordered table-striped">
						<thead>
							<tr>
								<th>Name</th>
								<th>&nbsp;</th>
							</tr>
						</thead>
						<tbody>
							<c:forEach items="${peopleList}" var="person">
								<tr>
									<td>${person.lastName},${person.firstName}</td>
									<td><form action="delete/${person.id}" method="post">
											<input type="submit" class="btn btn-danger btn-mini"
												value="Delete" />
										</form></td>
								</tr>
							</c:forEach>
						</tbody>
					</table>
				</c:if>
			</div>
		</div>
	</div>

</body>
</html>

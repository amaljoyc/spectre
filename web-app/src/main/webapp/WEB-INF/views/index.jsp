<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html lang="en">
<head>
<meta charset="utf-8">
<meta http-equiv="X-UA-Compatible" content="IE=edge">
<meta name="viewport" content="width=device-width, initial-scale=1">
<!-- The above 3 meta tags *must* come first in the head; any other head content must come *after* these tags -->
<meta name="spectre web application" content="">
<meta name="amaljoyc" content="">
<link rel="icon" href="<c:url value="/resources/images/favicon.ico" />">

<title>Spectre</title>

<link href="<c:url value="/resources/css/bootstrap.min.css" />"
	rel="stylesheet">
<link href="<c:url value="/resources/css/sticky-footer-navbar.css" />"
	rel="stylesheet">
<link href="<c:url value="/resources/css/grid.css" />" rel="stylesheet">
</head>

<body>

	<!-- Fixed navbar -->
	<nav class="navbar navbar-default navbar-fixed-top">
		<div class="container">
			<div class="navbar-header">
				<button type="button" class="navbar-toggle collapsed"
					data-toggle="collapse" data-target="#navbar" aria-expanded="false"
					aria-controls="navbar">
					<span class="sr-only">Toggle navigation</span> <span
						class="icon-bar"></span> <span class="icon-bar"></span> <span
						class="icon-bar"></span>
				</button>
				<a class="navbar-brand" href="">Spectre</a>
			</div>
			<div id="navbar" class="collapse navbar-collapse">
				<ul class="nav navbar-nav">
					<li class="active"><a href="">Home</a></li>
				</ul>
			</div>
			<!--/.nav-collapse -->
		</div>
	</nav>

	<!-- Begin page content -->
	<div class="container">
		<div class="page-header">
			<h1>List of Bank Accounts</h1>
		</div>
		<p class="lead">Below you will find the list of bank accounts. You
			can add, edit, or delete the details.</p>
		<br>
		<form id="add_form" class="form-inline" role="form">
			<div class="form-group">
				<input type="text" class="form-control" name="iban"
					placeholder="Enter IBAN" />
			</div>
			<div class="form-group">
				<input type="text" class="form-control" name="bic"
					placeholder="Enter BIC" />
			</div>
			<button type="submit" class="btn btn-default">Add</button>
		</form>
		<br>
		<p class="banklist-grid"></p>
	</div>

	<footer class="footer">
		<div class="container">
			<p class="text-muted">Copyright &copy; 2016 &middot; All Rights
				Reserved &middot; amaljoyc</p>
		</div>
	</footer>

	<script src="<c:url value="/resources/js/jquery-1.12.0.min.js" />"></script>
	<script src="<c:url value="/resources/js/bootstrap.min.js" />"></script>
	<script src="<c:url value="/resources/js/grid.js" />"></script>
</body>
</html>

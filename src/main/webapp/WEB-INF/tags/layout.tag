<%@ tag description="Overall Page template using bootstrap style" pageEncoding="UTF-8" %>
<%@ attribute name="title" fragment="true" %>
<%@ attribute name="h1Title" fragment="true" %>
<!DOCTYPE html>
<html>
	<head>
		<title><jsp:invoke fragment="title"/></title>
		<link rel='stylesheet' href='webjars/bootstrap/3.2.0/css/bootstrap.min.css'>
		<script type="text/javascript" src="webjars/jquery/2.2.0/jquery.min.js"></script>
		<script type="text/javascript" src="webjars/bootstrap/3.2.0/js/bootstrap.min.js"></script>
		
		<script type="text/javascript" src="resources/js/forms.js" />"></script>
	</head>
	<body>
		<nav class="navbar navbar-default">
		  <div class="container-fluid">
		    <div class="navbar-header">
		      <a class="navbar-brand" href="#">CalendarProject</a>
		    </div>
		    <div class="collapse navbar-collapse" id="bs-example-navbar-collapse-1">
		      <ul class="nav navbar-nav navbar-right">
		        <li><button class="btn btn-primary navbar-btn">Connexion</button></li>
		        <li><button class="btn btn-default navbar-btn">Inscription</button></li>
		      </ul>
		    </div>
		  </div>
		</nav>
		
		<section>
			<div class="container">
				<div class="row">
					<div class="col-md-10">
						<div class="page-header">
							<h1>
								<jsp:invoke fragment="h1Title" />
							</h1>
						</div>
					</div>
				</div>
				<div class="row">
					<jsp:doBody/>
				</div>
			</div>
		</section>

	</body>


</html>
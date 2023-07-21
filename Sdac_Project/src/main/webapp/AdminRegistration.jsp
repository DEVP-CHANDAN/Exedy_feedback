<!DOCTYPE html>
<html lang="en">
<head>
<meta charset="utf-8">
<meta name="viewport"
	content="width=device-width, initial-scale=1, shrink-to-fit=no">
<link rel="stylesheet"
	href="https://fonts.googleapis.com/css?family=Roboto:400,700">
<title>Bootstrap Sign up Form with Icons</title>
<link rel="stylesheet"
	href="https://stackpath.bootstrapcdn.com/bootstrap/4.5.0/css/bootstrap.min.css">
<link rel="stylesheet"
	href="https://maxcdn.bootstrapcdn.com/font-awesome/4.7.0/css/font-awesome.min.css">
<script src="https://code.jquery.com/jquery-3.5.1.min.js"></script>
<script
	src="https://cdn.jsdelivr.net/npm/popper.js@1.16.0/dist/umd/popper.min.js"></script>
<script
	src="https://stackpath.bootstrapcdn.com/bootstrap/4.5.0/js/bootstrap.min.js"></script>
<style>
body {
	color: #fff;
	font-family: 'Roboto', sans-serif;
	background: #0264d6; /* Old browsers */
	background: -moz-radial-gradient(center, ellipse cover, #0264d6 1%, #1c2b5a 100%);
	/* FF3.6+ */
	background: -webkit-gradient(radial, center center, 0px, center center, 100%,
		color-stop(1%, #0264d6), color-stop(100%, #1c2b5a));
	/* Chrome,Safari4+ */
	background: -webkit-radial-gradient(center, ellipse cover, #0264d6 1%, #1c2b5a 100%);
	/* Chrome10+,Safari5.1+ */
	background: -o-radial-gradient(center, ellipse cover, #0264d6 1%, #1c2b5a 100%);
	/* Opera 12+ */
	background: -ms-radial-gradient(center, ellipse cover, #0264d6 1%, #1c2b5a 100%);
	/* IE10+ */
	background: radial-gradient(ellipse at center, #0264d6 1%, #1c2b5a 100%);
	/* W3C */
	filter: progid:DXImageTransform.Microsoft.gradient( startColorstr='#0264d6',
		endColorstr='#1c2b5a', GradientType=1);
	/* IE6-9 fallback on horizontal gradient */
	height: calc(100vh);
	width: 100%;
}

.form-control {
	font-size: 15px;
}

.form-control, .form-control:focus, .input-group-text {
	border-color: #e1e1e1;
}

.form-control, .btn {
	border-radius: 3px;
}

.signup-form {
	width: 400px;
	margin: 0 auto;
	padding: 30px 0;
}

.signup-form form {
	color: #999;
	border-radius: 3px;
	margin-bottom: 15px;
	background: #fff;
	box-shadow: 0px 2px 2px rgba(0, 0, 0, 0.3);
	padding: 30px;
}

.signup-form h2 {
	color: #333;
	font-weight: bold;
	margin-top: 0;
}

.signup-form hr {
	margin: 0 -30px 20px;
}

.signup-form .form-group {
	margin-bottom: 20px;
}

.signup-form label {
	font-weight: normal;
	font-size: 15px;
}

.signup-form .form-control {
	min-height: 38px;
	box-shadow: none !important;
}

.signup-form .input-group-addon {
	max-width: 42px;
	text-align: center;
}

.signup-form .btn, .signup-form .btn:active {
	font-size: 16px;
	font-weight: bold;
	background: #000000;
	border: none;
	min-width: 140px;
}

.signup-form .btn:hover, .signup-form .btn:focus {
	background: #d44179;
}

.signup-form a {
	color: #d44179;
	text-decoration: underline;
}

.signup-form a:hover {
	text-decoration: none;
}

.signup-form form a {
	color: #d44179;
	text-decoration: none;
}

.signup-form form a:hover {
	text-decoration: underline;
}

.signup-form .fa {
	font-size: 21px;
}

.signup-form .fa-paper-plane {
	font-size: 18px;
}

.signup-form .fa-check {
	color: #fff;
	left: 17px;
	top: 18px;
	font-size: 7px;
	position: absolute;
}

.cont {
	height: 100vh;
	align-items: center;
	display: flex;
	justify-content: center
}
.fa.fa-home {
font-size:30px;
margin-top:40px;
}


</style>
</head>
<body>

	<div class="container">
		
		<ul class="navbar-nav ml-auto">
			<li class="nav-item "><a class="nav-link " href="Home.html">
					<i class="fa fa-home" ></i>
			</a></li>

		</ul>

	</div>


	<div class="signup-form">

		<form action="SignupController" method="post">
			<h2>Sign Up</h2>
			<p>Please fill in this form to create an account!</p>
			<hr>
			<div class="form-group">
				<div class="input-group">
					<div class="input-group-prepend">
						<span class="input-group-text"> <span class="fa fa-user"></span>
						</span>
					</div>
					<input type="text" class="form-control" name="username"
						placeholder="Username" required="required">
				</div>
			</div>
			<div class="form-group">
				<div class="input-group">
					<div class="input-group-prepend">
						<span class="input-group-text"> <i
							class="fa fa-paper-plane"></i>
						</span>
					</div>
					<input type="email" class="form-control" name="email"
						placeholder="Email Address" required="required">
				</div>
			</div>
			<div class="form-group">
				<div class="input-group">
					<div class="input-group-prepend">
						<span class="input-group-text"> <i class="fa fa-lock"></i>
						</span>
					</div>
					<input type="password" class="form-control" name="password"
						placeholder="Password" required="required">
				</div>
			</div>
			<div class="form-group">
				<div class="input-group">
					<div class="input-group-prepend">
						<span class="input-group-text"> <i class="fa fa-user"></i>
							<i class="fa fa-check"></i>
						</span>
					</div>
					<input type="text" name="fullname" class="form-control"
						placeholder="Full Name">
				</div>
			</div>
			<div class="form-group">
				<span>Role:</span><br> <input type="radio" name="role"
					id="option1" value="admin"> <label for="option1">Admin</label><br>

				<input type="radio" name="role" id="option2" value="analyst">
				<label for="option2">Analyst</label><br> <input type="radio"
					name="role" id="option3" value="user"> <label for="option3">User</label><br>

			</div>

			<div class="form-group">
				<button type="submit" class="btn btn-primary btn-lg">Sign
					Up</button>
			</div>
		</form>
		<div class="text-center">
			Already have an account? <a href="Login.jsp">Login here</a>
		</div>
	</div>
</body>
</html>
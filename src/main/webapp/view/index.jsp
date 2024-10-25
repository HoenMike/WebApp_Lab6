<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html>
	<head>
		<meta charset="UTF-8" />
		<meta name="viewport" content="width=device-width, initial-scale=1" />
		<title>Course Registration Demo</title>
		<link
			rel="stylesheet"
			href="https://stackpath.bootstrapcdn.com/bootstrap/4.5.2/css/bootstrap.min.css"
		/>
	</head>
	<body>
		<div class="container mt-5">
			<h1 class="mb-4">Course Registration Demo</h1>
			<form action="${pageContext.request.contextPath}/StudentServlet" method="post">
				<input type="hidden" name="action" value="view" />
				<div class="form-row align-items-end">
					<div class="form-group col-md-10">
						<label for="studentID">Student ID:</label>
						<input type="text" class="form-control" id="studentID" name="studentId" required />
					</div>
					<div class="form-group col-md-2">
						<button type="submit" class="btn btn-primary">Submit</button>
					</div>
				</div>
			</form>

			<h2>Manager</h2>
			<a
				href="${pageContext.request.contextPath}/StudentServlet?action=list"
				class="btn btn-primary mr-2"
				>Students Manager</a
			>
			<a href="${pageContext.request.contextPath}/CourseServlet?action=list" class="btn btn-primary"
				>Courses Manager</a
			>
		</div>

		<script src="https://code.jquery.com/jquery-3.5.1.slim.min.js"></script>
		<script src="https://cdn.jsdelivr.net/npm/@popperjs/core@2.5.4/dist/umd/popper.min.js"></script>
		<script src="https://stackpath.bootstrapcdn.com/bootstrap/4.5.2/js/bootstrap.min.js"></script>
	</body>
</html>

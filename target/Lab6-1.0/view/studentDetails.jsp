<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html>
	<head>
		<meta charset="UTF-8" />
		<meta name="viewport" content="width=device-width, initial-scale=1" />
		<title>Student Details</title>
		<link
			rel="stylesheet"
			href="https://stackpath.bootstrapcdn.com/bootstrap/4.5.2/css/bootstrap.min.css"
		/>
	</head>
	<body>
		<div class="container mt-5">
			<div class="card">
				<div class="card-body">
					<h2 class="card-title mb-4">Student's Details</h2>

					<div class="row mb-4">
						<div class="col-md-6">
							<p><strong>Student ID:</strong> ${student.id}</p>
							<p><strong>Student name:</strong> ${student.name}</p>
						</div>
					</div>

					<div class="mb-4">
						<h3>Select Course</h3>
						<form action="Student" method="post" class="form-inline">
							<input type="hidden" name="studentId" value="${student.id}" />
							<input type="hidden" name="action" value="addCourse" />
							<div class="form-group mr-2">
								<select name="courseId" class="form-control">
									<c:forEach items="${availableCourses}" var="course">
										<option value="${course.id}">${course.name}</option>
									</c:forEach>
								</select>
							</div>
							<button type="submit" class="btn btn-primary">Add</button>
						</form>
					</div>

					<div>
						<h3>Registered Courses:</h3>
						<table class="table table-striped">
							<thead>
								<tr>
									<th>Course ID</th>
									<th>Course Name</th>
									<th>Action</th>
								</tr>
							</thead>
							<tbody>
								<c:forEach items="${registeredCourses}" var="course">
									<tr>
										<td>${course.id}</td>
										<td>${course.name}</td>
										<td>
											<form action="Student" method="post" style="display: inline">
												<input type="hidden" name="studentId" value="${student.id}" />
												<input type="hidden" name="courseId" value="${course.id}" />
												<input type="hidden" name="action" value="removeCourse" />
												<button type="submit" class="btn btn-link text-danger">Remove</button>
											</form>
										</td>
									</tr>
								</c:forEach>
							</tbody>
						</table>
					</div>

					<div class="mt-4">
						<div class="mt-4">
							<a href="view/index.jsp" class="btn btn-secondary">Back to Homepage</a>
						</div>
					</div>
				</div>
			</div>
		</div>

		<script src="https://code.jquery.com/jquery-3.5.1.slim.min.js"></script>
		<script src="https://cdn.jsdelivr.net/npm/@popperjs/core@2.5.4/dist/umd/popper.min.js"></script>
		<script src="https://stackpath.bootstrapcdn.com/bootstrap/4.5.2/js/bootstrap.min.js"></script>
	</body>
</html>

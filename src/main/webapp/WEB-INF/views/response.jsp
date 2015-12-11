<%@ taglib prefix='c' uri='http://java.sun.com/jsp/jstl/core'%>
<!DOCTYPE html>
<html lang="en">
<body>
	<div id="main-content">

		<div id="main-content-search">
			<form action="${contextPath}/search" method="get">
				<input type="search" value="${keyword}" name="keyword" id="keyword" /> <br />
				<input type="submit" value="Search" id="search" />
			</form>
		</div>

		<div>
			<c:forEach items="${list}" var="e">
				<span>${e.title}</span>
			</c:forEach>
		</div>
	</div>
</body>
</html>

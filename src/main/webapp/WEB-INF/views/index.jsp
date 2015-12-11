<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ page session="false" %>
<c:set var="contextPath" value="<%=request.getContextPath()%>"/>
<!DOCTYPE html>
<html lang="en">
<body>
<a href="${contextPath}/getuploadpage">Upload Data</a>
<h2>Elastic Search!</h2>

<div id="content-search-part">
	<form action="${contextPath}/search" method="get">
		<input type="search" value="" name="keyword" id="keyword"/>
		<br/>
		<input type="submit" value="Search" id="search"/>
	</form>
</div>

</body>
</html>

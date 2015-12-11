<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ page session="false"%>
<c:set var="contextPath" value="<%=request.getContextPath()%>"/>
<!DOCTYPE html>
<html lang="en">
<head>
<title>Insert title here</title>
</head>
<body>
	<div id="content-upload-part">
		<form action="${contextPath}/upload" method="post" enctype="multipart/form-data">
			<input type="file" name="file" id="upload" /> 
			<br /> 
			<input type="submit" value="UPLOAD" id="upload" />
		</form>
	</div>
</body>
</html>
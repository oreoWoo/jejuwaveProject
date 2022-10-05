<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
	<input type="hidden" name="c_num" value="${c_num}">
	<input type="hidden" name="pageNum" value="${pageNum}">
	<c:if test="${result > 0}">
		<script type="text/javascript">
			alert("회원 커뮤니티글 삭제 완료!");
			location.href="adCommuForm.do?pageNum=${pageNum}";
		</script>
	</c:if>
	<c:if test="${result == 0 }">
		<script type="text/javascript">
			alert("수정 안 됐어요 T.T");
			location.href="adCommuForm.do?pageNum=${pageNum}";
		</script>
	</c:if>
</body>
</html>
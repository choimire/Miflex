<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"  %>
<h2 class="text-center mt-4 md-2">USER LOGIN</h2>
<c:if test="${not empty errorMessage }">
	<script>
		alert('${errorMessage}');
	</script>
</c:if>
	<form action="./login" id="loginform" method="post">
		<table>
			<tr>
				<td>ID:</td>
				<td>
				<input type="text" name="userid" class="form-control" id="userid" placeholder="id">
				</td>
				</tr>
				<tr>
				<td>PASSWORD:</td>
				<td>
				<input type="password" name="userpass" class="form-control" id="password" placeholder="password">
				</td>
				</tr>
				<tr>
				<td colspan="2" class="text-center">
				<input type="hidden" name="${_csrf.parameterName }" value="${ _csrf.token}"> 
				<input type="submit" value="LOGIN">
				</td>
				</tr>
		</table>	
	</form>
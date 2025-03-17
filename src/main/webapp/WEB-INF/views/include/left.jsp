<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://www.springframework.org/security/tags" prefix="sec" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>    
<section id="left">
  <div class="formbox"> 
	  <sec:authorize access="isAuthenticated()">
	     <sec:authorize access="hasAnyRole('ROLE_USER', 'ROLE_ADMIN')">
	        <div class="userbox">
	           <c:choose>
	              <c:when test="${not empty cuser.userimg }">
	                  <img src="res/upload/user/${cuser.userimg }" alt="${cuser.username }님">
	              </c:when>
	              <c:otherwise>
	                 <img src="res/images/user.png" alt="${cuser.username }님">
	              </c:otherwise>
	           </c:choose>
	        </div>
	        <div class="text-center">${cuser.username }님 접속.</div>
	        <div class="text-center">
	          <a href="#" class="mx-1">회원정보수정</a>
	     </sec:authorize>
	     <sec:authorize access="hasRole('ROLE_ADMIN')">
	        <a href="#" class="mx-1">관리자모드</a>
	     </sec:authorize>
	        </div>
	     <form class="text-center" action="${pageContext.request.contextPath }/logout" method="post">
	        <input type="hidden" name="${_csrf.parameterName }" value="${_csrf.token }" />
	        <button type="submit" class="btn btn-danger">로그아웃</button>
	     </form>
	  </sec:authorize>
	  <sec:authorize access="!isAuthenticated()">
	      <form name="leftLoginForm" action="./login" id="leftloginform" method="post">
	        <div class="py-1">
	           <input type="text" name="userid" id="leftuserid" class="form-control" placeholder="아이디">
	        </div>
	        <div class="py-1">
	           <input type="password" name="userpass" id="leftuserpass" class="form-control" placeholder="비밀번호">
	        </div>
	        <div class="py-1 d-flex justify-content-center">
	           <button type="reset" class="btn btn-warning mx-2">취소</button>
	           <button type="submit" class="btn btn-primary mx-2">로그인</button>
	           <sec:csrfInput />
	        </div>
	      </form> 
	  </sec:authorize>
  </div>
</section>
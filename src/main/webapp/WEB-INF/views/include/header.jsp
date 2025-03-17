<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://www.springframework.org/security/tags" prefix="sec" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>     
<section class="header" id="header">
SP Community 
</section>
<nav class="border-top-1 border-bottom-1 py-2">
  <div class="container">
       <div class="row justify-content-center">
		   <a href='#' class="mx-3">메인페이지</a>
		   <a href="#" class="mx-3">기타등등</a>
		   <a href='#' class="mx-3">메인페이지</a>
  <sec:authorize access="isAuthenticated()">
     <sec:authorize access="hasAnyRole('ROLE_USER', 'ROLE_ADMIN')">
        <a href="./member" class="mx-3">myPage</a>
     </sec:authorize>
     <sec:authorize access="hasRole('ROLE_ADMIN')">
        <a href="./admin" class="mx-3">관리자모드</a>
     </sec:authorize>
  </sec:authorize>
  <sec:authorize access="!isAuthenticated()">
        <a href="./login" class="mx-3">로그인</a>
        <a href="./signup" class="mx-3">회원가입</a>
  </sec:authorize>
  
	   </div>
   </div>
</nav>
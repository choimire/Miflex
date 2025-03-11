<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://tiles.apache.org/tags-tiles" prefix="tiles" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title><tiles:getAsString name="title"/></title>
<link rel="stylesheet" href="res/libs/bootstrap/css/bootstrap.min.css"/>
<link rel="stylesheet" href="res/css/style.css"/>

</head>
<body>
<header>
<tiles:insertAttribute name="header"/>

</header>
<div class="container">
	<div class="row">
		<div class="col-md-3">
<tiles:insertAttribute name="left"/>
		
		</div>
		<div class="col-md-9">
<tiles:insertAttribute name="body"/>
		
		</div>
	</div>
<footer>
<tiles:insertAttribute name="footer"/>

</footer>
</div>
</body>
</html>
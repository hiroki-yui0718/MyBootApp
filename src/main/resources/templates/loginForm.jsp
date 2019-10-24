<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html xmlns:th="http://www.thymeleaf.org">
<head>
<meta charset="UTF-8">
<link href="https://maxcdn.bootstrapcdn.com/bootstrap/4.1.1/css/bootstrap.min.css"
      th:href="@{/webjars/bootstrap/4.1.1/css/bootstrap.min.css}" rel="stylesheet" />
<script src="https://maxcdn.bootstrapcdn.com/bootstrap/4.1.1/js/bootstrap.min.js"
        th:src="@{/webjars/bootstrap/4.1.1/js/bootstrap.min.js}"></script>
 </head>
 <body>
<div th:include="header :: data"></div>
<div class="container">
<div class="row">
<div class="offset-md-3 col-md-6">
<h3>Log in</h3>
<c:if test="${param.containKey('error')}">
<span class="bg-danger border">
<c:out value="${SPRING_SECURITY_LAST_EXCEPTION.message}"/>
</span>
</c:if>
<form action="/login" method="post">
<label for="username">ユーザ名</label>
<input type="text" name="username" class="form-control">
<label for="password">パスワード</label>
<input type="password" name="password" class="form-control">
<input type="submit" class="btn btn-block btn-primary">
</form>
</div>
</div>
</div>
</body>
</html>
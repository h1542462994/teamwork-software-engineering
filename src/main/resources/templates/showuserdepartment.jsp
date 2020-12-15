<?xml version="1.0" encoding="UTF-8" ?>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
<title>输出学员所属部门信息</title>
</head>
<body>
<form>
<div align="center">
    <table style="border: 1px solid;">
        <tr>
            <th>学员编号（系统唯一）</th>
            <th>加入的部门</th>
            <th>身份</th>
        </tr>
        <c:forEach items="${sessionScope.DUCList}" var="departmentuser">
    <tr>
	 <td>${departmentuser.id}</td>
	 <td>${departmentuser.dId}</td>
	 <td>${departmentuser.dIdentity}</td>
    </tr>
</c:forEach>
    </table>
</div>
</form>
<a  href="/logout"><button>返回</button></a><!-- 返回上一级，还没写好 -->
</body>
</html>
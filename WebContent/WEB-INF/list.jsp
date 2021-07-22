<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>        
    
    
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
	<h1>전화번호 리스트</h1>
	<p>입력한 정보 내역입니다.</p>
	
	
	<c:forEach items="${pList }" var="personList">
		<table border="2">
		<tr>
			<td>이름</td>
			<td>${personList.name }</td>
		</tr>
		<tr>
			<td>핸드폰</td>
			<td>${personList.hp }</td>
		</tr>	
		<tr>
			<td>회사</td>
			<td>${personList.company}</td>
		</tr>	
		<tr>
			<td><a href="/phonebook2/pbc?action=uform&id=${personList.personId}">[수정폼]</a></td>
			<td><a href="/phonebook2/pbc?action=delete&id=${personList.personId}">[삭제]</a> </td>
		</tr>
		</table>
		<br>
	</c:forEach>
	
	
	<td><a href="/phonebook2/pbc?action=wform">[전화번호추가]</a> </td>
	
</body>
</html>
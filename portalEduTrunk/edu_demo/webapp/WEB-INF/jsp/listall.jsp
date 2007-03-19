<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>

<c:forEach var="item" items="${list}">
<p style="margin:0px">
<span style="align:left"/><b><c:out value="${item.login}"/></b>: 
  <c:out value="${item.firstName}"/> <c:out value="${item.lastName}"/> 
  (<c:out value="${item.email}"/>) </span>
<span style="align:right">
<a href="view?id=<c:out value="${item.id}"/>">View</a>
&nbsp;&nbsp;
<a href="edit?id=<c:out value="${item.id}"/>">Edit</a>
&nbsp;&nbsp;
<form action="delete" method="POST" style="font-size:80%">
<input type="hidden" name="id" value="<c:out value="${item.id}"/>">
<input type="submit" value="Delete" onSubmit="return verify()"/>
</form>
</p>
</c:forEach>
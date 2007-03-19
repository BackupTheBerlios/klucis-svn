<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>

<div class="box_content">
<table border="1" cellspacing="0" style="margin:10px">
<col width="70%" /><col width="10%" /><col width="10%" /><col width="10%" />
<c:forEach var="item" items="${list}">
<tr><td>
<span style="align:left"/><b><c:out value="${item.login}"/></b>: 
  <c:out value="${item.firstName}"/> <c:out value="${item.lastName}"/> 
  (<c:out value="${item.email}"/>) 
</td>
<td>
<a href="view?id=<c:out value="${item.id}"/>">View</a>
</td>
<td>
<a href="edit?id=<c:out value="${item.id}"/>">Edit</a>
</td>
<td>
<form action="delete" method="POST" 
style="display:inline; margin:0px; padding:0px; font-size:80%">
<input type="hidden" name="id" value="<c:out value="${item.id}"/>">
<input type="submit" value="Delete" onSubmit="return verify()"/>
</form>
</td>
</tr>
</c:forEach>
</table>
</div><!--box_content-->
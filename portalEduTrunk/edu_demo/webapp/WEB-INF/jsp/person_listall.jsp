<%@ page contentType="text/html;charset=UTF-8" %> 
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>

<div class="box_content">
<p><b><a href="new">Add new item</a></b></p>

<table border="1" width="90%" cellspacing="0" style="margin:10px">
<col width="80%" /><col width="10%" /><col width="10%" />
<c:forEach var="item" items="${list}">
<tr><td>
<span style="align:left"/><b><c:out value="${item.login}"/></b>: 
  <a href="<c:url value="mailto:${item.email}"/>">
  <c:out value="${item.firstName}"/> <c:out value="${item.lastName}"/></a>  
</td>
<td>
<a href="edit?id=<c:out value="${item.id}"/>">Edit</a>
</td>
<td>
<form action="delete" method="POST" onsubmit="return confirmDelete()" 
style="display:inline; margin:0px; padding:0px; font-size:80%">
<input type="hidden" name="id" value="<c:out value="${item.id}"/>">
<input type="submit" value="Delete"/>
</form>
</td>
</tr>
</c:forEach>
</table>

</div><!--box_content-->
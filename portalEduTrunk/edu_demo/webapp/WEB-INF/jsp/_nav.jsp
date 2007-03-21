<%@ page contentType="text/html;charset=UTF-8" %> 
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>

<a href="/eduDemo/mtest/welcome">Saakums</a> |
<c:choose>
<c:when test="${not empty user}">
<a href="/eduDemo/mtest/person/logout">Atsleegties</a> |
</c:when>
<c:otherwise>
<a href="/eduDemo/mtest/person/login">Piesleegties</a> |
</c:otherwise>
</c:choose>
<a href="/eduDemo/mtest/session/list">Mani testi</a> |
<a href="/eduDemo/mtest/module/list">Mani modulji</a> |
<a href="/eduDemo/mtest/assignment/list">Manas sagataves</a> |
<a href="/eduDemo/mtest/assignment/listall">Visas sagataves</a>
<c:if test="${not empty user}">&nbsp;&nbsp; Welcome, ${user.login}!</c:if>
<c:if test="${user.login == 'admin'}">
<br /><a href="/eduDemo/mtest/person/listall">Lietotaaji</a> |
<a href="/eduDemo/mtest/questiontype/listall">Jautaajumu tipi</a>
</c:if>
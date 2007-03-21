<%@ page contentType="text/html;charset=UTF-8" %> 
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>

<div class="box_content">
<form action="/eduDemo/mtest/person/new" method="post">

<spring:nestedPath path="command">  
<spring:bind path="id">
<input name="${status.expression}" type="hidden" value='<c:out value="${status.value}"/>' />
</spring:bind>

<spring:bind path="login">
<p><label for="login">Lietotāja vārds (login)</label></br>
<input id="login" name="${status.expression}" size="30" type="text" value='<c:out value="${status.value}"/>' />
<font color="#FF0000">${status.errorMessage}</font>
</p></spring:bind>

<spring:bind path="firstName">
<p><label for="firstName">Vārds</label></br>
<input id="firstName" name="${status.expression}" size="30" type="text" value='<c:out value="${status.value}"/>' />
<font color="#FF0000">${status.errorMessage}</font>
</p></spring:bind>

<spring:bind path="lastName">
<p><label for="lastName">Uzvārds</label></br>
<input id="lastName" name="${status.expression}" size="30" type="text" value='<c:out value="${status.value}"/>' />
<font color="#FF0000">${status.errorMessage}</font>
</p></spring:bind>

<spring:bind path="email">
<p><label for="email">Epasts</label></br>
<input id="email" name="${status.expression}" size="30" type="text" value='<c:out value="${status.value}"/>' />
<font color="#FF0000">${status.errorMessage}</font>
</p></spring:bind>

<spring:bind path="password">
<p><label for="password">Parole</label></br>
<input id="password" name="${status.expression}" size="30" type="password" value='<c:out value="${status.value}"/>' />
<font color="#FF0000">${status.errorMessage}</font>
</p></spring:bind>

<spring:bind path="password2">
<p><label for="password2">Parole vēlreiz</label></br>
<input id="password2" name="${status.expression}" size="30" type="password" value='<c:out value="${status.value}"/>' />
<font color="#FF0000">${status.errorMessage}</font>
</p></spring:bind>

</spring:nestedPath>

<input name="commit" type="submit" value="Izveidot" />
</form>

<p><font color="#FF0000">${success}</font></p>


<a href="/eduDemo/mtest/person/listall">Atpakaļ</a>

</div>
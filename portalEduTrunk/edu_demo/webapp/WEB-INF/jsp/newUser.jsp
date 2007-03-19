<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>

<div class="box_content">
<form action="/eduDemo/mtest/user/new" method="post">

<spring:nestedPath path="command">  
<spring:bind path="login">
<p><label for="login">Lietotaja vards</label></br>
<input id="login" name="${status.expression}" size="30" type="text" value='<c:out value="${status.value}"/>' />
<font color="#FF0000">${status.errorMessage}</font>
</p></spring:bind>

<spring:bind path="firstName">
<p><label for="firstName">Vards</label></br>
<input id="firstName" name="${status.expression}" size="30" type="text" value='<c:out value="${status.value}"/>' />
<font color="#FF0000">${status.errorMessage}</font>
</p></spring:bind>

<spring:bind path="lastName">
<p><label for="lastName">Uzvards</label></br>
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
<input id="password" name="${status.expression}" size="30" type="password" value="" />
<font color="#FF0000">${status.errorMessage}</font>
</p></spring:bind>

<spring:bind path="password2">
<p><label for="password2">Parole veelreiz</label></br>
<input id="password2" name="${status.expression}" size="30" type="password" value="" />
<font color="#FF0000">${status.errorMessage}</font>
</p></spring:bind>

</spring:nestedPath>

<input name="commit" type="submit" value="Izveidot" />
</form>

<p><font color="#FF0000">${success}</font></p>


<a href="/eduDemo/mtest/welcome">Atpakalj</a>

</div>
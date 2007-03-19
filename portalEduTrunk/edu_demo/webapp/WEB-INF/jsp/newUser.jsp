<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>

<div class="box_content">
<form action="/eduDemo/mtest/user/new" method="post">
  
<!--[form:user]-->
<p><label for="login">Lietotaja vards</label></br>
<spring:bind path="command.login"/>
<input id="login" name="${status.expression}" size="30" type="text" value='<c:out value="${status.value}"/>' />
<font color="#FF0000">${status.errorMessage}</font>
</p>

<p><label for="firstName">Vards</label></br>
<spring:bind path="command.firstName"/>
<input id="firstName" name="${status.expression}" size="30" type="text" value='<c:out value="${status.value}"/>' />
<font color="#FF0000">${status.errorMessage}</font>
</p>

<p><label for="lastName">Uzvards</label></br>
<spring:bind path="command.lastName"/>
<input id="lastName" name="${status.expression}" size="30" type="text" value='<c:out value="${status.value}"/>' />
<font color="#FF0000">${status.errorMessage}</font>
</p>

<p><label for="email">Epasts</label></br>
<spring:bind path="command.email"/>
<input id="email" name="${status.expression}" size="30" type="text" value='<c:out value="${status.value}"/>' />
<font color="#FF0000">${status.errorMessage}</font>
</p>

<p><label for="password">Parole</label></br>
<spring:bind path="command.password"/>
<input id="password" name="${status.expression}" size="30" type="password" value="" />
<font color="#FF0000">${status.errorMessage}</font>
</p>

<p><label for="password2">Parole veelreiz</label></br>
<spring:bind path="command.password2"/>
<input id="password2" name="${status.expression}" size="30" type="password" value="" />
<font color="#FF0000">${status.errorMessage}</font>
</p>
<!--[eoform:user]-->
<input name="commit" type="submit" value="Izveidot" />
</form>
<c:if test="$success">
<p>${success}</p>
</c:if>

<a href="/eduDemo/mtest/welcome">Atpakalj</a>
</div><!--box_content-->
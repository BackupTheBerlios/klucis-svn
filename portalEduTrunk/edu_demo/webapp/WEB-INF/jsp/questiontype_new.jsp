<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>

<div class="box_content">
<form action="/eduDemo/mtest/questiontype/new" method="post">

<spring:nestedPath path="command">  
<spring:bind path="label">
<p><label for="label">Simbolisks vaards (label)</label></br>
<input id="label" name="${status.expression}" size="30" type="text" value='<c:out value="${status.value}"/>' />
<font color="#FF0000">${status.errorMessage}</font>
</p></spring:bind>

<spring:bind path="instruction">
<p><label for="instruction">Instrukcija</label></br>
<input id="instruction" name="${status.expression}" size="30" type="text" value='<c:out value="${status.value}"/>' />
<font color="#FF0000">${status.errorMessage}</font>
</p></spring:bind>
</spring:nestedPath>

<input name="commit" type="submit" value="Pievienot" />
</form>

<p><font color="#FF0000">${success}</font></p>


<a href="/eduDemo/mtest/questiontype/listall">Atpakalj</a>

</div>
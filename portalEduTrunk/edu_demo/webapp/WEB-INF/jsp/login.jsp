<div class="box_content">
<form action="/eduDemo/mtest/user/new" method="post">
  
<p><label for="login">Login</label></br>
<spring:bind path="command.login"/>
<input id="login" name="${status.expression}" size="30" type="text" value='<c:out value="${status.value}"/>' />
<font color="#FF0000">${status.errorMessage}</font>
</p>

<p><label for="firstName">Vards</label></br>
<spring:bind path="command.firstName"/>
<input id="firstName" name="${status.expression}" size="30" type="text" value='<c:out value="${status.value}"/>' />
<font color="#FF0000">${status.errorMessage}</font>
</p>

</div><!--box_content-->
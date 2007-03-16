<div class="box_content">
<form action="/eduDemo/mtest/user_new.do" method="post">
  
<!--[form:user]-->
<p><label for="userName">Lietotaja vards</label></br>
#springBind("command.userName")
<input id="userName" name="${status.expression}" size="30" type="text" value="$!{status.value}" />
<font color="#FF0000">${status.errorMessage}</font>
</p>

<p><label for="name">Vards</label></br>
#springBind("command.name")
<input id="name" name="${status.expression}" size="30" type="text" value="$!{status.value}" />
<font color="#FF0000">${status.errorMessage}</font>
</p>

<p><label for="name">Uzvards</label></br>
#springBind("command.name")
<input id="name" name="${status.expression}" size="30" type="text" value="$!{status.value}" />
<font color="#FF0000">${status.errorMessage}</font>
</p>

<p><label for="email">Epasts</label></br>
#springBind("command.email")
<input id="user_email" name="${status.expression}" size="30" type="text" value="$!{status.value}" />
<font color="#FF0000">${status.errorMessage}</font>
</p>

<p><label for="password">Parole</label></br>
#springBind("command.password")
<input id="password" name="${status.expression}" size="30" type="password" value="" />
<font color="#FF0000">${status.errorMessage}</font>
</p>

<p><label for="password2">Parole veelreiz</label></br>
#springBind("command.password2")
<input id="password2" name="${status.expression}" size="30" type="password" value="" />
<font color="#FF0000">${status.errorMessage}</font>
</p>
<!--[eoform:user]-->
<input name="commit" type="submit" value="Izveidot" />
</form>
#if ($success) 
<p>${success}</p>
#end

<a href="/eduDemo/mtest/welcome.do">Atpakalj</a>
</div><!--box_content-->
<%@ page contentType="text/html;charset=UTF-8" %> 
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>

<div class="box_content">
<form action="/eduDemo/mtest/person/login" method="POST">
  
<p><label for="login">Login</label></br>
<input id="login" name="login" size="30" type="text"/>
</p>

<p><label for="password">Password</label></br>
<input id="password" name="password" type="password"/>
</p>

<p><input type="submit" value="Login"/>
<font color="#FF0000">${error}</font></p>

</div><!--box_content-->
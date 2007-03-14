<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.1//EN" "http://www.w3.org/TR/xhtml11/DTD/xhtml11.dtd">
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jstl/fmt" %>


<html xmlns="http://www.w3.org/1999/xhtml">
  <head>
    <title><c:out value="${htitle}"/><%--<fmt:message key="${htitle}"/>--%></title>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
    <link rel="stylesheet" href="stylesheets/style.css" type="text/css"  />
  </head>
  <body>
    <div id="indent"><a href="/eduDemo">
    <img alt="Logo" src="images/logo_big.gif"></a>
    </div>
    <div id="top1"></div>
    <div class="navigation" id="navigation_" >   
    <c:import url="${nav}"/>
    </div>
    
    <div class="main">
    <h1><fmt:message key="${htitle}"/></h1>
    <div class="box_green">
    <div class="box_green_t"><div></div></div>
   
      <c:import url="${content}"/>
   

    <div class="box_green_b"><div></div></div>
    </div><!--box_green-->
    </div><!--main-->

<div id="footer">2007-03-14 17:16:18 <img src="images/firefox.png" width="80" height="15"></div>


</body>
</html>
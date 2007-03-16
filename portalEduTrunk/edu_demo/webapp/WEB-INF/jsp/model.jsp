<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<html xmlns="http://www.w3.org/1999/xhtml">
  <head>
    <title><fmt:message key="${htitle}"/></title>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
    <link rel="stylesheet" href="/eduDemo/stylesheets/style.css" type="text/css"  />
  </head>
  <body>
    <div id="indent"><a href="/eduDemo">
    <img alt="Logo" src="/eduDemo/images/logo_big.gif"></a>
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

<div id="footer">
<jsp:useBean id="now" class="java.util.Date" />
<fmt:formatDate value="${now}" type="date"
  dateStyle="full"/>
  <img src="/eduDemo/images/firefox.png" width="80" height="15"></div>


</body>
</html>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>

<a href="/eduDemo/mtest/welcome">Saakums</a> |
<a href="/eduDemo/mtest/user/login">Piesleegties</a> |
<a href="/eduDemo/mtest/session/list">Mani testi</a> |
<a href="/eduDemo/mtest/module/list">Mani modulji</a> |
<a href="/eduDemo/mtest/assignment/list">Manas sagataves</a> |
<a href="/eduDemo/mtest/assignment/listall">Visas sagataves</a>
<c:if test="${user.login == 'admin'}">
<br /><a href="/eduDemo/mtest/user/listall">Lietotaaji</a> |
<a href="/eduDemo/mtest/questiontype/listall">Jautaajumu tipi</a>
</c:if>
<%--
  Created by IntelliJ IDEA.
  User: antoniosilvadelpozo
  Date: 03/04/14
  Time: 20:13
  To change this template use File | Settings | File Templates.
--%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>

<header id="header">
    <spring:message code="header.title" />
    <nav>
        <ul>
            <li><a href="home"><spring:message code="header.menu.home" /></a></li>
            <li><a href="restaurants"><spring:message code="header.menu.restaurants" /></a></li>
            <li><a href=""><spring:message code="header.menu.rankings" /></a></li>
            <li><a href=""><spring:message code="header.menu.contact" /></a></li>
        </ul>
    </nav>
</header>

<%--
  Created by IntelliJ IDEA.
  User: antoniosilvadelpozo
  Date: 07/06/14
  Time: 19:40
  To change this template use File | Settings | File Templates.
--%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>

<article class="restaurant">
    <header class="headerRestaurant">
        <p class="titleRestaurant">${restaurant.name}</p>
        <p></p>
    </header>
    <section class="contentRestaurant">
        <img class="mainPicture" src="<c:url value="/photo/${restaurant.id}/content" />" alt="${restaurant.name}">
        <div class="dataRestaurant">
            <p class="description">${restaurant.description}</p>
            <c:forEach items="${restaurant.categories}" var="category">
                <img src="/static/images/icons/${category}.png" alt="${category}" title="${category}">
            </c:forEach>
            <p><span class="underline"><spring:message code="restaurant.detail.general.zone" /></span><span>: ${restaurant.zone}</span></p>
            <p><span class="underline"><spring:message code="restaurant.detail.general.address" /></span><span>: ${restaurant.address}</span></p>
            <p><span class="underline"><spring:message code="restaurant.detail.general.telephono" /></span><span>: ${restaurant.telephone}</span></p>
        </div>
    </section>
</article>

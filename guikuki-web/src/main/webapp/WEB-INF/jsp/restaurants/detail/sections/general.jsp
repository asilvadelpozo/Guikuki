<%--
  Created by IntelliJ IDEA.
  User: antoniosilvadelpozo
  Date: 08/06/14
  Time: 21:05
  To change this template use File | Settings | File Templates.
--%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>

<section class="pictureSection">
    <img class="mainPicture" src="<c:url value="/photo/${restaurant.id}/content" />" alt="${restaurant.name}">
</section>
<section class="dataSection">
    <header class="headerRestaurant">
        <p class="titleRestaurantDetail">${restaurant.name}</p>
    </header>
    <p class="description">${restaurant.description}</p>
    <c:forEach items="${restaurant.categories}" var="category">
        <img src="/static/images/icons/${category}.png" alt="${category}" title="${category}">
    </c:forEach>
    <p><span class="underline"><spring:message code="restaurant.detail.general.zone"/></span><span>: ${restaurant.zone}</span></p>

    <p><span class="underline"><spring:message code="restaurant.detail.general.address"/></span><span>: ${restaurant.address}</span></p>

    <p><span class="underline"><spring:message code="restaurant.detail.general.telephone"/></span><span>: ${restaurant.telephone}</span>
    </p>
</section>
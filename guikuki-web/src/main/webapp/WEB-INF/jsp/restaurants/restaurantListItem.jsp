<%--
  Created by IntelliJ IDEA.
  User: antoniosilvadelpozo
  Date: 01/04/14
  Time: 21:14
  To change this template use File | Settings | File Templates.
--%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>

<article class="restaurant">
    <header class="headerRestaurant">
        <p class="titleRestaurantListItem">${param.name}</p>
        <p></p>
    </header>
    <section class="contentRestaurant">
        <img class="mainPicture" src="<c:url value="/photo/${param.id}/content" />" alt="${param.name}">
        <div class="descriptionRestaurant">
            <p>${param.description}</p>
            <a href="restaurants/${param.id}" class="button rightBottom"><spring:message code="restaurant.list.item.detail.button" /></a>
        </div>
    </section>
</article>



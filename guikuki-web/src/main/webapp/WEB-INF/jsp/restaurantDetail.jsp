<%--
  Created by IntelliJ IDEA.
  User: antoniosilvadelpozo
  Date: 01/04/14
  Time: 21:14
  To change this template use File | Settings | File Templates.
--%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>


<article class="restaurant">
    <header class="headerRestaurant">
        <p class="titleRestaurant">${restaurant.name}</p>

        <p></p>
    </header>
    <section class="contentRestaurant">
        <img src="<c:url value="/photo/${restaurant.id}/content" />" alt="${restaurant.name}">

        <div class="descriptionRestaurant">
            <p>${restaurant.description}</p>
        </div>
    </section>
</article>



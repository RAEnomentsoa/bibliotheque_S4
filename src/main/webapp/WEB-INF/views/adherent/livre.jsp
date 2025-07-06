<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Liste des livres disponibles</title>
</head>
<body>
<h2>Livres disponibles à la réservation</h2>

<c:if test="${not empty message}">
    <p style="color:green;">${message}</p>
</c:if>
<c:if test="${not empty error}">
    <p style="color:red;">${error}</p>
</c:if>

<table border="1" cellpadding="10">
    <thead>
        <tr>
            <th>Titre</th>
            <th>Action</th>
        </tr>
    </thead>
    <tbody>
        <c:forEach var="livre" items="${livres}">
            <tr>
                <td>${livre.nom}</td>
                <td>
                    <form action="${pageContext.request.contextPath}/adherent/livre/${livre.id}/exemplaires" method="get">
                        <button type="submit">Voir les exemplaires</button>
                    </form>
                </td>
            </tr>
        </c:forEach>
    </tbody>
</table>

</body>
</html>

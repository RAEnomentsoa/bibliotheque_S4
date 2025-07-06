<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head><title>Exemplaires disponibles</title></head>
<body>
    <h2>Exemplaires pour : ${livre.nom}</h2>

    <c:if test="${not empty message}">
        <p style="color:green;">${message}</p>
    </c:if>
    <c:if test="${not empty error}">
        <p style="color:red;">${error}</p>
    </c:if>

    <table border="1">
        <tr>
            <th>ID</th>
            <th>Action</th>
        </tr>
        <c:forEach var="ex" items="${exemplaires}">
            <tr>
                <td>Exemplaire ${ex.id}</td>
                <td>
                    <form action="${pageContext.request.contextPath}/adherent/reserver" method="post">
                        <input type="hidden" name="exemplaireId" value="${ex.id}" />
                        <button type="submit">reserver</button>
                    </form>
                </td>
            </tr>
        </c:forEach>
    </table>

    <p><a href="${pageContext.request.contextPath}/adherent/livres">Retour à la liste des livres</a></p>
</body>
</html>

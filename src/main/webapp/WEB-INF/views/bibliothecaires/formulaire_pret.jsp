<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <title>📚 Exemplaires réservés</title>
    <link href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/6.0.0/css/all.min.css" rel="stylesheet">
    </head>
<body>
<%@ include file="/WEB-INF/views/navbar_admin.jsp" %>
<form action="${pageContext.request.contextPath}/bibliothecaires/prets/ajouter" method="post">
    <input type="hidden" name="adherentId" value="${adherentId}" />
    <input type="hidden" name="exemplaireId" value="${exemplaireId}" />

    <label for="datePret">Date du pret :</label>
    <input type="date" name="datePret" required value="${now}" />

    <label for="typeId">Type de pret :</label>
    <select name="typeId">
        <c:forEach var="type" items="${typesPret}">
            <option value="${type.id}">${type.libelle}</option>
        </c:forEach>
    </select>

    <button type="submit">Valider le pret</button>
</form>

</body>
</html>

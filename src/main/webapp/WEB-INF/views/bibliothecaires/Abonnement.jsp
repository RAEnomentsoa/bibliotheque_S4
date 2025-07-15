<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <title>Nouvel Abonnement</title>
</head>
<body>
<%@ include file="/WEB-INF/views/navbar_admin.jsp" %>
    <h2>Créer un abonnement</h2>
    <form action="${pageContext.request.contextPath}/bibliothecaires/abonnement/create" method="post">
        <label>Adherent :</label>
        <select name="adherent.id" required>
            <option value="">-- Selectionner --</option>
            <c:forEach var="adherent" items="${adherents}">
                <option value="${adherent.id}">${adherent.nom}</option>
            </c:forEach>
        </select>
        <br/>
        <label>Date debut :</label>
        <input type="date" name="dateDebut" required/>
        <br/>
        <label>Date fin :</label>
        <input type="date" name="dateFin" required/>
        <br/>
        <button type="submit">Creer</button>
    </form>
</body>
</html>
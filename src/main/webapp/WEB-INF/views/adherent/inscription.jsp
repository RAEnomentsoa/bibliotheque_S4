<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head><title>Inscription</title></head>
<body>
<h2>Formulaire d'inscription</h2>

<form action="${pageContext.request.contextPath}/adherent/inscription" method="post">
    <label>Nom :</label>
    <input type="text" name="nom" required/><br/>

    <label>Email :</label>
    <input type="email" name="email" required/><br/>

    <label>Mot de passe :</label>
    <input type="password" name="motDePasse" required/><br/>

    <label>Date de naissance :</label>
    <input type="date" name="dateNaissance" required/><br/>

    <label>Type d'adhérent :</label>
    <select name="typeAdherent.id" required>
        <c:forEach var="type" items="${types}">
            <option value="${type.id}">${type.libelle}</option>
        </c:forEach>
    </select><br/><br/>

    <input type="submit" value="S'inscrire" />
</form>

<c:if test="${not empty message}">
    <p style="color:green">${message}</p>
</c:if>

</body>
</html>

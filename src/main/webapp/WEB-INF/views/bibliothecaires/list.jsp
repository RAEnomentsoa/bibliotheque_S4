<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
<head>
    <title>Liste des Bibliothécaires</title>
</head>
<body>
    <h1>Liste des Bibliothécaires</h1>
    <table border="1">
        <thead>
            <tr>
                <th>ID</th>
                <th>Nom</th>
                <th>Mot de passe</th>
            </tr>
        </thead>
        <tbody>
            <c:forEach var="b" items="${bibliothecaires}">
                <tr>
                    <td>${b.id}</td>
                    <td>${b.nom}</td>
                    <td>${b.motDePasse}</td>
                </tr>
            </c:forEach>
        </tbody>
    </table>
</body>
</html>

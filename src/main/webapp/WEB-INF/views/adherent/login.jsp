<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head><title>Connexion</title></head>
<body>
<h2>Connexion</h2>

<form action="${pageContext.request.contextPath}/adherent/loginAdherent" method="post">
    Email: <input type="email" name="email" /><br/>
    Mot de passe: <input type="password" name="motDePasse" /><br/>
    <input type="submit" value="Se connecter" />
</form>

<c:if test="${not empty error}">
    <p style="color:red">${error}</p>
</c:if>

</body>
</html>

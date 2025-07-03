<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Document</title>
</head>
<body>
<% if (request.getAttribute("loginError") != null) { %>
    <div style="color:red;"><%= request.getAttribute("loginError") %></div>
<% } %>
<form action="/bibliotec/login" method="post">
    <label>Nom:</label>
    <input type="text" name="nom" required title="Entrez votre nom" placeholder="Votre nom" /><br>
    <label>Mot de passe:</label>
    <input type="password" name="mot_de_passe" required title="Entrez votre mot de passe" placeholder="Votre mot de passe" /><br>
    <button type="submit">Se connecter</button>
</form>
</body>
</html>
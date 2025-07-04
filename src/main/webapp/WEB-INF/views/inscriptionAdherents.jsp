<!DOCTYPE html>
<html>
<head>
  <title>Inscription</title>
</head>
<body>
  <h2>Inscription</h2>
  
  <form action="inscription" method="post">
    <div>
      <label>Nom complet:</label><br>
      <input type="text" name="nom" required>
    </div>
    
    <div>
      <label>Email:</label><br>
      <input type="email" name="email" required>
    </div>
    
    <div>
      <label>Mot de passe:</label><br>
      <input type="password" name="mot_de_passe" required>
    </div>
    
    <div>
      <label>Date de naissance:</label><br>
      <input type="date" name="date_naissance" required>
    </div>
    
    <div>
      <label>Type d'adherent:</label><br>
      <select name="type_id" required>
        <option value="">-- Selectionnez un type --</option>
        <option value="1">Etudiant</option>
        <option value="2">Professeur</option>
        <option value="3">Professionnel</option>
      </select>
    </div>
    
    <button type="submit">S'inscrire</button>
  </form>
  
  <p>Deja inscrit? <a href="/connexion">Connectez-vous ici</a></p>
</body>
</html>
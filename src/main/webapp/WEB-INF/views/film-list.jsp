<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
<head>
    <title>Liste des Films</title>
   
</head>
<body>
    <!-- Vaisseaux spatiaux en arrière-plan -->
    <div class="spaceship"></div>
    <div class="spaceship2"></div>
    
    <div class="container mt-5">
        <h2>Liste des Films</h2>
        <a href="films/new" class="btn btn-primary mb-3">Ajouter un Film</a>
        
        <table class="table">
            <thead>
                <tr>
                    <th>ID</th>
                    <th>Titre</th>
                    <th>Type</th>
                    <th>Catégories</th>
                    <th>Année de sortie</th>
                    <th>Actions</th>
                </tr>
            </thead>
            <tbody>
                <c:forEach var="film" items="${films}">
                    <tr>
                        <td>${film.idFilm}</td>
                        <td>${film.titre}</td>
                        <td>${film.type.nomType}</td>
                        <td>
                            <c:forEach var="categorie" items="${film.categories}" varStatus="status">
                                ${categorie.nomCategorie}${!status.last ? ', ' : ''}
                            </c:forEach>
                        </td>
                        <td>${film.anneeSortie}</td>
                        <td>
                            <a href="films/edit/${film.idFilm}" class="btn btn-warning btn-sm">Modifier</a>
                            <a href="films/delete/${film.idFilm}" class="btn btn-danger btn-sm" 
                               onclick="return confirmDelete(event, '${film.titre}')">Supprimer</a>
                        </td>
                    </tr>
                </c:forEach>
            </tbody>
        </table>
    </div>

</body>
</html>
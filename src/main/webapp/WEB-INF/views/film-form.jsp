<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
<head>
   
</head>
<body>
    <div class="container mt-5">
        <h2>${film.idFilm == null ? 'Nouveau Film' : 'Modifier Film'}</h2>
                 
        <form action="${pageContext.request.contextPath}/films" method="post">
            <input type="hidden" name="idFilm" value="${film.idFilm}">
                         
            <div class="mb-3">
                <label for="titre" class="form-label">Titre</label>
                <input type="text" class="form-control" id="titre" name="titre" value="${film.titre}" required>
            </div>
                         
            <div class="mb-3">
                <label for="anneeSortie" class="form-label">Année de sortie</label>
                <input type="number" class="form-control" id="anneeSortie" name="anneeSortie" value="${film.anneeSortie}" min="1888">
            </div>
                         
            <div class="mb-3">
                <label for="typeId" class="form-label">Type</label>
                <select class="form-select" id="typeId" name="typeId" required>
                    <option value="">Sélectionnez un type</option>
                    <c:forEach var="type" items="${types}">
                        <option value="${type.idType}" ${film.type != null && film.type.idType == type.idType ? 'selected' : ''}>${type.nomType}</option>
                    </c:forEach>
                </select>
            </div>
                         
            <div class="mb-3">
                <label for="categorieIds" class="form-label">Catégories</label>
                <select class="form-select" id="categorieIds" name="categorieIds" multiple>
                    <c:forEach var="categorie" items="${categories}">
                        <option value="${categorie.idCategorie}"
                                 <c:forEach var="filmCategorie" items="${film.categories}">
                                    ${filmCategorie.idCategorie == categorie.idCategorie ? 'selected' : ''}
                                </c:forEach>
                        >${categorie.nomCategorie}</option>
                    </c:forEach>
                </select>
            </div>
                         
            <button type="submit" class="btn btn-primary">Enregistrer</button>
            <a href="${pageContext.request.contextPath}/films" class="btn btn-secondary">Annuler</a>
        </form>
    </div>
</body>
</html>
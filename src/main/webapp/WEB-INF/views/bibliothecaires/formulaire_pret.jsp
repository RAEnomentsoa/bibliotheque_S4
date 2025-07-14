<form action="/prets/ajouter" method="post">
    <input type="hidden" name="adherentId" value="${adherent.id}" />
    <input type="hidden" name="exemplaireId" value="${exemplaire.id}" />

    <label for="datePret">Date du prêt :</label>
    <input type="date" name="datePret" required value="${now}" />

    <label for="typeId">Type de prêt :</label>
    <select name="typeId">
        <c:forEach var="type" items="${typesPret}">
            <option value="${type.id}">${type.libelle}</option>
        </c:forEach>
    </select>

    <button type="submit">Valider le prêt</button>
</form>

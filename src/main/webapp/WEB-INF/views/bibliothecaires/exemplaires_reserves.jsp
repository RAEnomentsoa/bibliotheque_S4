<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>📚 Exemplaires réservés</title>
    <link href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/6.0.0/css/all.min.css" rel="stylesheet">
    <style>
        body {
            font-family: 'Georgia', serif;
            background: linear-gradient(135deg, #2c1810 0%, #4a3429 50%, #6b4c3a 100%);
            min-height: 100vh;
            padding: 2rem;
            color: #fff;
        }

        h2 {
            text-align: center;
            font-size: 2rem;
            margin-bottom: 1rem;
            color: #d4af37;
            text-shadow: 0 2px 4px rgba(0,0,0,0.5);
        }

        table {
            width: 100%;
            border-collapse: collapse;
            margin-bottom: 3rem;
            background: rgba(255,255,255,0.95);
            color: #2c1810;
            border-radius: 12px;
            overflow: hidden;
            box-shadow: 0 10px 30px rgba(0,0,0,0.3);
        }

        th, td {
            padding: 12px 20px;
            text-align: center;
            border-bottom: 1px solid #ddd;
        }

        thead {
            background: linear-gradient(90deg, #8b4513, #d4af37, #8b4513);
            color: #fff;
            text-transform: uppercase;
            font-size: 0.95rem;
        }

        tr:hover {
            background-color: rgba(255, 248, 220, 0.3);
        }

        form, a {
            display: inline-block;
        }

        input[type="submit"],
        button {
            background-color: #8b4513;
            color: #fff;
            padding: 6px 14px;
            border: none;
            border-radius: 6px;
            cursor: pointer;
            font-weight: bold;
            transition: background 0.3s ease;
        }

        input[type="submit"]:hover,
        button:hover {
            background-color: #d4af37;
            color: #2c1810;
        }

        p {
            text-align: center;
            font-size: 1rem;
        }

        .success {
            color: lightgreen;
        }

        .error {
            color: #ff4d4d;
        }
    </style>
</head>
<body>

    <h2>Liste des exemplaires en attente</h2>

    <c:if test="${not empty message}">
        <p class="success">${message}</p>
    </c:if>
    <c:if test="${not empty error}">
        <p class="error">${error}</p>
    </c:if>

    <table>
        <thead>
            <tr>
                <th>Exemplaire</th>
                <th>Nom adhérent</th>
                <th>Livre</th>
                <th>État actuel</th>
                <th>Date de changement</th>
                <th>Actions</th>
            </tr>
        </thead>
        <tbody>
            <c:forEach var="statut" items="${statuts}">
                <tr>
                    <td>EX-${statut.exemplaire.id}</td>
                    <td>${nomsAdherents[statut.exemplaire.id]}</td>
                    <td>${statut.exemplaire.livre.nom}</td>
                    <td>${statut.etatExemplaire.libelle}</td>
                    <td>${statut.dateChangement}</td>
                    <td>
                        <form action="${pageContext.request.contextPath}/bibliothecaires/confirmerReservation" method="post">
                            <input type="hidden" name="exemplaireId" value="${statut.exemplaire.id}" />
                            <input type="submit" value="Réserver" />
                        </form>
                    </td>
                </tr>
            </c:forEach>
        </tbody>
    </table>

    <h2>Liste des exemplaires réservés</h2>

    <table>
        <thead>
            <tr>
                <th>Exemplaire</th>
                <th>Nom adhérent</th>
                <th>Livre</th>
                <th>État actuel</th>
                <th>Date de changement</th>
                <th>Actions</th>
            </tr>
        </thead>
        <tbody>
            <c:forEach var="Exemplairereserver" items="${Exemplairereservers}">
                <tr>
                    <td>EX-${Exemplairereserver.exemplaire.id}</td>
                    <td>${nomsAdherents[Exemplairereserver.exemplaire.id]}</td>
                    <td>${Exemplairereserver.exemplaire.livre.nom}</td>
                    <td>${Exemplairereserver.etatExemplaire.libelle}</td>
                    <td>${Exemplairereserver.dateChangement}</td>
                    <td>
                        <a href="${pageContext.request.contextPath}/bibliothecaires/prets/nouveau?adherentId=${adherentsParExemplaire[Exemplairereserver.exemplaire.id].id}&exemplaireId=${Exemplairereserver.exemplaire.id}">
                            <button type="button">Prêter</button>
                        </a>
                    </td>
                </tr>
            </c:forEach>
        </tbody>
    </table>

</body>
</html>

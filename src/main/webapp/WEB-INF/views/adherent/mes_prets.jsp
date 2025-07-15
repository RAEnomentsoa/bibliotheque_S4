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
            margin: 0;
            padding: 2rem;
            color: #2c1810;
        }

        h2 {
            text-align: center;
            color: #fff;
            margin-bottom: 2rem;
            text-shadow: 1px 1px 2px #000;
        }

        table {
            width: 100%;
            border-collapse: collapse;
            background: #fff;
            border-radius: 10px;
            overflow: hidden;
            box-shadow: 0 10px 30px rgba(0,0,0,0.2);
        }

        th, td {
            padding: 1rem;
            text-align: center;
            border-bottom: 1px solid #eee;
        }

        th {
            background: #8b4513;
            color: white;
            text-transform: uppercase;
            letter-spacing: 0.05em;
        }

        tr:nth-child(even) {
            background-color: #f9f9f9;
        }

        tr:hover {
            background-color: #f3ece7;
        }

        .btn {
            padding: 0.5rem 1rem;
            border: none;
            border-radius: 8px;
            cursor: pointer;
            font-weight: bold;
            text-transform: uppercase;
            font-size: 0.9rem;
            transition: background 0.3s ease;
        }

        .btn-danger {
            background: #c0392b;
            color: white;
        }

        .btn-danger:hover {
            background: #922b21;
        }

        input[type="date"] {
            padding: 0.4rem;
            border: 1px solid #ccc;
            border-radius: 6px;
            font-size: 0.9rem;
        }

        form {
            display: flex;
            flex-direction: column;
            gap: 0.5rem;
            align-items: center;
        }

        @media (max-width: 768px) {
            table, th, td {
                font-size: 0.85rem;
            }

            form {
                gap: 0.3rem;
            }

            input[type="date"] {
                width: 100%;
            }
        }
    </style>
</head>
<body>
<%@ include file="/WEB-INF/views/navbar.jsp" %>
    <h2> Mes livres empruntés</h2>
    
    <table>
        <tr>
            <th>Livre</th>
            <th>Date de prêt</th>
            <th>Date de retour</th>
            <th>Actions</th>
        </tr>
        <c:forEach var="pret" items="${prets}">
            <tr>
                <td>${pret.exemplaire.livre.nom}</td>
                <td>${pret.datePret}</td>
                <td>
                    <c:choose>
                        <c:when test="${empty pret.dateRetour}">
                            -
                        </c:when>
                        <c:otherwise>
                            ${pret.dateRetour}
                        </c:otherwise>
                    </c:choose>
                </td>
                <td>
                    <c:choose>
                        <c:when test="${empty pret.dateRetour}">
                            <form action="${pageContext.request.contextPath}/adherent/prets/retourner" method="post">
                                <input type="hidden" name="pretId" value="${pret.id}" />
                                <input type="hidden" name="exemplaireId" value="${pret.exemplaire.id}" />
                                <input type="hidden" name="adherentId" value="${pret.adherent.id}" />
                                <input type="date" name="dateRetour" value="${now}" />
                                <button type="submit" class="btn btn-danger">Retourner</button>
                            </form>
                        </c:when>
                        <c:otherwise>
                            <span style="color:green;">Déjà retourné</span>
                        </c:otherwise>
                    </c:choose>
                </td>
            </tr>
        </c:forEach>
    </table>
</body>
</html>

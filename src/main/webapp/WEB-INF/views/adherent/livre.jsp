<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<html>
<head>
    <title>Liste des livres disponibles</title>
    <link href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/6.0.0/css/all.min.css" rel="stylesheet">
    <style>
        body {
            font-family: 'Georgia', serif;
            background: linear-gradient(135deg, #2c1810 0%, #4a3429 50%, #6b4c3a 100%);
            color: #2c1810;
            min-height: 100vh;
            display: flex;
            align-items: center;
            justify-content: center;
            padding: 2rem;
        }

        .container {
            background: #fff;
            border-radius: 15px;
            padding: 2rem;
            max-width: 800px;
            width: 100%;
            box-shadow: 0 20px 40px rgba(0,0,0,0.3);
        }

        h2 {
            text-align: center;
            margin-bottom: 1.5rem;
            color: #6b4c3a;
        }

        .message {
            text-align: center;
            margin-bottom: 1rem;
            font-weight: bold;
        }

        .message.success {
            color: green;
        }

        .message.error {
            color: red;
        }

        table {
            width: 100%;
            border-collapse: collapse;
            margin-top: 1rem;
        }

        table thead {
            background: #8b4513;
            color: white;
        }

        table th, table td {
            padding: 1rem;
            text-align: left;
            border-bottom: 1px solid #ccc;
        }

        button {
            background: #a0522d;
            color: white;
            border: none;
            padding: 0.5rem 1rem;
            border-radius: 8px;
            cursor: pointer;
            transition: background 0.3s ease;
        }

        button:hover {
            background: #8b4513;
        }

        form {
            margin: 0;
        }
    </style>
</head>
<body>
<%@ include file="/WEB-INF/views/navbar.jsp" %>
<div class="container">
    <h2>Livres disponibles à la réservation</h2>

    <c:if test="${not empty message}">
        <p class="message success">${message}</p>
    </c:if>
    <c:if test="${not empty error}">
        <p class="message error">${error}</p>
    </c:if>

    <table>
        <thead>
            <tr>
                <th>Titre</th>
                <th>Action</th>
            </tr>
        </thead>
        <tbody>
            <c:forEach var="livre" items="${livres}">
                <tr>
                    <td>${livre.nom}</td>
                    <td>
                        <form action="${pageContext.request.contextPath}/adherent/livre/${livre.id}/exemplaires" method="get">
                            <button type="submit">
                                <i class="fas fa-eye"></i> Voir les exemplaires
                            </button>
                        </form>
                    </td>
                </tr>
            </c:forEach>
        </tbody>
    </table>
</div>
</body>
</html>

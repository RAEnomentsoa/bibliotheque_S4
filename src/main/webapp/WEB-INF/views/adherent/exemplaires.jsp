<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<html>
<head>
    <title>Exemplaires disponibles</title>
    <link href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/6.0.0/css/all.min.css" rel="stylesheet">
    <style>
        body {
            font-family: 'Georgia', serif;
            background: linear-gradient(135deg, #2c1810 0%, #4a3429 50%, #6b4c3a 100%);
            min-height: 100vh;
            padding: 2rem;
            color: #2c1810;
        }

        .container {
            background: #fff;
            padding: 2rem 2.5rem;
            border-radius: 15px;
            max-width: 700px;
            margin: auto;
            box-shadow: 0 20px 40px rgba(0,0,0,0.3);
        }

        h2 {
            text-align: center;
            color: #6b4c3a;
            margin-bottom: 1.5rem;
        }

        table {
            width: 100%;
            border-collapse: collapse;
            margin-bottom: 1.5rem;
        }

        table th, table td {
            border: 1px solid #ddd;
            padding: 1rem;
            text-align: center;
        }

        table th {
            background-color: #8b4513;
            color: white;
        }

        table tr:nth-child(even) {
            background-color: #f9f3ef;
        }

        button {
            padding: 0.6rem 1rem;
            background: linear-gradient(135deg, #8b4513, #a0522d);
            color: white;
            border: none;
            border-radius: 8px;
            font-weight: bold;
            cursor: pointer;
            transition: all 0.3s ease;
        }

        button:hover {
            background: #6b4c3a;
            transform: translateY(-2px);
            box-shadow: 0 5px 15px rgba(139, 69, 19, 0.3);
        }

        a {
            text-decoration: none;
            color: #8b4513;
            font-weight: bold;
        }

        .message {
            text-align: center;
            font-weight: bold;
            margin-bottom: 1rem;
        }

        .message.success {
            color: green;
        }

        .message.error {
            color: red;
        }
    </style>
</head>
<body>
<%@ include file="/WEB-INF/views/navbar.jsp" %>
    <div class="container">
        <h2><i class="fas fa-book-reader"></i> Exemplaires pour : ${livre.nom}</h2>

        <c:if test="${not empty message}">
            <p class="message success">${message}</p>
        </c:if>
        <c:if test="${not empty error}">
            <p class="message error">${error}</p>
        </c:if>

        <table>
            <tr>
                <th>ID</th>
                <th>Action</th>
            </tr>
            <c:forEach var="ex" items="${exemplaires}">
                <tr>
                    <td>Exemplaire ${ex.id}</td>
                    <td>
                        <form action="${pageContext.request.contextPath}/adherent/reserver" method="post">
                            <input type="hidden" name="exemplaireId" value="${ex.id}" />
                            <button type="submit"><i class="fas fa-calendar-plus"></i> Reserver</button>
                        </form>
                    </td>
                </tr>
            </c:forEach>
        </table>

        <p style="text-align: center;">
            <a href="${pageContext.request.contextPath}/adherent/livres"><i class="fas fa-arrow-left"></i> Retour a la liste des livres</a>
        </p>
    </div>
</body>
</html>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<html>
<head>
    <title>Inscription</title>
    <link href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/6.0.0/css/all.min.css" rel="stylesheet">
    <style>
        body {
            font-family: 'Georgia', serif;
            background: linear-gradient(135deg, #2c1810 0%, #4a3429 50%, #6b4c3a 100%);
            min-height: 100vh;
            display: flex;
            align-items: center;
            justify-content: center;
            padding: 2rem;
        }

        .form-container {
            background: #fff;
            padding: 2rem 2.5rem;
            border-radius: 15px;
            width: 100%;
            max-width: 500px;
            box-shadow: 0 20px 40px rgba(0,0,0,0.3);
        }

        h2 {
            text-align: center;
            margin-bottom: 1.5rem;
            color: #6b4c3a;
        }

        label {
            display: block;
            margin-bottom: 0.5rem;
            color: #2c1810;
            font-weight: bold;
        }

        input, select {
            width: 100%;
            padding: 0.75rem;
            border: 2px solid #ccc;
            border-radius: 10px;
            margin-bottom: 1.2rem;
            font-family: inherit;
            font-size: 1rem;
            background: #fdfdfd;
            transition: all 0.3s ease;
        }

        input:focus, select:focus {
            border-color: #8b4513;
            box-shadow: 0 0 0 3px rgba(139, 69, 19, 0.2);
            outline: none;
        }

        input[type="submit"] {
            background: linear-gradient(135deg, #8b4513, #a0522d);
            color: white;
            font-weight: bold;
            text-transform: uppercase;
            letter-spacing: 1px;
            cursor: pointer;
            transition: all 0.3s ease;
        }

        input[type="submit"]:hover {
            background: #6b4c3a;
            transform: translateY(-2px);
            box-shadow: 0 10px 20px rgba(139, 69, 19, 0.4);
        }

        .message {
            text-align: center;
            font-weight: bold;
            margin-top: 1rem;
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
    <div class="form-container">
        <h2><i class="fas fa-user-plus"></i> Formulaire d'inscription</h2>

        <form action="${pageContext.request.contextPath}/adherent/inscription" method="post">
            <label>Nom :</label>
            <input type="text" name="nom" required/>

            <label>Email :</label>
            <input type="email" name="email" required/>

            <label>Mot de passe :</label>
            <input type="password" name="motDePasse" required/>

            <label>Date de naissance :</label>
            <input type="date" name="dateNaissance" required/>

            <label>Type d'adhérent :</label>
            <select name="typeAdherent.id" required>
                <c:forEach var="type" items="${types}">
                    <option value="${type.id}">${type.libelle}</option>
                </c:forEach>
            </select>

            <input type="submit" value="S'inscrire" />
        </form>

        <c:if test="${not empty message}">
            <p class="message success">${message}</p>
        </c:if>
        <c:if test="${not empty error}">
            <p class="message error">${error}</p>
        </c:if>
    </div>
</body>
</html>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <title>Connexion</title>
    <link href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/6.0.0/css/all.min.css" rel="stylesheet">
    <style>
        body {
            font-family: 'Georgia', serif;
            background: linear-gradient(135deg, #2c1810 0%, #4a3429 50%, #6b4c3a 100%);
            min-height: 100vh;
            display: flex;
            align-items: center;
            justify-content: center;
            color: #2c1810;
        }

        .login-box {
            background: #fff;
            padding: 2.5rem;
            border-radius: 15px;
            width: 100%;
            max-width: 400px;
            box-shadow: 0 20px 40px rgba(0,0,0,0.3);
        }

        h2 {
            text-align: center;
            color: #8b4513;
            margin-bottom: 2rem;
        }

        form {
            display: flex;
            flex-direction: column;
            gap: 1rem;
        }

        input[type="email"],
        input[type="password"] {
            padding: 1rem;
            border: 1px solid #ccc;
            border-radius: 10px;
            font-size: 1rem;
        }

        input[type="submit"] {
            padding: 1rem;
            background: linear-gradient(135deg, #8b4513, #a0522d);
            color: white;
            border: none;
            border-radius: 10px;
            font-size: 1rem;
            font-weight: bold;
            cursor: pointer;
            transition: all 0.3s ease;
        }

        input[type="submit"]:hover {
            background: #6b4c3a;
            transform: translateY(-2px);
            box-shadow: 0 5px 15px rgba(139, 69, 19, 0.3);
        }

        .error {
            color: red;
            text-align: center;
            margin-top: 1rem;
            font-weight: bold;
        }
    </style>
</head>
<body>
    <div class="login-box">
        <h2><i class="fas fa-sign-in-alt"></i> Connexion</h2>

        <form action="${pageContext.request.contextPath}/adherent/loginAdherent" method="post">
            <input type="email" name="email" placeholder="Email" required />
            <input type="password" name="motDePasse" placeholder="Mot de passe" required />
            <input type="submit" value="Se connecter" />
        </form>

        <c:if test="${not empty error}">
            <p class="error">${error}</p>
        </c:if>
    </div>
</body>
</html>

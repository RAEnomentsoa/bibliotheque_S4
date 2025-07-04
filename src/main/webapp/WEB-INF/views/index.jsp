<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Bibliothèque - Connexion</title>
    <link href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/6.0.0/css/all.min.css" rel="stylesheet">
    <style>
        * {
            margin: 0;
            padding: 0;
            box-sizing: border-box;
        }

        body {
            font-family: 'Georgia', serif;
            background: linear-gradient(135deg, #2c1810 0%, #4a3429 50%, #6b4c3a 100%);
            min-height: 100vh;
            display: flex;
            align-items: center;
            justify-content: center;
            position: relative;
            overflow: hidden;
        }

        body::before {
            content: '';
            position: absolute;
            top: 0;
            left: 0;
            right: 0;
            bottom: 0;
            background: url('data:image/svg+xml,<svg xmlns="http://www.w3.org/2000/svg" viewBox="0 0 100 100"><defs><pattern id="books" x="0" y="0" width="20" height="20" patternUnits="userSpaceOnUse"><rect width="2" height="15" x="2" y="2" fill="%23ffffff" opacity="0.03"/><rect width="2" height="12" x="6" y="5" fill="%23ffffff" opacity="0.02"/><rect width="2" height="18" x="10" y="1" fill="%23ffffff" opacity="0.03"/><rect width="2" height="14" x="14" y="3" fill="%23ffffff" opacity="0.02"/></pattern></defs><rect width="100" height="100" fill="url(%23books)"/></svg>');
            opacity: 0.1;
        }

        .login-container {
            background: rgba(255, 255, 255, 0.95);
            backdrop-filter: blur(10px);
            border-radius: 20px;
            padding: 3rem 2.5rem;
            width: 100%;
            max-width: 420px;
            box-shadow: 
                0 25px 50px rgba(0, 0, 0, 0.3),
                0 0 0 1px rgba(255, 255, 255, 0.1),
                inset 0 1px 0 rgba(255, 255, 255, 0.8);
            position: relative;
            transform: translateY(0);
            animation: slideIn 0.8s ease-out;
        }

        @keyframes slideIn {
            from {
                opacity: 0;
                transform: translateY(30px);
            }
            to {
                opacity: 1;
                transform: translateY(0);
            }
        }

        .login-header {
            text-align: center;
            margin-bottom: 2.5rem;
        }

        .library-icon {
            font-size: 3rem;
            color: #8b4513;
            margin-bottom: 1rem;
            display: block;
            text-shadow: 0 2px 4px rgba(0, 0, 0, 0.1);
        }

        .login-title {
            font-size: 2rem;
            color: #2c1810;
            margin-bottom: 0.5rem;
            font-weight: 300;
            letter-spacing: 1px;
        }

        .login-subtitle {
            color: #6b4c3a;
            font-size: 0.9rem;
            font-style: italic;
        }

        .error-message {
            background: linear-gradient(135deg, #ff6b6b, #ee5a5a);
            color: white;
            padding: 1rem;
            border-radius: 10px;
            margin-bottom: 1.5rem;
            text-align: center;
            box-shadow: 0 4px 15px rgba(255, 107, 107, 0.3);
            animation: shake 0.5s ease-in-out;
        }

        @keyframes shake {
            0%, 100% { transform: translateX(0); }
            25% { transform: translateX(-5px); }
            75% { transform: translateX(5px); }
        }

        .form-group {
            margin-bottom: 1.5rem;
            position: relative;
        }

        .form-group label {
            display: block;
            margin-bottom: 0.5rem;
            color: #2c1810;
            font-weight: 500;
            font-size: 0.9rem;
            text-transform: uppercase;
            letter-spacing: 0.5px;
        }

        .input-wrapper {
            position: relative;
        }

        .input-wrapper i {
            position: absolute;
            left: 1rem;
            top: 50%;
            transform: translateY(-50%);
            color: #8b4513;
            font-size: 1.1rem;
            z-index: 2;
        }

        .form-group input {
            width: 100%;
            padding: 1rem 1rem 1rem 3rem;
            border: 2px solid #e0e0e0;
            border-radius: 12px;
            font-size: 1rem;
            font-family: inherit;
            background: rgba(255, 255, 255, 0.9);
            transition: all 0.3s ease;
            outline: none;
        }

        .form-group input:focus {
            border-color: #8b4513;
            box-shadow: 0 0 0 3px rgba(139, 69, 19, 0.1);
            background: rgba(255, 255, 255, 1);
            transform: translateY(-2px);
        }

        .form-group input::placeholder {
            color: #a0a0a0;
            font-style: italic;
        }

        .login-button {
            width: 100%;
            padding: 1.2rem;
            background: linear-gradient(135deg, #8b4513, #a0522d);
            color: white;
            border: none;
            border-radius: 12px;
            font-size: 1.1rem;
            font-weight: 600;
            cursor: pointer;
            transition: all 0.3s ease;
            text-transform: uppercase;
            letter-spacing: 1px;
            position: relative;
            overflow: hidden;
        }

        .login-button::before {
            content: '';
            position: absolute;
            top: 0;
            left: -100%;
            width: 100%;
            height: 100%;
            background: linear-gradient(90deg, transparent, rgba(255, 255, 255, 0.2), transparent);
            transition: left 0.5s ease;
        }

        .login-button:hover::before {
            left: 100%;
        }

        .login-button:hover {
            transform: translateY(-3px);
            box-shadow: 0 15px 30px rgba(139, 69, 19, 0.4);
        }

        .login-button:active {
            transform: translateY(0);
        }

        .decorative-elements {
            position: absolute;
            top: -10px;
            right: -10px;
            width: 60px;
            height: 60px;
            border-radius: 50%;
            background: linear-gradient(45deg, #8b4513, #a0522d);
            opacity: 0.1;
            animation: float 3s ease-in-out infinite;
        }

        .decorative-elements::before {
            content: '';
            position: absolute;
            top: 20px;
            left: -20px;
            width: 40px;
            height: 40px;
            border-radius: 50%;
            background: linear-gradient(45deg, #6b4c3a, #8b4513);
            opacity: 0.8;
            animation: float 2s ease-in-out infinite reverse;
        }

        @keyframes float {
            0%, 100% { transform: translateY(0px); }
            50% { transform: translateY(-10px); }
        }

        .footer-text {
            text-align: center;
            margin-top: 2rem;
            color: #6b4c3a;
            font-size: 0.8rem;
            font-style: italic;
        }

        @media (max-width: 480px) {
            .login-container {
                margin: 1rem;
                padding: 2rem 1.5rem;
            }
            
            .login-title {
                font-size: 1.5rem;
            }
            
            .library-icon {
                font-size: 2.5rem;
            }
        }
    </style>
</head>
<body>
    <div class="login-container">
        <div class="decorative-elements"></div>
        
        <div class="login-header">
            <i class="fas fa-book-open library-icon"></i>
            <h1 class="login-title">Bibliotheque</h1>
            <p class="login-subtitle">Acces aux collections</p>
        </div>

        <% if (request.getAttribute("loginError") != null) { %>
            <div class="error-message">
                <i class="fas fa-exclamation-circle"></i>
                <%= request.getAttribute("loginError") %>
            </div>
        <% } %>

        <form action="/bibliotec/login" method="post">
            <div class="form-group">
                <label for="nom">Nom d'utilisateur</label>
                <div class="input-wrapper">
                    <i class="fas fa-user"></i>
                    <input type="text" id="nom" name="nom" required 
                           title="Entrez votre nom" 
                           placeholder="Votre nom d'utilisateur" />
                </div>
            </div>

            <div class="form-group">
                <label for="mot_de_passe">Mot de passe</label>
                <div class="input-wrapper">
                    <i class="fas fa-lock"></i>
                    <input type="password" id="mot_de_passe" name="mot_de_passe" required 
                           title="Entrez votre mot de passe" 
                           placeholder="Votre mot de passe" />
                </div>
            </div>

            <button type="submit" class="login-button">
                <i class="fas fa-sign-in-alt"></i> Se connecter
            </button>
        </form>

        <p class="footer-text">
            "Un livre est un reve que vous tenez dans vos mains"
        </p>
    </div>
</body>
</html>
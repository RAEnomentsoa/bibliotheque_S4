<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html lang="fr">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>${employe.id == null ? 'Ajouter' : 'Modifier'} Employé</title>
    <style>
        * {
            margin: 0;
            padding: 0;
            box-sizing: border-box;
        }

        body {
            font-family: 'Segoe UI', Tahoma, Geneva, Verdana, sans-serif;
            background: linear-gradient(135deg, #667eea 0%, #764ba2 100%);
            min-height: 100vh;
            position: relative;
            overflow-x: hidden;
        }

        /* Effet de neige en arrière-plan */
        body::before {
            content: '';
            position: fixed;
            top: 0;
            left: 0;
            width: 100%;
            height: 100%;
            background-image: 
                radial-gradient(2px 2px at 20px 30px, rgba(255,255,255,0.9), transparent),
                radial-gradient(2px 2px at 40px 70px, rgba(255,255,255,0.8), transparent),
                radial-gradient(1px 1px at 90px 40px, rgba(255,255,255,0.7), transparent),
                radial-gradient(1px 1px at 130px 80px, rgba(255,255,255,0.6), transparent),
                radial-gradient(2px 2px at 160px 30px, rgba(255,255,255,0.8), transparent);
            background-repeat: repeat;
            background-size: 200px 100px;
            animation: snowfall 20s linear infinite;
            pointer-events: none;
            z-index: 1;
        }

        @keyframes snowfall {
            0% { transform: translateY(-100px); }
            100% { transform: translateY(100vh); }
        }

        .container {
            max-width: 600px;
            margin: 50px auto;
            position: relative;
            z-index: 2;
        }

        .form-card {
            background: rgba(255, 255, 255, 0.95);
            backdrop-filter: blur(20px);
            border-radius: 20px;
            padding: 40px;
            box-shadow: 
                0 20px 40px rgba(0, 0, 0, 0.1),
                0 0 0 1px rgba(255, 255, 255, 0.2);
            border: 1px solid rgba(255, 255, 255, 0.3);
        }

        h1 {
            text-align: center;
            color: #2c3e50;
            margin-bottom: 30px;
            font-size: 2rem;
            font-weight: 300;
            letter-spacing: 1px;
            position: relative;
        }

        h1::after {
            content: '';
            position: absolute;
            bottom: -10px;
            left: 50%;
            transform: translateX(-50%);
            width: 60px;
            height: 3px;
            background: linear-gradient(90deg, #74b9ff, #0984e3);
            border-radius: 2px;
        }

        .form-group {
            margin-bottom: 25px;
            position: relative;
        }

        label {
            display: block;
            margin-bottom: 8px;
            color: #2c3e50;
            font-weight: 500;
            font-size: 0.95rem;
            letter-spacing: 0.5px;
        }

        input[type="text"],
        select {
            width: 100%;
            padding: 12px 16px;
            border: 2px solid #e3f2fd;
            border-radius: 12px;
            font-size: 1rem;
            transition: all 0.3s ease;
            background: rgba(255, 255, 255, 0.9);
            color: #2c3e50;
        }

        input[type="text"]:focus,
        select:focus {
            outline: none;
            border-color: #74b9ff;
            box-shadow: 0 0 0 3px rgba(116, 185, 255, 0.1);
            transform: translateY(-2px);
        }

        select {
            cursor: pointer;
            appearance: none;
            background-image: url("data:image/svg+xml,%3csvg xmlns='http://www.w3.org/2000/svg' fill='none' viewBox='0 0 20 20'%3e%3cpath stroke='%236b7280' stroke-linecap='round' stroke-linejoin='round' stroke-width='1.5' d='m6 8 4 4 4-4'/%3e%3c/svg%3e");
            background-position: right 12px center;
            background-repeat: no-repeat;
            background-size: 16px;
        }

        .error {
            color: #e74c3c;
            font-size: 0.85rem;
            margin-top: 5px;
            display: block;
            font-weight: 500;
        }

        .button-group {
            display: flex;
            gap: 15px;
            margin-top: 30px;
            justify-content: center;
        }

        button {
            background: linear-gradient(135deg, #74b9ff 0%, #0984e3 100%);
            color: white;
            border: none;
            padding: 12px 30px;
            border-radius: 12px;
            font-size: 1rem;
            font-weight: 500;
            cursor: pointer;
            transition: all 0.3s ease;
            letter-spacing: 0.5px;
            min-width: 140px;
        }

        button:hover {
            transform: translateY(-2px);
            box-shadow: 0 10px 25px rgba(116, 185, 255, 0.3);
        }

        button:active {
            transform: translateY(0);
        }

        .back-link {
            display: inline-block;
            color: #74b9ff;
            text-decoration: none;
            font-weight: 500;
            padding: 12px 30px;
            border: 2px solid #74b9ff;
            border-radius: 12px;
            transition: all 0.3s ease;
            text-align: center;
            min-width: 140px;
        }

        .back-link:hover {
            background: #74b9ff;
            color: white;
            transform: translateY(-2px);
            box-shadow: 0 10px 25px rgba(116, 185, 255, 0.3);
        }

        /* Effet de cristaux de glace */
        .form-card::before {
            content: '';
            position: absolute;
            top: -2px;
            left: -2px;
            right: -2px;
            bottom: -2px;
            background: linear-gradient(45deg, 
                rgba(116, 185, 255, 0.1) 0%, 
                rgba(255, 255, 255, 0.2) 25%,
                rgba(116, 185, 255, 0.1) 50%,
                rgba(255, 255, 255, 0.2) 75%,
                rgba(116, 185, 255, 0.1) 100%);
            border-radius: 22px;
            z-index: -1;
            animation: shimmer 3s ease-in-out infinite alternate;
        }

        @keyframes shimmer {
            0% { opacity: 0.5; }
            100% { opacity: 1; }
        }

        /* Responsive */
        @media (max-width: 768px) {
            .container {
                margin: 20px;
                max-width: none;
            }
            
            .form-card {
                padding: 25px;
            }
            
            h1 {
                font-size: 1.5rem;
            }
            
            .button-group {
                flex-direction: column;
                align-items: center;
            }
            
            button,
            .back-link {
                width: 100%;
                max-width: 200px;
            }
        }

        /* Effet de focus amélioré */
        .form-group {
            position: relative;
        }

        .form-group::after {
            content: '';
            position: absolute;
            bottom: -5px;
            left: 0;
            width: 0;
            height: 2px;
            background: linear-gradient(90deg, #74b9ff, #0984e3);
            transition: width 0.3s ease;
        }

        .form-group:focus-within::after {
            width: 100%;
        }
    </style>
</head>
<body>
    <div class="container">
        <div class="form-card">
            <h1>${employe.id == null ? 'Ajouter' : 'Modifier'} Employé</h1>
            
            <form:form modelAttribute="employe" method="post" action="${pageContext.request.contextPath}/employes">
                <form:hidden path="id"/>
                
                <div class="form-group">
                    <form:label path="nom">Nom :</form:label>
                    <form:input path="nom" required="true" cssClass="form-control"/>
                    <form:errors path="nom" cssClass="error"/>
                </div>
                
                <div class="form-group">
                    <form:label path="prenom">Prénom :</form:label>
                    <form:input path="prenom" required="true" cssClass="form-control"/>
                    <form:errors path="prenom" cssClass="error"/>
                </div>
                
                <div class="form-group">
                    <form:label path="poste">Poste :</form:label>
                    <form:input path="poste" required="true" cssClass="form-control"/>
                    <form:errors path="poste" cssClass="error"/>
                </div>
                
                <div class="form-group">
                    <form:label path="departement">Département :</form:label>
                    <form:select path="departement.id" required="true" cssClass="form-control">
                        <form:option value="">-- Sélectionnez un département --</form:option>
                        <form:options items="${departements}" itemValue="id" itemLabel="nom"/>
                    </form:select>
                    <form:errors path="departement" cssClass="error"/>
                </div>
                
                <div class="button-group">
                    <button type="submit">❄️ Enregistrer</button>
                    <a href="${pageContext.request.contextPath}/employes" class="back-link">🏠 Retour à la liste</a>
                </div>
            </form:form>
        </div>
    </div>

    <script>
        // Animation d'entrée pour les champs de formulaire
        document.addEventListener('DOMContentLoaded', function() {
            const formGroups = document.querySelectorAll('.form-group');
            
            formGroups.forEach((group, index) => {
                group.style.opacity = '0';
                group.style.transform = 'translateY(20px)';
                
                setTimeout(() => {
                    group.style.transition = 'all 0.6s ease';
                    group.style.opacity = '1';
                    group.style.transform = 'translateY(0)';
                }, index * 100);
            });
        });

        // Validation en temps réel
        const inputs = document.querySelectorAll('input[required], select[required]');
        
        inputs.forEach(input => {
            input.addEventListener('blur', function() {
                const errorSpan = this.parentNode.querySelector('.error');
                
                if (this.value.trim() === '') {
                    if (!errorSpan.textContent) { // Ne pas écraser les messages de validation Spring
                        errorSpan.textContent = 'Ce champ est obligatoire';
                    }
                    this.style.borderColor = '#e74c3c';
                } else {
                    if (errorSpan.textContent === 'Ce champ est obligatoire') {
                        errorSpan.textContent = '';
                    }
                    this.style.borderColor = '#74b9ff';
                }
            });
            
            input.addEventListener('input', function() {
                if (this.value.trim() !== '') {
                    const errorSpan = this.parentNode.querySelector('.error');
                    if (errorSpan.textContent === 'Ce champ est obligatoire') {
                        errorSpan.textContent = '';
                    }
                    this.style.borderColor = '#74b9ff';
                }
            });
        });
    </script>
</body>
</html>
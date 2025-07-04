<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<html>
<head>
    <title>📊 Tableau de Bord - Bibliothèque</title>
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
            padding: 2rem;
            position: relative;
            overflow-x: hidden;
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
            z-index: -1;
        }

        .header {
            text-align: center;
            margin-bottom: 3rem;
            animation: fadeInDown 0.8s ease-out;
        }

        .header h1 {
            font-size: 2.5rem;
            color: #fff;
            margin-bottom: 0.5rem;
            font-weight: 300;
            letter-spacing: 2px;
            text-shadow: 0 4px 8px rgba(0, 0, 0, 0.3);
        }

        .header .subtitle {
            color: rgba(255, 255, 255, 0.8);
            font-size: 1.1rem;
            font-style: italic;
            margin-bottom: 1rem;
        }

        .header-icon {
            font-size: 3rem;
            color: #d4af37;
            margin-bottom: 1rem;
            display: block;
            text-shadow: 0 2px 4px rgba(0, 0, 0, 0.3);
        }

        @keyframes fadeInDown {
            from {
                opacity: 0;
                transform: translateY(-30px);
            }
            to {
                opacity: 1;
                transform: translateY(0);
            }
        }

        .dashboard {
            display: grid;
            grid-template-columns: repeat(auto-fit, minmax(280px, 1fr));
            gap: 2rem;
            max-width: 1200px;
            margin: 0 auto;
        }

        .card {
            background: rgba(255, 255, 255, 0.95);
            backdrop-filter: blur(10px);
            border-radius: 20px;
            padding: 2rem;
            box-shadow: 
                0 20px 40px rgba(0, 0, 0, 0.2),
                0 0 0 1px rgba(255, 255, 255, 0.1),
                inset 0 1px 0 rgba(255, 255, 255, 0.8);
            text-align: center;
            position: relative;
            overflow: hidden;
            transition: all 0.3s ease;
            animation: fadeInUp 0.8s ease-out;
            animation-fill-mode: both;
        }

        .card:nth-child(1) { animation-delay: 0.1s; }
        .card:nth-child(2) { animation-delay: 0.2s; }
        .card:nth-child(3) { animation-delay: 0.3s; }
        .card:nth-child(4) { animation-delay: 0.4s; }
        .card:nth-child(5) { animation-delay: 0.5s; }
        .card:nth-child(6) { animation-delay: 0.6s; }
        .card:nth-child(7) { animation-delay: 0.7s; }
        .card:nth-child(8) { animation-delay: 0.8s; }

        @keyframes fadeInUp {
            from {
                opacity: 0;
                transform: translateY(30px);
            }
            to {
                opacity: 1;
                transform: translateY(0);
            }
        }

        .card::before {
            content: '';
            position: absolute;
            top: 0;
            left: 0;
            right: 0;
            height: 4px;
            background: linear-gradient(90deg, #8b4513, #d4af37, #8b4513);
            border-radius: 20px 20px 0 0;
        }

        .card:hover {
            transform: translateY(-10px);
            box-shadow: 
                0 30px 60px rgba(0, 0, 0, 0.25),
                0 0 0 1px rgba(255, 255, 255, 0.2),
                inset 0 1px 0 rgba(255, 255, 255, 0.9);
        }

        .card-icon {
            font-size: 2.5rem;
            margin-bottom: 1rem;
            display: block;
            text-shadow: 0 2px 4px rgba(0, 0, 0, 0.1);
        }

        .card:nth-child(1) .card-icon { color: #8b4513; } /* Livres */
        .card:nth-child(2) .card-icon { color: #d4af37; } /* Exemplaires */
        .card:nth-child(3) .card-icon { color: #228b22; } /* Adhérents */
        .card:nth-child(4) .card-icon { color: #4169e1; } /* Prêts */
        .card:nth-child(5) .card-icon { color: #ff8c00; } /* Prolongements */
        .card:nth-child(6) .card-icon { color: #9370db; } /* Réservations */
        .card:nth-child(7) .card-icon { color: #dc143c; } /* Pénalités */
        .card:nth-child(8) .card-icon { color: #20b2aa; } /* Jours Fériés */

        .card h2 {
            font-size: 1.3rem;
            color: #2c1810;
            margin-bottom: 1rem;
            font-weight: 500;
            text-transform: uppercase;
            letter-spacing: 1px;
        }

        .card p {
            font-size: 3rem;
            margin: 0;
            font-weight: 300;
            color: #2c1810;
            text-shadow: 0 2px 4px rgba(0, 0, 0, 0.1);
            position: relative;
        }

        .card p::after {
            content: '';
            position: absolute;
            bottom: -10px;
            left: 50%;
            transform: translateX(-50%);
            width: 50px;
            height: 2px;
            background: linear-gradient(90deg, #8b4513, #d4af37);
            border-radius: 2px;
        }

        .floating-elements {
            position: absolute;
            top: 20%;
            right: 10%;
            width: 100px;
            height: 100px;
            opacity: 0.05;
            animation: float 6s ease-in-out infinite;
        }

        .floating-elements::before {
            content: '📚';
            font-size: 4rem;
            position: absolute;
            top: 0;
            left: 0;
        }

        .floating-elements::after {
            content: '📖';
            font-size: 3rem;
            position: absolute;
            top: 60px;
            left: 80px;
            animation: float 4s ease-in-out infinite reverse;
        }

        @keyframes float {
            0%, 100% { transform: translateY(0px) rotate(0deg); }
            50% { transform: translateY(-20px) rotate(10deg); }
        }

        .stats-summary {
            background: rgba(255, 255, 255, 0.1);
            backdrop-filter: blur(10px);
            border-radius: 20px;
            padding: 2rem;
            margin-top: 3rem;
            text-align: center;
            border: 1px solid rgba(255, 255, 255, 0.1);
        }

        .stats-summary h3 {
            color: #fff;
            font-size: 1.5rem;
            margin-bottom: 1rem;
            font-weight: 300;
        }

        .stats-summary p {
            color: rgba(255, 255, 255, 0.8);
            font-style: italic;
            font-size: 1.1rem;
        }

        @media (max-width: 768px) {
            body {
                padding: 1rem;
            }
            
            .header h1 {
                font-size: 2rem;
            }
            
            .dashboard {
                grid-template-columns: 1fr;
                gap: 1.5rem;
            }
            
            .card {
                padding: 1.5rem;
            }
            
            .card p {
                font-size: 2.5rem;
            }
        }

        @media (max-width: 480px) {
            .header h1 {
                font-size: 1.5rem;
            }
            
            .card-icon {
                font-size: 2rem;
            }
            
            .card p {
                font-size: 2rem;
            }
        }
    </style>
</head>
<body>
    <div class="floating-elements"></div>
    
    <div class="header">
        <i class="fas fa-chart-line header-icon"></i>
        <h1>Tableau de Bord</h1>
        <p class="subtitle">Gestion de Bibliotheque</p>
    </div>

    <div class="dashboard">
        <div class="card">
            <i class="fas fa-book card-icon"></i>
            <h2>Livres</h2>
            <p>${livres}</p>
        </div>
        <div class="card">
            <i class="fas fa-copy card-icon"></i>
            <h2>Exemplaires</h2>
            <p>${exemplaires}</p>
        </div>
        <div class="card">
            <i class="fas fa-users card-icon"></i>
            <h2>Adhérents</h2>
            <p>${adherents}</p>
        </div>
        <div class="card">
            <i class="fas fa-hand-holding card-icon"></i>
            <h2>Prêts</h2>
            <p>${prets}</p>
        </div>
        <div class="card">
            <i class="fas fa-clock card-icon"></i>
            <h2>Prolongements</h2>
            <p>${prolongements}</p>
        </div>
        <div class="card">
            <i class="fas fa-bookmark card-icon"></i>
            <h2>Réservations</h2>
            <p>${reservations}</p>
        </div>
        <div class="card">
            <i class="fas fa-exclamation-triangle card-icon"></i>
            <h2>Pénalités</h2>
            <p>${penalites}</p>
        </div>
        <div class="card">
            <i class="fas fa-calendar-alt card-icon"></i>
            <h2>Jours Fériés</h2>
            <p>${joursFeries}</p>
        </div>
    </div>

    <div class="stats-summary">
        <h3>Activité de la Bibliotheque</h3>
        <p>"La connaissance est la seule richesse qui grandit quand on la partage"</p>
    </div>
</body>
</html>
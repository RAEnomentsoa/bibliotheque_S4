<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html lang="fr">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Liste des Employés</title>
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
            overflow-x: auto;
            padding: 20px;
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
            max-width: 1200px;
            margin: 0 auto;
            position: relative;
            z-index: 2;
        }

        .header-card {
            background: rgba(255, 255, 255, 0.95);
            backdrop-filter: blur(20px);
            border-radius: 20px;
            padding: 30px;
            margin-bottom: 30px;
            box-shadow: 
                0 20px 40px rgba(0, 0, 0, 0.1),
                0 0 0 1px rgba(255, 255, 255, 0.2);
            border: 1px solid rgba(255, 255, 255, 0.3);
            text-align: center;
            position: relative;
        }

        .header-card::before {
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

        h1 {
            color: #2c3e50;
            font-size: 2.5rem;
            font-weight: 300;
            letter-spacing: 2px;
            margin-bottom: 20px;
            position: relative;
        }

        h1::after {
            content: '❄️';
            position: absolute;
            right: -40px;
            top: 50%;
            transform: translateY(-50%);
            font-size: 1.5rem;
            animation: rotate 4s linear infinite;
        }

        @keyframes rotate {
            0% { transform: translateY(-50%) rotate(0deg); }
            100% { transform: translateY(-50%) rotate(360deg); }
        }

        .add-button {
            display: inline-block;
            background: linear-gradient(135deg, #74b9ff 0%, #0984e3 100%);
            color: white;
            text-decoration: none;
            padding: 12px 30px;
            border-radius: 25px;
            font-weight: 500;
            letter-spacing: 1px;
            transition: all 0.3s ease;
            box-shadow: 0 5px 15px rgba(116, 185, 255, 0.3);
        }

        .add-button:hover {
            transform: translateY(-3px);
            box-shadow: 0 10px 25px rgba(116, 185, 255, 0.4);
        }

        .add-button::before {
            content: '➕ ';
            margin-right: 8px;
        }

        .table-card {
            background: rgba(255, 255, 255, 0.95);
            backdrop-filter: blur(20px);
            border-radius: 20px;
            padding: 30px;
            box-shadow: 
                0 20px 40px rgba(0, 0, 0, 0.1),
                0 0 0 1px rgba(255, 255, 255, 0.2);
            border: 1px solid rgba(255, 255, 255, 0.3);
            position: relative;
            overflow: hidden;
        }

        .table-card::before {
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

        .table-container {
            overflow-x: auto;
            border-radius: 15px;
        }

        table {
            width: 100%;
            border-collapse: collapse;
            background: rgba(255, 255, 255, 0.9);
            border-radius: 15px;
            overflow: hidden;
            box-shadow: 0 10px 30px rgba(0, 0, 0, 0.1);
        }

        th {
            background: linear-gradient(135deg, #74b9ff 0%, #0984e3 100%);
            color: white;
            padding: 18px 15px;
            text-align: left;
            font-weight: 600;
            letter-spacing: 1px;
            text-transform: uppercase;
            font-size: 0.9rem;
            position: relative;
        }

        th::after {
            content: '';
            position: absolute;
            bottom: 0;
            left: 0;
            width: 100%;
            height: 2px;
            background: rgba(255, 255, 255, 0.3);
        }

        td {
            padding: 15px;
            border-bottom: 1px solid rgba(116, 185, 255, 0.1);
            color: #2c3e50;
            font-weight: 500;
            transition: all 0.3s ease;
        }

        tr:hover td {
            background: rgba(116, 185, 255, 0.05);
            transform: scale(1.01);
        }

        tr:last-child td {
            border-bottom: none;
        }

        .actions {
            display: flex;
            gap: 10px;
            flex-wrap: wrap;
        }

        .action-btn {
            padding: 8px 16px;
            border-radius: 20px;
            text-decoration: none;
            font-weight: 500;
            font-size: 0.85rem;
            transition: all 0.3s ease;
            display: inline-flex;
            align-items: center;
            gap: 5px;
            letter-spacing: 0.5px;
        }

        .edit-btn {
            background: linear-gradient(135deg, #00b894 0%, #00a085 100%);
            color: white;
            box-shadow: 0 3px 10px rgba(0, 184, 148, 0.3);
        }

        .edit-btn:hover {
            transform: translateY(-2px);
            box-shadow: 0 6px 20px rgba(0, 184, 148, 0.4);
        }

        .edit-btn::before {
            content: '✏️';
        }

        .delete-btn {
            background: linear-gradient(135deg, #e17055 0%, #d63031 100%);
            color: white;
            box-shadow: 0 3px 10px rgba(225, 112, 85, 0.3);
        }

        .delete-btn:hover {
            transform: translateY(-2px);
            box-shadow: 0 6px 20px rgba(225, 112, 85, 0.4);
        }

        .delete-btn::before {
            content: '🗑️';
        }

        .department-badge {
            background: linear-gradient(135deg, #a29bfe 0%, #6c5ce7 100%);
            color: white;
            padding: 5px 12px;
            border-radius: 15px;
            font-size: 0.8rem;
            font-weight: 600;
            letter-spacing: 0.5px;
            display: inline-block;
            box-shadow: 0 2px 8px rgba(108, 92, 231, 0.3);
        }

        tr {
            opacity: 0;
            animation: fadeInUp 0.6s ease forwards;
        }

        tr:nth-child(1) { animation-delay: 0.1s; }
        tr:nth-child(2) { animation-delay: 0.2s; }
        tr:nth-child(3) { animation-delay: 0.3s; }
        tr:nth-child(4) { animation-delay: 0.4s; }
        tr:nth-child(5) { animation-delay: 0.5s; }
        tr:nth-child(n+6) { animation-delay: 0.6s; }

        @keyframes fadeInUp {
            from {
                opacity: 0;
                transform: translateY(20px);
            }
            to {
                opacity: 1;
                transform: translateY(0);
            }
        }

        .empty-state {
            text-align: center;
            padding: 60px 20px;
            color: #74b9ff;
        }

        .empty-state::before {
            content: '🏔️';
            font-size: 4rem;
            display: block;
            margin-bottom: 20px;
        }

        .empty-state h3 {
            font-size: 1.5rem;
            margin-bottom: 10px;
            color: #2c3e50;
        }

        .empty-state p {
            font-size: 1.1rem;
            opacity: 0.8;
        }

        @media (max-width: 768px) {
            .container {
                padding: 10px;
            }
            
            h1 {
                font-size: 2rem;
            }
            
            h1::after {
                right: -30px;
                font-size: 1.2rem;
            }
            
            .header-card,
            .table-card {
                padding: 20px;
                margin-bottom: 20px;
            }
            
            table {
                font-size: 0.9rem;
            }
            
            th, td {
                padding: 12px 8px;
            }
            
            .actions {
                flex-direction: column;
                gap: 5px;
            }
            
            .action-btn {
                font-size: 0.8rem;
                padding: 6px 12px;
                justify-content: center;
            }
        }

        @media (max-width: 600px) {
            .table-container {
                font-size: 0.8rem;
            }
            
            th, td {
                padding: 10px 6px;
            }
            
            .department-badge {
                font-size: 0.7rem;
                padding: 3px 8px;
            }
        }

        .stats {
            display: flex;
            justify-content: center;
            gap: 30px;
            margin-top: 20px;
            flex-wrap: wrap;
        }

        .stat-item {
            text-align: center;
            color: #74b9ff;
        }

        .stat-number {
            font-size: 2rem;
            font-weight: bold;
            color: #2c3e50;
            display: block;
        }

        .stat-label {
            font-size: 0.9rem;
            text-transform: uppercase;
            letter-spacing: 1px;
            margin-top: 5px;
        }
    </style>
</head>
<body>
    <div class="container">
        <div class="header-card">
            <h1>Liste des Employés</h1>
            <a href="${pageContext.request.contextPath}/employes/new" class="add-button">Ajouter un Employé</a>
            <div class="stats">
                <div class="stat-item">
                    <span class="stat-number" id="totalEmployees">0</span>
                    <div class="stat-label">Employés</div>
                </div>
                <div class="stat-item">
                    <span class="stat-number" id="totalDepartments">0</span>
                    <div class="stat-label">Départements</div>
                </div>
            </div>
        </div>

        <div class="table-card">
            <div class="table-container">
                <table>
                    <thead>
                        <tr>
                            <th>ID</th>
                            <th>Nom</th>
                            <th>Prénom</th>
                            <th>Poste</th>
                            <th>Département</th>
                            <th>Actions</th>
                        </tr>
                    </thead>
                    <tbody id="employeeTableBody">
                        <c:choose>
                            <c:when test="${empty employes}">
                                <tr>
                                    <td colspan="6" class="empty-state">
                                        <h3>Aucun employé trouvé</h3>
                                        <p>Commencez par ajouter votre premier employé !</p>
                                    </td>
                                </tr>
                            </c:when>
                            <c:otherwise>
                                <c:forEach var="employe" items="${employes}">
                                    <tr>
                                        <td>${employe.id}</td>
                                        <td>${employe.nom}</td>
                                        <td>${employe.prenom}</td>
                                        <td>${employe.poste}</td>
                                        <td><span class="department-badge">${employe.departement.nom}</span></td>
                                        <td>
                                            <div class="actions">
                                                <a href="${pageContext.request.contextPath}/employes/edit/${employe.id}" class="action-btn edit-btn">Modifier</a>
                                                <a href="${pageContext.request.contextPath}/employes/delete/${employe.id}" class="action-btn delete-btn" onclick="return confirm('Êtes-vous sûr de vouloir supprimer cet employé ?')">Supprimer</a>
                                            </div>
                                        </td>
                                    </tr>
                                </c:forEach>
                            </c:otherwise>
                        </c:choose>
                    </tbody>
                </table>
            </div>
        </div>
    </div>

    <script>
        document.addEventListener('DOMContentLoaded', function() {
            const cards = document.querySelectorAll('.header-card, .table-card');
            
            cards.forEach((card, index) => {
                card.style.opacity = '0';
                card.style.transform = 'translateY(30px)';
                
                setTimeout(() => {
                    card.style.transition = 'all 0.8s ease';
                    card.style.opacity = '1';
                    card.style.transform = 'translateY(0)';
                }, index * 200);
            });

            // Mise à jour des compteurs
            const tbody = document.getElementById('employeeTableBody');
            const totalEmployees = document.getElementById('totalEmployees');
            const totalDepartments = document.getElementById('totalDepartments');
            if (tbody && totalEmployees && totalDepartments) {
                const employeeCount = tbody.querySelectorAll('tr:not(.empty-state)').length;
                totalEmployees.textContent = employeeCount;
                
                // Calculer les départements uniques
                const departments = new Set();
                tbody.querySelectorAll('.department-badge').forEach(badge => {
                    departments.add(badge.textContent);
                });
                totalDepartments.textContent = departments.size;
            }

            // Effet de hover pour les lignes
            document.querySelectorAll('tbody tr:not(.empty-state)').forEach(row => {
                row.addEventListener('mouseenter', function() {
                    this.style.transform = 'scale(1.02)';
                    this.style.zIndex = '10';
                });
                
                row.addEventListener('mouseleave', function() {
                    this.style.transform = 'scale(1)';
                    this.style.zIndex = '1';
                });
            });
        });
    </script>
</body>
</html>
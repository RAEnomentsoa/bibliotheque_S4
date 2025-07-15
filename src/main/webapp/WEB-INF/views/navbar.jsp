    <style>
        * {
            margin: 0;
            padding: 0;
            box-sizing: border-box;
        }

        body {
            font-family: 'Segoe UI', Tahoma, Geneva, Verdana, sans-serif;
            background: #f8fafc;
            min-height: 100vh;
            padding-top: 70px;
        }

        .navbar {
            position: fixed;
            top: 0;
            left: 0;
            right: 0;
            background: #1a1a1a;
            border-bottom: 1px solid #333;
            z-index: 1000;
            box-shadow: 0 2px 10px rgba(0, 0, 0, 0.1);
            margin-bottom: 200px;
            
        }

        .nav-container {
            max-width: 1200px;
            margin: 0 auto;
            padding: 0 2rem;
            display: flex;
            align-items: center;
            justify-content: space-between;
            height: 70px;
        }

        /* Logo */
        .logo {
            font-size: 1.5rem;
            font-weight: 700;
            color: #ffffff;
            text-decoration: none;
            display: flex;
            align-items: center;
            gap: 0.5rem;
        }

        .logo:hover {
            color: #60a5fa;
        }

        .logo-icon {
            font-size: 1.5rem;
        }

        /* Navigation Links */
        .nav-links {
            display: flex;
            list-style: none;
            gap: 1.5rem;
            align-items: center;
        }

        .nav-link {
            padding: 0.5rem 1rem;
            text-decoration: none;
            color: #ffffff;
            font-weight: 500;
            border-radius: 6px;
            transition: background-color 0.2s ease, color 0.2s ease;
        }

        .nav-link:hover {
            background: #374151;
            color: #60a5fa;
        }

        /* Dropdown Menu */
        .dropdown {
            position: relative;
        }

        .dropdown-content {
            position: absolute;
            top: 100%;
            left: 0;
            background: #2d2d2d;
            min-width: 180px;
            border-radius: 6px;
            box-shadow: 0 4px 12px rgba(0, 0, 0, 0.3);
            opacity: 0;
            visibility: hidden;
            transform: translateY(-5px);
            transition: opacity 0.2s ease, transform 0.2s ease;
            border: 1px solid #444;
        }

        .dropdown:hover .dropdown-content {
            opacity: 1;
            visibility: visible;
            transform: translateY(0);
        }

        .dropdown-link {
            display: block;
            padding: 0.75rem 1rem;
            color: #ffffff;
            text-decoration: none;
            transition: background-color 0.2s ease;
            border-bottom: 1px solid #444;
        }

        .dropdown-link:last-child {
            border-bottom: none;
        }

        .dropdown-link:hover {
            background: #374151;
            color: #60a5fa;
        }

        /* Mobile Menu Toggle */
        .mobile-toggle {
            display: none;
            flex-direction: column;
            cursor: pointer;
            padding: 0.5rem;
            border-radius: 4px;
        }

        .mobile-toggle:hover {
            background: #374151;
        }

        .hamburger {
            width: 24px;
            height: 3px;
            background: #ffffff;
            margin: 3px 0;
            transition: 0.2s;
            border-radius: 2px;
        }

        .mobile-toggle.active .hamburger:nth-child(1) {
            transform: rotate(45deg) translate(5px, 5px);
        }

        .mobile-toggle.active .hamburger:nth-child(2) {
            opacity: 0;
        }

        .mobile-toggle.active .hamburger:nth-child(3) {
            transform: rotate(-45deg) translate(7px, -6px);
        }

        /* Action Buttons */
        .nav-actions {
            display: flex;
            align-items: center;
            gap: 1rem;
        }

        .btn {
            padding: 0.5rem 1rem;
            border: none;
            border-radius: 6px;
            font-weight: 600;
            font-size: 0.9rem;
            cursor: pointer;
            transition: background-color 0.2s ease, transform 0.1s ease;
            text-decoration: none;
        }

        .btn-primary {
            background: #3b82f6;
            color: #ffffff;
        }

        .btn-primary:hover {
            background: #2563eb;
            transform: translateY(-1px);
        }

        .btn-secondary {
            background: transparent;
            color: #ffffff;
            border: 1px solid #ffffff;
        }

        .btn-secondary:hover {
            background: #ffffff;
            color: #1a1a1a;
        }

        /* Search Bar */
        .search-container {
            position: relative;
            margin-right: 1rem;
        }

        .search-input {
            background: #374151;
            border: 1px solid #4b5563;
            border-radius: 20px;
            padding: 0.4rem 1rem 0.4rem 2.2rem;
            color: #ffffff;
            width: 200px;
            transition: border-color 0.2s ease, width 0.2s ease;
        }

        .search-input:focus {
            outline: none;
            border-color: #60a5fa;
            width: 240px;
        }

        .search-input::placeholder {
            color: #9ca3af;
        }

        .search-icon {
            position: absolute;
            left: 0.7rem;
            top: 50%;
            transform: translateY(-50%);
            color: #9ca3af;
        }

        /* Notification Badge */
        .notification-badge {
            position: relative;
            cursor: pointer;
            padding: 0.5rem;
            border-radius: 50%;
            transition: background-color 0.2s ease;
        }

        .notification-badge:hover {
            background: #374151;
        }

        .notification-badge::after {
            content: '3';
            position: absolute;
            top: 0;
            right: 0;
            background: #ef4444;
            color: #ffffff;
            border-radius: 50%;
            width: 16px;
            height: 16px;
            font-size: 0.7rem;
            display: flex;
            align-items: center;
            justify-content: center;
            font-weight: 600;
        }

        /* Responsive Design */
        @media (max-width: 768px) {
            .nav-links {
                position: fixed;
                top: 70px;
                left: 0;
                right: 0;
                background: #2d2d2d;
                flex-direction: column;
                padding: 1rem 0;
                transform: translateX(-100%);
                transition: transform 0.2s ease;
                box-shadow: 0 4px 12px rgba(0, 0, 0, 0.3);
            }

            .nav-links.active {
                transform: translateX(0);
            }

            .nav-link {
                padding: 1rem 2rem;
                width: 100%;
                text-align: center;
            }

            .mobile-toggle {
                display: flex;
            }

            .search-container {
                display: none;
            }

            .nav-actions {
                display: none;
            }

            .dropdown-content {
                position: static;
                opacity: 1;
                visibility: visible;
                transform: none;
                box-shadow: none;
                background: #374151;
                border: none;
                margin-top: 0.5rem;
            }
        }

        /* Demo Content */
        .demo-content {
            padding: 3rem 2rem;
            max-width: 1200px;
            margin: 0 auto;
            text-align: center;
        }

        .demo-content h1 {
            font-size: 2.5rem;
            margin-bottom: 1rem;
            color: #1f2937;
        }

        .demo-content p {
            font-size: 1.1rem;
            color: #6b7280;
            line-height: 1.6;
            margin-bottom: 2rem;
        }

        .demo-section {
            padding: 2rem;
            margin: 2rem 0;
            background: #ffffff;
            border-radius: 8px;
            box-shadow: 0 2px 4px rgba(0, 0, 0, 0.1);
        }
    </style>
</head>
<body>
    <!-- Navigation Bar -->
    <nav class="navbar">
        <div class="nav-container">
            <!-- Logo -->
            <a href="#" class="logo">
                <span class="logo-icon"></span>
                <span>BIBLIO</span>
            </a>

            <!-- Navigation Links -->
            <ul class="nav-links" id="navLinks">
                <li><a href="#" class="nav-link"> Home</a></li>
                <li class="dropdown">
                    <a href="#" class="nav-link"> Client-biblio </a>
                    <div class="dropdown-content">
                        <a href="${pageContext.request.contextPath}/adherent/livres" class="dropdown-link"> liste livre</a>
                        <a href="#" class="dropdown-link"> Abonnement</a>
                        <a href="${pageContext.request.contextPath}/adherent/mes-prets" class="dropdown-link"> Mes prets</a>
    
                    </div>
                </li>
                <li><a href="#" class="nav-link"> Contact</a></li>
                <li><a href="#" class="nav-link"> About</a></li>
            </ul>

            <!-- Search Bar -->
            <div class="search-container">
                <span class="search-icon"></span>
                <input type="text" class="search-input" placeholder="Search...">
            </div>

            <!-- Action Buttons -->
            <div class="nav-actions">
                <!-- Notification Bell -->
                <div class="notification-badge">
                    <span style="font-size: 1.2rem;"></span>
                </div>
                
                <!-- Action Buttons -->
                <!-- <a href="#" class="btn btn-secondary">Login</a>
                <a href="#" class="btn btn-primary">Get Started</a>
            </div> -->

            <!-- Mobile Menu Toggle -->
            <div class="mobile-toggle" id="mobileToggle">
                <div class="hamburger"></div>
                <div class="hamburger"></div>
                <div class="hamburger"></div>
            </div>
        </div>
    </nav>

    <!-- Demo Content -->
    <!-- <div class="demo-content">
        <h1>Optimized Beautiful Navbar</h1>
        <p>This navbar is optimized for weak PCs while maintaining a beautiful, modern design. All heavy animations and complex effects have been removed for better performance.</p>
        
        <div class="demo-section">
            <h2>Performance Optimizations</h2>
            <p>✅ Removed complex gradients and animations<br>
            ✅ Simplified transitions (0.2s instead of 0.3s+)<br>
            ✅ Eliminated backdrop-filter and heavy shadows<br>
            ✅ Reduced keyframe animations<br>
            ✅ Optimized hover effects</p>
        </div>

        <div class="demo-section">
            <h2>Features Retained</h2>
            <p>✅ Responsive design<br>
            ✅ Dropdown menus<br>
            ✅ Search functionality<br>
            ✅ Mobile hamburger menu<br>
            ✅ Clean, professional appearance</p>
        </div>
    </div> -->

    <script>
        // Mobile menu toggle
        const mobileToggle = document.getElementById('mobileToggle');
        const navLinks = document.getElementById('navLinks');

        mobileToggle.addEventListener('click', () => {
            mobileToggle.classList.toggle('active');
            navLinks.classList.toggle('active');
        });

        // Close mobile menu when clicking outside
        document.addEventListener('click', (e) => {
            if (!e.target.closest('.navbar')) {
                mobileToggle.classList.remove('active');
                navLinks.classList.remove('active');
            }
        });
    </script>

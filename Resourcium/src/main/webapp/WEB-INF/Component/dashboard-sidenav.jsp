


<div class="navigation">
    <ul>
        <li>
            <a href="#">
                    <span class="icon">
                        <ion-icon name="car-sport-outline"></ion-icon>
                    </span>
                <span class="title">HR MANAGEMENT</span>
            </a>
        </li>

        <li>
            <a href="${pageContext.request.contextPath}/dashboard">
                    <span class="icon">
                        <ion-icon name="home-outline"></ion-icon>
                    </span>
                <span class="title">Dashboard</span>
            </a>
        </li>

        <li>
            <a href="">
                    <span class="icon">
                        <ion-icon name="card-outline"></ion-icon>
                    </span>
                <span class="title">Tasks</span>
            </a>
        </li>
        <li>
            <a href="">
                    <span class="icon">
                        <ion-icon name="folder-outline"></ion-icon>
                    </span>
                <span class="title">Equipement</span>
            </a>
        </li>

        <li>
            <a href="${pageContext.request.contextPath}/profile">
                    <span class="icon">
                        <ion-icon name="settings-outline"></ion-icon>
                    </span>
                <span class="title">Profile</span>
            </a>
        </li>

        <li>
            <a href="">
                    <span class="icon">
                        <ion-icon name="log-out-outline"></ion-icon>
                    </span>
                <span class="title">Sign Out</span>
            </a>
        </li>
    </ul>
</div>

<!-- ========================= Main ==================== -->
<div class="main">
    <div class="topbar">
        <div class="toggle">
            <ion-icon name="menu-outline"></ion-icon>
        </div>


        <a class="badge text-bg-primary fs-6 text-decoration-none" href="/">
            <span>${sessionScope.fullName}</span>
        </a>


    </div>

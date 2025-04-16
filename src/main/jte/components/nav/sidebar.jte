@param String activeTab
<section class="sidebar">
    <nav>
        <a href="/" class="logo">
            <img style="width: 2rem; height: 2rem;" src="https://unpkg.com/lucide-static@latest/icons/chart-no-axes-column-increasing.svg" />
            <div>
                <p>Progressive</p>
                <p>Overlords</p>
            </div>
        </a>
        <a class="${"feed".equals(activeTab) ? "active" : ""}" href="/">
            <img src="../icons/dashboard.svg" />
            Feed
        </a>
        <a class="${"routines".equals(activeTab) ? "active" : ""}" href="/routines">
            <img src="../icons/dumbell.svg" />
            Routines
        </a>
        <a class="${"records".equals(activeTab) ? "active" : ""}" href="/stats">
            <img src="../icons/chart-column.svg" />
            Records
        </a>
        <a class="${"compare".equals(activeTab) ? "active" : ""}" href="/compare">
            <img src="../icons/users.svg" />
            Compare
        </a>
    </nav>
    <div class="logout-section">
        <div class="flex-row align-center gap-05">
            @template.components.friends.user-logo(username = "perepalacin")
            <p>Username</p>
        </div>
        <button hx-post="/api/v1/auth/logout" hx-swap="none" class="ghost-button-icon">
            <img src="https://unpkg.com/lucide-static@latest/icons/log-out.svg" style="width: 1.2rem" />
        </button>
    </div>
</section>
<div class="sidebar-filler">
</div>
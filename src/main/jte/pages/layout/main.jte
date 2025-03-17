@param gg.jte.Content content
@param String tabName
<!DOCTYPE html>
<html lang="cat">
<head>
    <meta charset="UTF-8" name="htmx-config" content='{"responseHandling": [{"code":".*", "swap": true}]}'>
    <link rel="icon" href="data:image/svg+xml,<svg xmlns=%22http://www.w3.org/2000/svg%22 viewBox=%220 0 100 100%22><text y=%22.9em%22 font-size=%2290%22>🎓</text></svg>">
    <title>${tabName} - Progressive Overlords</title>
    <script src="https://unpkg.com/htmx.org@2.0.3"
            integrity="sha384-0895/pl2MU10Hqc6jd4RvrthNlDiE9U1tWmX7WRESftEDRosgxNsQG/Ze9YMRzHq"
            crossorigin="anonymous"></script>
    <script src="https://unpkg.com/htmx-ext-response-targets@2.0.0/response-targets.js"></script>
    <script src="https://cdn.tailwindcss.com"></script>
    <link href="../styles/index.css" rel="stylesheet">
</head>
<body>
<main class="w-full h-screen flex flex-row justify-between">
    <nav class="flex flex-col w-1/5 justify-between items-start border-r px-2 py-2 ">
        <div class="flex flex-col gap-2">
            <a href="/" class="flex flex-row gap-1 mb-2">
                <img src="https://unpkg.com/lucide-static@latest/icons/chart-no-axes-column-increasing.svg" class="font-bold"/>
                <p class="text-xl font-semibold">Progressive Overlords</p>
            </a>
            <a href="/">Feed</a>
            <a href="/templates">Templates</a>
        </div>
        <div class="w-full flex flex-row justify-between">
            <div>
                <img src=""/>
                <p>Username</p>
            </div>
            <button hx-post="/api/v1/auth/logout" hx-swap="none">
                <img src="https://unpkg.com/lucide-static@latest/icons/log-out.svg" class="w-2/3"/>
            </button>
        </div>
    </nav>
    <section class="py-2">
        ${content}
    </section>
    <aside class="flex flex-col w-1/5">
        <p>Side section!</p>
    </aside>
    <div id="toastContainer" class="fixed top-5 right-5 flex flex-col gap-2 z-50"></div>
</main>
</body>
<script type="module" src="../scripts/dropdown.js"></script>
<script type="module" src="../scripts/toasts.js"></script>
</html>
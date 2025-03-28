@param gg.jte.Content content
@param String tabName
<!DOCTYPE html>
<html lang="cat">
<head>
    <meta charset="UTF-8" name="htmx-config" content='{"responseHandling": [{"code":".*", "swap": true}]}'>
    <link rel="icon" href="https://www.notion.so/icons/gym_red.svg?mode=light">
    <title>${tabName} - Progressive Overlords</title>
    <script src="https://unpkg.com/htmx.org@2.0.3"
            integrity="sha384-0895/pl2MU10Hqc6jd4RvrthNlDiE9U1tWmX7WRESftEDRosgxNsQG/Ze9YMRzHq"
            crossorigin="anonymous"></script>
    <script src="https://unpkg.com/htmx-ext-response-targets@2.0.0/response-targets.js"></script>
    <link href="../styles/styles.css" rel="stylesheet">
</head>
<body>
    <main>
        ${content}
    </main>
    <div id="toastContainer"></div>
</body>
<script type="module" src="../scripts/toasts.js"></script>
</html>
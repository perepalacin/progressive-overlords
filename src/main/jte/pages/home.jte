

<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Login</title>
    <script src="https://unpkg.com/htmx.org@1.9.10"></script>
    <script src="https://cdn.tailwindcss.com"></script>
</head>
<body class="flex items-center justify-center h-screen bg-gray-100">

<p>Welcome to the home page of progressive overlords</p>
<button
        hx-post="/api/v1/auth/logout"
        hx-swap="none"
        class="bg-red-600 text-white px-4 py-2 rounded-lg hover:bg-red-700">
    Logout
</button>

</body>
</html>

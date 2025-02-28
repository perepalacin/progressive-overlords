<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Sign Up</title>
    <script src="https://unpkg.com/htmx.org@1.9.10"></script>
    <script src="https://cdn.tailwindcss.com"></script>
</head>
<body class="flex items-center justify-center h-screen bg-gray-100">

<div class="bg-white p-6 rounded-lg shadow-lg w-96">
    <h2 class="text-2xl font-bold text-center mb-4">Sign Up</h2>

    <form hx-post="/api/v1/auth/sign-up"
          hx-target="#error-message"
          hx-swap="innerHTML"
          class="space-y-4">

        <div>
            <label class="block text-gray-700">Username</label>
            <input type="text" name="username" required
                   class="w-full px-3 py-2 border rounded-lg focus:ring focus:ring-indigo-200">
        </div>

        <div>
            <label class="block text-gray-700">Password</label>
            <input type="password" name="password" required
                   class="w-full px-3 py-2 border rounded-lg focus:ring focus:ring-indigo-200">
        </div>

        <button type="submit"
                class="w-full bg-indigo-600 text-white py-2 rounded-lg hover:bg-indigo-700">
            Sign Up
        </button>
    </form>

    <div id="error-message" class="text-red-600 text-center mt-3"></div>
</div>

</body>
</html>

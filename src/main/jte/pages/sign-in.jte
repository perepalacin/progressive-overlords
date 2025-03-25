<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Login</title>
    <link rel="stylesheet" href="../styles/styles.css">
    <script src="https://unpkg.com/htmx.org@1.9.10"></script>
</head>
<body >


<div class="login-container">
    <div class="login-card">
        <div class="logo">
            <h1>Progressive Overlords</h1>
        </div>

        <h2 class="login-title">Welcome Back</h2>
        <p class="login-subtitle">Sign in to continue your fitness journey</p>

        <form hx-post="/api/v1/auth/sign-in"
              hx-target="#error-message"
              hx-swap="innerHTML"
              class="login-form"
        >
            <div class="form-group">
                <label for="email">Email</label>
                <input type="username" id="username" name="username" placeholder="username" required>
            </div>

            <div class="form-group">
                <label for="password">Password</label>
                <input type="password" id="password" name="password" placeholder="Enter your password" required>
            </div>

            <button type="submit" class="login-button">Sign In</button>

        </form>

        <div class="signup-link">
            <p>Don't have an account yet? <a href="/register">Sign Up</a></p>
        </div>
    </div>
</div>

</body>
</html>

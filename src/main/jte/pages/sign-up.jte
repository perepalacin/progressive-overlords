@template.main(tabName = "Sign up",content = @`
    <div class="w-full flex-column align-center justify-center mt-6 text-center gap-2 ">
        <div class="flex-row gap-1 align-center logo">
            <img style="width: 4rem; height: 4rem;" src="https://unpkg.com/lucide-static@latest/icons/chart-no-axes-column-increasing.svg" />
            <h1 class="m-0">Progressive Overlords</h1>
        </div>
        <h2 class="">Welcome!</h2>
        <p class="">Sign up to start your fitness journey</p>

        <form hx-post="/api/v1/auth/sign-up"
              hx-target="#error-message"
              hx-swap="innerHTML"
              class="flex-column gap-2 justify-center">

            <div class="flex-row gap-2 align-center">
                <label class="auth-form-label" for="email">Email</label>
                <input type="username" id="username" name="username" placeholder="username" required>
            </div>

            <div class="flex-row gap-2 align-center">
                <label class="auth-form-label" for="password">Password</label>
                <input type="password" id="password" name="password" placeholder="Enter your password" required>
            </div>

            <button type="submit" class="button main-button">Sign Up</button>
            <div class="signup-link">
                <p>You already have an account? <a class="font-bold" style="text-decoration: underline" href="/sign-in">Sign In</a></p>
            </div>
        </form>

        <div id="error-message" class=""></div>
    </div>
`)


@template.main(tabName = "Sign in",content = @`
    <div class="w-full flex-column align-center justify-center mt-6 text-center gap-2">
            <div class="flex-row gap-1 align-center logo">
                <img style="width: 4rem; height: 4rem;" src="https://unpkg.com/lucide-static@latest/icons/chart-no-axes-column-increasing.svg" />
                <h1 class="m-0">Progressive Overlords</h1>
            </div>
            <h2 class="">Welcome Back</h2>
            <p class="">Sign in to continue your fitness journey</p>

            <form hx-post="/api/v1/auth/sign-in"
                  hx-target="#error-message"
                  hx-swap="innerHTML"
                  class="flex-column gap-2 justify-center"
            >
                <div class="flex-row gap-2 align-center">
                    <label class="auth-form-label" for="email">Email</label>
                    <input type="username" id="username" name="username" placeholder="username" required>
                </div>

                <div class="flex-row gap-2 align-center">
                    <label class="auth-form-label" for="password">Password</label>
                    <input type="password" id="password" name="password" placeholder="Enter your password" required>
                </div>

                <button type="submit" class="button main-button">Sign In</button>

            </form>

            <div class="signup-link">
                <p>Don't have an account yet? <a class="font-bold" style="text-decoration: underline" href="/sign-up">Sign Up</a></p>
            </div>
            <div id="error-message" class="text-red-600 text-center mt-3"></div>
    </div>
`)


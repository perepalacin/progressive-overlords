# 🏋️‍♂️ Progressive Overlords - Fitness Tracker

This is a full-featured fitness tracker application built using **Spring Boot**. The UI is server-side rendered using **JTE** (Java Templating Engine) complemented by **HTMX** for enhanced interactivity. The project focuses on a seamless, dynamic user experience without extensive use of JavaScript.

## 📦 Technologies

- `Spring Boot`
- `JTE`
- `HTMX`
- `JavaScript`
- `Spring Security`
- `JDBC Template`
- `PostgreSQL`

## 🚄 Features

The Fitness Tracker provides several features:

- **Dynamic UI with Minimal JavaScript**: Using JTE and HTMX, the app provides a responsive, interactive experience while minimizing JavaScript.
- **User Authentication**: Utilizes Spring Security with HTTP cookies for secure user sessions.
- **Social Features**: Users can follow others and view shared workouts.
- **Workout Routines**: Users can create, edit, and manage workout routines with exercise selections.
- **Responsive Design**: Suitable for mobile, tablet, and desktop usage.
- **Workout Tracking**: Real-time tracking of workout duration and performance.
- **Comprehensive History**: View past workouts and performance metrics.
- **Server-Rendered Statistics**: View workout statistics with charts and insights, fully rendered on the server.
- **Database Interactions**: Implemented using JDBC Template with SQL for data management.

## 📚 What I Learned

Working on this project has enhanced my understanding of:

### JTE and SSR:

- Utilizing JTE for server-side rendering without learning a new templating language.

### Minimal JavaScript with HTMX:

- Implementing interactivity using HTMX to reduce reliance on JavaScript, achieving maintainability.

### Spring Security:

- Setting up user authentication and session management securely.

### Direct SQL with JDBC:

- Managing database interactions with JDBC Template, improving SQL skills without using an ORM.

## 🚦 Running the Project

To run the Fitness Tracker on your local environment, follow these steps:

0. **Set up the environment variables**:
   ```plaintext
   DB_USERNAME=db_username
   DB_PASSWORD=db_password
   ```

1. Clone the repository to your local machine.
2. Navigate to the project's root directory.

3. Run the application using Maven:
   ```bash
   ./mvnw spring-boot:run
   ```

4. Open [http://localhost:8080](http://localhost:8080) in your web browser to view the app.

## 🍿 Demo Video

[Fitness Tracker Demo](https://www.youtube.com/watch?v=3ZlLuygKth8)

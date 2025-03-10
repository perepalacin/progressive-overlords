DROP SCHEMA IF EXISTS progressive_overlords CASCADE;

CREATE SCHEMA progressive_overlords;
SET search_path TO progressive_overlords;

CREATE EXTENSION IF NOT EXISTS "uuid-ossp";

CREATE TYPE progressive_overlords.units AS ENUM ('kg', 'lb');
CREATE TYPE progressive_overlords.body_part AS ENUM ('shoulders', 'arms', 'legs', 'glutes', 'back', 'chest', 'core');
CREATE TYPE progressive_overlords.motion_type AS ENUM ('push', 'pull', 'legs', 'core');
CREATE TYPE progressive_overlords.upper_or_lower AS ENUM ('upper', 'lower');
CREATE TYPE progressive_overlords.resistance_type AS ENUM ('bodyWeight', 'weight', 'resistance bands');

-- Users Table
CREATE TABLE IF NOT EXISTS progressive_overlords.users (
    id UUID DEFAULT progressive_overlords.uuid_generate_v4() NOT NULL PRIMARY KEY,
    username VARCHAR(255),
    password VARCHAR(255),
    created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP NOT NULL,
    updated_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP NOT NULL,
    weight_units CHAR(2),
    enabled BOOLEAN DEFAULT TRUE
);

-- Roles Table
CREATE TABLE IF NOT EXISTS progressive_overlords.roles (
    id SERIAL PRIMARY KEY,
    role VARCHAR(45) NOT NULL UNIQUE
);

-- Users & Roles Join Table
CREATE TABLE IF NOT EXISTS progressive_overlords.users_roles (
    role_id INT NOT NULL,
    user_id UUID NOT NULL,
    PRIMARY KEY (user_id, role_id),
    CONSTRAINT fk_user_in_roles FOREIGN KEY (user_id)
        REFERENCES progressive_overlords.users (id) ON DELETE CASCADE,
    CONSTRAINT fk_roles_in_users FOREIGN KEY (role_id)
        REFERENCES progressive_overlords.roles (id) ON DELETE CASCADE
);

-- Exercises Table
CREATE SEQUENCE IF NOT EXISTS progressive_overlords.exercises_id_seq;

CREATE TABLE IF NOT EXISTS progressive_overlords.exercises (
    id SERIAL PRIMARY KEY,
    name VARCHAR(255) NOT NULL,
    description VARCHAR(511),
    thumbnail BYTEA,
    muscle VARCHAR(255),
    body_part progressive_overlords.body_part NOT NULL,
    motion progressive_overlords.motion_type NOT NULL,
    upper_or_lower progressive_overlords.upper_or_lower NOT NULL,
    resistance progressive_overlords.resistance_type NOT NULL,
    isometric BOOLEAN NOT NULL
);

-- Workouts Table
CREATE SEQUENCE IF NOT EXISTS progressive_overlords.workouts_id_seq;

CREATE TABLE IF NOT EXISTS progressive_overlords.workouts (
    id SERIAL PRIMARY KEY,
    name VARCHAR(255) NOT NULL,
    description VARCHAR(511),
    color VARCHAR(255),
--    body_part VARCHAR(255),
    tags VARCHAR(255),
    created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP NOT NULL,
    started_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP NOT NULL,
    ended_at TIMESTAMP,
    updated_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP NOT NULL,
    is_template BOOLEAN DEFAULT FALSE,
    template_id INT NULL,
    user_id UUID NOT NULL,
    CONSTRAINT fk_workout_template_in_workouts FOREIGN KEY (template_id)
        REFERENCES progressive_overlords.workouts (id) ON DELETE SET NULL,
    CONSTRAINT fk_user_in_workouts FOREIGN KEY (user_id)
        REFERENCES progressive_overlords.users (id) ON DELETE CASCADE
);

-- Workout Exercises Join Table
CREATE TABLE IF NOT EXISTS progressive_overlords.workout_exercises (
    id SERIAL PRIMARY KEY,
    workout_id INT NOT NULL,
    exercise_id INT NOT NULL,
    set_num INT NOT NULL,
    reps DECIMAL NOT NULL,
    weight DECIMAL NOT NULL,
    annotation VARCHAR(255),
    created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP NOT NULL,
    updated_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP NOT NULL,
    user_id UUID NOT NULL,
    CONSTRAINT fk_workout FOREIGN KEY (workout_id)
        REFERENCES progressive_overlords.workouts (id) ON DELETE CASCADE,
    CONSTRAINT fk_exercise_in_workout FOREIGN KEY (exercise_id)
        REFERENCES progressive_overlords.exercises (id) ON DELETE CASCADE,
    CONSTRAINT fk_user_in_workouts FOREIGN KEY (user_id)
        REFERENCES progressive_overlords.users (id) ON DELETE CASCADE
);


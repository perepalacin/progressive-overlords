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
    exercise_num INT NOT NULL,
    set_num INT NOT NULL,
    reps DECIMAL NOT NULL,
    weight DECIMAL NOT NULL,
    annotation VARCHAR(255),
    is_warmup BOOLEAN DEFAULT FALSE,
    created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP NOT NULL,
    updated_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP NOT NULL,
    user_id UUID NOT NULL,
    CONSTRAINT fk_workout FOREIGN KEY (workout_id)
        REFERENCES progressive_overlords.workouts (id) ON DELETE CASCADE,
    CONSTRAINT fk_exercise_in_workout FOREIGN KEY (exercise_id)
        REFERENCES progressive_overlords.exercises (id) ON DELETE CASCADE,
    CONSTRAINT fk_user_in_workouts FOREIGN KEY (user_id)
        REFERENCES progressive_overlords.users (id) ON DELETE CASCADE,
    CONSTRAINT unique_workout_exercise_set UNIQUE (workout_id, exercise_num, set_num)
);

CREATE TABLE IF NOT EXISTS progressive_overlords.workout_exercises (
    id SERIAL PRIMARY KEY,
    workout_id INT NOT NULL,
    exercise_id INT NOT NULL,
    exercise_num INT NOT NULL,
    set_num INT NOT NULL,
    reps DECIMAL NOT NULL,
    weight DECIMAL NOT NULL,
    annotation VARCHAR(255),
    is_warmup BOOLEAN DEFAULT FALSE,
    created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP NOT NULL,
    updated_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP NOT NULL,
    user_id UUID NOT NULL,
    CONSTRAINT fk_workout FOREIGN KEY (workout_id)
        REFERENCES progressive_overlords.workouts (id) ON DELETE CASCADE,
    CONSTRAINT fk_exercise_in_workout FOREIGN KEY (exercise_id)
        REFERENCES progressive_overlords.exercises (id) ON DELETE CASCADE,
    CONSTRAINT fk_user_in_workouts FOREIGN KEY (user_id)
        REFERENCES progressive_overlords.users (id) ON DELETE CASCADE,
    CONSTRAINT unique_workout_exercise_set UNIQUE (workout_id, exercise_num, set_num)
);

CREATE TABLE IF NOT EXISTS progressive_overlords.workouts_summary (
    id SERIAL PRIMARY KEY,
    workout_id INT NOT NULL,
    duration decimal NOT NULL,
    volume decimal NOT NULL,
    started_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP NOT NULL,
    user_id UUID NOT NULL,
    CONSTRAINT fk_workout FOREIGN KEY (workout_id)
        REFERENCES progressive_overlords.workouts (id) ON DELETE CASCADE,
    CONSTRAINT fk_user_in_workouts FOREIGN KEY (user_id)
        REFERENCES progressive_overlords.users (id) ON DELETE CASCADE
);

CREATE TABLE IF NOT EXISTS progressive_overlords.workout_exercises_summary (
    id SERIAL PRIMARY KEY,
    workout_id INT NOT NULL,
    exercise_id INT NOT NULL,
    sets INT NOT NULL,
    CONSTRAINT fk_workout FOREIGN KEY (workout_id)
        REFERENCES progressive_overlords.workouts (id) ON DELETE CASCADE,
    CONSTRAINT fk_exercise_in_workout FOREIGN KEY (exercise_id)
        REFERENCES progressive_overlords.exercises (id) ON DELETE CASCADE
);

CREATE TABLE IF NOT EXISTS progressive_overlords.friends (
    id SERIAL PRIMARY KEY,
    follower_user_id UUID NOT NULL,
    following_user_id UUID NOT NULL,
    CONSTRAINT fk_follower_in_users FOREIGN KEY (follower_user_id)
        REFERENCES progressive_overlords.users (id) ON DELETE CASCADE,
    CONSTRAINT fk_following_in_users FOREIGN KEY (following_user_id)
        REFERENCES progressive_overlords.users (id) ON DELETE CASCADE,
    CONSTRAINT unique_follower UNIQUE (follower_user_id, following_user_id)
);

CREATE TABLE IF NOT EXISTS progressive_overlords.aggregated_user_data (
    id SERIAL PRIMARY KEY,
    user_id UUID NOT NULL,
    followers INT NOT NULL,
    following INT NOT NULL,
    workouts INT NOT NULL,
    CONSTRAINT fk_aggregated_user_data_in_users FOREIGN KEY (user_id)
        REFERENCES progressive_overlords.users (id) ON DELETE CASCADE
);

CREATE OR REPLACE FUNCTION update_aggregated_user_follow_data()
RETURNS TRIGGER
LANGUAGE plpgsql
AS '
BEGIN
    UPDATE progressive_overlords.aggregated_user_data
    SET following = (
        SELECT COUNT(*) FROM progressive_overlords.friends
        WHERE follower_user_id = NEW.follower_user_id
    )
    WHERE user_id = NEW.follower_user_id;

    UPDATE progressive_overlords.aggregated_user_data
    SET followers = (
        SELECT COUNT(*) FROM progressive_overlords.friends
        WHERE following_user_id = NEW.following_user_id
    )
    WHERE user_id = NEW.following_user_id;

    RETURN NEW;
END;
';

CREATE TRIGGER trg_update_follow_data
AFTER INSERT OR DELETE ON progressive_overlords.friends
FOR EACH ROW
EXECUTE FUNCTION update_aggregated_user_follow_data();

CREATE OR REPLACE FUNCTION update_aggregated_user_workout_data()
RETURNS TRIGGER
LANGUAGE plpgsql
AS '
BEGIN
    IF TG_OP = ''DELETE'' OR (TG_OP = ''INSERT'' AND NEW.is_template = FALSE) THEN
        UPDATE progressive_overlords.aggregated_user_data
        SET workouts = (
            SELECT COUNT(*) FROM progressive_overlords.workouts
            WHERE user_id = COALESCE(NEW.user_id, OLD.user_id)
            AND is_template = FALSE
        )
        WHERE user_id = COALESCE(NEW.user_id, OLD.user_id);
    END IF;
    RETURN NEW;
END;
';

CREATE TRIGGER trg_update_workout_data
AFTER INSERT OR DELETE ON progressive_overlords.workouts
FOR EACH ROW
EXECUTE FUNCTION update_aggregated_user_workout_data();

CREATE OR REPLACE FUNCTION insert_empty_aggregated_data()
RETURNS TRIGGER
LANGUAGE plpgsql
AS '
BEGIN
    INSERT INTO progressive_overlords.aggregated_user_data (user_id, followers, following, workouts)
    VALUES (NEW.id, 0, 0, 0);
    RETURN NEW;
END;
';

CREATE TRIGGER trg_insert_aggregated_data
AFTER INSERT ON progressive_overlords.users
FOR EACH ROW
EXECUTE FUNCTION insert_empty_aggregated_data();

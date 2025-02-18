-- #### USERS TABLE #### --

CREATE EXTENSION IF NOT EXISTS "uuid-ossp";

CREATE TYPE units AS ENUM ('kg', 'lb');

CREATE TABLE IF NOT EXISTS "users" (
    "id" UUID DEFAULT uuid_generate_v4() NOT NULL PRIMARY KEY,
    "username" VARCHAR(255),
    "password" VARCHAR(255),
    "created_at" TIMESTAMP DEFAULT CURRENT_TIMESTAMP NOT NULL,
    "updated_at" TIMESTAMP DEFAULT CURRENT_TIMESTAMP NOT NULL,
    "weight_units" CHAR(2)
);

-- #### EXERCISES TABLE #### --

CREATE SEQUENCE IF NOT EXISTS "exercises_id_seq";

CREATE TYPE body_part AS ENUM ('shoulders', 'arms', 'legs', 'glutes', 'back', 'chest', 'core');
CREATE TYPE motion_type AS ENUM ('push', 'pull', 'legs', 'core');
CREATE TYPE body_section AS ENUM ('upper', 'lower');
CREATE TYPE resistance_type AS ENUM ('bodyWeight', 'weight', 'resistance bands');

CREATE TABLE IF NOT EXISTS "exercises" (
    "id" SERIAL PRIMARY KEY,
    "name" VARCHAR(255) NOT NULL,
    "description" VARCHAR(511),
    "thumbnail" BYTEA,
    "muscle" VARCHAR(255),
    "bodyPart" body_part NOT NULL,
    "motion" motion_type NOT NULL,
    "body" body_section NOT NULL,
    "resistance" resistance_type NOT NULL,
    "isometric" BOOLEAN NOT NULL
);

COMMENT ON TABLE "exercises" IS 'Table containing information about different exercises.';
COMMENT ON COLUMN "exercises"."id" IS 'Unique identifier for each exercise.';
COMMENT ON COLUMN "exercises"."name" IS 'Name of the exercise.';
COMMENT ON COLUMN "exercises"."description" IS 'Detailed description of the exercise.';
COMMENT ON COLUMN "exercises"."thumbnail" IS 'Image representing the exercise, stored as binary data.';
COMMENT ON COLUMN "exercises"."muscle" IS 'Primary muscle group targeted by the exercise.';
COMMENT ON COLUMN "exercises"."bodyPart" IS 'Body part involved in the exercise, such as shoulders, arms, legs, etc.';
COMMENT ON COLUMN "exercises"."motion" IS 'Type of motion used in the exercise, such as push, pull, or legs.';
COMMENT ON COLUMN "exercises"."body" IS 'Body section targeted by the exercise, such as upper or lower body.';
COMMENT ON COLUMN "exercises"."resistance" IS 'Type of resistance used in the exercise, such as bodyWeight, weights, or resistance bands.';
COMMENT ON COLUMN "exercises"."isometric" IS 'Indicates if the exercise involves isometric holds (true or false).';

-- #### WORKOUT TEMPLATES TABLE #### --

CREATE SEQUENCE IF NOT EXISTS "workout_template_id_seq";

CREATE TABLE IF NOT EXISTS "workout_templates" (
    "id" SERIAL PRIMARY KEY,
    "name" VARCHAR(255) NOT NULL,
    "description" VARCHAR(511),
    "color" VARCHAR(255),
    "body_part" VARCHAR(255),
    "tags" VARCHAR(255),
    "user_id" UUID,
    "created_at" TIMESTAMP DEFAULT CURRENT_TIMESTAMP NOT NULL,
    "updated_at" TIMESTAMP DEFAULT CURRENT_TIMESTAMP NOT NULL,
    CONSTRAINT "fk_user"
        FOREIGN KEY ("user_id")
        REFERENCES "users" ("id")
        ON DELETE CASCADE
);

COMMENT ON TABLE "workout_templates" IS 'Table containing the workout templates created by the users.';
COMMENT ON COLUMN "workout_templates"."id" IS 'Unique identifier for each workout template.';
COMMENT ON COLUMN "workout_templates"."name" IS 'Name of the workout template.';
COMMENT ON COLUMN "workout_templates"."description" IS 'Detailed description of the workout template.';
COMMENT ON COLUMN "workout_templates"."color" IS 'Field to store a color assigned to this template.';
COMMENT ON COLUMN "workout_templates"."tags" IS 'String containing the tags assigned to a template.';

-- #### JOIN TABLE BETWEEN TEMPLATES AND EXERCISES #### --

CREATE TABLE IF NOT EXISTS "workout_template_exercises" (
    "id" SERIAL PRIMARY KEY,
    "workout_template_id" INT NOT NULL,
    "exercise_id" INT NOT NULL,
    -- renamed "set" -> "set_count":
    "set_count" INT NOT NULL,
    "weight" INT NOT NULL,
    "reps" INT NOT NULL,
    CONSTRAINT "fk_workout_template"
        FOREIGN KEY ("workout_template_id")
        REFERENCES "workout_templates" ("id")
        ON DELETE CASCADE,
    CONSTRAINT "fk_exercise"
        FOREIGN KEY ("exercise_id")
        REFERENCES "exercises" ("id")
        ON DELETE CASCADE
);

COMMENT ON TABLE "workout_template_exercises" IS 'Join table linking workout templates and exercises, with sets, weight, and reps.';
COMMENT ON COLUMN "workout_template_exercises"."set_count" IS 'Number of sets assigned to the exercise in the workout template.';

---- #### TEMPLATES AND USERS ####
--
--CREATE TABLE IF NOT EXISTS "user_workout_templates" (
--    "id" SERIAL PRIMARY KEY,
--    "user_id" UUID NOT NULL,
--    "workout_template_id" INT NOT NULL,
--    CONSTRAINT "fk_user"
--        FOREIGN KEY ("user_id")
--        REFERENCES "users" ("id")
--        ON DELETE CASCADE,
--    CONSTRAINT "fk_workout_template_user"
--        FOREIGN KEY ("workout_template_id")
--        REFERENCES "workout_templates" ("id")
--        ON DELETE CASCADE
--);
--
--COMMENT ON TABLE "user_workout_templates" IS 'Join table linking users and workout templates.';
--COMMENT ON COLUMN "user_workout_templates"."id" IS 'Unique identifier for each user-workout template relationship.';
--COMMENT ON COLUMN "user_workout_templates"."user_id" IS 'Reference to the user who created or uses the workout template.';
--COMMENT ON COLUMN "user_workout_templates"."workout_template_id" IS 'Reference to the workout template associated with the user.';

-- #### WORKOUTS TABLE #### --

CREATE SEQUENCE IF NOT EXISTS "workouts_id_seq";

CREATE TABLE IF NOT EXISTS "workouts" (
    "id" SERIAL PRIMARY KEY,
    "name" VARCHAR(255) NOT NULL,
    "description" VARCHAR(511),
    "color" VARCHAR(255),
    "body_part" VARCHAR(255),
    "tags" VARCHAR(255),
    "created_at" TIMESTAMP DEFAULT CURRENT_TIMESTAMP NOT NULL,
    "updated_at" TIMESTAMP DEFAULT CURRENT_TIMESTAMP NOT NULL,
    "workout_template_id" INT,
    "user_id" UUID,
    CONSTRAINT "fk_workout_template_in_workouts"
        FOREIGN KEY ("workout_template_id")
        REFERENCES "workout_templates" ("id")
        ON DELETE CASCADE,
    CONSTRAINT "fk_user_in_workouts"
        FOREIGN KEY ("user_id")
        REFERENCES "users" ("id")
        ON DELETE CASCADE
);

COMMENT ON TABLE "workouts" IS 'Table containing user-specific workouts, which may be based on a workout template.';
COMMENT ON COLUMN "workouts"."id" IS 'Unique identifier for each workout instance.';

-- #### WORKOUT EXERCISES TABLE (RENAMED) #### --

CREATE TABLE IF NOT EXISTS "workout_exercises" (
    "id" SERIAL PRIMARY KEY,
    "workout_id" INT NOT NULL,
    "exercise_id" INT NOT NULL,
    "set_count" INT NOT NULL,
    "reps" INT NOT NULL,
    "weight" INT NOT NULL,
    "annotation" VARCHAR(255),
    CONSTRAINT "fk_workout"
        FOREIGN KEY ("workout_id")
        REFERENCES "workouts" ("id")
        ON DELETE CASCADE,
    CONSTRAINT "fk_exercise_in_workout"
        FOREIGN KEY ("exercise_id")
        REFERENCES "exercises" ("id")
        ON DELETE CASCADE
);

COMMENT ON TABLE "workout_exercises" IS 'Join table linking actual workouts and exercises.';
COMMENT ON COLUMN "workout_exercises"."set_count" IS 'Number of sets for this exercise in the user’s actual workout.';
COMMENT ON COLUMN "workout_exercises"."reps" IS 'Number of reps for each set.';
COMMENT ON COLUMN "workout_exercises"."weight" IS 'Weight to be lifted or used, if applicable.';
COMMENT ON COLUMN "workout_exercises"."annotation" IS 'User notes for this exercise in the current workout.';
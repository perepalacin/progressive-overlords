INSERT INTO "users"
    ("username", "password", "weight_units", "enabled" )
VALUES
    ('test1', 'test1234', 'kg', true),
    ('test2', 'test1234', 'kg', true),
    ('test3', 'test1234', 'kg', true),
	('32312', 'test1234', 'kg', true),
	('231233243', 'test1234', 'kg', true),
	('sdsdewd', 'test1234', 'kg', true),
	('1323wfe', 'test1234', 'kg', true),
	('4erfgerew', 'test1234', 'kg', true),
	('fewwfweff', 'test1234', 'kg', true),
	('23214r23rfed', 'test1234', 'kg', true),
	('ewqeqqfewfwe', 'test1234', 'kg', true),
	('dfvvvsd', 'test1234', 'kg', true),
	('ipioppo', 'test1234', 'kg', true),
	('2312sda', 'test1234', 'kg', true),
	('122132121', 'test1234', 'kg', true),
	('poeqwpeeowq', 'test1234', 'kg', true),
	('wefvnfvvfdv', 'test1234', 'kg', true),
	('2wqewqew', 'test1234', 'kg', true);

INSERT INTO "exercises" ("name", "description", "thumbnail", "muscle", "body_part", "motion", "upper_or_lower", "resistance", "isometric") VALUES
('Push-up', 'A basic upper body exercise that works the chest, shoulders, and triceps.', NULL, 'Chest', 'arms', 'push', 'upper', 'bodyWeight', false),
('Pull-up', 'An exercise primarily targeting the back, biceps, and forearms.', NULL, 'Back', 'arms', 'pull', 'upper', 'bodyWeight', false),
('Squat', 'A fundamental lower body exercise that strengthens the quads, glutes, and hamstrings.', NULL, 'Legs', 'legs', 'push', 'lower', 'weight', false),
('Lunges', 'A lower body exercise targeting the quads, glutes, and hamstrings.', NULL, 'Legs', 'legs', 'push', 'lower', 'weight', false),
('Deadlift', 'A weightlifting exercise targeting the back, glutes, and hamstrings.', NULL, 'Back', 'legs', 'push', 'lower', 'weight', false),
('Bench Press', 'A strength exercise for the chest, shoulders, and triceps.', NULL, 'Chest', 'arms', 'push', 'upper', 'weight', false),
('Plank', 'An isometric exercise to engage the core muscles.', NULL, 'Core', 'core', 'push', 'upper', 'bodyWeight', true),
('Overhead Press', 'A shoulder exercise that strengthens the deltoids, triceps, and upper chest.', NULL, 'Shoulders', 'arms', 'push', 'upper', 'weight', false),
('Barbell Row', 'An upper body exercise for the back, traps, and biceps.', NULL, 'Back', 'arms', 'pull', 'upper', 'weight', false),
('Leg Press', 'A lower body exercise that targets the quads, hamstrings, and glutes.', NULL, 'Legs', 'legs', 'push', 'lower', 'weight', false),
('Dips', 'An upper body exercise that targets the chest, triceps, and shoulders.', NULL, 'Chest', 'arms', 'push', 'upper', 'bodyWeight', false),
('Bicep Curl', 'An exercise targeting the biceps using a barbell or dumbbell.', NULL, 'Arms', 'arms', 'pull', 'upper', 'weight', false),
('Tricep Extension', 'An exercise targeting the triceps using a cable or dumbbell.', NULL, 'Arms', 'arms', 'push', 'upper', 'weight', false),
('Leg Curl', 'A machine exercise targeting the hamstrings and glutes.', NULL, 'Legs', 'legs', 'pull', 'lower', 'weight', false),
('Chest Fly', 'An exercise that isolates the chest muscles using dumbbells or cables.', NULL, 'Chest', 'arms', 'push', 'upper', 'weight', false),
('Hip Thrust', 'A lower body exercise that focuses on the glutes and hamstrings.', NULL, 'Glutes', 'legs', 'push', 'lower', 'weight', false),
('Lat Pulldown', 'A machine exercise that targets the back and biceps.', NULL, 'Back', 'arms', 'pull', 'upper', 'weight', false),
('Seated Row', 'A machine exercise that targets the back, biceps, and forearms.', NULL, 'Back', 'arms', 'pull', 'upper', 'weight', false),
('Mountain Climbers', 'A full-body cardio exercise that engages the core, chest, and legs.', NULL, 'Core', 'core', 'push', 'upper', 'bodyWeight', false),
('Russian Twist', 'A core exercise that works the obliques and abs.', NULL, 'Core', 'core', 'push', 'upper', 'bodyWeight', false),
('Wall Sit', 'An isometric exercise that targets the quads and core.', NULL, 'Legs', 'legs', 'push', 'lower', 'bodyWeight', true),
('Glute Bridge', 'A lower body exercise targeting the glutes and hamstrings.', NULL, 'Glutes', 'legs', 'push', 'lower', 'bodyWeight', false),
('Side Plank', 'An isometric core exercise that strengthens the obliques.', NULL, 'Core', 'core', 'push', 'upper', 'bodyWeight', true),
('Leg Extension', 'A machine exercise focusing on the quads.', NULL, 'Legs', 'legs', 'push', 'lower', 'weight', false),
('Face Pull', 'An upper body exercise that targets the rear deltoids and upper traps.', NULL, 'Shoulders', 'arms', 'pull', 'upper', 'weight', false),
('Kettlebell Swing', 'A dynamic exercise that works the hips, glutes, and core.', NULL, 'Core', 'legs', 'push', 'lower', 'weight', false),
('T-Bar Row', 'An exercise that targets the back and biceps using a T-bar machine.', NULL, 'Back', 'arms', 'pull', 'upper', 'weight', false),
('Bulgarian Split Squat', 'A single-leg exercise that targets the quads and glutes.', NULL, 'Legs', 'legs', 'push', 'lower', 'weight', false),
('Farmer’s Walk', 'A grip-strengthening exercise that engages the forearms, shoulders, and core.', NULL, 'Shoulders', 'arms', 'pull', 'upper', 'weight', false),
('Jumping Jack', 'A cardio exercise that engages the entire body and increases heart rate.', NULL, 'Full Body', 'core', 'push', 'upper', 'bodyWeight', false),
('Superman', 'A core exercise that targets the lower back and glutes.', NULL, 'Back', 'core', 'push', 'upper', 'bodyWeight', false),
('Pistol Squat', 'A one-legged squat that requires balance, strength, and flexibility.', NULL, 'Legs', 'legs', 'push', 'lower', 'bodyWeight', false),
('Barbell Squat', 'A strength exercise for the lower body, targeting the quads and glutes.', NULL, 'Legs', 'legs', 'push', 'lower', 'weight', false),
('Shrugs', 'An exercise targeting the traps using a barbell or dumbbells.', NULL, 'Shoulders', 'arms', 'pull', 'upper', 'weight', false),
('Cable Kickback', 'An isolation exercise for the glutes using a cable machine.', NULL, 'Glutes', 'legs', 'push', 'lower', 'weight', false),
('Seated Calf Raise', 'A machine exercise targeting the calves.', NULL, 'Legs', 'legs', 'push', 'lower', 'weight', false),
('Cable Chest Press', 'A chest exercise that targets the pectorals using cables.', NULL, 'Chest', 'arms', 'push', 'upper', 'weight', false),
('Chest Dip', 'A bodyweight exercise targeting the chest and triceps.', NULL, 'Chest', 'arms', 'push', 'upper', 'bodyWeight', false),
('Kettlebell Clean and Press', 'A full-body exercise that targets the legs, shoulders, and arms.', NULL, 'Full Body', 'legs', 'push', 'upper', 'weight', false),
('Ab Rollout', 'A core exercise that targets the abs and stabilizes the shoulders.', NULL, 'Core', 'core', 'push', 'upper', 'bodyWeight', false),
('Cable Tricep Pushdown', 'An exercise targeting the triceps using a cable machine.', NULL, 'Arms', 'arms', 'push', 'upper', 'weight', false),
('Sled Push', 'A lower body exercise that targets the quads, hamstrings, and glutes.', NULL, 'Legs', 'legs', 'push', 'lower', 'weight', false),
('Single-Leg Romanian Deadlift', 'A unilateral deadlift exercise for the hamstrings and glutes.', NULL, 'Legs', 'legs', 'pull', 'lower', 'weight', false),
('Concentration Curl', 'An isolation exercise targeting the biceps.', NULL, 'Arms', 'arms', 'pull', 'upper', 'weight', false),
('Cable Lateral Raise', 'An exercise for the shoulders using a cable machine.', NULL, 'Shoulders', 'arms', 'push', 'upper', 'weight', false),
('Sissy Squat', 'An advanced bodyweight squat variation that targets the quads.', NULL, 'Legs', 'legs', 'push', 'lower', 'bodyWeight', false),
('Incline Bench Press', 'A variation of the bench press that focuses on the upper chest.', NULL, 'Chest', 'arms', 'push', 'upper', 'weight', false),
('Hack Squat', 'A machine exercise targeting the quads and glutes.', NULL, 'Legs', 'legs', 'push', 'lower', 'weight', false),
('Glute Kickback', 'An isolation exercise for the glutes using a machine or cable.', NULL, 'Glutes', 'legs', 'push', 'lower', 'weight', false),
('Rotator Cuff Exercise', 'A shoulder exercise designed to strengthen the rotator cuff muscles.', NULL, 'Shoulders', 'arms', 'pull', 'upper', 'bodyWeight', false),
('Lateral Lunge', 'A lower body exercise that targets the quads, glutes, and adductors.', NULL, 'Legs', 'legs', 'push', 'lower', 'weight', false),
('Zercher Squat', 'A squat variation that places the barbell in the crooks of your elbows.', NULL, 'Legs', 'legs', 'push', 'lower', 'weight', false);

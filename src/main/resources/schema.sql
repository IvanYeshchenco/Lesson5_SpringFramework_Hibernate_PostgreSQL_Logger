DROP TABLE IF EXISTS courses,faculties,groups,lessons,students,teachers cascade
CREATE TABLE courses (course_id SERIAL PRIMARY KEY NOT NULL, course_name VARCHAR(50))
CREATE TABLE faculties (faculty_id SERIAL PRIMARY KEY NOT NULL, faculty_name VARCHAR(50))
CREATE TABLE groups (group_id SERIAL PRIMARY KEY NOT NULL, group_name VARCHAR(50), capacity INTEGER)
CREATE TABLE lessons (lesson_id SERIAL PRIMARY KEY NOT NULL, lesson_name VARCHAR(50), lesson_time timestamp, lesson_group_id INTEGER,  lesson_course_name VARCHAR(50),  lesson_teacher_id INTEGER)
CREATE TABLE students (student_id SERIAL PRIMARY KEY NOT NULL, group_id INTEGER REFERENCES groups (group_id), student_name VARCHAR(100), sex VARCHAR(50), age INTEGER)
CREATE TABLE teachers (teacher_id SERIAL PRIMARY KEY NOT NULL, position VARCHAR(100), teacher_name VARCHAR(100), sex VARCHAR(50), age INTEGER)
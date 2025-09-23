CREATE TABLE users (
    id UUID PRIMARY KEY,
    registration VARCHAR(20) UNIQUE NOT NULL,
    name VARCHAR(150) NOT NULL,
    email VARCHAR(150) UNIQUE NOT NULL
);

CREATE TABLE courses (
    id UUID PRIMARY KEY,
    name VARCHAR(100),
    acronym VARCHAR(10) UNIQUE,
    duration INTEGER
);

CREATE TABLE disciplines (
    id UUID PRIMARY KEY,
    course_id UUID,
    name VARCHAR(100),
    workload INTEGER,
    FOREIGN KEY (course_id) REFERENCES courses (id)
);
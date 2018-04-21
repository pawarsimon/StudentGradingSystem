USE gc200361569;

DROP TABLE IF EXISTS grades;
 CREATE TABLE grades
 (
	studentId INT NOT NULL AUTO_INCREMENT PRIMARY KEY,
	firstName VARCHAR(50),
    lastName VARCHAR(50),
    studentNumber VARCHAR(50),
    grades double
 );
 
 CREATE TABLE members
 (
	userId INT NOT NULL AUTO_INCREMENT PRIMARY KEY,
	userName VARCHAR(100),
    password VARCHAR(500),
    salt BLOB
 );
 
 SELECT * FROM members;
 SELECT * FROM grades;
	
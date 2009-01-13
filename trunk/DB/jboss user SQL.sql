CREATE USER 'jboss'@'localhost' IDENTIFIED BY 'jboss';


GRANT SELECT ,
INSERT ,

UPDATE ,
DELETE ,
CREATE ,
REFERENCES ,
INDEX ,
ALTER ,
CREATE TEMPORARY TABLES ,
CREATE VIEW ,
SHOW VIEW ,
CREATE ROUTINE,
ALTER ROUTINE,
EXECUTE ON `webdomotic` . * TO 'jboss'@'localhost';


/*GRANT ALL PRIVILEGES ON `webdomotic` . * TO 'jboss'@'localhost' WITH GRANT OPTION ;*/
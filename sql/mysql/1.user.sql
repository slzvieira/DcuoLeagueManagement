CREATE DATABASE dcuo;

CREATE USER magno IDENTIFIED BY 'brutus';
CREATE USER 'magno'@'localhost' IDENTIFIED BY 'brutus';

GRANT ALL ON dcuo.* TO magno;

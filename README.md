# NTPartner

##Prerequisites: 

eclipse plugins:
	* m2e-atp
	* cloud foundry

database
	* mysql

## mysql setup

1.) Start mysql command line
	mysql -u root -p
    Password: root
2.) Create new database ntpartner
	mysql> create database ntpartner DEFAULT CHARACTER SET utf8 DEFAULT COLLATE utf8_general_ci;
3.) Create database user 'dbuser' (password = user name)
	mysql> create user 'dbuser'@'localhost' IDENTIFIED BY 'dbuser';
4.) Authorize 'dbuser' to access database 'ntpartner'
	mysql> grant all ON ntpartner.* TO 'dbuser'@'localhost';
5.) Enable authorization
	mysql> flush privileges;
	
## mysql schema creation
USE ntpartner;

CREATE TABLE Address (
  id int(11) NOT NULL AUTO_INCREMENT,
  street varchar(80) NOT NULL,
  streetNo varchar(40) NOT NULL,
  zipCode varchar(40) NOT NULL,
  city varchar(80) NOT NULL,
  PRIMARY KEY (id)
);
CREATE TABLE Person (
  id int(11) NOT NULL AUTO_INCREMENT,
  firstname varchar(80) NOT NULL,
  lastname varchar(80) NOT NULL,
  idAddress int(11),
  PRIMARY KEY (id),
  FOREIGN KEY (idAddress) REFERENCES Address (id) ON DELETE RESTRICT ON UPDATE CASCADE
);

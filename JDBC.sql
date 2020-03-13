# drop databse
DROP DATABASE assignment;
# creating database
CREATE DATABASE assignment;

# use database
USE assignment;

# creating table customers
DROP TABLE IF EXISTS customers;
CREATE TABLE customers (
  customer_id INT NOT NULL AUTO_INCREMENT,
  customer_firstname VARCHAR(45) NOT NULL,
  customer_lastname VARCHAR(45) NOT NULL,
  customer_address VARCHAR(45) NOT NULL,
  customer_number BIGINT(20) NOT NULL,
  PRIMARY KEY (customer_id));
  
 insert into customers(customer_firstname,customer_lastname,customer_address,customer_number)  values("vivek","modi","sdv",123456789);
# Login table
DROP TABLE IF EXISTS login;
create table login(
  username VARCHAR(45) NOT NULL,
  password VARCHAR(45) NOT NULL,
  type VARCHAR(45) NOT NULL);
  
# order table to generate bill
DROP TABLE IF EXISTS orders;
create table orders(
	order_id INT NOT NULL AUTO_INCREMENT,
    customer_name VARCHAR(90) NOT NULL,
    item_name  VARCHAR(45) NOT NULL,
    item_quantity int NOT NULL,
    amount int NOT NULL,
PRIMARY KEY (order_id));

# Employee table to add employee
DROP TABLE IF EXISTS employees;
create table employees(
      id INT NOT NULL AUTO_INCREMENT,
	  firstname VARCHAR(45) NOT NULL,
      lastname VARCHAR(45) NOT NULL,
	  gender VARCHAR(45) NOT NULL,
      designation VARCHAR(45) NOT NULL,
      department VARCHAR(45) NOT NULL,
      PRIMARY KEY (id)
);

#item table to add item in database
DROP TABLE IF EXISTS items;
CREATE TABLE items (
    item_id INT NOT NULL AUTO_INCREMENT,
    item_name VARCHAR(45) NOT NULL,
    item_type VARCHAR(45) NOT NULL,
    item_price INT NOT NULL,
    item_quantity INT NOT NULL,
    PRIMARY KEY (item_id)
);

# save all delete 		
DROP TABLE IF EXISTS backup;
CREATE TABLE backup(
	Item_ID INT NOT NULL,
    Item_Name VARCHAR(50),
    action_type VARCHAR(50),
    action_date DATETIME NOT NULL
);

# show employees database
# to return all columns of employees
show Columns from employees;

# Trigge to change the gender of employee into Uppercase before updating
DROP TRIGGER IF EXISTS employees_before_update;
DELIMITER //
CREATE TRIGGER employees_before_update
   BEFORE UPDATE ON employees
   FOR EACH ROW
BEGIN 
  SET NEW.gender = UPPER(NEW.gender);
END//
 
 # Trigger to save the deleted row from the table to another table backup
DROP TRIGGER IF EXISTS save_after_delete;
DELIMITER //
CREATE TRIGGER save_after_delete
    AFTER DELETE on items
    FOR EACH ROW
BEGIN
    INSERT INTO backup VALUES
    (OLD.Item_ID, OLD.Item_Name, "DELETED", NOW());   
END//

#employees view
DROP VIEW IF EXISTS employees_view;
CREATE VIEW employees_view AS
    SELECT 
        id AS id,
        firstname AS firstname,
        lastname AS lastname,
        gender AS gender,
        designation AS designation,
        department AS department
    FROM
        employees;
        
#customer view
DROP VIEW IF EXISTS customers_view;
CREATE VIEW customers_view AS
    SELECT 
        customer_id AS customer_id,
        customer_firstname AS customer_firstname,
        customer_lastname  AS customer_lastname,
        customer_address AS customer_address,
        customer_number AS customer_number
    FROM
        customers;
        
#store procedure to calculate salart of employees
DROP PROCEDURE IF EXISTS salary;
DELIMITER //
CREATE PROCEDURE salary (
	fname VARCHAR(50),
    lname VARCHAR(50))
BEGIN
	DECLARE des CHAR(50);
    DECLARE dep CHAR(50);
        SET des = (SELECT designation FROM employees
		WHERE firstname = fname AND lastname = lname);
        SET dep = (SELECT department FROM employees
		WHERE firstname = fname AND lastname = lname);
        
	IF (des = 'Executive Chef' AND dep = 'Management') THEN
			SELECT firstname, 5 * 4 AS salary FROM employees WHERE firstname = fname AND lastname = lname;
	ELSEIF (des = 'Bartender' AND dep = 'Chef') THEN
        SELECT firstname, 32 * 2 AS salary FROM employees WHERE firstname = fname AND lastname = lname ;
    ELSEIF (des = 'Executive Chef' AND dep = 'Inventory') THEN
        SELECT firstname, 6 * 6 AS salary FROM employees WHERE firstname = fname AND lastname = lname ;
	ELSEIF (des = 'Bartender' AND dep = 'Maintenance') THEN
        SELECT firstname, 5 * 10 AS salary FROM employee WHERE firstname = fname AND lastname = lname ;
	END IF;
END //

#delete employee procedure 
DROP PROCEDURE IF EXISTS deleteemployee;
DELIMITER //
CREATE PROCEDURE deleteemployee(
	  IN fName VARCHAR(50),
      IN lName VARCHAR(50))
BEGIN
     DELETE FROM  employees WHERE firstname = fName and lastname = lName;
END //

#delete item procedure 
DROP PROCEDURE IF EXISTS deleteitem;
DELIMITER //
CREATE PROCEDURE deleteitem(
	IN id integer)
BEGIN
	 DELETE FROM items WHERE item_id = id;
END //

#view stocks item
DROP VIEW IF EXISTS stock;
CREATE OR REPLACE VIEW stock AS 
SELECT item_id,item_name,item_type,item_price,item_quantity, item_price * item_quantity as 'stockprice' FROM items;


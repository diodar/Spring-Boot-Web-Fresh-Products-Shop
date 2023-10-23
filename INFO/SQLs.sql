CREATE DATABASE demo_db;

CREATE TABLE IF NOT EXISTS vegets
( id INTEGER NOT NULL AUTO_INCREMENT,
  img VARCHAR(255) NOT NULL,
  name VARCHAR(255) NOT NULL,
  article VARCHAR(255) NOT NULL,
  description VARCHAR(255) NOT NULL,
  price DECIMAL(6,2) NOT NULL,
  PRIMARY KEY (id)
);

CREATE TABLE IF NOT EXISTS fruits
( id INTEGER NOT NULL AUTO_INCREMENT,
  img VARCHAR(255) NOT NULL,
  name VARCHAR(255) NOT NULL,
  article VARCHAR(255) NOT NULL,
  description VARCHAR(255) NOT NULL,
  price DECIMAL(6,2) NOT NULL,
  PRIMARY KEY (id)
);

CREATE TABLE IF NOT EXISTS orders
( id INTEGER NOT NULL AUTO_INCREMENT,
  order_code VARCHAR(255) NOT NULL,
  buyer_name VARCHAR(255) NOT NULL,
  buyer_phone VARCHAR(255) NOT NULL,
  buyer_email VARCHAR(255) NOT NULL,
  content VARCHAR(255) NOT NULL,
  status VARCHAR(255) NOT NULL,
  PRIMARY KEY (id)
);

INSERT INTO
 vegets (img, name, article, description, price)
VALUES
 ('veget-carrot.png', 'Carrot', 'TSG3209', 'Lorem ipsum green', 2.99),
 ('veget-corn.png', 'Corn', 'TSY3222', 'Lorem ipsum yellow', 3.95),
 ('veget-onion.png', 'Onion', 'TSB3200', 'Lorem ipsum black', 1.99),
 ('veget-potato.png', 'Potato', 'TSO3214', 'Lorem ipsum orange', 4.95),
 ('veget-tomato.png', 'Tomato', 'TSB3255', 'Lorem ipsum blue', 10.95);


INSERT INTO
 fruits (img, name, article, description, price)
VALUES
 ('fruit-apple.png', 'Apple', 'CBD4114', 'Lorem ipsum blue dark', 9.95),
 ('fruit-banana.png', 'Banana', 'CBG4123', 'Lorem ipsum blue grey', 7.99),
 ('fruit-grape.png', 'Grape', 'COW4111', 'Lorem ipsum white', 6.99),
 ('fruit-mango.png', 'Mango', 'COG4103', 'Lorem ipsum grey', 6.99),
 ('fruit-orange.png', 'Orange', 'COS4199', 'Lorem ipsum sand', 12.99);



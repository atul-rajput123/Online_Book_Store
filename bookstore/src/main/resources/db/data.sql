-- =====================================
-- BOOKS
-- =====================================
INSERT INTO books (name, price, authors, isbn, publisher, published_on) 
VALUES ('The Lord of the Rings', 99.99, 'J. R. R. Tolkien', '9780261103207', 'Allen & Unwin', '1954-07-29');

INSERT INTO books (name, price, authors, isbn, publisher, published_on) 
VALUES ('Harry Potter and the Half-Blood Prince', 55.00, 'J. K. Rowling', '9780439784542', 'Bloomsbury Publishing (UK)', '2005-07-16');

INSERT INTO books (name, price, authors, isbn, publisher, published_on) 
VALUES ('The Da Vinci Code', 250.89, 'Dan Brown', '0385504209', 'Doubleday', '2003-04-02');

INSERT INTO books (name, price, authors, isbn, publisher, published_on) 
VALUES ('Fifty Shades of Grey', 26.95, 'E. L. James', '9781612130286', 'Vintage Books', '2012-04-17');

INSERT INTO books (name, price, authors, isbn, publisher, published_on) 
VALUES ('Twilight', 38.00, 'Stephenie Meyer', '0316160172', 'Little, Brown and Company', '2005-10-05');

INSERT INTO books (name, price, authors, isbn, publisher, published_on) 
VALUES ('Hannibal', 66.60, 'Thomas Harris', '0385334877', 'Delacorte Press', '1999-06-08');

INSERT INTO books (name, price, authors, isbn, publisher, published_on) 
VALUES ('The Hunger Games', 132.00, 'Suzanne Collins', '9780439023528', 'Scholastic Press', '2008-09-14');

INSERT INTO books (name, price, authors, isbn, publisher, published_on) 
VALUES ('Life of Pi', 26.95, 'Yann Martel', '0676973760', 'Knopf Canada', '2001-09-11');

INSERT INTO books (name, price, authors, isbn, publisher, published_on) 
VALUES ('The Last Olympian', 150.68, 'Rick Riordan', '9781423101475', 'Disney Hyperion', '2009-05-05');

-- =====================================
-- USERS
-- =====================================
INSERT INTO users (username, password, enabled) 
VALUES ('admin', '{noop}admin', 1);

-- =====================================
-- AUTHORITIES
-- =====================================
INSERT INTO authorities (username, authority) 
VALUES ('admin', 'ADMIN');

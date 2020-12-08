USE book_sc;


INSERT INTO books VALUES ('0672329166','Luke Welling and Laura Thomson','PHP and MySQL Web Development',1,49.99,
'PHP & MySQL Web Development teaches the reader to develop dynamic, secure e-commerce web sites. You will learn to integrate and implement these technologies by following real-world examples and working sample projects.');
INSERT INTO books VALUES ('067232976X','Julie Meloni','Sams Teach Yourself PHP, MySQL and Apache All-in-One',1,34.99,
'Using a straightforward, step-by-step approach, each lesson in this book builds on the previous ones, enabling you to learn the essentials of PHP scripting, MySQL databases, and the Apache web server from the ground up.');
INSERT INTO books VALUES ('0672319241','Sterling Hughes and Andrei Zmievski','PHP Developer\'s Cookbook',1,39.99,
'Provides a complete, solutions-oriented guide to the challenges most often faced by PHP developers\r\nWritten specifically for experienced Web developers, the book offers real-world solutions to real-world needs\r\n');

INSERT INTO categories VALUES (1,'Internet');
INSERT INTO categories VALUES (2,'Self-help');
INSERT INTO categories VALUES (5,'Fiction');
INSERT INTO categories VALUES (4,'Gardening');

INSERT INTO admin VALUES ('admin', sha1('admin'));

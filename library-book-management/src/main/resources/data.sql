INSERT INTO app_user(id, username, password_hash, role, full_name, email) VALUES
 (1,'librarian', '{bcrypt}$2a$10$2mDzY8VQ3U8C0sQbOZzEmeS7e2b7yQdKqf1O8eJt9rJmVbYxFZKpS', 'LIBRARIAN', 'Head Librarian','lib@example.com'),
 (2,'alice', '{bcrypt}$2a$10$2mDzY8VQ3U8C0sQbOZzEmeS7e2b7yQdKqf1O8eJt9rJmVbYxFZKpS', 'STUDENT', 'Alice Sharma','alice@example.com'),
 (3,'bob', '{bcrypt}$2a$10$2mDzY8VQ3U8C0sQbOZzEmeS7e2b7yQdKqf1O8eJt9rJmVbYxFZKpS', 'STUDENT', 'Bob Verma','bob@example.com');

-- password for all above users is: password

INSERT INTO book(id, title, author, isbn, category, available) VALUES
(1,'Clean Code','Robert C. Martin','9780132350884','Programming', true),
(2,'Introduction to Algorithms','CLRS','9780262046305','Algorithms', true),
(3,'The Alchemist','Paulo Coelho','9780061122415','Fiction', true);

use springblog;

CREATE TABLE users (
                       ID BIGINT AUTO_INCREMENT PRIMARY KEY,
                       USERNAME VARCHAR(255) NOT NULL,
                       EMAIL VARCHAR(255) NOT NULL UNIQUE,
                       PASSWORD_HASH VARCHAR(255) NOT NULL,
                       PHONE VARCHAR(45) NOT NULL,
                       CREATED_AT DATETIME,
                       ROLE VARCHAR(50) NOT NULL
);

INSERT INTO users (USERNAME, EMAIL, PASSWORD_HASH, PHONE, CREATED_AT, ROLE)
VALUES
    ('admin', 'admin@example.com', '$2a$10$Dow1x4d3i5c0pCkVdFRJTOYiCmLgEUnZtOOP7EKvF3WQoT/yLuDvu', '1234567890', NOW(), 'ADMIN'),
    ('user1', 'user1@example.com', '$2a$10$7uNchZB63U3TTkHRbLCx6.dCBKaCIGG06QwMvtZJ0cGyONIAXmZbS', '1112223333', NOW(), 'USER'),
    ('user2', 'user2@example.com', '$2a$10$7uNchZB63U3TTkHRbLCx6.dCBKaCIGG06QwMvtZJ0cGyONIAXmZbS', '4445556666', NOW(), 'USER');

CREATE TABLE posts (
                       id BIGINT AUTO_INCREMENT PRIMARY KEY,
                       title VARCHAR(255) NOT NULL,
                       content TEXT NOT NULL,
                       created_at DATETIME,
                       user_id BIGINT,
                       CONSTRAINT fk_user FOREIGN KEY (user_id) REFERENCES users(ID) ON DELETE CASCADE
);

INSERT INTO posts (title, content, created_at, user_id) VALUES
                                                            ('Primer Post de Admin', 'Este es el contenido del primer post creado por el administrador.', NOW(), 1),
                                                            ('Post de User1', 'Contenido de ejemplo para el usuario uno. Muy emocionante.', NOW(), 2),
                                                            ('Post de User2', 'Este es un mensaje de prueba publicado por user2.', NOW(), 3);


select * from users;
select * from posts;


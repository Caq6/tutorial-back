INSERT INTO CATEGORY(id, name) VALUES (1, 'Eurogames');
INSERT INTO CATEGORY(id, name) VALUES (2, 'Ameritrash');
INSERT INTO CATEGORY(id, name) VALUES (3, 'Familiar');

INSERT INTO AUTHOR(id, name, nationality) VALUES (1, 'Alan R. Moon', 'US');
INSERT INTO AUTHOR(id, name, nationality) VALUES (2, 'Vital Lacerda', 'PT');
INSERT INTO AUTHOR(id, name, nationality) VALUES (3, 'Simone Luciani', 'IT');
INSERT INTO AUTHOR(id, name, nationality) VALUES (4, 'Perepau Llistosella', 'ES');
INSERT INTO AUTHOR(id, name, nationality) VALUES (5, 'Michael Kiesling', 'DE');
INSERT INTO AUTHOR(id, name, nationality) VALUES (6, 'Phil Walker-Harding', 'US');

INSERT INTO GAME(id, title, age, category_id, author_id) VALUES (1, 'On Mars', '14', 1, 2);
INSERT INTO GAME(id, title, age, category_id, author_id) VALUES (2, 'Aventureros al tren', '8', 3, 1);
INSERT INTO GAME(id, title, age, category_id, author_id) VALUES (3, '1920: Wall Street', '12', 1, 4);
INSERT INTO GAME(id, title, age, category_id, author_id) VALUES (4, 'Barrage', '14', 1, 3);
INSERT INTO GAME(id, title, age, category_id, author_id) VALUES (5, 'Los viajes de Marco Polo', '12', 1, 3);
INSERT INTO GAME(id, title, age, category_id, author_id) VALUES (6, 'Azul', '8', 3, 5);

INSERT INTO CLIENT(id, name) VALUES (1, 'Blanca Paloma Ramos');
INSERT INTO CLIENT(id, name) VALUES (2, 'Chanel Terrero');
INSERT INTO CLIENT(id, name) VALUES (3, 'Cornelia Jakobs');
INSERT INTO CLIENT(id, name) VALUES (4, 'Salvador Sobral');
INSERT INTO CLIENT(id, name) VALUES (5, 'Blas Canto');
INSERT INTO CLIENT(id, name) VALUES (6, 'Claudia Pascoal');

INSERT INTO LOAN(id, begin, end, client_id, game_id) VALUES (1, '2023-02-28', '2023-03-14', 1, 5);
INSERT INTO LOAN(id, begin, end, client_id, game_id) VALUES (2, '2023-02-20', '2023-03-01', 1, 6);
INSERT INTO LOAN(id, begin, end, client_id, game_id) VALUES (3, '2023-03-01', '2023-03-14', 3, 1);
INSERT INTO LOAN(id, begin, end, client_id, game_id) VALUES (4, '2023-03-15', '2022-03-29', 4, 6);
INSERT INTO LOAN(id, begin, end, client_id, game_id) VALUES (5, '2023-02-22', '2023-03-08', 2, 2);
INSERT INTO LOAN(id, begin, end, client_id, game_id) VALUES (6, '2023-03-08', '2023-03-16', 3, 4);
INSERT INTO LOAN(id, begin, end, client_id, game_id) VALUES (7, '2023-02-24', '2023-02-28', 5, 3);




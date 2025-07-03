-- Langues
INSERT INTO language (code, name) VALUES
                                      ('fr', 'Français'),
                                      ('en', 'Anglais'),
                                      ('es', 'Espagnol'),
                                      ('de', 'Allemand'),
                                      ('it', 'Italien');

-- Éditeurs
INSERT INTO editor (name, description) VALUES
                                           ('Gallimard', 'Éditeur français historique'),
                                           ('Flammarion', 'Grand groupe éditorial français'),
                                           ('Penguin Books', 'Éditeur anglais renommé'),
                                           ('Seuil', 'Éditeur français littéraire'),
                                           ('Hachette', 'Groupe éditorial international');

-- Catégories
INSERT INTO category (name, description) VALUES
                                             ('Roman', 'Œuvre narrative en prose'),
                                             ('Science-Fiction', 'Fiction spéculative'),
                                             ('Policier', 'Genre littéraire basé sur des enquêtes'),
                                             ('Fantasy', 'Genre mêlant magie et mondes imaginaires'),
                                             ('Biographie', 'Récit de la vie d''une personne réelle');

-- Auteurs
INSERT INTO author (first_name, last_name, biography, birth_date) VALUES
                                                                      ('Victor', 'Hugo', 'Écrivain, poète et dramaturge français', '1802-02-26'),
                                                                      ('Jules', 'Verne', 'Écrivain français, pionnier de la science-fiction', '1828-02-08'),
                                                                      ('Agatha', 'Christie', 'Écrivaine anglaise, reine du roman policier', '1890-09-15'),
                                                                      ('J.R.R.', 'Tolkien', 'Écrivain, poète et universitaire britannique', '1892-01-03'),
                                                                      ('George', 'Orwell', 'Écrivain et journaliste anglais', '1903-06-25'),
                                                                      ('Marcel', 'Proust', 'Écrivain français, auteur de "À la recherche du temps perdu"', '1871-07-10'),
                                                                      ('Albert', 'Camus', 'Écrivain, philosophe et journaliste français', '1913-11-07'),
                                                                      ('Simone', 'de Beauvoir', 'Écrivaine, philosophe et féministe française', '1908-01-09');

-- Livres
INSERT INTO book (isbn, title, publication_date, page_count, summary, editor_id, language_id) VALUES
                                                                                                  ('9782070360024', 'Les Misérables', '1862-01-01', 1488, 'Roman historique qui suit la vie de plusieurs personnages sur une vingtaine d''années', 1, 1),
                                                                                                  ('9782070368228', 'Vingt mille lieues sous les mers', '1870-01-01', 420, 'Aventure sous-marine du Capitaine Nemo', 2, 1),
                                                                                                  ('9782070368112', 'Le Tour du monde en quatre-vingts jours', '1873-01-01', 217, 'Pari audacieux de Phileas Fogg', 2, 1),
                                                                                                  ('9782253006329', 'Le Meurtre de Roger Ackroyd', '1926-01-01', 256, 'Enquête d''Hercule Poirot dans un meurtre mystérieux', 4, 1),
                                                                                                  ('9782070612758', 'Le Seigneur des Anneaux', '1954-01-01', 1216, 'Quête épique pour détruire l''Anneau Unique', 1, 1),
                                                                                                  ('9782070368227', '1984', '1949-01-01', 328, 'Roman dystopique sur une société totalitaire', 3, 2),
                                                                                                  ('9782070360429', 'La Peste', '1947-01-01', 347, 'Roman sur une épidémie à Oran et la condition humaine', 1, 1),
                                                                                                  ('9782070360788', 'Le Deuxième Sexe', '1949-01-01', 408, 'Ouvre fondatrice du féminisme moderne', 1, 1);

-- Relations Livres-Auteurs
INSERT INTO book_author (book_id, author_id) VALUES
                                                 (1, 1), -- Les Misérables - Victor Hugo
                                                 (2, 2), -- Vingt mille lieues - Jules Verne
                                                 (3, 2), -- Tour du monde - Jules Verne
                                                 (4, 3), -- Roger Ackroyd - Agatha Christie
                                                 (5, 4), -- Seigneur des Anneaux - Tolkien
                                                 (6, 5), -- 1984 - George Orwell
                                                 (7, 7), -- La Peste - Albert Camus
                                                 (8, 8); -- Le Deuxième Sexe - Simone de Beauvoir



-- Relations Livres-Catégories
INSERT INTO book_category (book_id, category_id) VALUES
                                                     (1, 1), -- Les Misérables - Roman
                                                     (2, 2), -- Vingt mille lieues - Science-Fiction
                                                     (2, 1), -- Vingt mille lieues - Roman
                                                     (3, 1), -- Tour du monde - Roman
                                                     (4, 3), -- Roger Ackroyd - Policier
                                                     (5, 4), -- Seigneur des Anneaux - Fantasy
                                                     (5, 1), -- Seigneur des Anneaux - Roman
                                                     (6, 1), -- 1984 - Roman
                                                     (6, 2), -- 1984 - Science-Fiction
                                                     (7, 1), -- La Peste - Roman
                                                     (8, 5); -- Le Deuxième Sexe - Biographie


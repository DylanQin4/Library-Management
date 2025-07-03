-- Table pour les éditeurs
CREATE TABLE editor (
                        id SERIAL PRIMARY KEY,
                        name VARCHAR(100) NOT NULL,
                        description TEXT
);

-- Table pour les langues
CREATE TABLE language (
                          id SERIAL PRIMARY KEY,
                          code VARCHAR(10) NOT NULL UNIQUE,
                          name VARCHAR(50) NOT NULL
);

-- Table pour les catégories
CREATE TABLE category (
                          id SERIAL PRIMARY KEY,
                          name VARCHAR(100) NOT NULL UNIQUE,
                          description TEXT
);

-- Table pour les auteurs
CREATE TABLE author (
                        id SERIAL PRIMARY KEY,
                        first_name VARCHAR(100) NOT NULL,
                        last_name VARCHAR(100) NOT NULL,
                        biography TEXT,
                        birth_date DATE,
                        CONSTRAINT unique_author UNIQUE (first_name, last_name)
);

-- Table principale pour les livres
CREATE TABLE book (
                      id SERIAL PRIMARY KEY,
                      isbn VARCHAR(20) UNIQUE,
                      title VARCHAR(255) NOT NULL,
                      publication_date DATE,
                      page_count INTEGER,
                      summary TEXT,
                      editor_id INTEGER REFERENCES editor(id),
                      language_id INTEGER REFERENCES language(id)
);

-- Table de jointure pour les livres et auteurs (many-to-many)
CREATE TABLE book_author (
                             book_id INTEGER REFERENCES book(id) ON DELETE CASCADE,
                             author_id INTEGER REFERENCES author(id) ON DELETE CASCADE,
                             PRIMARY KEY (book_id, author_id)
);

-- Table de jointure pour les livres et catégories (many-to-many)
CREATE TABLE book_category (
                               book_id INTEGER REFERENCES book(id) ON DELETE CASCADE,
                               category_id INTEGER REFERENCES category(id) ON DELETE CASCADE,
                               PRIMARY KEY (book_id, category_id)
);
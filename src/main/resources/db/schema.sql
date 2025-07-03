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

-- Enumération pour l'état de l'exemplaire
CREATE TYPE copy_status AS ENUM ('AVAILABLE', 'RESERVED', 'UNAVAILABLE', 'SPECIAL');

-- Table pour les exemplaires
CREATE TABLE book_copy (
                           id SERIAL PRIMARY KEY,
                           barcode VARCHAR(50) NOT NULL UNIQUE,
                           status copy_status NOT NULL DEFAULT 'AVAILABLE',
                           shelf_location VARCHAR(100),
                           room VARCHAR(50),
                           book_id INTEGER NOT NULL REFERENCES book(id) ON DELETE CASCADE,
                           created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
                           updated_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP
);

-- Index pour les recherches par livre
CREATE INDEX idx_book_copy_book_id ON book_copy(book_id);

-- Supprimez d'abord la contrainte si elle existe
ALTER TABLE book_copy DROP CONSTRAINT IF EXISTS book_copy_status_check;

-- Modifiez le type de la colonne
ALTER TABLE book_copy ALTER COLUMN status TYPE VARCHAR(20);

-- Recréez la contrainte si nécessaire
ALTER TABLE book_copy ADD CONSTRAINT book_copy_status_check
    CHECK (status IN ('AVAILABLE', 'RESERVED', 'UNAVAILABLE', 'SPECIAL'));
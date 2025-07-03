package itu.etu1792.library.book;

import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class BookService {
    private final BookRepository bookRepository;
    private final AuthorRepository authorRepository;
    private final EditorRepository editorRepository;
    private final CategoryRepository categoryRepository;
    private final LanguageRepository languageRepository;

    public List<Book> findAllBooks() {
        return bookRepository.findAll();
    }

    public Book findBookById(Long id) {
        return bookRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Book not found with id: " + id));
    }

    public Book saveBook(Book book) {
        return bookRepository.save(book);
    }

    public void deleteBook(Long id) {
        bookRepository.deleteById(id);
    }

    public List<Book> searchBooks(String keyword) {
        if (keyword == null || keyword.trim().isEmpty()) {
            return bookRepository.findAll();
        }
        return bookRepository.searchBooks(keyword);
    }

    // Méthodes pour gérer les relations
    public void addAuthorToBook(Long bookId, Long authorId) {
        Book book = findBookById(bookId);
        Author author = authorRepository.findById(authorId)
                .orElseThrow(() -> new EntityNotFoundException("Author not found"));
        book.getAuthors().add(author);
        bookRepository.save(book);
    }

    // Autres méthodes similaires pour les catégories, etc.
}

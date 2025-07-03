package itu.etu1792.library.book;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface BookRepository extends JpaRepository<Book, Long> {
    @Query("SELECT DISTINCT b FROM Book b " +
            "LEFT JOIN b.authors a " +
            "WHERE LOWER(b.title) LIKE LOWER(concat('%', :keyword, '%')) " +
            "OR LOWER(b.isbn) LIKE LOWER(concat('%', :keyword, '%')) " +
            "OR LOWER(a.firstName) LIKE LOWER(concat('%', :keyword, '%')) " +
            "OR LOWER(a.lastName) LIKE LOWER(concat('%', :keyword, '%'))")
    List<Book> searchBooks(@Param("keyword") String keyword);
}

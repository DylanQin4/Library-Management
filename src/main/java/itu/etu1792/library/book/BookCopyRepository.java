package itu.etu1792.library.book;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface BookCopyRepository extends JpaRepository<BookCopy, Long> {
    List<BookCopy> findByBookId(Long bookId);

    @Query("SELECT c FROM BookCopy c WHERE c.book.id = :bookId AND c.status = itu.etu1792.library.book.BookCopy.Status.AVAILABLE")
    List<BookCopy> findAvailableCopiesByBookId(@Param("bookId") Long bookId);

    boolean existsByBarcode(String barcode);
}
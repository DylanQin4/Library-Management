package itu.etu1792.library.book;

import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class BookCopyService {
    private final BookCopyRepository copyRepository;
    private final BookService bookService;

    public BookCopy createCopy(BookCopy copy) {
        if (copyRepository.existsByBarcode(copy.getBarcode())) {
            throw new IllegalArgumentException("Un exemplaire avec ce code-barres existe déjà");
        }
        return copyRepository.save(copy);
    }

    public List<BookCopy> getCopiesByBookId(Long bookId) {
        return copyRepository.findByBookId(bookId);
    }

    public List<BookCopy> getAvailableCopiesByBookId(Long bookId) {
        return copyRepository.findAvailableCopiesByBookId(bookId);
    }

    public BookCopy updateCopyStatus(Long copyId, BookCopy.Status status) {
        BookCopy copy = copyRepository.findById(copyId)
                .orElseThrow(() -> new EntityNotFoundException("Exemplaire non trouvé"));
        copy.setStatus(status);
        return copyRepository.save(copy);
    }

    public BookCopy updateCopyLocation(Long copyId, String room, String shelfLocation) {
        BookCopy copy = copyRepository.findById(copyId)
                .orElseThrow(() -> new EntityNotFoundException("Exemplaire non trouvé"));
        copy.setRoom(room);
        copy.setShelfLocation(shelfLocation);
        return copyRepository.save(copy);
    }

    public void deleteCopy(Long copyId) {
        copyRepository.deleteById(copyId);
    }
}
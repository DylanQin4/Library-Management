package itu.etu1792.library.members;

import itu.etu1792.library.book.Book;
import itu.etu1792.library.book.BookCopy;
import itu.etu1792.library.book.BookCopyRepository;
import itu.etu1792.library.book.BookRepository;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;

@Service
public class ReservationService {
    private final ReservationRepository reservationRepository;
    private final MemberRepository memberRepository;
    private final BookCopyRepository bookCopyRepository;

    public ReservationService(ReservationRepository reservationRepository,
                              MemberRepository memberRepository, BookCopyRepository bookCopyRepository) {
        this.reservationRepository = reservationRepository;
        this.memberRepository = memberRepository;
        this.bookCopyRepository = bookCopyRepository;
    }

    public Reservation createReservation(Long memberId, Long bookId) {
        Member member = memberRepository.findById(memberId)
                .orElseThrow(() -> new IllegalArgumentException("Membre non trouvé"));
        BookCopy bookCopy = bookCopyRepository.findById(bookId)
                .orElseThrow(() -> new IllegalArgumentException("Livre non trouvé"));

        Reservation reservation = new Reservation();
        reservation.setMember(member);
        reservation.setBookCopy(bookCopy);
        reservation.setReservationDate(LocalDate.now());
        reservation.setExpiryDate(LocalDate.now().plusDays(7)); // Par exemple, expire dans 7 jours
        reservation.setFulfilled(false);

        return reservationRepository.save(reservation);
    }

    public List<Reservation> getReservationsByMember(Long memberId) {
        return reservationRepository.findByMemberId(memberId);
    }

}

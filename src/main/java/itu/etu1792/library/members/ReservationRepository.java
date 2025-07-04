package itu.etu1792.library.members;

import org.springframework.data.jpa.repository.JpaRepository;

import java.time.LocalDate;
import java.util.List;

public interface ReservationRepository extends JpaRepository<Reservation, Long> {
    List<Reservation> findByMemberId(Long memberId);
    List<Reservation> findByBookId(Long bookId);
    List<Reservation> findByFulfilledFalseAndExpiryDateBefore(LocalDate date);
}
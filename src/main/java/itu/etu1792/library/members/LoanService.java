package itu.etu1792.library.members;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;

@Service
@AllArgsConstructor
public class LoanService {
    private final LoanRepository loanRepository;
    private final ReservationRepository reservationRepository;

    public List<Loan> getLoansByMember(Long memberId) {
        return loanRepository.findByMemberId(memberId);
    }

    public List<Loan> getOverdueLoans() {
        return loanRepository.findByReturnDateIsNullAndDueDateBefore(LocalDate.now());
    }

    public List<Reservation> getExpiredReservations() {
        return reservationRepository.findByFulfilledFalseAndExpiryDateBefore(LocalDate.now());
    }
}

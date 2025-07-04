package itu.etu1792.library.members;

import org.springframework.data.jpa.repository.JpaRepository;

import java.time.LocalDate;
import java.util.List;

public interface LoanRepository extends JpaRepository<Loan, Long> {
    List<Loan> findByMemberId(Long memberId);
    List<Loan> findByReturnDateIsNull();
    List<Loan> findByReturnDateIsNullAndDueDateBefore(LocalDate date);
}

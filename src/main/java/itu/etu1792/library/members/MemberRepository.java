package itu.etu1792.library.members;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.time.LocalDate;
import java.util.List;

public interface MemberRepository extends JpaRepository<Member, Long> {
    List<Member> findByActiveTrue();
    List<Member> findByActiveFalse();
    List<Member> findByType(Member.MemberType type);

    @Query("SELECT m FROM Member m WHERE m.membershipExpiry BETWEEN :start AND :end")
    List<Member> findExpiringMemberships(@Param("start") LocalDate start, @Param("end") LocalDate end);

    boolean existsByEmail(String email);
    boolean existsByPhone(String phone);
}
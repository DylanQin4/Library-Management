package itu.etu1792.library.members;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;

@Service
@RequiredArgsConstructor
public class MemberService {
    private final MemberRepository memberRepository;

    public Member registerMember(Member member) {
        if (memberRepository.existsByEmail(member.getEmail())) {
            throw new IllegalArgumentException("Un adhérent avec cet email existe déjà");
        }
        if (memberRepository.existsByPhone(member.getPhone())) {
            throw new IllegalArgumentException("Un adhérent avec ce téléphone existe déjà");
        }

        member.setRegistrationDate(LocalDate.now());
        // 1 an d'adhésion par défaut
        member.setMembershipExpiry(LocalDate.now().plusYears(1));
        return memberRepository.save(member);
    }

    public Member renewMembership(Long memberId, int years) {
        Member member = memberRepository.findById(memberId)
                .orElseThrow(() -> new IllegalArgumentException("Adhérent non trouvé"));
        member.setMembershipExpiry(member.getMembershipExpiry().plusYears(years));
        return memberRepository.save(member);
    }

    public List<Member> getAllActiveMembers() {
        return memberRepository.findByActiveTrue();
    }

    public Member getMemberById(Long id) {
        return memberRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Adhérent non trouvé"));
    }
}
package itu.etu1792.library.members;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@AllArgsConstructor
@RequestMapping("/reservations")
public class ReservationController {
    private final ReservationService reservationService;
    private final MemberService memberService;

    @GetMapping("/{id}/reservations")
    public String viewMemberReservations(@PathVariable Long id, Model model) {
        Member member = memberService.getMemberById(id);
        List<Reservation> reservations = reservationService.getReservationsByMember(id);

        model.addAttribute("member", member);
        model.addAttribute("reservations", reservations);
        return "members/reservations";
    }


    @PostMapping("/create")
    public String createReservation(@RequestParam Long memberId, @RequestParam Long bookCopyId, Model model) {
        try {
            reservationService.createReservation(memberId, bookCopyId);
            model.addAttribute("success", "Réservation effectuée avec succès !");
        } catch (IllegalArgumentException e) {
            model.addAttribute("error", e.getMessage());
        }

        return "redirect:/books"; // ou redirige vers la fiche du livre
    }
}

package itu.etu1792.library.members;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/members")
@RequiredArgsConstructor
public class MemberController {
    private final MemberService memberService;
    private final LoanService loanService;

    @GetMapping
    public String listMembers(Model model) {
        model.addAttribute("members", memberService.getAllActiveMembers());
        return "members/list";
    }

    @GetMapping("/create")
    public String showCreateForm(Model model) {
        model.addAttribute("member", new Member());
        model.addAttribute("memberTypes", Member.MemberType.values());
        return "members/create";
    }

    @PostMapping("/create")
    public String createMember(@ModelAttribute Member member) {
        memberService.registerMember(member);
        return "redirect:/members";
    }

    @GetMapping("/{id}/loans")
    public String viewMemberLoans(@PathVariable Long id, Model model) {
        model.addAttribute("member", memberService.getMemberById(id));
        model.addAttribute("loans", loanService.getLoansByMember(id));
        return "members/loans";
    }
}

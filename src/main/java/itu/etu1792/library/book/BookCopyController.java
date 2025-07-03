package itu.etu1792.library.book;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Controller
@RequestMapping("/books/{bookId}/copies")
@RequiredArgsConstructor
public class BookCopyController {
    private final BookCopyService copyService;
    private final BookService bookService;

    @GetMapping
    public String listCopies(@PathVariable Long bookId, Model model) {
        model.addAttribute("book", bookService.findBookById(bookId));
        model.addAttribute("copies", copyService.getCopiesByBookId(bookId));
        return "books/copies/list";
    }

    @GetMapping("/create")
    public String showCreateForm(@PathVariable Long bookId, Model model) {
        BookCopy copy = new BookCopy();
        copy.setBook(bookService.findBookById(bookId));
        model.addAttribute("copy", copy);
        return "books/copies/create";
    }

    @PostMapping("/create")
    public String createCopy(
            @PathVariable Long bookId,
            @ModelAttribute BookCopy copy,
            RedirectAttributes redirectAttributes) {
        copy.setBook(bookService.findBookById(bookId));
        copyService.createCopy(copy);
        redirectAttributes.addFlashAttribute("success", "Exemplaire ajouté avec succès");
        return "redirect:/books/" + bookId + "/copies";
    }

    @PostMapping("/{copyId}/update-status")
    public String updateStatus(
            @PathVariable Long bookId,
            @PathVariable Long copyId,
            @RequestParam BookCopy.Status status,
            RedirectAttributes redirectAttributes) {
        copyService.updateCopyStatus(copyId, status);
        redirectAttributes.addFlashAttribute("success", "Statut mis à jour");
        return "redirect:/books/" + bookId + "/copies";
    }

    @PostMapping("/{copyId}/update-location")
    public String updateLocation(
            @PathVariable Long bookId,
            @PathVariable Long copyId,
            @RequestParam String room,
            @RequestParam String shelfLocation,
            RedirectAttributes redirectAttributes) {
        copyService.updateCopyLocation(copyId, room, shelfLocation);
        redirectAttributes.addFlashAttribute("success", "Localisation mise à jour");
        return "redirect:/books/" + bookId + "/copies";
    }

    @GetMapping("/{copyId}/delete")
    public String deleteCopy(
            @PathVariable Long bookId,
            @PathVariable Long copyId,
            RedirectAttributes redirectAttributes) {
        copyService.deleteCopy(copyId);
        redirectAttributes.addFlashAttribute("success", "Exemplaire supprimé");
        return "redirect:/books/" + bookId + "/copies";
    }
}
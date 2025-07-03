package itu.etu1792.library.book;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.List;

@Controller
@RequestMapping("/books")
@RequiredArgsConstructor
public class BookController {
    private final BookService bookService;
    private final AuthorRepository authorRepository;
    private final EditorRepository editorRepository;
    private final CategoryRepository categoryRepository;
    private final LanguageRepository languageRepository;

    @GetMapping
    public String listBooks(
            @RequestParam(required = false) String search,
            Model model) {
        List<Book> books;
        if (search != null && !search.isEmpty()) {
            books = bookService.searchBooks(search);
            model.addAttribute("search", search);
        } else {
            books = bookService.findAllBooks();
        }
        model.addAttribute("books", books);
        return "books/list";
    }

    @GetMapping("/create")
    public String showCreateForm(Model model) {
        model.addAttribute("book", new Book());
        // Ajouter les listes pour les selects
        addSelectListsToModel(model);
        return "books/create";
    }

    @PostMapping("/create")
    public String createBook(@ModelAttribute Book book, RedirectAttributes redirectAttributes) {
        bookService.saveBook(book);
        redirectAttributes.addFlashAttribute("success", "Book created successfully");
        return "redirect:/books";
    }

    @GetMapping("/edit/{id}")
    public String showEditForm(@PathVariable Long id, Model model) {
        Book book = bookService.findBookById(id);
        model.addAttribute("book", book);
        addSelectListsToModel(model);
        return "books/edit";
    }

    @PostMapping("/edit/{id}")
    public String updateBook(@PathVariable Long id, @ModelAttribute Book book, RedirectAttributes redirectAttributes) {
        book.setId(id);
        bookService.saveBook(book);
        redirectAttributes.addFlashAttribute("success", "Book updated successfully");
        return "redirect:/books";
    }

    @GetMapping("/delete/{id}")
    public String deleteBook(@PathVariable Long id, RedirectAttributes redirectAttributes) {
        bookService.deleteBook(id);
        redirectAttributes.addFlashAttribute("success", "Book deleted successfully");
        return "redirect:/books";
    }

    private void addSelectListsToModel(Model model) {
        model.addAttribute("authors", authorRepository.findAll());
        model.addAttribute("editors", editorRepository.findAll());
        model.addAttribute("categories", categoryRepository.findAll());
        model.addAttribute("languages", languageRepository.findAll());
    }
}
